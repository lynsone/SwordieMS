package net.swordie.ms.client.guild.updates;

import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.result.GuildResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.guild.result.GuildResultType;

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
