package client.party;

import connection.OutPacket;
import enums.PartyResultType;

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
