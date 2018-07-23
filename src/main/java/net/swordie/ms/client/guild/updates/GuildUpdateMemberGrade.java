package net.swordie.ms.client.guild.updates;

import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.result.GuildResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.guild.result.GuildType;

/**
 * Created on 3/21/2018.
 */
public class GuildUpdateMemberGrade implements GuildResultInfo {

    public int guildID;
    public GuildMember gm;

    @Override
    public GuildType getType() {
        return GuildType.Res_SetMemberGrade_Done;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(gm.getCharID());
        outPacket.encodeByte(gm.getGrade());
    }
}
