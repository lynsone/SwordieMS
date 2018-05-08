package net.swordie.ms.enums;

/**
 * Created on 2/21/2018.
 */
public enum DropEnterType {
    DEFAULT(0),
    FLOATING(1),
    NO_ANIMATION(2), // ?
    UNK(3),
    ;

    private byte val;

    DropEnterType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
