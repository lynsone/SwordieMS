package enums;

/**
 * Created on 2/21/2018.
 */
public enum DropLeaveType {
    IDK_0(0),
    IDK_1(1),
    CHAR_PICKUP_1(2),
    CHAR_PICKUP_2(3),
    DELAYED_PICKUP(4),
    PET_PICKUP(5),
    IDK_2(6),
    IDK_3(7), // CAnimationDisplayer::RegisterAbsorbItemAnimationJP ?
    ;

    private byte val;

    DropLeaveType(int  val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
