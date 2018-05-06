package client.guild;

import connection.OutPacket;
import enums.GuildResultType;

/**
 * Created on 3/22/2018.
 */
public class GuildUpdate implements GuildResultInfo {
    public Guild guild;

    public GuildUpdate(Guild guild) {
        this.guild = guild;
    }

    @Override
    public GuildResultType getType() {
        return GuildResultType.Update;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(guild != null);
        outPacket.encodeByte(guild != null); // ??
        guild.encode(outPacket);
        outPacket.encodeInt(0); // aGuildNeedPoint
    }
}
