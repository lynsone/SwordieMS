package enums;

/**
 * Created on 1/6/2018.
 */
public enum MoveAbility {
    STATIC(0),
    FOLLOW(1),
    ;

    private byte val;

    MoveAbility(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
