package net.swordie.ms.client.party.result;

import net.swordie.ms.client.party.Party;
import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/19/2018.
 */
public class PartyJoinResult implements PartyResultInfo {

    public Party party;
    public String joinerName;

    @Override
    public PartyResultType getType() {
        return PartyResultType.Join;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(party.getId());
        outPacket.encodeString(joinerName);
        party.encode(outPacket);
    }
}
