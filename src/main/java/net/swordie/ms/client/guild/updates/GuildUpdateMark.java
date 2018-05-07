package net.swordie.ms.client.guild.updates;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.guild.result.GuildResultType;

/**
 * Created on 3/22/2018.
 */
public class GuildUpdateMark implements GuildResultInfo {

    public Guild guild;

    public GuildUpdateMark(Guild guild) {
        this.guild = guild;
    }

    @Override
    public GuildResultType getType() {
        return GuildResultType.UpdateGuildMark;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guild.getId());
        outPacket.encodeShort(guild.getMarkBg());
        outPacket.encodeShort(guild.getMarkBgColor());
        outPacket.encodeShort(guild.getMark());
        outPacket.encodeShort(guild.getMarkColor());
    }
}
