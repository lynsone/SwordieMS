package enums;

import java.util.Arrays;

/**
 * Created on 3/19/2018.
 */
public enum PartyRequestResultType {
    DeclinePartyApply(43),
    AcceptPartyApply(44),
    DeclinePartyInvite(73),
    AcceptPartyInvite(74),
    ;

    private byte val;

    PartyRequestResultType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static PartyRequestResultType getByVal(byte type) {
        return Arrays.stream(values()).filter(i -> i.getVal() == type).findFirst().orElse(null);
    }
}
