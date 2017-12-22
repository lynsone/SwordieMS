package enums;

/**
 * Created on 12/22/2017.
 */
public enum EquipBaseStat {
    ruc(0x1, 1),
    cuc(0x2, 1),
    iStr(0x4, 1),
    iDex(0x8, 1),
    iInt(0x10, 1),
    iLuk(0x20, 1),
    iMaxHP(0x40, 1),
    iMaxMP(0x80, 1),
    iPAD(0x100, 1),
    iMAD(0x200, 1),
    iPDD(0x400, 1),
    iMDD(0x800, 1),
    iACC(0x1000, 1),
    iEVA(0x2000, 1),
    iCraft(0x4000, 1),
    iSpeed(0x8000, 1),
    iJump(0x10000, 1),
    attribute(0x20000, 1),
    levelUpType(0x40000, 1),
    level(0x80000, 1),
    exp(0x100000, 1),
    durability(0x200000, 1),
    iuc(0x400000, 1),
    iPvpDamage(0x800000, 1),
    iReduceReq(0x1000000, 1),
    specialAttribute(0x2000000, 1),
    durabilityMax(0x4000000, 1),
    iIncReq(0x8000000, 1),
    growthEnchant(0x10000000, 1),
    psEnchant(0x20000000, 1),
    bdr(0x40000000, 1),
    imdr(0x80000000, 1),
    damR(0x1, 2),
    statR(0x2, 2),
    cuttable(0x4, 2),
    exGradeOption(0x8, 2),
    itemState(0x10, 2),

    ;
    private int val, pos;

    EquipBaseStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    public int getVal() {
        return val;
    }

    public int getPos() {
        return pos;
    }
}
