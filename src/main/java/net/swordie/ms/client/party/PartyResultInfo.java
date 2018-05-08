package net.swordie.ms.client.party;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public interface PartyResultInfo {

    PartyResultType getType();

    void encode(OutPacket outPacket);

}
