package enums;

/**
 * Created on 1/18/2018.
 */
public enum ChatType {
    USER(0),
    GM(1),

    ;

    private byte val;

    ChatType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
