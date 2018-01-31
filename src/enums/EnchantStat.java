package enums;

/**
 * Created on 1/25/2018.
 */
public enum EnchantStat{
    PAD(0x1),
    MAD(0x2),
    STR(0x4),
    DEX(0x8),
    INT(0x10),
    LUK(0x20),
    PDD(0x40),
    MDD(0x80),
    MHP(0x100),
    MMP(0x200),
    ACC(0x400),
    EVA(0x800),
    JUMP(0x1000),
    SPEED(0x2000),
    ;
    private int val;

    EnchantStat(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
