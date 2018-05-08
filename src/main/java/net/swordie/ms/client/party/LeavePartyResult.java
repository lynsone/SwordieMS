package net.swordie.ms.client.party;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class LeavePartyResult implements PartyResultInfo {

    public Party party;
    public PartyMember leaver;
    public boolean partyExists;
    public boolean wasExpelled;


    @Override
    public PartyResultType getType() {
        return PartyResultType.Leave;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(party.getId());
        outPacket.encodeInt(leaver.getCharID());
        outPacket.encodeByte(partyExists);
        if(partyExists) {
            outPacket.encodeByte(wasExpelled);
            outPacket.encodeString(leaver.getCharName());
            party.encode(outPacket);
        }
    }
}
