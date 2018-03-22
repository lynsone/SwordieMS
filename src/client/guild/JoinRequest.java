package client.guild;

import client.character.Char;
import connection.OutPacket;
import enums.GuildResultType;

/**
 * Created on 3/21/2018.
 */
public class JoinRequest implements GuildResultInfo {

    public Char chr;

    @Override
    public GuildResultType getType() {
        return GuildResultType.JoinRequest;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(chr.getGuild().getId());
        outPacket.encodeString(chr.getName());
        outPacket.encodeInt(chr.getLevel());
        outPacket.encodeInt(chr.getJob());
        outPacket.encodeInt(chr.getSubJob());
    }
}
