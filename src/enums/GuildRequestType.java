package enums;

import java.util.Arrays;

/**
 * Created on 3/21/2018.
 */
public enum GuildRequestType {
    Create(4),
    SetGuildRankTitles(18),
    SetEmblem(20),
    NoblesseSkillButton(44),
    GuildSearch(45),
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
