package client.guild;

import connection.OutPacket;
import enums.GuildResultType;

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
