package net.swordie.ms.constants;

import net.swordie.ms.client.character.items.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.ItemInfo;
import org.apache.log4j.LogManager;
import net.swordie.ms.util.Util;

import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.enums.InvType.EQUIP;

/**
 * Created on 12/12/2017.
 */
public class ItemConstants {
    public static final int EMPTY_SOCKET_ID = 3;
    public static final short INACTIVE_SOCKET = 0;
    public static final short MIN_LEVEL_FOR_SOUL_SOCKET = 75;
    public static final int SOUL_ENCHANTER_BASE_ID = 2590000;
    public static final int SOUL_ITEM_BASE_ID = 2591000;
    public static final int MAX_SOUL_CAPACITY = 1000;
    public static final int MOB_DEATH_SOUL_MP_COUNT = 150;
    public static final int MOB_CARD_BASE_ID = 2380000;
    public static final int FAMILIAR_PREFIX = 996;
    public static final int SPELL_TRACE_ID = 4001832;
    public static final int RAND_CHAOS_MAX = 5;
    public static final int INC_RAND_CHAOS_MAX = 10;

    static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static final int THIRD_LINE_CHANCE = 50;
    public static final int PRIME_LINE_CHANCE = 15;

    public static final int RED_CUBE = 5062009;
    public static final int BONUS_POT_CUBE = 5062500;
    public static final int BLACK_CUBE = 5062010;

    public static final int NEBILITE_BASE_ID = 3060000;

    public static final int HORNTAIL_NECKLACE = 1122000;
    public static final int CHAOS_HORNTAIL_NECKLACE = 1122076;

    public static final int MAX_HAMMER_SLOTS = 2;
    public static final int GOLDEN_HAMMER_20 = 2470004;
    public static final int GOLDEN_HAMMER_50 = 2470001;
    public static final int GOLDEN_HAMMER_100 = 2470007;

    private static final Integer[] soulPotList = new Integer[]{32001, 32002, 32003, 32004, 32005, 32006, 32011, 32012, // flat
            32041, 32042, 32043, 32044, 32045, 32046, 32051, 32052}; // rate

    // Spell tracing
    private static final int BASE_ST_COST = 30;
    private static final int INNOCENCE_ST_COST = 1337;
    private static final int CLEAN_SLATE_ST_COST = 200;

    public static int getGenderFromId(int nItemID) {
        int result; // eax

        if (nItemID / 1000000 != 1 && nItemID / 10000 != 254 || nItemID / 10000 == 119 || nItemID / 10000 == 168)
            return 2;
        switch (nItemID / 1000 % 10) {
            case 0:
                result = 0;
                break;
            case 1:
                result = 1;
                break;
            default:
                return 2;
        }
        return result;
    }

    public static int getBodyPartFromItem(int nItemID, int gender) {
        List<Integer> arr = getBodyPartArrayFromItem(nItemID, gender);
        int result = arr.size() > 0 ? arr.get(0) : 0;
        return result;
    }

