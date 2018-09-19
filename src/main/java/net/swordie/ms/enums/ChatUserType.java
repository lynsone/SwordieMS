package net.swordie.ms.enums;

/**
 * Created on 1/18/2018.
 */
public enum ChatUserType {
    USER(0),
    GM(1),

    ;

    private byte val;

    ChatUserType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
