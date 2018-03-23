package enums;

import java.util.Arrays;

/**
 * Created on 3/21/2018.
 */
public enum GuildRequestType {
    AcceptJoinRequest(1),
    Create(4),
    JoinRequest(7),
    Expel(12),
    SetGuildGrades(18),
    SetMemberGrade(19),
    SetMark(20),
    NoblesseSkillButton(44),
    GuildFind(45),
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
