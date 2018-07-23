package net.swordie.ms.client.guild.updates;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.result.GuildResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.guild.result.GuildType;

/**
 * Created on 3/21/2018.
 */
public class GuildUpdateMemberLevelAndJob implements GuildResultInfo {

    public int guildID;
    public Char charToUpdate;

    @Override
    public GuildType getType() {
        return GuildType.Res_ChangeLevelOrJob;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(charToUpdate.getId());
        outPacket.encodeInt(charToUpdate.getLevel());
        outPacket.encodeInt(charToUpdate.getJob());
    }
}
