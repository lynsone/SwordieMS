package net.swordie.ms.client.party.result;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/19/2018.
 */
public class LeaderShipTransferResult implements PartyResultInfo {

    public int newLeaderID;
    public boolean reasonIsDisconnect;

    @Override
    public PartyResultType getType() {
        return PartyResultType.LeadershipTransfer;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(newLeaderID);
        outPacket.encodeByte(reasonIsDisconnect);
    }
}
