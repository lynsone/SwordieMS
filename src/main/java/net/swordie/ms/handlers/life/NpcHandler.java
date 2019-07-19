package net.swordie.ms.handlers.life;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.anticheat.Offense;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.trunk.*;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.InventoryOperation;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.NpcData;
import net.swordie.ms.loaders.QuestData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.QuestInfo;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.gach.GachaponConstants;
import net.swordie.ms.world.gach.result.GachaponDlgType;
import net.swordie.ms.world.gach.result.GachaponResult;
import net.swordie.ms.world.shop.NpcShopDlg;
import net.swordie.ms.world.shop.NpcShopItem;
import net.swordie.ms.world.shop.ShopRequestType;
import net.swordie.ms.world.shop.result.MsgShopResult;
import net.swordie.ms.world.shop.result.ShopResultType;
import org.apache.log4j.Logger;

public class NpcHandler {

    private static final Logger log = Logger.getLogger(NpcHandler.class);


    @Handler(op = InHeader.USER_SELECT_NPC)
    public static void handleUserSelectNpc(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int npcID = inPacket.decodeInt();
        Position playerPos = inPacket.decodePosition();
        Life life = chr.getField().getLifeByObjectID(npcID);
        if (!(life instanceof Npc)) {
            chr.chatMessage("Could not find that npc.");
            return;
        }
        Npc npc = (Npc) life;
        int templateID = npc.getTemplateId();
        if (npc.getTrunkGet() > 0 || npc.getTrunkPut() > 0) {
            chr.write(FieldPacket.trunkDlg(new TrunkOpen(templateID, chr.getAccount().getTrunk())));
            return;
        }
        String script = npc.getScripts().get(0);
        if (script == null) {
            NpcShopDlg nsd = NpcData.getShopById(templateID);
            if (nsd != null) {
                chr.getScriptManager().stop(ScriptType.Npc); // reset contents before opening shop?
                chr.setShop(nsd);
                chr.write(ShopDlg.openShop(0, nsd));
                chr.chatMessage(String.format("Opening shop %s", npc.getTemplateId()));
                return;
            } else {
                script = String.valueOf(npc.getTemplateId());
            }
        }
        chr.getScriptManager().startScript(npc.getTemplateId(), npcID, script, ScriptType.Npc);
    }

    @Handler(op = InHeader.USER_COMPLETE_NPC_SPEECH)
    public static void handleUserCompleteNpcSpeech(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        QuestManager qm = chr.getQuestManager();
        int questID = inPacket.decodeInt();
        int npcTemplateID = inPacket.decodeInt();
        int speech = inPacket.decodeByte();

        int objectID = inPacket.decodeInt();
        Life life = chr.getField().getLifeByObjectID(objectID);
        if (!(life instanceof Npc)) {
            chr.chatMessage("Could not find that npc.");
            return;
        }
        if (qm.hasQuestInProgress(questID)) {
            QuestInfo qi = QuestData.getQuestInfoById(questID);
            String scriptName = qi.getSpeech().get(speech - 1);
            if (scriptName == null || scriptName.equalsIgnoreCase("")) {
                chr.chatMessage("Could not find that speech - quest id " + questID + ", speech " + speech);
            }
            if (scriptName.contains("NpcSpeech=")) {
                if (scriptName.endsWith("/")) {
                    scriptName = scriptName.substring(0, scriptName.length() - 1);
                }
                Quest quest = chr.getQuestManager().getQuests().get(questID);
                if (quest != null) {
                    quest.setQrValue(scriptName);
                    chr.write(WvsContext.questRecordExMessage(quest));
                }
            } else {
                chr.getScriptManager().startScript(questID, scriptName, ScriptType.Quest);
            }
        }
    }

