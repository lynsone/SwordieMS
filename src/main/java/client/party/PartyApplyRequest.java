package client.party;

import client.character.Char;
import connection.OutPacket;
import enums.PartyResultType;

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
