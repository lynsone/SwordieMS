package client.party;

import connection.OutPacket;
import enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public interface PartyResultInfo {

    PartyResultType getType();

    void encode(OutPacket outPacket);

}
