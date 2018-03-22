package enums;

import java.util.Arrays;

/**
 * Created on 3/21/2018.
 */
public enum GuildRequestType {
    Create(4),
    ;

    private byte val;

    GuildRequestType(int val) {
        this.val = (byte) val;
    }

    public static GuildRequestType getTypeByVal(int val) {
        return Arrays.stream(values()).filter(grt -> grt.getVal() == val).findAny().orElse(null);
    }

    public byte getVal() {
        return val;
    }
}
