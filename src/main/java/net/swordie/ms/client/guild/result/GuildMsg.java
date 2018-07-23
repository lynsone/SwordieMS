package net.swordie.ms.client.guild.result;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/21/2018.
 */
public class GuildMsg implements GuildResultInfo {

    public GuildType type;

    public GuildMsg() {
    }

    public GuildMsg(GuildType type) {
        this.type = type;
    }

    @Override
    public GuildType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {

    }
}
