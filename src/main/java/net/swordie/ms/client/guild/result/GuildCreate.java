package net.swordie.ms.client.guild.result;

import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/21/2018.
 */
public class GuildCreate implements GuildResultInfo {

    public Guild guild;

    public GuildCreate() {
    }

    public GuildCreate(Guild guild) {
        this.guild = guild;
    }

    @Override
    public GuildType getType() {
        return GuildType.Res_CreateNewGuild_Done;
    }

    @Override
    public void encode(OutPacket outPacket) {
        guild.encode(outPacket);
    }
}
