package net.swordie.ms.client.guild;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.GuildResultType;

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
    public GuildResultType getType() {
        return GuildResultType.Create;
    }

    @Override
    public void encode(OutPacket outPacket) {
        guild.encode(outPacket);
    }
}