    @Handler(op = InHeader.USER_SHOP_REQUEST)
    public static void handleUserShopRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte type = inPacket.decodeByte();
        ShopRequestType shr = ShopRequestType.getByVal(type);
        if (shr == null) {
            log.error(String.format("Unhandled shop request type %d", type));
        }
        NpcShopDlg nsd = chr.getShop();
        if (nsd == null) {
            chr.chatMessage("You are currently not in a shop.");
            return;
        }
        switch (shr) {
            case BUY:
                short itemIndex = inPacket.decodeShort();
                int itemID = inPacket.decodeInt();
                short quantity = inPacket.decodeShort();
                NpcShopItem nsi = nsd.getItemByIndex(itemIndex);
                if (nsi == null || nsi.getItemID() != itemID) {
                    chr.chatMessage("The server's item at that position was different than the client's.");
                    log.warn(String.format("Possible hack: expected shop itemID %d, got %d (chr %d)", nsi.getItemID(), itemID, chr.getId()));
                    return;
                }
                if (quantity < 0) {
                    chr.getOffenseManager().addOffense(Offense.Type.Editing,
                            "User tried buying negative quantity from NPC shop");
                    chr.dispose();
                    return;
                }
                if (nsi.getMaxPerSlot() == 0 ? quantity != 1 : quantity > nsi.getMaxPerSlot()) {
                    chr.getOffenseManager().addOffense(Offense.Type.Editing,
                            String.format("Possible hack: max slot for shop itemID %d is %d, got %d",
                            nsi.getItemID(), nsi.getMaxPerSlot(), quantity));
                    chr.dispose();
                    return;
                }
                int itemQuantity = nsi.getQuantity() > 0 ? nsi.getQuantity() : 1;
                if (itemQuantity == 1 && quantity == 1 ? !chr.canHold(itemID) : !chr.canHold(itemID, itemQuantity * quantity)) {
                    chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.FullInvMsg)));
                    return;
                }
                int buyLimit = nsi.getBuyLimit();
                if (buyLimit > 0) {
                    int amountBought = chr.getItemBoughtAmounts().getOrDefault(nsi.getId(), 0);
                    int amountLeft = buyLimit - amountBought;
                    if (quantity > amountLeft) {
                        chr.chatMessage("Can't buy this item more than " + buyLimit + " times.");
                        return;
                    }
                }
                if (nsi.getTokenItemID() != 0) {
                    int cost = nsi.getTokenPrice() * quantity;
                    if (chr.hasItemCount(nsi.getTokenItemID(), cost)) {
                        chr.consumeItem(nsi.getTokenItemID(), cost);
                    } else {
                        chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.NotEnoughMesosMsg)));
                        return;
                    }
                } else {
                    long cost = nsi.getPrice() * quantity;
                    if (chr.getMoney() < cost) {
                        chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.NotEnoughMesosMsg)));
                        return;
                    }
                    chr.deductMoney(cost);
                }
                if(buyLimit>0) {
                    int amountBought = chr.getItemBoughtAmounts().getOrDefault(nsi.getId(), 0);
                    amountBought += quantity;
                    chr.addItemBoughtAmount(nsi.getId(), amountBought);
                }
                Item item = ItemData.getItemDeepCopy(itemID);
                item.setQuantity(quantity * itemQuantity);
                chr.addItemToInventory(item);
                chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.Success)));
                break;
            case RECHARGE:
                short slot = inPacket.decodeShort();
                item = chr.getConsumeInventory().getItemBySlot(slot);
                if (item == null || !ItemConstants.isRechargable(item.getItemId())) {
                    chr.chatMessage(String.format("Was not able to find a rechargable item at position %d.", slot));
                    return;
                }
                ItemInfo ii = ItemData.getItemInfoByID(item.getItemId());
                long cost = ii.getSlotMax() - item.getQuantity();
                if (chr.getMoney() < cost) {
                    chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.NotEnoughMesosMsg)));
                    return;
                }
                chr.deductMoney(cost);
                item.addQuantity(ii.getSlotMax());
                chr.write(WvsContext.inventoryOperation(true, false,
                        InventoryOperation.UpdateQuantity, slot, (short) 0, 0, item));
                chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.Success)));
                break;
            case SELL:
                slot = inPacket.decodeShort();
                itemID = inPacket.decodeInt();
                quantity = inPacket.decodeShort();
                InvType it = ItemConstants.getInvTypeByItemID(itemID);
                item = chr.getInventoryByType(it).getItemBySlot(slot);
                if (item == null || item.getItemId() != itemID) {
                    chr.chatMessage("Could not find that item.");
                    return;
                }
                if (quantity < 0) {
                    chr.getOffenseManager().addOffense(Offense.Type.Editing,
                            "User tried selling negative quantity to NPC shop");
                    chr.dispose();
                    return;
                }
                if (!chr.hasItemCount(itemID, quantity)) {
                    chr.getOffenseManager().addOffense(Offense.Type.Editing,
                            String.format("Possible hack: User tried to sell %d amount of item %d while owning less",
                            quantity, itemID));
                    chr.dispose();
                    return;
                }
                if (ItemConstants.isEquip(itemID)) {
                    cost = ((Equip) item).getPrice();
                } else {
                    cost = ItemData.getItemInfoByID(itemID).getPrice() * quantity;
                }
                chr.consumeItem(itemID, quantity);
                chr.addMoney(cost);
                chr.write(ShopDlg.shopResult(new MsgShopResult(ShopResultType.Success)));
                break;
            case CLOSE:
                chr.setShop(null);
                break;
            default:
                log.error(String.format("Unhandled shop request type %s", shr));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_TRUNK_REQUEST)
    public static void handleUserTrunkRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Trunk trunk = chr.getAccount().getTrunk();
        byte req = inPacket.decodeByte();
        TrunkType trunkType = TrunkType.getByVal(req);
        if (trunkType == null) {
            log.error(String.format("Unknown trunk request type %d.", req));
            return;
        }
        switch (trunkType) {
            case TrunkReq_Money:
                long reqMoney = inPacket.decodeLong();
                boolean put = reqMoney < 0;
                long curMoney = chr.getMoney();
                if (put) {
                    reqMoney = -reqMoney;
                    if (reqMoney > curMoney && trunk.canAddMoney(reqMoney)) {
                        chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_PutNoMoney)));
                        return;
                    }
                    trunk.addMoney(reqMoney);
                    chr.deductMoney(reqMoney);
                    chr.write(FieldPacket.trunkDlg(new TrunkUpdate(TrunkType.TrunkRes_MoneySuccess, trunk)));
                } else {
                    if (reqMoney <= trunk.getMoney() && chr.canAddMoney(reqMoney)) {
                        trunk.addMoney(-reqMoney);
                        chr.addMoney(reqMoney);
                        chr.write(FieldPacket.trunkDlg(new TrunkUpdate(TrunkType.TrunkRes_MoneySuccess, trunk)));
                    } else {
                        chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_GetNoMoney)));
                    }
                }
                break;
            case TrunkReq_GetItem:
                byte idk = inPacket.decodeByte();
                short pos = inPacket.decodeByte();
                if (pos >= 0 && pos < trunk.getItems().size()) {
                    Item getItem = trunk.getItems().get(pos);
                    if (chr.getInventoryByType(getItem.getInvType()).canPickUp(getItem)) {
                        trunk.removeItem(getItem);
                        chr.addItemToInventory(getItem);
                        chr.write(FieldPacket.trunkDlg(new TrunkUpdate(TrunkType.TrunkRes_GetSuccess, trunk)));
                    } else {
                        chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_GetUnknown)));
                    }
                } else {
                    chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_GetUnknown)));
                }
                break;
            case TrunkReq_PutItem:
                short slot = inPacket.decodeShort();
                int itemID = inPacket.decodeInt();
                short quantity = inPacket.decodeShort();
                InvType invType = ItemConstants.getInvTypeByItemID(itemID);
                Item item = chr.getInventoryByType(invType).getItemBySlot(slot);
                if (item != null && quantity > 0 && item.getQuantity() >= quantity && item.getItemId() == itemID) {
                    if (trunk.getItems().size() >= trunk.getSlotCount()) {
                        chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_PutNoSpace)));
                        return;
                    }
                    chr.consumeItem(itemID, quantity);
                    trunk.addItem(item, quantity);
                    chr.write(FieldPacket.trunkDlg(new TrunkUpdate(TrunkType.TrunkRes_PutSuccess, trunk)));
                } else {
                    chr.write(FieldPacket.trunkDlg(new TrunkMsg(TrunkType.TrunkRes_PutUnknown)));
                }
                break;
            case TrunkReq_SortItem:
