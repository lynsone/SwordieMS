package net.swordie.ms.client.guild.result;

import net.swordie.ms.connection.OutPacket;

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
    public GuildType getType() {
        return expel ? GuildType.Res_KickGuild_Done : GuildType.Res_WithdrawGuild_Done;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(expelledID);
        outPacket.encodeString(expelledName);
    }
}
