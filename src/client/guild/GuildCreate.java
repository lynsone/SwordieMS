package client.guild;

import connection.OutPacket;
import enums.GuildResultType;

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