    public static List<Integer> getBodyPartArrayFromItem(int itemID, int genderArg) {
        int gender = getGenderFromId(itemID);
        int prefix = itemID / 10000;
        List<Integer> bodyPartList = new ArrayList<>();
        if (prefix != 119 && prefix != 168) {
            if (gender != 2 && genderArg != 2 && gender != genderArg) {
                return bodyPartList;
            }
        }
        switch (prefix) {
            case 100:
                bodyPartList.add(1);
                bodyPartList.add(1200);
                bodyPartList.add(1300);
                bodyPartList.add(1501);
                break;
            case 101:
                bodyPartList.add(2);
                bodyPartList.add(1202);
                bodyPartList.add(1302);
                bodyPartList.add(1502);
                break;
            case 102:
                bodyPartList.add(3);
                bodyPartList.add(1500);
                break;
            case 103:
                bodyPartList.add(4);
                bodyPartList.add(1503);
                break;
            case 104:
            case 105:
                bodyPartList.add(5);
                bodyPartList.add(1203);
                bodyPartList.add(1505);
                break;
            case 106:
                bodyPartList.add(6);
                bodyPartList.add(1204);
                bodyPartList.add(1505);
                break;
            case 107:
                bodyPartList.add(7);
                bodyPartList.add(1205);
                bodyPartList.add(1509);
                break;
            case 108:
                bodyPartList.add(8);
                bodyPartList.add(1206);
                bodyPartList.add(1304);
                bodyPartList.add(1506);
                break;
            case 109:
            case 134:
            case 135:
            case 156:
                bodyPartList.add(10);
                break;
            case 110:
                bodyPartList.add(9);
                bodyPartList.add(1201);
                bodyPartList.add(1301);
                bodyPartList.add(1504);
                break;
            case 111:
                bodyPartList.add(12);
                bodyPartList.add(13);
                bodyPartList.add(15);
                bodyPartList.add(16);
                bodyPartList.add(1510);
                bodyPartList.add(1511);
                break;
            case 112:
                bodyPartList.add(17);
                bodyPartList.add(65);
                bodyPartList.add(1512);
                bodyPartList.add(1513);
                break;
            case 113:
                bodyPartList.add(50);
                break;
            case 114:
                bodyPartList.add(49);
                break;
            case 115:
                bodyPartList.add(51);
                break;
            case 116:
                bodyPartList.add(52);
                break;
            case 117:
                bodyPartList.add(55);
                break;
            case 118:
                bodyPartList.add(56);
                break;
            case 119:
                bodyPartList.add(61);
                break;
            case 120:
                bodyPartList.add(5000);
                bodyPartList.add(5001);
                bodyPartList.add(5002);
                break;
            case 161:
                bodyPartList.add(1100);
                break;
            case 162:
                bodyPartList.add(1101);
                break;
            case 163:
                bodyPartList.add(1102);
                break;
            case 164:
                bodyPartList.add(1103);
                break;
            case 165:
                bodyPartList.add(1104);
                break;
            case 166:
                bodyPartList.add(53);
                break;
            case 167:
                bodyPartList.add(54);
                bodyPartList.add(61);
                break;
            case 168:
                for (int id = 1400; id < 1425; id++) {
                    bodyPartList.add(id);
                }
                break;
            case 180:
                bodyPartList.add(14);
                bodyPartList.add(30);
                bodyPartList.add(38);
                break;
            case 184:
                bodyPartList.add(5100);
                break;
            case 185:
                bodyPartList.add(5102);
                break;
            case 186:
                bodyPartList.add(5103);
                break;
            case 187:
                bodyPartList.add(5104);
                break;
            case 188:
                bodyPartList.add(5101);
                break;
            case 189:
                bodyPartList.add(5105);
                break;
            case 190:
                bodyPartList.add(18);
                break;
            case 191:
                bodyPartList.add(19);
                break;
            case 192:
                bodyPartList.add(20);
                break;
            case 194:
                bodyPartList.add(1000);
                break;
            case 195:
                bodyPartList.add(1001);
                break;
            case 196:
                bodyPartList.add(1002);
                break;
            case 197:
                bodyPartList.add(1003);
                break;
            default:
                if (ItemConstants.isLongOrBigSword(itemID) || ItemConstants.isWeapon(itemID)) {
                    bodyPartList.add(11);
                    if(ItemConstants.isFan(itemID)) {
                        bodyPartList.add(5200);
                    } else {
                        bodyPartList.add(1507);
                    }
                } else {
                    log.debug("Unknown type? id = " + itemID);
                }
                break;
        }
        return bodyPartList;
    }

    private static boolean isLongOrBigSword(int nItemID) {
        int prefix = nItemID / 10000;
        return prefix % 100 == 56 || prefix % 100 == 57;
    }

    private static boolean isFan(int nItemID) {
        int prefix = nItemID / 10000;
        return prefix % 100 == 55;
    }

    public static int getWeaponType(int itemID) {
        if (itemID / 1000000 != 1) {
            return 0;
        }
        return itemID / 10000 % 100;
    }

    public static boolean isThrowingItem(int itemID) {
        return isThrowingStar(itemID) || isBullet(itemID) || isBowArrow(itemID);
    }

    public static boolean isThrowingStar(int itemID) {
        return itemID / 10000 == 207;
    }

    public static boolean isBullet(int itemID) {
        return itemID / 10000 == 233;
    }

    public static boolean isBowArrow(int itemID) {
        return itemID / 1000 == 2060;
    }

    public static boolean isFamiliar(int itemID) {
        return itemID / 10000 == 287;
    }

    public static boolean isEnhancementScroll(int scrollID) {
        return scrollID / 100 == 20493;
    }

