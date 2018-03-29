package constants;

import client.character.items.Equip;
import client.character.items.ItemOption;
import enums.InvType;
import enums.ItemGrade;
import loaders.ItemData;
import loaders.ItemInfo;
import org.apache.log4j.LogManager;
import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static enums.InvType.EQUIP;

/**
 * Created on 12/12/2017.
 */
public class ItemConstants {
    static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    public static final int THIRD_LINE_CHANCE = 50;

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

    public static boolean isAccessory(int itemID) {
        return (itemID >= 1010000 && itemID < 1040000) || (itemID >= 1122000 && itemID < 1153000) ||
                (itemID >= 1112000 && itemID < 1113000) || (itemID >= 1670000 && itemID < 1680000);
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

    public static boolean isArmor(int itemID) {
        return !isAccessory(itemID) && !isWeapon(itemID);
    }

    public static List<ItemOption> getOptionsByEquip(Equip equip, boolean bonus) {
        int id = equip.getItemId();
        List<ItemOption> data = ItemData.getItemOptions();
        for(ItemOption io : data) {
            // TODO: Debug data, remove once prime line logic is completed (chance for prime/lower tier pot)
            ItemGrade ioGrade = ItemGrade.getGradeByOption(io.getId());
            ItemGrade itemGrade = ItemGrade.getGradeByVal(equip.getBaseGrade());
            boolean jwz = io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade());
            boolean zwj = io.isBonus() == bonus;
            int i = 0;
            i += 3;
        }
        List<ItemOption> res = data.stream().filter(
                io -> io.getOptionType() == 0 &&
                io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                .collect(Collectors.toList());
        if (isWeapon(id)) {
            res.addAll(data.stream().filter(
                    io -> io.getOptionType() == 10
                    &&  io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus
            ).collect(Collectors.toList()));
        } else {
            res.addAll(data.stream().filter(
                    io -> io.getOptionType() == 11
                    && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                    .collect(Collectors.toList()));
            if (isAccessory(id)) {
                res.addAll(data.stream().filter(
                        io -> io.getOptionType() == 40
                        && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                        .collect(Collectors.toList()));
            } else {
                res.addAll(data.stream().filter(
                        io -> io.getOptionType() == 20
                        && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                        .collect(Collectors.toList()));
                if (isHat(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 51
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isTop(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 52
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isBottom(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 53
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isOverall(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 52
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 53
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isGlove(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 54
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isShoe(id)) {
                    res.addAll(data.stream().filter(
                            io -> io.getOptionType() == 55
                            && io.hasMatchingGrade(bonus ? equip.getBonusGrade() : equip.getBaseGrade()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
            }
        }
        return res.stream().filter(io -> io.getReqLevel() <= equip.getrLevel()).collect(Collectors.toList());
    }

    public static List<Integer> getWeightedOptionsByEquip(Equip equip, boolean bonus) {
        List<Integer> res = new ArrayList<>();
        List<ItemOption> data = getOptionsByEquip(equip, bonus);
        for(ItemOption io : data) {
            for (int i = 0; i < io.getWeight(); i++) {
                res.add(io.getId());
            }
        }
        return res;
    }

    public static int getRandomOption(Equip equip, boolean bonus) {
        List<Integer> data = getWeightedOptionsByEquip(equip, bonus);
        return data.get(Util.getRandom(data.size()));
    }

    public static int getTierUpChance(int id) {
        int res = 0;
        switch(id) {
            case 5062009: // Red cube
            case 5062500: // Bonus potential cube
                res = 30;
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
}
