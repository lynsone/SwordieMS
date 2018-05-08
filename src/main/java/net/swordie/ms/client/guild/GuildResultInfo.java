package net.swordie.ms.client.guild;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.GuildResultType;

/**
 * Created on 3/21/2018.
 */
public interface GuildResultInfo {

    GuildResultType getType();

    void encode(OutPacket outPacket);
}
