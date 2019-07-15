package net.swordie.ms.handlers.item;

import net.swordie.ms.Server;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.pet.PetSkill;
import net.swordie.ms.loaders.FieldData;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.FieldInstanceType;
import net.swordie.ms.world.field.Portal;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.*;

import static net.swordie.ms.enums.ChatType.*;
import static net.swordie.ms.enums.EquipBaseStat.tuc;
import static net.swordie.ms.enums.InvType.*;
import static net.swordie.ms.enums.InventoryOperation.Move;

public class ItemHandler {

    private static final Logger log = Logger.getLogger(ItemHandler.class);

    @Handler(op = InHeader.USER_PORTAL_SCROLL_USE_REQUEST)
    public static void handleUserPortalScrollUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.PortalScrollLimit.getVal()) > 0 || !field.isChannelField()) {
            chr.chatMessage("You may not use a return scroll in this map.");
            chr.dispose();
            return;
        }
        inPacket.decodeInt(); //tick
        short slot = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        ItemInfo ii = ItemData.getItemInfoByID(itemID);
        Field toField;

        if (itemID != 2030000) {
            toField = chr.getOrCreateFieldByCurrentInstanceType(ii.getMoveTo());
        } else {
            toField = chr.getOrCreateFieldByCurrentInstanceType(field.getReturnMap());
        }
        Portal portal = toField.getDefaultPortal();
        chr.warp(toField, portal);
        chr.consumeItem(itemID, 1);
    }


    @Handler(op = InHeader.USER_STAT_CHANGE_ITEM_CANCEL_REQUEST)
    public static void handleUserStatChangeItemCancelRequest(Char chr, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int itemID = inPacket.decodeInt();
        tsm.removeStatsBySkill(itemID);
        tsm.sendResetStatPacket();
    }


    @Handler(op = InHeader.USER_CONSUME_CASH_ITEM_USE_REQUEST)
    public static void handleUserConsumeCashItemUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Inventory cashInv = chr.getInventoryByType(InvType.CASH);
        inPacket.decodeInt(); // tick
        short pos = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        Item item = cashInv.getItemBySlot(pos);
        ItemInfo itemInfo = ItemData.getItemInfoByID(itemID);
        if (item == null || item.getItemId() != itemID) {
            return;
        }
        if (itemID / 10000 == 553) {
            // Reward items
            Item reward = itemInfo.getRandomReward();
            chr.addItemToInventory(reward);

        } else if (itemID / 10000 == 539) {
            // Avatar Megaphones
            List<String> lineList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                String line = inPacket.decodeString();
                lineList.add(line);
            }
            boolean whisperIcon = inPacket.decodeByte() != 0;
            World world = c.getWorld();
            world.broadcastPacket(WvsContext.setAvatarMegaphone(chr, itemID, lineList, whisperIcon));

        } else if (itemID / 10000 == 519) {
            // Pet Skill Items
            long sn = inPacket.decodeLong();
            PetSkill ps = ItemConstants.getPetSkillFromID(itemID);
            if (ps == null) {
                chr.chatMessage(String.format("Unhandled pet skill item %d", itemID));
                return;
            }
            Item pi = chr.getCashInventory().getItemBySN(sn);
            if (!(pi instanceof PetItem)) {
                chr.chatMessage("Could not find that pet.");
                return;
            }
            boolean add = itemID >= 5190014; // add property doesn't include the "Slimming Medicine"
            PetItem petItem = (PetItem) pi;
            if (add) {
                petItem.addPetSkill(ps);
            } else {
                petItem.removePetSkill(ps);
            }
            petItem.updateToChar(chr);
            chr.consumeItem(item);
        } else {

            Equip medal = (Equip) chr.getEquippedInventory().getFirstItemByBodyPart(BodyPart.Medal);
            int medalInt = 0;
            if (medal != null) {
                medalInt = (medal.getAnvilId() == 0 ? medal.getItemId() : medal.getAnvilId()); // Check for Anvilled medal
            }
            String medalString = (medalInt == 0 ? "" : String.format("<%s> ", StringData.getItemStringById(medalInt)));

            switch (itemID) {
                case ItemConstants.HYPER_TELEPORT_ROCK: // Hyper Teleport Rock
                    short type = inPacket.decodeShort();
                    if (type == 1) {
                        int fieldId = inPacket.decodeInt();
                        Field field = chr.getOrCreateFieldByCurrentInstanceType(fieldId);
                        if (field == null || (field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0 ||
                                !FieldData.getWorldMapFields().contains(fieldId)) {
                            chr.chatMessage("You may not warp to that map.");
                            chr.dispose();
                            return;
                        }
                        chr.warp(field);
                    } else {
                        String targetName = inPacket.decodeString();
                        int worldID = chr.getClient().getChannelInstance().getWorldId();
                        World world = Server.getInstance().getWorldById(worldID);
                        Char targetChr = world.getCharByName(targetName);

                        // Target doesn't exist
                        if (targetChr == null) {
                            chr.chatMessage(String.format("%s is not online.", targetName));
                            chr.dispose();
                            return;
                        }

                        Position targetPosition = targetChr.getPosition();

                        Field targetField = targetChr.getField();
                        if (targetField == null || (targetField.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
                            chr.chatMessage("You may not warp to that map.");
                            chr.dispose();
                            return;
                        }
                        // Target is in an instanced Map
                        if (targetChr.getFieldInstanceType() != FieldInstanceType.CHANNEL) {
                            chr.chatMessage(String.format("cannot find %s", targetName));
                            // Change channels & warp & teleport
                        } else if (targetChr.getClient().getChannel() != c.getChannel()) {
                            int fieldId = targetChr.getFieldID();
                            chr.changeChannelAndWarp(targetChr.getClient().getChannel(), fieldId);
                            // warp & teleport
                        } else if (targetChr.getFieldID() != chr.getFieldID()) {
                            chr.warp(targetField);
                            chr.write(FieldPacket.teleport(targetPosition, chr));
                            // teleport
                        } else {
                            chr.write(FieldPacket.teleport(targetPosition, chr));
                        }
                    }
                    break;

                case ItemConstants.RED_CUBE: // Red Cube
                case ItemConstants.BLACK_CUBE: // Black cube
                    short ePos = (short) inPacket.decodeInt();
                    InvType invType = ePos < 0 ? EQUIPPED : EQUIP;
                    Equip equip = (Equip) chr.getInventoryByType(invType).getItemBySlot(ePos);
                    if (equip == null) {
                        chr.chatMessage(SystemNotice, "Could not find equip.");
                        chr.dispose();
                        return;
                    } else if (equip.getBaseGrade() < ItemGrade.Rare.getVal()) {
                        String msg = String.format("Character %d tried to use cube (id %d) an equip without a potential (id %d)", chr.getId(), itemID, equip.getItemId());
                        chr.getOffenseManager().addOffense(msg);
                        chr.dispose();
                        return;
                    }
                    Equip oldEquip = equip.deepCopy();
                    int tierUpChance = ItemConstants.getTierUpChance(itemID);
                    short hiddenValue = ItemGrade.getHiddenGradeByVal(equip.getBaseGrade()).getVal();
                    boolean tierUp = !(hiddenValue >= ItemGrade.HiddenLegendary.getVal()) && Util.succeedProp(tierUpChance);
                    if (tierUp) {
                        hiddenValue++;
                    }
                    equip.setHiddenOptionBase(hiddenValue, ItemConstants.THIRD_LINE_CHANCE);
                    equip.releaseOptions(false);
                    if (itemID == ItemConstants.RED_CUBE) {
                        c.write(FieldPacket.redCubeResult(chr.getId(), tierUp, itemID, ePos, equip));
                        c.write(FieldPacket.showItemReleaseEffect(chr.getId(), ePos, false));
                    } else {
                        if (chr.getMemorialCubeInfo() == null) {
                            chr.setMemorialCubeInfo(new MemorialCubeInfo(equip, oldEquip, itemID));
                        }
                        chr.getField().broadcastPacket(UserPacket.showItemMemorialEffect(chr.getId(), true, itemID));
                        c.write(WvsContext.blackCubeResult(equip, chr.getMemorialCubeInfo()));
                    }
                    equip.updateToChar(chr);
                    break;
                case ItemConstants.BONUS_POT_CUBE: // Bonus Potential Cube
                case ItemConstants.SPECIAL_BONUS_POT_CUBE: // [Special] Bonus Potential Cube
                case ItemConstants.WHITE_BONUS_POT_CUBE: // White Bonus Potential Cube
                    if (c.getWorld().isReboot()) {
                        chr.getOffenseManager().addOffense(String.format("Character %d attempted to use a bonus potential cube in reboot world.", chr.getId()));
                        chr.dispose();
                        return;
                    }
                    ePos = (short) inPacket.decodeInt();
                    invType = ePos < 0 ? EQUIPPED : EQUIP;
                    equip = (Equip) chr.getInventoryByType(invType).getItemBySlot(ePos);
                    if (equip == null) {
                        chr.chatMessage(SystemNotice, "Could not find equip.");
                        return;
                    } else if (equip.getBonusGrade() < ItemGrade.Rare.getVal()) {
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to use cube (id %d) an equip without a potential (id %d)", chr.getId(), itemID, equip.getItemId()));
                        chr.dispose();
                        return;
                    }
                    oldEquip = equip.deepCopy();
                    tierUpChance = ItemConstants.getTierUpChance(itemID);
                    hiddenValue = ItemGrade.getHiddenGradeByVal(equip.getBonusGrade()).getVal();
                    tierUp = !(hiddenValue >= ItemGrade.HiddenLegendary.getVal()) && Util.succeedProp(tierUpChance);
                    if (tierUp) {
                        hiddenValue++;
                    }
                    equip.setHiddenOptionBonus(hiddenValue, ItemConstants.THIRD_LINE_CHANCE);
                    equip.releaseOptions(true);
                    if (itemID != ItemConstants.WHITE_BONUS_POT_CUBE) {
                        c.write(FieldPacket.inGameCubeResult(chr.getId(), tierUp, itemID, ePos, equip));
                        c.write(FieldPacket.showItemReleaseEffect(chr.getId(), ePos, true));
                    } else {
                        if (chr.getMemorialCubeInfo() == null) {
                            chr.setMemorialCubeInfo(new MemorialCubeInfo(equip, oldEquip, itemID));
                        }
                        chr.getField().broadcastPacket(UserPacket.showItemMemorialEffect(chr.getId(), true, itemID));
                        c.write(WvsContext.whiteCubeResult(equip, chr.getMemorialCubeInfo()));
                    }
                    equip.updateToChar(chr);
                    break;
                case 5750001: // Nebulite Diffuser
                    ePos = inPacket.decodeShort();
                    equip = (Equip) chr.getEquipInventory().getItemBySlot(ePos);
                    if (equip == null || equip.getSocket(0) == 0 || equip.getSocket(0) == ItemConstants.EMPTY_SOCKET_ID) {
                        chr.chatMessage("That item currently does not have an active socket.");
                        chr.dispose();
                        return;
                    }
                    equip.setSocket(0, ItemConstants.EMPTY_SOCKET_ID);
                    equip.updateToChar(chr);
                    break;
                case 5072000: // Super Megaphone
                    String text = inPacket.decodeString();
                    boolean whisperIcon = inPacket.decodeByte() != 0;
                    World world = chr.getClient().getWorld();
                    BroadcastMsg smega = BroadcastMsg.megaphone(String.format("%s%s : %s", medalString, chr.getName(), text), (byte) chr.getClient().getChannelInstance().getChannelId(), whisperIcon);
                    world.broadcastPacket(WvsContext.broadcastMsg(smega));
                    break;
                case 5076000: // Item Megaphone
                    text = inPacket.decodeString();
                    whisperIcon = inPacket.decodeByte() != 0;
                    boolean eqpSelected = inPacket.decodeByte() != 0;
                    invType = EQUIP;
                    int itemPosition = 0;
                    if (eqpSelected) {
                        invType = InvType.getInvTypeByVal(inPacket.decodeInt());
                        itemPosition = inPacket.decodeInt();
                        if (invType == EQUIP && itemPosition < 0) {
                            invType = EQUIPPED;
                        }
                    }
                    Item broadcastedItem = chr.getInventoryByType(invType).getItemBySlot((short) itemPosition);

                    world = chr.getClient().getWorld();
                    smega = BroadcastMsg.itemMegaphone(String.format("%s%s : %s", medalString, chr.getName(), text), (byte) chr.getClient().getChannelInstance().getChannelId(), whisperIcon, eqpSelected, broadcastedItem);
                    world.broadcastPacket(WvsContext.broadcastMsg(smega));
                    break;
                case 5077000: // Triple Megaphone
                    byte stringListSize = inPacket.decodeByte();
                    List<String> stringList = new ArrayList<>();
                    for (int i = 0; i < stringListSize; i++) {
                        stringList.add(String.format("%s%s : %s", medalString, chr.getName(), inPacket.decodeString()));
                    }
                    whisperIcon = inPacket.decodeByte() != 0;

                    world = chr.getClient().getWorld();
                    smega = BroadcastMsg.tripleMegaphone(stringList, (byte) chr.getClient().getChannelInstance().getChannelId(), whisperIcon);
                    world.broadcastPacket(WvsContext.broadcastMsg(smega));
                    break;
                case 5062405: // Fusion anvil
                    int appearancePos = inPacket.decodeInt();
                    int functionPos = inPacket.decodeInt();
                    Inventory inv = chr.getEquipInventory();
                    Equip appearance = (Equip) inv.getItemBySlot((short) appearancePos);
                    Equip function = (Equip) inv.getItemBySlot((short) functionPos);
                    if (appearance != null && function != null && appearance.getItemId() / 10000 == function.getItemId() / 10000) {
                        function.getOptions().set(6, appearance.getItemId());
                    }
                    function.updateToChar(chr);
                    break;
                default:
                    chr.chatMessage(Mob, String.format("Cash item %d is not implemented, notify Sjonnie pls.", itemID));
                    return;
            }
        }
        if (itemID != 5040004) {
            chr.consumeItem(item);
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_STAT_CHANGE_ITEM_USE_REQUEST)
    public static void handleUserStatChangeItemUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.StatChangeItemConsumeLimit.getVal()) > 0) {
            chr.dispose();
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        inPacket.decodeInt(); // tick
        short slot = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        Item item = chr.getConsumeInventory().getItemBySlot(slot);
        if (item == null || item.getItemId() != itemID) {
            return;
        }
        chr.useStatChangeItem(item, true);
    }


    @Handler(op = InHeader.USER_SCRIPT_ITEM_USE_REQUEST)
    public static void handleUserScriptItemUseRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        short slot = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        int quant = inPacket.decodeInt();
        Char chr = c.getChr();
        Item item = chr.getConsumeInventory().getItemBySlot(slot);
        if (item == null || item.getItemId() != itemID) {
            item = chr.getCashInventory().getItemBySlot(slot);
        }
        if (item == null || item.getItemId() != itemID) {
            chr.dispose();
            return;
        }
        String script = String.valueOf(itemID);
        ItemInfo ii = ItemData.getItemInfoByID(itemID);
        if (ii.getScript() != null && !"".equals(ii.getScript())) {
            script = ii.getScript();
        }
        chr.getScriptManager().startScript(itemID, script, ScriptType.Item);
        chr.dispose();
    }


    @Handler(op = InHeader.USER_EQUIPMENT_ENCHANT_WITH_SINGLE_UI_REQUEST)
    public static void handleUserEquipmentEnchantWithSingleUIRequest(Client c, InPacket inPacket) {
        byte equipmentEnchantType = inPacket.decodeByte();

        Char chr = c.getChr();
        EquipmentEnchantType eeType = EquipmentEnchantType.getByVal(equipmentEnchantType);

        if (eeType == null) {
            log.error(String.format("Unknown enchant UI request %d", equipmentEnchantType));
            chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
            return;
        }

        switch (eeType) {
            case ScrollUpgradeRequest:
                inPacket.decodeInt();// tick
                short pos = inPacket.decodeShort();
                int scrollID = inPacket.decodeInt();
                Inventory inv = pos < 0 ? chr.getEquippedInventory() : chr.getEquipInventory();
                pos = (short) Math.abs(pos);
                Equip equip = (Equip) inv.getItemBySlot(pos);
                Equip prevEquip = equip.deepCopy();
                if (equip == null || equip.getBaseStat(tuc) <= 0 || equip.hasSpecialAttribute(EquipSpecialAttribute.Vestige)) {
                    chr.getOffenseManager().addOffense(String.format("Character %d tried to enchant a non-scrollable equip (pos %d, itemid %d).",
                            chr.getId(), pos, equip == null ? 0 : equip.getItemId()));
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                List<ScrollUpgradeInfo> suis = ItemConstants.getScrollUpgradeInfosByEquip(equip);
                if (scrollID < 0 || scrollID >= suis.size()) {
                    chr.getOffenseManager().addOffense(String.format("Characer %d tried to spell trace scroll with an invalid scoll ID (%d, "
                            + "itemID %d).", chr.getId(), scrollID, equip.getItemId()));
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                ScrollUpgradeInfo sui = suis.get(scrollID);
                chr.consumeItem(ItemConstants.SPELL_TRACE_ID, sui.getCost());
                boolean success = sui.applyTo(equip);
                equip.recalcEnchantmentStats();
                String desc = success ? "Your item has been upgraded." : "Your upgrade has failed.";
                chr.write(FieldPacket.showScrollUpgradeResult(false, success ? 1 : 0, desc, prevEquip, equip));
                equip.updateToChar(chr);
                if (equip.getBaseStat(tuc) > 0) {
                    suis = ItemConstants.getScrollUpgradeInfosByEquip(equip);
                    c.write(FieldPacket.scrollUpgradeDisplay(false, suis));
                }
                break;
            case HyperUpgradeResult:
                inPacket.decodeInt(); //tick
                int eqpPos = inPacket.decodeShort();
                boolean extraChanceFromMiniGame = inPacket.decodeByte() != 0;
                equip = (Equip) chr.getEquipInventory().getItemBySlot(eqpPos);
                if (extraChanceFromMiniGame) {
                    inPacket.decodeInt();
                }
                inPacket.decodeInt();
                inPacket.decodeInt();
                boolean safeGuard = inPacket.decodeByte() != 0;
                boolean equippedInv = eqpPos < 0;
                inv = equippedInv ? chr.getEquippedInventory() : chr.getEquipInventory();
                equip = (Equip) inv.getItemBySlot(Math.abs(eqpPos));
                if (equip == null) {
                    chr.chatMessage("Could not find the given equip.");
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                if (!ItemConstants.isUpgradable(equip.getItemId())
                        || (equip.getBaseStat(tuc) != 0 && !c.getWorld().isReboot())
                        || chr.getEquipInventory().getEmptySlots() == 0
                        || equip.getChuc() >= GameConstants.getMaxStars(equip)
                        || equip.hasSpecialAttribute(EquipSpecialAttribute.Vestige)) {
                    chr.chatMessage("Equipment cannot be enhanced.");
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                long cost = GameConstants.getEnchantmentMesoCost(equip.getrLevel() + equip.getiIncReq(), equip.getChuc(), equip.isSuperiorEqp());
                if (chr.getMoney() < cost) {
                    chr.chatMessage("Mesos required: " + NumberFormat.getNumberInstance(Locale.US).format(cost));
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                Equip oldEquip = equip.deepCopy();
                int successProp = GameConstants.getEnchantmentSuccessRate(equip);
                if (extraChanceFromMiniGame) {
                    successProp *= 1.045;
                }
                int destroyProp = safeGuard && equip.canSafeguardHyperUpgrade() ? 0 : GameConstants.getEnchantmentDestroyRate(equip);
                if (equippedInv && destroyProp > 0 && chr.getEquipInventory().getEmptySlots() == 0) {
                    c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("You do not have enough space in your "
                            + "equip inventory in case your item gets destroyed.")));
                    return;
                }
                success = Util.succeedProp(successProp, 1000);
                boolean boom = false;
                boolean canDegrade = equip.isSuperiorEqp() ? equip.getChuc() > 0 : equip.getChuc() > 5 && equip.getChuc() % 5 != 0;
                if (success) {
                    equip.setChuc((short) (equip.getChuc() + 1));
                    equip.setDropStreak(0);
                } else if (Util.succeedProp(destroyProp, 1000)) {
                    equip.setChuc((short) 0);
                    equip.addSpecialAttribute(EquipSpecialAttribute.Vestige);
                    boom = true;
                    if (equippedInv) {
                        chr.unequip(equip);
                        equip.setBagIndex(chr.getEquipInventory().getFirstOpenSlot());
                        equip.updateToChar(chr);
                        c.write(WvsContext.inventoryOperation(true, false, Move, (short) eqpPos, (short) equip.getBagIndex(), 0, equip));
                    }
                    if (!equip.isSuperiorEqp()) {
                        equip.setChuc((short) Math.min(12, equip.getChuc()));
                    } else {
                        equip.setChuc((short) 0);
                    }
                } else if (canDegrade) {
                    equip.setChuc((short) (equip.getChuc() - 1));
                    equip.setDropStreak(equip.getDropStreak() + 1);
                }
                chr.deductMoney(cost);
                equip.recalcEnchantmentStats();
                oldEquip.recalcEnchantmentStats();
                equip.updateToChar(chr);
                c.write(FieldPacket.showUpgradeResult(oldEquip, equip, success, boom, canDegrade));
                chr.dispose();
                break;
            case TransmissionResult:
                inPacket.decodeInt(); // tick
                short toPos = inPacket.decodeShort();
                short fromPos = inPacket.decodeShort();
                Equip fromEq = (Equip) chr.getEquipInventory().getItemBySlot(fromPos);
                Equip toEq = (Equip) chr.getEquipInventory().getItemBySlot(toPos);
                if (fromEq == null || toEq == null || fromEq.getItemId() != toEq.getItemId()
                        || !fromEq.hasSpecialAttribute(EquipSpecialAttribute.Vestige)) {
                    log.error(String.format("Equip transmission failed: from = %s, to = %s", fromEq, toEq));
                    c.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                fromEq.removeSpecialAttribute(EquipSpecialAttribute.Vestige);
                fromEq.setChuc((short) 0);
                chr.consumeItem(toEq);
                fromEq.updateToChar(chr);
                c.write(FieldPacket.showTranmissionResult(fromEq, toEq));
                break;
            case ScrollUpgradeDisplay:
                int ePos = inPacket.decodeInt();
                inv = ePos < 0 ? chr.getEquippedInventory() : chr.getEquipInventory();
                ePos = Math.abs(ePos);
                equip = (Equip) inv.getItemBySlot(ePos);
                if (c.getWorld().isReboot()) {
                    chr.getOffenseManager().addOffense(String.format("Character %d attempted to scroll in reboot world (pos %d, itemid %d).",
                            chr.getId(), ePos, equip == null ? 0 : equip.getItemId()));
                    chr.dispose();
                    return;
                }
                if (equip == null || equip.getBaseStat(tuc) <= 0 || equip.hasSpecialAttribute(EquipSpecialAttribute.Vestige) || !ItemConstants.isUpgradable(equip.getItemId())) {
                    chr.getOffenseManager().addOffense(String.format("Character %d tried to scroll a non-scrollable equip (pos %d, itemid %d).",
                            chr.getId(), ePos, equip == null ? 0 : equip.getItemId()));
                    chr.dispose();
                    return;
                }
                suis = ItemConstants.getScrollUpgradeInfosByEquip(equip);
                c.write(FieldPacket.scrollUpgradeDisplay(false, suis));
                break;
            /*case ScrollTimerEffective:
             break;*/
            case HyperUpgradeDisplay:
                ePos = inPacket.decodeInt();
                safeGuard = inPacket.decodeByte() != 0;
                inv = ePos < 0 ? chr.getEquippedInventory() : chr.getEquipInventory();
                ePos = Math.abs(ePos);
                equip = (Equip) inv.getItemBySlot(ePos);
                if (equip == null || equip.hasSpecialAttribute(EquipSpecialAttribute.Vestige) || !ItemConstants.isUpgradable(equip.getItemId())) {
                    chr.getOffenseManager().addOffense(String.format("Character %d tried to enchant a non-enchantable equip (pos %d, itemid %d).",
                            chr.getId(), ePos, equip == null ? 0 : equip.getItemId()));
                    chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                    return;
                }
                cost = GameConstants.getEnchantmentMesoCost(equip.getrLevel() + equip.getiIncReq(), equip.getChuc(), equip.isSuperiorEqp());
                destroyProp = GameConstants.getEnchantmentDestroyRate(equip);
                if (safeGuard && equip.canSafeguardHyperUpgrade()) {
                    cost *= 2;
                }
                c.write(FieldPacket.hyperUpgradeDisplay(equip, equip.isSuperiorEqp() ? equip.getChuc() > 0 : equip.getChuc() > 5 && equip.getChuc() % 5 != 0,
                        cost, 0, 0, GameConstants.getEnchantmentSuccessRate(equip),  equip.getDropStreak() >= 2));
                break;
            case MiniGameDisplay:
                c.write(FieldPacket.miniGameDisplay(eeType));
                break;
            //case ShowScrollUpgradeResult:
            case ScrollTimerEffective:
            case ShowHyperUpgradeResult:
                break;
            /*
             case ShowScrollVestigeCompensationResult:
             case ShowTransmissionResult:
             case ShowUnknownFailResult:
             break;*/
            default:
                log.debug("Unhandled Equipment Enchant Type: " + eeType);
                chr.write(FieldPacket.showUnknownEnchantFailResult((byte) 0));
                break;
        }
    }

    @Handler(op = InHeader.USER_SKILL_LEARN_ITEM_USE_REQUEST)
    public static void handleUserLearnItemUseRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); //tick
        short pos = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        Char chr = c.getChr();

        ItemInfo ii = ItemData.getItemInfoByID(itemID);
        if (ii == null || !chr.hasItem(itemID)) {
            chr.chatMessage("Could not find that item.");
            return;
        }
        int masterLevel = ii.getMasterLv();
        int reqSkillLv = ii.getReqSkillLv();
        int skillid = 0;
        Map<ScrollStat, Integer> vals = ii.getScrollStats();
        int chance = vals.getOrDefault(ScrollStat.success, 100);

        for (int skill : ii.getSkills()) {
            if (chr.hasSkill(skill)) {
                skillid = skill;
                break;
            }
        }
        Skill skill = chr.getSkill(skillid);
        if (skill == null) {
            chr.chatMessage(Notice2, "An error has occured. Mastery Book ID: " + itemID + ",  skill ID: " + skillid + ".");
            chr.dispose();
            return;
        }
        if (skillid == 0 || (skill.getMasterLevel() >= masterLevel) || skill.getCurrentLevel() < reqSkillLv) {
            chr.chatMessage(SystemNotice, "You cannot use this Mastery Book.");
            chr.dispose();
            return;
        }

        if (skill.getCurrentLevel() > reqSkillLv && skill.getMasterLevel() < masterLevel) {
            chr.chatMessage(Mob, "Success Chance: " + chance + "%.");
            chr.consumeItem(itemID, 1);
            if (Util.succeedProp(chance)) {
                skill.setMasterLevel(masterLevel);
                chr.addSkill(skill);
                chr.write(WvsContext.changeSkillRecordResult(skill));
                chr.chatMessage(Notice2, "[Mastery Book] Item id: " + itemID + "  set Skill id: " + skillid + "'s Master Level to: " + masterLevel + ".");
            } else {
                chr.chatMessage(Notice2, "[Mastery Book] Item id: " + itemID + " was used, however it was unsuccessful.");
            }
        }
        chr.dispose();
    }


    @Handler(op = InHeader.SOCKET_CREATE_REQUEST)
    public static void handleSocketCreateRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        short uPos = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        short ePos = inPacket.decodeShort();
        Item item = chr.getConsumeInventory().getItemBySlot(uPos);
        Equip equip = (Equip) chr.getEquipInventory().getItemBySlot(ePos);
        if (equip == null || item == null || item.getItemId() != itemID) {
            log.error("Unknown equip or mismatching use items.");
            return;
        }
        boolean success = true;
        if (equip.getSocket(0) == ItemConstants.INACTIVE_SOCKET && ItemConstants.canEquipHavePotential(equip)) {
            chr.consumeItem(item);
            equip.setSocket(0, ItemConstants.EMPTY_SOCKET_ID);
        } else {
            success = false;
        }
        c.write(FieldPacket.socketCreateResult(success));
        equip.updateToChar(chr);
    }

    @Handler(op = InHeader.NEBULITE_INSERT_REQUEST)
    public static void handleNebuliteInsertRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        short nebPos = inPacket.decodeShort();
        int nebID = inPacket.decodeInt();
        Item item = chr.getInstallInventory().getItemBySlot(nebPos);
        short ePos = inPacket.decodeShort();
        Equip equip = (Equip) chr.getEquipInventory().getItemBySlot(ePos);
        if (item == null || equip == null || item.getItemId() != nebID || !ItemConstants.isNebulite(item.getItemId())) {
            log.error("Nebulite or equip was not found when inserting.");
            chr.dispose();
            return;
        }
        if (equip.getSocket(0) != ItemConstants.EMPTY_SOCKET_ID) {
            log.error("Tried to Nebulite an item without an empty socket.");
            chr.chatMessage("You can only insert a Nebulite into empty socket slots.");
            chr.dispose();
            return;
        }
        if (!ItemConstants.nebuliteFitsEquip(equip, item)) {
            chr.getOffenseManager().addOffense(String.format("Character %d attempted to use a nebulite (%d) that doesn't fit an equip (%d).", chr.getId(), item.getItemId(), equip.getItemId()));
            chr.chatMessage("The nebulite cannot be mounted on this equip.");
            chr.dispose();
            return;
        }
        chr.consumeItem(item);
        equip.setSocket(0, nebID % ItemConstants.NEBILITE_BASE_ID);
        equip.updateToChar(chr);
    }

    @Handler(op = InHeader.USER_ITEM_SKILL_SOCKET_UPGRADE_ITEM_USE_REQUEST)
    public static void handleUserItemSkillSocketUpdateItemUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        short uPos = inPacket.decodeShort();
        short ePos = inPacket.decodeShort();
        Item item = chr.getConsumeInventory().getItemBySlot(uPos);
        Equip equip = (Equip) chr.getEquipInventory().getItemBySlot(ePos);
        if (item == null || equip == null || !ItemConstants.isWeapon(equip.getItemId())
                || !ItemConstants.isSoulEnchanter(item.getItemId()) || equip.getrLevel() + equip.getiIncReq() < ItemConstants.MIN_LEVEL_FOR_SOUL_SOCKET) {
            chr.dispose();
            return;
        }
        int successProp = ItemData.getItemInfoByID(item.getItemId()).getScrollStats().get(ScrollStat.success);
        boolean success = Util.succeedProp(successProp);
        if (success) {
            equip.setSoulSocketId((short) (item.getItemId() % ItemConstants.SOUL_ENCHANTER_BASE_ID));
            equip.updateToChar(chr);
        }
        chr.getField().broadcastPacket(UserPacket.showItemSkillSocketUpgradeEffect(chr.getId(), success));
        chr.consumeItem(item);
    }

    @Handler(op = InHeader.USER_ITEM_SKILL_OPTION_UPGRADE_ITEM_USE_REQUEST)
    public static void handleUserItemSkillOptionUpdateItemUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt(); // tick
        short uPos = inPacket.decodeShort();
        short ePos = inPacket.decodeShort();
        Item item = chr.getConsumeInventory().getItemBySlot(uPos);
        Equip equip = (Equip) chr.getEquipInventory().getItemBySlot(ePos);
        if (item == null || equip == null || !ItemConstants.isWeapon(equip.getItemId())
                || !ItemConstants.isSoul(item.getItemId()) || equip.getSoulSocketId() == 0) {
            chr.dispose();
            return;
        }
        equip.setSoulOptionId((short) (1 + item.getItemId() % ItemConstants.SOUL_ITEM_BASE_ID));
        short option = ItemConstants.getSoulOptionFromSoul(item.getItemId());
        if (option == 0) {
            option = (short) ItemConstants.getRandomSoulOption();
        }
        equip.setSoulOption(option);
        equip.updateToChar(chr);
        chr.consumeItem(item);
        chr.getField().broadcastPacket(UserPacket.showItemSkillOptionUpgradeEffect(chr.getId(), true, false));
    }

    @Handler(op = InHeader.USER_WEAPON_TEMP_ITEM_OPTION_REQUEST)
    public static void handleUserWeaponTempItemOptionRequest(Char chr, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(CharacterTemporaryStat.SoulMP)
                && tsm.getOption(CharacterTemporaryStat.SoulMP).nOption >= ItemConstants.MAX_SOUL_CAPACITY) {
            Option o = new Option();
            o.nOption = tsm.getOption(CharacterTemporaryStat.SoulMP).nOption;
            o.xOption = tsm.getOption(CharacterTemporaryStat.SoulMP).xOption;
            o.rOption = ItemConstants.getSoulSkillFromSoulID(
                    ((Equip) chr.getEquippedItemByBodyPart(BodyPart.Weapon)).getSoulOptionId()
            );
            tsm.putCharacterStatValue(CharacterTemporaryStat.FullSoulMP, o);
            tsm.sendSetStatPacket();
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_PROTECT_BUFF_DIE_ITEM_REQUEST)
    public static void handleUserProtectBuffDieItemRequest(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        boolean used = inPacket.decodeByte() != 0;
        if (used) {
            // grabs the first one from the list of buffItems
            Item buffProtector = chr.getBuffProtectorItem();
            if (buffProtector != null) {
                chr.setBuffProtector(true);
                chr.consumeItem(buffProtector);
                chr.write(UserLocal.setBuffProtector(buffProtector.getItemId(), true));
            } else {
                chr.getOffenseManager().addOffense(String.format("Character id %d tried to use a buff without having the appropriate item.", chr.getId()));
            }
        }
    }

    @Handler(op = InHeader.USER_DEFAULT_WING_ITEM)
    public static void handleUserDefaultWingItem(Char chr, InPacket inPacket) {
        int wingItem = inPacket.decodeInt();
        if (wingItem == 5010093) { // AB
            chr.getAvatarData().getCharacterStat().setWingItem(wingItem);
            chr.getField().broadcastPacket(UserRemote.setDefaultWingItem(chr));
        }
    }

    @Handler(op = InHeader.USER_RECIPE_OPEN_ITEM_USE_REQUEST)
    public static void handleUserRecipeOpenItemUseRequest(Char chr, InPacket inPacket) {
        inPacket.decodeInt();// tick
        short pos = inPacket.decodeShort();// // nPOS
        int itemID = inPacket.decodeInt();// nItemID

        Item item = chr.getInventoryByType(CONSUME).getItemBySlot(pos);
        if (item.getItemId() != itemID) {
            chr.dispose();
            return;
        }
        if (chr != null && chr.getHP() > 0 && ItemConstants.isRecipeOpenItem(itemID)) {
            ItemInfo recipe = ItemData.getItemInfoByID(itemID);
            if (recipe != null) {
                int recipeID = recipe.getSpecStats().getOrDefault(SpecStat.recipe, 0);
                int reqSkillLevel = recipe.getSpecStats().getOrDefault(SpecStat.reqSkillLevel, 0);
                MakingSkillRecipe msr = SkillData.getRecipeById(recipeID);
                if (msr != null && msr.isNeedOpenItem()) {
                    if (chr.getSkillLevel(msr.getReqSkillID()) < reqSkillLevel || chr.getSkillLevel(recipeID) > 0) {
                        return;
                    }
                    chr.addSkill(recipeID, 1, 1);
                }
            }
        }
    }

    @Handler(op = InHeader.USER_ACTIVATE_NICK_ITEM)
    public static void handleUserActivateNickItem(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int nickItem = inPacket.decodeInt();
        chr.setNickItem(nickItem);
        chr.getField().broadcastPacket(UserRemote.setActiveNickItem(chr), chr);
    }

    @Handler(op = InHeader.USER_HYPER_SKILL_UP_REQUEST)
    public static void handleUserHyperSkillUpRequest(Char chr, InPacket inPacket) {
        // TODO: verify hyper skill is of the character's class
        inPacket.decodeInt(); // tick
        int skillID = inPacket.decodeInt();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        if (si == null) {
            log.error(String.format("Character %d attempted assigning hyper SP to a skill with null skillinfo (%d).", chr.getId(), skillID));
            chr.dispose();
            return;
        }
        if (si.getHyper() == 0 && si.getHyperStat() == 0 ||
                (!SkillConstants.isMatching(si.getRootId(), chr.getJob()) && !SkillConstants.isHyperstatSkill(skillID))) {
            log.error(String.format("Character %d attempted assigning hyper SP to a wrong skill (skill id %d, player job %d)", chr.getId(), skillID, chr.getJob()));
            chr.dispose();
            return;
        }
        Skill skill = chr.getSkill(skillID, true);
        short level = chr.getLevel();
        if (si.getHyper() != 0) { // Passive/Active hyper
            boolean active = si.getHyper() == 2;
            int totalHyperSp;
            int spentSp;
            if (active) {
                totalHyperSp = SkillConstants.getTotalActiveHyperSpByLevel(level);
                spentSp = chr.getSpentActiveHyperSkillSp();
            } else {
                totalHyperSp = SkillConstants.getTotalPassiveHyperSpByLevel(level);
                spentSp = chr.getSpentPassiveHyperSkillSp();
            }
            int availableSp = totalHyperSp - spentSp;
            if (availableSp <= 0 || skill.getCurrentLevel() != 0) {
                log.error(String.format("Character %d attempted assigning hyper skill SP without having it, or too much. Available SP %d, current %d (%d, job %d)",
                        chr.getId(), availableSp, skill.getCurrentLevel(), skillID, chr.getJob()));
                chr.dispose();
                return;
            }
        } else if (si.getHyperStat() != 0) {
            int totalHyperSp = SkillConstants.getTotalHyperStatSpByLevel(level);
            int spentSp = chr.getSpentHyperSp();
            int availableSp = totalHyperSp - spentSp;
            int neededSp = SkillConstants.getNeededSpForHyperStatSkill(skill.getCurrentLevel() + 1);
            if (skill.getCurrentLevel() >= skill.getMaxLevel() || availableSp < neededSp) {
                log.error(String.format("Character %d attempted assigning too many hyper stat levels. Available SP %d, needed %d, current %d (%d, job %d)",
                        chr.getId(), availableSp, neededSp, skill.getCurrentLevel(), skillID, chr.getJob()));
                chr.dispose();
                return;
            }
        } else { // not hyper stat and not hyper skill
            log.error(String.format("Character %d attempted assigning hyper stat to an improper skill. (%d, job %d)", chr.getId(), skillID, chr.getJob()));
            chr.dispose();
            return;
        }
        chr.removeFromBaseStatCache(skill);
        skill.setCurrentLevel(skill.getCurrentLevel() + 1);
        chr.addToBaseStatCache(skill);
        chr.addSkill(skill);
        chr.write(WvsContext.changeSkillRecordResult(skill));
    }

    @Handler(op = InHeader.USER_HYPER_STAT_SKILL_RESET_REQUEST)
    public static void handleUserHyperStatSkillResetRequest(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        if (chr.getMoney() < GameConstants.HYPER_STAT_RESET_COST) {
            chr.chatMessage("Not enough money for this operation.");
            chr.write(WvsContext.receiveHyperStatSkillResetResult(chr.getId(), true, false));
        } else {
            chr.deductMoney(GameConstants.HYPER_STAT_RESET_COST);
            List<Skill> skills = new ArrayList<>();
            for (int skillID = 80000400; skillID <= 80000418; skillID++) {
                Skill skill = chr.getSkill(skillID);
                if (skill != null) {
                    skill.setCurrentLevel(0);
                    skills.add(skill);
                    chr.addSkill(skill);
                }
            }
            chr.write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
            chr.write(WvsContext.receiveHyperStatSkillResetResult(chr.getId(), true, true));
        }
    }


}
