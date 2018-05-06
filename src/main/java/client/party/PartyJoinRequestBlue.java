package client.party;

import connection.OutPacket;
import enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class PartyJoinRequestBlue implements PartyResultInfo {

    public int partyID;
    public PartyMember inviter;

    @Override
    public PartyResultType getType() {
        return PartyResultType.PartyJoinRequestBlue;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(partyID); // key for the window, so you don't get spammed
        outPacket.encodeString(inviter.getCharName());
        outPacket.encodeInt(inviter.getLevel());
        outPacket.encodeInt(inviter.getJob());
        outPacket.encodeInt(inviter.getSubSob());
    }
}
