package net.swordie.ms.client.party;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class UpdatePartyResult implements PartyResultInfo {

    public Party party;

    @Override
    public PartyResultType getType() {
        return PartyResultType.UserUpdate;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(party.getId());
        party.encode(outPacket);
    }
}