//                trunk.getItems().sort(Comparator.comparingInt(Item::getItemId));
                chr.write(FieldPacket.trunkDlg(new TrunkUpdate(TrunkType.TrunkRes_SortItem, trunk)));
                break;
            case TrunkReq_CloseDialog:
                chr.dispose();
                break;
            default:
                log.error(String.format("Unhandled trunk request type %s.", trunkType));
        }
    }

    @Handler(op = InHeader.GACHAPON_REQUEST)
    public static void handleGachaponRequest(Char chr, InPacket inPacket) {
        // TODO: Handle error messages with popup dialog like GMS.
        // TODO: Add rewards to gachapon.
        final int type = inPacket.decodeByte();
        final GachaponResult result = GachaponResult.getByVal(type);

        if (result == null) {
            log.error("[Gachapon] Found unknown gachapon result " + type);
            chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
            return;
        }
        if (chr == null) {
            chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
            return;
        }
        switch (result) {
            case SUCCESS:
                final int ticketID = inPacket.decodeInt();
                GachaponDlgType dialog = GachaponConstants.getDlgByTicket(ticketID);
                if (dialog == null || !chr.hasItem(ticketID)) {
                    chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
                    return;
                }
                final int reward = GachaponConstants.getRandomItem(dialog);
                if (reward == -1) {
                    chr.chatMessage(ChatType.Mob, "Cannot find reward ID");
                    chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
                    return;
                }
                if (!chr.canHold(reward)) {
                    chr.chatMessage(ChatType.Mob, "Cannot hold reward ID " + reward);
                    chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
                    return;
                }
                Equip equip = ItemData.getEquipDeepCopyFromID(reward, true);
                if (equip == null) {
                    Item item = ItemData.getItemDeepCopy(reward, true);
                    if (item == null) {
                        chr.write(GachaponDlg.gachResult(GachaponResult.ERROR));
                        chr.chatMessage(ChatType.Mob, "Item is null" + reward);
                        return;
                    }
                    item.setQuantity(1);
                    chr.addItemToInventory(item);
                    chr.write(GachaponDlg.gachResult(GachaponResult.SUCCESS, item, (short) 1));
                    chr.getGachaponManager().addItem(dialog, item, (short) 1);
                } else {
                    chr.addItemToInventory(InvType.EQUIP, equip, false);
                    chr.write(GachaponDlg.gachResult(GachaponResult.SUCCESS, equip, (short) 1));
                    chr.getGachaponManager().addItem(dialog, equip, (short) 1);
                }
                chr.consumeItem(chr.getCashInventory().getItemByItemID(ticketID));
                chr.getField().broadcastPacket(UserPacket.setGachaponEffect(chr));
                break;
            case EXIT:
                chr.write(GachaponDlg.gachResult(GachaponResult.EXIT));
                break;
        }
    }

    @Handler(op = InHeader.EVOLVING_REQUEST)
    public static void handleEvolvingRequest(Client c, InPacket inpacket) {
        byte unk = inpacket.decodeByte();
        int unk2 = inpacket.decodeInt();
        int timestamp = inpacket.decodeInt();
        byte unk3 = inpacket.decodeByte();
        Char chr = c.getChr();
        if (chr.getField().isTown()) {
            chr.warp(957019000);
        }
    }

    @Handler(op = InHeader.NPC_MOVE)
    public static void handleNpcMove(Char chr, InPacket inPacket) {
        int objectID = inPacket.decodeInt();
        byte oneTimeAction = inPacket.decodeByte();
        byte chatIdx = inPacket.decodeByte();
        int duration = inPacket.decodeInt();
        Life life = chr.getField().getLifeByObjectID(objectID);
        if (life instanceof Npc && ((Npc) life).isMove()) {
            Npc npc = (Npc) chr.getField().getLifeByObjectID(objectID);
            boolean move = npc.isMove();
            MovementInfo movementInfo = new MovementInfo(npc.getPosition(), npc.getVPosition());
            byte keyPadState = 0;
            if (move) {
                movementInfo.decode(inPacket);
                for (Movement m : movementInfo.getMovements()) {
                    Position pos = m.getPosition();
                    Position vPos = m.getVPosition();
                    if (pos != null) {
                        npc.setPosition(pos);
                    }
                    if (vPos != null) {
                        npc.setvPosition(vPos);
                    }
                    npc.setMoveAction(m.getMoveAction());
                    npc.setFh(m.getFh());
                }
                if (inPacket.getUnreadAmount() > 0) {
                    keyPadState = inPacket.decodeByte(); // not always encoded?
                }
            }
            chr.getField().broadcastPacket(NpcPool.npcMove(objectID, oneTimeAction, chatIdx, duration, move,
                    movementInfo, keyPadState));
        }
    }
}
