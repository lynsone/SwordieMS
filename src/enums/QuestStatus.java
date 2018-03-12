package enums;

/**
 * Created on 3/2/2018.
 */
public enum QuestStatus {
    NOT_STARTED(0),
    STARTED(1),
    COMPLETE(2)
    ;

    private byte val;

    QuestStatus(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