    public static boolean isHat(int itemID) {
        return itemID / 10000 == 100;
    }

    public static boolean isWeapon(int itemID) {
        return itemID >= 1210000 && itemID < 1600000 || isSecondary(itemID);
    }

    private static boolean isSecondary(int itemID) {
        return itemID / 10000 == 135;
    }

    private static boolean isShield(int itemID) {
        return itemID / 10000 == 109;
    }

    public static boolean isAccessory(int itemID) {
        return (itemID >= 1010000 && itemID < 1040000) || (itemID >= 1122000 && itemID < 1153000) ||
                (itemID >= 1112000 && itemID < 1113000) || (itemID >= 1670000 && itemID < 1680000);
    }

    public static boolean isFaceAccessory(int itemID) {
        return itemID / 10000 == 101;
    }

    public static boolean isEyeAccessory(int itemID) {
        return itemID / 10000 == 102;
    }

    public static boolean isEarrings(int itemID) {
        return itemID / 10000 == 103;
    }

    public static boolean isTop(int itemID) {
        return itemID / 10000 == 104;
    }

    public static boolean isOverall(int itemID) {
        return itemID / 10000 == 105;
    }

    public static boolean isBottom(int itemID) {
        return itemID / 10000 == 106;
    }

    public static boolean isShoe(int itemID) {
        return itemID / 10000 == 107;
    }

    public static boolean isGlove(int itemID) {
        return itemID / 10000 == 108;
    }

    public static boolean isCape(int itemID) {
        return itemID / 10000 == 110;
    }

    public static boolean isArmor(int itemID) {
        return !isAccessory(itemID) && !isWeapon(itemID);
    }

    public static boolean isRing(int itemID) {
        return itemID >= 1112000 && itemID < 1113000;
    }

    public static boolean isPendant(int itemID) {
        return itemID / 10000 == 112;
    }

    public static boolean isBelt(int itemID) {
        return itemID / 10000 == 113;
    }

    public static boolean isMedal(int itemID) {
        return itemID / 10000 == 114;
    }

    public static boolean isShoulder(int itemID) {
        return itemID / 10000 == 115;
    }

    public static boolean isPocketItem(int itemID) {
        return itemID / 10000 == 116;
    }

    public static boolean isCrusaderCodex(int itemID) {
        return itemID / 10000 == 117;
    }

    public static boolean isBadge(int itemID) {
        return itemID / 10000 == 118;
    }

    public static boolean isEmblem(int itemID) {
        return itemID / 10000 == 119;
    }

    public static boolean isTotem(int itemID) {
        return itemID / 10000 == 120;
    }

    public static boolean isAndroid(int itemID) {
        return itemID / 10000 == 166;
    }

    public static boolean isMechanicalHeart(int itemID) {
        return itemID / 10000 == 167;
    }

    public static boolean canEquipTypeHavePotential(int itemid) {
        return isRing(itemid) ||
                isPendant(itemid) ||
                isWeapon(itemid) ||
                isBelt(itemid) ||
                isHat(itemid) ||
                isFaceAccessory(itemid) ||
                isEyeAccessory(itemid) ||
                isOverall(itemid) ||
                isTop(itemid) ||
                isBottom(itemid) ||
                isShoe(itemid) ||
                isEarrings(itemid) ||
                isShoulder(itemid) ||
                isGlove(itemid) ||
                isEmblem(itemid) ||
                isBadge(itemid) ||
                isShield(itemid) ||
                isCape(itemid) ||
                isMechanicalHeart(itemid);
    }

    public static boolean canEquipHavePotential(Equip equip) {
        return !equip.isCash() ||
                canEquipTypeHavePotential(equip.getItemId()) &&
                !equip.isNoPotential() &&
                ItemData.getEquipById(equip.getItemId()).getTuc() >= 1;
    }

    public static boolean canEquipGoldHammer(Equip equip) {
        return !(equip.getItemId() == HORNTAIL_NECKLACE || // Horntail Necklace and the Chaos version are the only exceptions that Golden Hammer has.
                equip.getItemId() == CHAOS_HORNTAIL_NECKLACE ||
                equip.getIuc() >= MAX_HAMMER_SLOTS ||
                ItemData.getEquipById(equip.getItemId()).getTuc() <= 0); // No upgrade slots by default
    }

