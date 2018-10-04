package net.swordie.ms.enums;

/**
 * Created on 1/6/2018.
 */
public enum AssistType {
    Passive(0),
    Attacking(1),
    Buffing(2),
    // 3-6 unknown
    Manual(6);

    private byte val;

    AssistType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
