package net.swordie.ms.client.party.result;

import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/19/2018.
 */
public class CreatePartyResult implements PartyResultInfo {

    public Party party;

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(party.getId());
        party.getTownPortal().encode(outPacket);
        PartyMember leader = party.getPartyLeader();
        outPacket.encodeByte(leader.isOnline());
        outPacket.encodeByte(party.isAppliable());
        outPacket.encodeString(party.getName());
    }

    @Override
    public PartyResultType getType() {
        return PartyResultType.Create;
    }
}
