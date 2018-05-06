package enums;

/**
 * Created on 1/6/2018.
 */
public enum AssistType {
    PASSIVE(0),
    ATTACKING(1),
    BUFFING(2),
    ;

    private byte val;

    AssistType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
