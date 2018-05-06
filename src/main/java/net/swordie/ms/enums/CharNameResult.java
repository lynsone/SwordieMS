package net.swordie.ms.enums;

/**
 * Created on 11/17/2017.
 */
public enum CharNameResult {
    OK(0),
    ALREADY_IN_USE(1),
    INVALID_NAME(2),
    UNAVAILABLE_DUE_TO_ITEM_PURCHASE(3)
    ;

    private byte val;

    CharNameResult(byte val) {
        this.val = val;
    }

    CharNameResult(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
