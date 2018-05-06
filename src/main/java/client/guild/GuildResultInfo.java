package client.guild;

import connection.OutPacket;
import enums.GuildResultType;

/**
 * Created on 3/21/2018.
 */
public interface GuildResultInfo {

    GuildResultType getType();

    void encode(OutPacket outPacket);
}
