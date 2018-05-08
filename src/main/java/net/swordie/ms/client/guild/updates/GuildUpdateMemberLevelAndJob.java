package net.swordie.ms.client.guild.updates;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.result.GuildResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.guild.result.GuildResultType;

/**
 * Created on 3/21/2018.
 */
public class GuildUpdateMemberLevelAndJob implements GuildResultInfo {

    public int guildID;
    public Char charToUpdate;

    @Override
    public GuildResultType getType() {
        return GuildResultType.UpdateMemberInfo;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(charToUpdate.getId());
        outPacket.encodeInt(charToUpdate.getLevel());
        outPacket.encodeInt(charToUpdate.getJob());
    }
}
