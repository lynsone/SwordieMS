package net.swordie.ms.client.guild;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.GuildResultType;

/**
 * Created on 3/23/2018.
 */
public class GuildLeaveResult implements GuildResultInfo {

    private int guildID;
    private int expelledID;
    private String expelledName;
    private boolean expel;

    public GuildLeaveResult(int guildID, int expelledID, String expelledName, boolean expel) {
        this.guildID = guildID;
        this.expelledID = expelledID;
        this.expelledName = expelledName;
        this.expel = expel;
    }

    @Override
    public GuildResultType getType() {
        return expel ? GuildResultType.Expel : GuildResultType.Quit;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(expelledID);
        outPacket.encodeString(expelledName);
    }
}
