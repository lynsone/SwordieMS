package client.party;

import client.character.Char;
import connection.OutPacket;
import enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class UpdateMemberLoggedIn implements PartyResultInfo {

    public Char updatedChar;

    @Override
    public PartyResultType getType() {
        return PartyResultType.UpdateMemberLoggedIn;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(updatedChar.getId());
        outPacket.encodeByte(updatedChar.isOnline());
    }
}
