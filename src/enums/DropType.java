package enums;

/**
 * Created on 2/21/2018.
 */
public enum DropType {

    MONEY(0),
    ITEM(1),
    ;

    private byte val;

    DropType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
