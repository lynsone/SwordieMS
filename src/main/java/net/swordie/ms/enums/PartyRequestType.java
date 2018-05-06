package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 3/19/2018.
 */
public enum PartyRequestType {
    Create(1),
    Leave(2),
    Invite(4),
    Expel(6),
    ChangeLeadership(7),
    InviteRequest(8),
    ;

    private byte val;

    PartyRequestType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static PartyRequestType getByVal(byte type) {
        return Arrays.stream(values()).filter(prt -> prt.getVal() == type).findFirst().orElse(null);
    }
}