    public static boolean isGoldHammer(Item item) {
        return item.getItemId() == GOLDEN_HAMMER_20 ||
                item.getItemId() == GOLDEN_HAMMER_50 ||
                item.getItemId() == GOLDEN_HAMMER_100;
    }

    /**
     * Gets potential tier for a line.
     * Accounts prime lines too.
     * @param line Potential line.
     * @param grade Our current potential grade.
     */
    public static ItemGrade getLineTier(int line, ItemGrade grade) {
        if (line == 0 || Util.succeedProp(PRIME_LINE_CHANCE)) {
            return grade;
        }

        return ItemGrade.getOneTierLower(grade.getVal());
    }

    public static List<ItemOption> getOptionsByEquip(Equip equip, boolean bonus, int line) {
        int id = equip.getItemId();
        Collection<ItemOption> data = ItemData.getItemOptions().values();
        ItemGrade grade = getLineTier(line, ItemGrade.getGradeByVal(bonus ? equip.getBonusGrade() : equip.getBaseGrade()));

        // need a list, as we take a random item from it later on
        List<ItemOption> res = data.stream().filter(
                io -> io.getOptionType() == 0 &&
                io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                .collect(Collectors.toList());
        if (isWeapon(id)) {
            res.addAll(data.stream().filter(
                    io -> io.getOptionType() == 10
                    &&  io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus
            ).collect(Collectors.toList()));
        } else {
            res.addAll(data.stream().filter(
                    io -> io.getOptionType() == 11
                    && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                    .collect(Collectors.toList()));
            if (isAccessory(id)) {
                res.addAll(data.stream().filter(
                        io -> io.getOptionType() == 40
                        && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                        .collect(Collectors.toList()));
            } else {
                res.addAll(data.stream().filter(
                        io -> io.getOptionType() == 20
                        && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                        .collect(Collectors.toList()));
                if (isHat(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 51
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isTop(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 52
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isBottom(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 53
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isOverall(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 52
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 53
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isGlove(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 54
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isShoe(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 55
                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
            }
        }
        return res.stream().filter(io -> io.getReqLevel() <= equip.getrLevel()).collect(Collectors.toList());
    }

    public static List<Integer> getWeightedOptionsByEquip(Equip equip, boolean bonus, int line) {
        List<Integer> res = new ArrayList<>();
        List<ItemOption> data = getOptionsByEquip(equip, bonus, line);
        for(ItemOption io : data) {
            for (int i = 0; i < io.getWeight(); i++) {
                res.add(io.getId());
            }
        }
        return res;
    }

    public static int getRandomOption(Equip equip, boolean bonus, int line) {
        List<Integer> data = getWeightedOptionsByEquip(equip, bonus, line);
        return data.get(Util.getRandom(data.size()));
    }

    public static int getTierUpChance(int id) {
        int res = 0;
        switch(id) {
            case ItemConstants.RED_CUBE: // Red cube
            case ItemConstants.BONUS_POT_CUBE: // Bonus potential cube
                res = 30;
                break;
            case ItemConstants.BLACK_CUBE:
                res = 40;
                break;
        }
        return res;
    }

    public static boolean isEquip(int id) {
        return id / 1000000 == 1;
    }

    public static boolean isClaw(int id) {
        return id / 10000 == 147;
    }

    public static boolean isBow(int id) {
        return id / 10000 == 145;
    }

    public static boolean isXBow(int id) {
        return id / 10000 == 146;
    }

    public static boolean isGun(int id) {
        return id / 10000 == 149;
    }

    public static boolean isXBowArrow(int id) {
        return id / 1000 == 2061;
    }

    public static InvType getInvTypeByItemID(int itemID) {
        if(isEquip(itemID)) {
            return EQUIP;
        } else {
            ItemInfo ii = ItemData.getItemInfoByID(itemID);
            if(ii == null) {
                return null;
            }
            return ii.getInvType();
        }
    }

    public static Set<Integer> getRechargeablesList() {
        Set<Integer> itemList = new HashSet<>();
        // all throwing stars
        for(int i = 2070000; i <= 2070016; i++) {
            itemList.add(i);
        }
        itemList.add(2070018);
        itemList.add(2070023);
        itemList.add(2070024);
        itemList.add(2070026);
        // all bullets
        for(int i = 2330000; i <= 2330006; i++) {
            itemList.add(i);
        }
        itemList.add(2330008);
        itemList.add(2330016);
        itemList.add(2331000);
        itemList.add(2332000);
        return itemList;
    }

    public static boolean isRechargable(int itemId) {
        return isThrowingStar(itemId) || isBullet(itemId);
    }

    public static int getDamageSkinIDByItemID(int itemID) {
        switch(itemID) {
            case 2431965: // base damage Skin:
                return 0;
            case 2431966: // digital Sunrise Skin Damage:
            case 2432084: // digital Sunrise damage the skin
                return 1;
            case 2431967: // Kritias Skin Damage:
                return 2;
            case 2432131: // Party Quest Skin Damage:
                return 3;
            case 2432153: // Hard Hitting:
            case 2432638: // Creative Impact Damage Skin
            case 2432659: // Creative Impact Damage Skin
                return 4;
            case 2432154: // sweet traditional Han Skin Damage:
            case 2432637: // sweet traditional one and damage the skin
            case 2432658: // sweet traditional one and damage the skin
                return 5;
            case 2432207: // Club Henesys' damage Skin:
                 return 6;
            case 2432354: // Merry Christmas Skin Damage:
                 return 7;
            case 2432355: // Snow Blossom Skin Damage:
            case 2432972: // Snow Blossom Skin Damage
                 return 8;
            case 2432465: // damage the skin of Alicia:
                 return 9;
            case 2432479: // Dorothy skin damage:
                 return 10;
            case 2432526: // Keyboard Warrior Skin Damage:
            case 2432639: // Keyboard Warrior Skin Damage
            case 2432660: // Keyboard Warrior Skin Damage
                 return 11;
            case 2432532: // spring breeze rustling skin damage:
                 return 12;
            case 2432592: // solo troops skin damage:
                 return 13;
            case 2432640: // Reminiscence skin damage:
            case 2432661: // Remy you damage the skin Suns
                 return 14;
            case 2432710: // Orange Mushroom Skin Damage:
                 return 15;
            case 2432836: // crown damage Skin:
                 return 16;
            case 2432973: // monotone skin damage:
                 return 17;
            case 2433063: // Star Planet skin:
                 return 18;
            case 2433178: // Halloween Skin (bones):
                 return 20;
            case 2433456: // Hangul Skin:
                 return 21;
            case 2435960: // Fried Chicken Dmg Skin(Unknown ItemID):
                 return 22;
            case 2433715: // Striped Damage Skin:
                 return 23;
            case 2433804: // Couples Army Damage Skin:
                 return 24;
            case 5680343: // Star Damage Skin:
                 return 25;
            case 2433913: // Yeti and Pepe Damage Skin:
                 return 26;
            case 2433980: // Slime and Mushroom Damage Skin:
                 return 27;
            case 2433981: // Pink bean Damage skin:
                 return 28;
            case 2436229: // Pig Bar Dmg Skin(Unknown ItemID):
                 return 29;
//         case 2432659: // Hard-Hitting Dmg Skin (already in): 30
//         case 2return 432526;: // Keyboard Warrior (already in): 31
//         case 2432710: // Orange mushroom Skin Damage(already in): 32
//         case 2432355: // Snowflake Dmg Skin(already in): 33
            case 2434248: // Rainbow Boom Damage Skin:
                 return 34;
            case 2433362: // Night Sky Damage Skin:
                 return 35;
            case 2434274: // Marshmallow Damage Skin:
                 return 36;
            case 2434289: // Mu Lung Dojo Dmg Skin:
                 return 37;
            case 2434390: // Teddy Damage Skin:
                 return 38;
            case 2434391: // Mighty Ursus Damage Skin:
                 return 39;
            case 5680395: // Scorching Heat Damage Skin:
                 return 40;
            case 2434528: // USA Damage Skin:
                 return 41;
            case 2434529: // Churro Damage Skin:
                 return 42;
            case 2434530: // Singapore Night Damage Skin:
                 return 43;
            case 2433571: // Scribble Crush Damage Skin:
                 return 44;
            case 2434574: // Full Moon Damage Skin:
                 return 45;
            case 2433828: // White Heaven Sun Damage Skin:
                 return 46;
            case 2432804: // Princess No Damage Skin:
                 return 47;
            case 2434654: // Murgoth Damage Skin:
                 return 48;
            case 2435326: // Nine-Tailed Fox Damage Skin:
                 return 49;
            case 2432749: // Zombie Damage Skin:
                 return 50;
            case 2434710: // MVP Special Damage Skin:
                 return 51;
            case 2433777: // Black Heaven Damage Skin:
                 return 52;
            case 2434824: // Monster Park Damage Skin:
                 return 53;
                // case 2431966: // Digital Damage Skin(already in): 54 - (1)
                // case 2431967: // Kritias Damage Skin(already in): 55 - (2)
                // case 2432154: // Sweet tea cake Damage Skin(already in): 56 - (5)
                // case 2432354: // Merry Christmas Damage Skin(already in): 57 - (7)
                // case 2432532: // Gentle spring breeze damage skin(already in): 58 - (12)
                // case 2433715: // Striped Damage Skin(already in): 59 - (23)
                // case 2433063: // Star Damage Skin(already in): 60 - (25)
                // case 2433913: // Yeti and Pepe Damage Skin(already in): 61 - (26)
                // case 2433980: // Slime and Mushroom Damage Skin(already in): 62 - (27)
                // case 2434248: // Rainbow Boom Damage Skin(already in): 63 - (34)
                // case 2433362: // Night Sky Damage Skin(already in): 64 - (35)
                // case 2434274: // Marshmallow Damage Skin(already in): 65 - (36)
                // case 2434390: // Teddy Damage Skin(already in): 66 - (38)
                // case 5680395: // Scorching Heat Damage Skin(already in): 67 - (40)
                // case 2434528: // USA Damage Skin(already in): 68 - (41)
                // case 2434529: // Churro Damage Skin(already in): 69 - (42)
                // case 2434530: // Singapore Night Damage Skin(already in): 70 - (43)
                // case 2433571: // Scribble Crush Damage Skin(already in): 71 - (44)
                // case 2434574: // Full Moon Damage Skin(already in): 72 - (45)
                // case 2433828: // White Heaven Sun Damage Skin(already in): 73 - (46)
            case 2434662: // Jelly Beans Damage Skin:
                return 74;
            case 2434664: // Soft-Serve Damage Skin:
                return 75;
            case 2434868: // Christmas lights Damage skin:
                return 76;
            case 2436041: // Phantom Damage Skin:
                return 77;
            case 2436042: // Mercedes Damage Skin:
                return 78;
            case 2435046: // Fireworks Damage Skin:
                return 79;
            case 2435047: // Heart Balloon Damage Skin:
                return 80;
            case 2435836: // Neon Sign Damage Skin:
                return 81;
            case 2435141: // Freeze Tag Damage Skin:
                return 82;
            case 2435179: // Candy Damage Skin:
                return 83;
            case 2435162: // Antique Gold Damage Skin:
                return 84;
            case 2435157: // Calligraphy Damage Skin:
                return 85;
            case 2435835: // Explosion Damage Skin:
                return 86;
            case 2435159: // Snow-wing Damage Skin:
                return 87;
            case 2436044: // Miho Damage Skin:
                return 88;
            case 2434663: // Donut Damage Skin:
                return 89;
            case 2435182: // Music Score Damage Skin:
                return 90;
            case 2435850: // Moon Bunny Damage Skin:
                return 91;
            case 2435184: // Forest of Tenacity Damage Skin:
                return 92;
            case 2435222: // Festival Tortoise Damage Skin:
                return 93;
            case 2435293: // April Fools' Damage Skin:
                return 94;
            case 2435313: // Blackheart Day Damage Skin:
                return 95;
            case 2435331: // Bubble April Fools' Damage Skin:
                return 96;
            case 2435332: // Retro April Fools' Damage Skin:
                return 97;
            case 2435333: // Monochrome April Fools' Damage Skin:
                return 98;
            case 2435334: // Sparkling April Fools' Damage Skin:
                return 99;
            case 2435316: // Haste Damage Skin:
                return 100;
            case 2435408: // 13th Anniversary Maple Leaf Damage Skin:
                return 101;
            case 2435427: // Cyber Damage Skin:
                return 102;
            case 2435428: // Cosmic Damage Skin:
                return 103;
            case 2435429: // Choco Donut Damage Skin:
                return 104;
            case 2435456: // Lovely Damage Skin:
                return 105;
            case 2435493: // Monster Balloon Damage Skin:
                return 106;
                // case 2435331: // Bubble April Fools' Damage Skin(already in): 107 - (96)
                // case 2435334: // Sparkling April Fools' Damage Skin(already in): 108 - (99)
            case 2435959: // Henesys Damage Skin (unknown ID):
                return 109;
            case 2435958: // Leafre Damage Skin (unknown ID):
                return 110;
            case 2435431: // Algebraic Damage Skin:
                return 111;
            case 2435430: // Blue Fire Damage Skin:
                return 112;
            case 2435432: // Purple Damage Skin:
                return 113;
            case 2435433: // Nanopixel Damage Skin:
                return 114;
            case 2434601: // Invisible Damage Skin(unknown ID):
                return 115;
            case 2435521: // Crystal Damage Skin:
                return 116;
            case 2435196: // Crow Damage Skin:
                return 117;
            case 2435523: // Chocolate Damage Skin:
                return 118;
            case 2435524: // Spark Damage Skin:
                return 119;
            case 2435538: // Royal Damage Skin:
                return 120;
            case 2435832: // Chrome Damage Skin (Ver.1):
                return 121;
            case 2435833: // Neon Lights Damage Skin:
                return 122;
            case 2435839: // Cosmic Damage Skin(Cards):
                return 123;
            case 2435840: // Gilded Damage Skin:
                return 124;
            case 2435841: // Batty Damage Skin:
                return 125;
            case 2435849: // Monochrome April Fools' Damage Skin:
                return 126;
            case 2435972: // Vanishing Journey Damage Skin:
                return 127;
            case 2436023: // Chu Chu Damage Skin:
                return 128;
            case 2436024: // Lachelein Damage Skin:
                return 129;
            case 2436026: // Poison flame Damage Skin:
                return 130;
            case 2436027: // Blue Strike Damage Skin:
                return 131;
            case 2436028: // Music Power Damage Skin:
                return 132;
            case 2436029: // Collage Power Damage Skin:
                return 133;
            case 2436045: // Starlight Aurora Damage Skin:
                return 134;
            default:
                return 0;
        }
    }

    public static boolean isMasteryBook(int itemId) {
        return itemId / 10000 == 229;
    }

    public static boolean isPet(int itemId) {
        return itemId / 10000 == 500;
    }

    public static boolean isSoulEnchanter(int itemID) {
        return itemID / 1000 == 2590;
    }

    public static boolean isSoul(int itemID) {
        return itemID / 1000 == 2591;
    }

    public static short getSoulOptionFromSoul(int itemId) {
        short id = 0;
        switch(itemId) {

        }
        return id;
    }

    public static int getRandomSoulOption() {
        return Util.getRandomFromList(soulPotList);
    }

    public static int getSoulSkillFromSoulID(int soulID) {
        switch(soulID) {
            case 256:
            case 257:
            case 258:
            case 259:
            case 260:
            case 261:
            case 262:
            case 263:
                return 80001340; // Advance of Magnus
        }
        return 0;
    }

    public static boolean isMobCard(int itemID) {
        return itemID / 10000 == 238;
    }

    public static boolean isCollisionLootItem(int itemID) {
        switch (itemID) {
            case 2023484: // Blue Exp Orb
            case 2023494: // Purple Exp Orb
            case 2023495: // Red Exp Orb
                return true;

            default:
                return false;
        }
    }

    public static boolean isUpgradable(int itemID) {
        BodyPart bodyPart = BodyPart.getByVal(getBodyPartFromItem(itemID, 0));
        if (bodyPart == null || itemID / 10000 == 135) { // 135 are secondaries
            return false;
        }
        switch (bodyPart) {
            case RING1:
            case RING2:
            case RING3:
            case RING4:
            case PENDANT:
            case EXT_PENDANT1:
            case WEAPON:
            case BELT:
            case CAP:
            case FACEACC:
            case EYEACC:
            case CLOTHES:
            case PANTS:
            case SHOES:
            case EARACC:
            case SHOULDER:
            case GLOVES:
            case BADGE:
            case SHIELD:
            case CAPE:
            case MACHINEHEART:
                return true;
            default:
                return false;
        }
    }

    public static List<ScrollUpgradeInfo> getScrollUpgradeInfosByEquip(Equip equip) {
        // not the most beautiful way to do this, but I'd like to think that it's pretty easy to understand
        BodyPart bp = BodyPart.getByVal(ItemConstants.getBodyPartFromItem(equip.getItemId(), 0));
        List<ScrollUpgradeInfo> scrolls = new ArrayList<>();
        int rLevel = equip.getrLevel();
        int rJob = equip.getrJob();
        Set<EnchantStat> possibleStat = new HashSet<>();
        int plusFromLevel;
        int[] chances;
        int[] attStats = new int[0];
        int[] stat;
        int[] armorHp = new int[]{5, 20, 30, 70, 120};
        int[] armorDef = new int[]{1, 2, 4, 7, 10};
        boolean armor = false;
        if (bp == BodyPart.WEAPON) {
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if (rJob == 1) { // warrior
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if (rJob == 2) { // mage
                possibleStat.add(EnchantStat.MAD);
                possibleStat.add(EnchantStat.INT);
            } else if (rJob == 3) { // bowman
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.DEX);
            } else if (rJob == 4 || rJob == 5) { // thief/pirate
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.MAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30, 15};
            attStats = new int[]{1, 2, 3, 5, 7, 9};
            stat = new int[]{0, 0, 1, 2, 3, 4};
        } else if (bp == BodyPart.GLOVES) {
            plusFromLevel = rLevel <= 70 ? 0 : 1;
            if (rJob == 2) {
                possibleStat.add(EnchantStat.MAD);
            } else {
                possibleStat.add(EnchantStat.PAD);
            }
            possibleStat.add(EnchantStat.PDD);
            possibleStat.add(EnchantStat.MDD);
            chances = new int[]{100, 70, 30};
            attStats = new int[]{0, 1, 2, 3};
            stat = new int[]{3, 0, 0, 0};
        } else if (ItemConstants.isAccessory(equip.getItemId())) {
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if (rJob == 1) { // warrior
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if (rJob == 2) { // mage
                possibleStat.add(EnchantStat.INT);
            } else if (rJob == 3) { // bowman
                possibleStat.add(EnchantStat.DEX);
            } else if (rJob == 4 || rJob == 5) { // thief/pirate
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30};
            stat = new int[]{1, 1, 2, 3, 5};
        } else {
            armor = true;
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if (rJob == 1) { // warrior
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if (rJob == 2) { // mage
                possibleStat.add(EnchantStat.INT);
            } else if (rJob == 3) { // bowman
                possibleStat.add(EnchantStat.DEX);
            } else if (rJob == 4 || rJob == 5) { // thief/pirate
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30};
            stat = new int[]{1, 2, 3, 5, 7};
        }
        for (int i = 0; i < chances.length; i++) { // 4 scroll tiers for weapons
            int tier = i + plusFromLevel;
            TreeMap<EnchantStat, Integer> stats = new TreeMap<>();
            for (EnchantStat es : possibleStat) {
                int val;
                if (es.isAttackType()) {
                    val = attStats[tier];
                } else if (es.isHpOrMp()){
                    val = stat[tier] * 50;
                } else {
                    val = stat[tier];
                }
                if (val != 0) {
                    stats.put(es, val);
                }
            }
            if (armor) {
                stats.put(EnchantStat.PDD, armorDef[tier] + stats.getOrDefault(EnchantStat.PDD, 0));
                stats.put(EnchantStat.MDD, armorDef[tier] + stats.getOrDefault(EnchantStat.MDD, 0));
                stats.put(EnchantStat.MHP, armorHp[tier] + stats.getOrDefault(EnchantStat.MHP, 0));
            }
            String title = chances[i] + "% ";
            title += bp == BodyPart.WEAPON ? "Attack" : "Stat";
            ScrollUpgradeInfo sui = new ScrollUpgradeInfo(i, title, SpellTraceScrollType.Normal, 0, stats,
                    BASE_ST_COST + rLevel * (tier + 1), chances[i]);
            scrolls.add(sui);
        }
        if (equip.hasUsedSlots()) {
            scrolls.add(new ScrollUpgradeInfo(4, "Innocence Scroll 30%",
                    SpellTraceScrollType.Innocence, 0, new TreeMap<>(), INNOCENCE_ST_COST, 30));
            scrolls.add(new ScrollUpgradeInfo(5, "Clean Slate Scroll 5%",
                    SpellTraceScrollType.CleanSlate, 0, new TreeMap<>(), CLEAN_SLATE_ST_COST, 5));
        }
        return scrolls;
    }
}
