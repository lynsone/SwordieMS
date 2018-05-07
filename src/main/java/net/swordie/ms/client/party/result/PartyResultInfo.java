package net.swordie.ms.client.party.result;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/19/2018.
 */
public interface PartyResultInfo {

    PartyResultType getType();

    void encode(OutPacket outPacket);

}
