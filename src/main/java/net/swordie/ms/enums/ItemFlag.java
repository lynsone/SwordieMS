package net.swordie.ms.enums;

/**
 * Created on 4/28/2018.
 */
public enum ItemFlag {
    LOCKED(0x1),
    UNTRADABLE_AFTER_TRANSACTION(0x2), // requires tradeblock = true
    UNTRADABLE(0x4),
    ONCE_IN_ACCOUNT(0x8),
    ;

    private short val;

    ItemFlag(int val) {
        this.val = (short) val;
    }

    public short getVal() {
        return val;
    }
}
