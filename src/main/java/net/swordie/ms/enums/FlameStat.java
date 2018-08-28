package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created on 28/8/2018.
 */
public enum FlameStat {
    FlameSTR(0),
    FlameDEX(1),
    FlameINT(2),
    FlameLUK(3),
    FlameSTRDEX(4),
    FlameSTRINT(5),
    FlameSTRLUK(6),
    FlameDEXINT(7),
    FlameDEXLUK(8),
    FlameINTLUK(9),
    FlameATT(10),
    FlameMATT(11),
    FlameDEF(12),
    FlameHP(13),
    FlameMP(14),
    FlameSpeed(15),
    FlameJump(16),
    FlameAllStats(17),
    FlameBossDamage(18),
    FlameTotalDamage(19),
    FlameEquipLevelReduction(20);

    private int val;

    FlameStat(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static FlameStat getByVal(int val) {
        return Util.getFromCollectionWithPred(Arrays.asList(values()), csat -> csat.getVal() == val);
    }
}
