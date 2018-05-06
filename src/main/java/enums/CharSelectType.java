package enums;

/**
 * Created on 12/13/2017.
 */
public enum CharSelectType {

    IN_ALBA(39),
    CLIENT_ALREADY_RUNNING(55),
    HAVING_TROUBLE_LOGGING_IN(67),

    ;

    private byte val;

    CharSelectType(byte val) {
        this.val = val;
    }

    CharSelectType(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
