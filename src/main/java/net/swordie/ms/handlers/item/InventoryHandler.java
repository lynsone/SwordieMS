package net.swordie.ms.handlers.item;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.AndroidPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.FieldOption;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.InventoryOperation;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static net.swordie.ms.enums.InvType.EQUIP;
import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.InventoryOperation.*;
import static net.swordie.ms.enums.InventoryOperation.Move;

public class InventoryHandler {

    private static final Logger log = Logger.getLogger(InventoryHandler.class);


    @Handler(op = InHeader.USER_CHANGE_SLOT_POSITION_REQUEST)
    public static void handleUserChangeSlotPositionRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // update tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        short oldPos = inPacket.decodeShort();
        short newPos = inPacket.decodeShort();
        short quantity = inPacket.decodeShort();
//        log.debug("Equipped old: " + chr.getEquippedInventory());
//        log.debug("Equip old: " + chr.getEquipInventory());
        InvType invTypeFrom = invType == EQUIP ? oldPos < 0 ? EQUIPPED : EQUIP : invType;
        InvType invTypeTo = invType == EQUIP ? newPos < 0 ? EQUIPPED : EQUIP : invType;
        Item item = chr.getInventoryByType(invTypeFrom).getItemBySlot(oldPos);
        if (item == null) {
            chr.dispose();
            return;
        }
        String itemBefore = item.toString();
        if (newPos == 0) { // Drop
            Field field = chr.getField();
            if ((field.getFieldLimit() & FieldOption.DropLimit.getVal()) > 0) {
                chr.dispose();
                return;
            }
            boolean fullDrop;
            Drop drop;
            if (!item.getInvType().isStackable() || quantity >= item.getQuantity() ||
                    ItemConstants.isThrowingStar(item.getItemId()) || ItemConstants.isBullet(item.getItemId())) {
                // Whole item is dropped (equip/stackable items with all their quantity)
                fullDrop = true;
                chr.getInventoryByType(invTypeFrom).removeItem(item);
                item.drop();
                drop = new Drop(-1, item);
            } else {
                // Part of the stack is dropped
                fullDrop = false;
                Item dropItem = ItemData.getItemDeepCopy(item.getItemId());
                dropItem.setQuantity(quantity);
                item.removeQuantity(quantity);
                drop = new Drop(-1, dropItem);
            }
            int x = chr.getPosition().getX();
            int y = chr.getPosition().getY();
            Foothold fh = chr.getField().findFootHoldBelow(new Position(x, y - GameConstants.DROP_HEIGHT));
            chr.getField().drop(drop, chr.getPosition(), new Position(x, fh.getYFromX(x)));
            drop.setCanBePickedUpByPet(false);
            if (fullDrop) {
                c.write(WvsContext.inventoryOperation(true, false, Remove,
                        oldPos, newPos, 0, item));
            } else {
                c.write(WvsContext.inventoryOperation(true, false, UpdateQuantity,
                        oldPos, newPos, 0, item));
            }
        } else {
            Item swapItem = chr.getInventoryByType(invTypeTo).getItemBySlot(newPos);
            if (swapItem != null) {
//                log.debug("SwapItem before: " + swapItem);
            }
            item.setBagIndex(newPos);
            int beforeSizeOn = chr.getEquippedInventory().getItems().size();
            int beforeSize = chr.getEquipInventory().getItems().size();
            if (invType == EQUIP && invTypeFrom != invTypeTo) {
                // TODO: verify job (see item.RequiredJob), level, stat, unique equip requirements
                // before we allow the player to equip this
                if (invTypeFrom == EQUIPPED) {
                    chr.unequip(item);
                } else {
                    chr.equip(item);
                    if (swapItem != null) {
                        chr.unequip(swapItem);
                    }
                }
            }
            if (swapItem != null) {
                swapItem.setBagIndex(oldPos);
//                log.debug("SwapItem after: " + swapItem);
            }
            int afterSizeOn = chr.getEquippedInventory().getItems().size();
            int afterSize = chr.getEquipInventory().getItems().size();
            if (afterSize + afterSizeOn != beforeSize + beforeSizeOn) {
                throw new RuntimeException("Data duplication!");
            }
            c.write(WvsContext.inventoryOperation(true, false, Move, oldPos, newPos,
                    0, item));
            item.updateToChar(chr);
//            log.debug("Item before: " + itemBefore);
//            log.debug("Item before: " + item);
        }
//        log.debug("Equipped after: " + chr.getEquippedInventory());
//        log.debug("Equip after: " + chr.getEquipInventory());
        chr.setBulletIDForAttack(chr.calculateBulletIDForAttack());
        if (newPos < 0
                && -newPos >= BodyPart.APBase.getVal() && -newPos < BodyPart.APEnd.getVal()
                && chr.getAndroid() != null) {
            // update android look
            chr.getField().broadcastPacket(AndroidPacket.modified(chr.getAndroid()));
        }
    }

    @Handler(op = InHeader.USER_GATHER_ITEM_REQUEST)
    public static void handleUserGatherItemRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        Char chr = c.getChr();
        Inventory inv = chr.getInventoryByType(invType);
        List<Item> items = new ArrayList<>(inv.getItems());
        items.sort(Comparator.comparingInt(Item::getBagIndex));
        for (Item item : items) {
            int firstSlot = inv.getFirstOpenSlot();
            if (firstSlot < item.getBagIndex()) {
                short oldPos = (short) item.getBagIndex();
                item.setBagIndex(firstSlot);
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Move,
                        oldPos, (short) item.getBagIndex(), 0, item));
            }

        }
        c.write(WvsContext.gatherItemResult(invType.getVal()));
        chr.dispose();
    }

    @Handler(op = InHeader.USER_SORT_ITEM_REQUEST)
    public static void handleUserSortItemRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        Char chr = c.getChr();
        Inventory inv = chr.getInventoryByType(invType);
        List<Item> items = new ArrayList<>(inv.getItems());
        items.sort(Comparator.comparingInt(Item::getItemId));
        for (Item item : items) {
            if (item.getBagIndex() != items.indexOf(item) + 1) {
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Remove,
                        (short) item.getBagIndex(), (short) 0, -1, item));
            }
        }
        for (Item item : items) {
            int index = items.indexOf(item) + 1;
            if (item.getBagIndex() != index) {
                item.setBagIndex(index);
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Add,
                        (short) item.getBagIndex(), (short) 0, -1, item));
            }
        }
        c.write(WvsContext.sortItemResult(invType.getVal()));
        chr.dispose();
    }
}
