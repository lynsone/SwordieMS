package net.swordie.ms.client.party.request;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.result.PartyResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.party.result.PartyResultType;

/**
 * Created on 3/20/2018.
 */
public class PartyApplyRequest implements PartyResultInfo {

    public int partyID;
    public Char applier;

    @Override
    public PartyResultType getType() {
        return PartyResultType.PartyApplyRequestPurple;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(partyID);
        outPacket.encodeString(applier.getName());
        outPacket.encodeInt(applier.getLevel());
        outPacket.encodeInt(applier.getJob());
        outPacket.encodeInt(applier.getAvatarData().getCharacterStat().getSubJob());
    }
}
