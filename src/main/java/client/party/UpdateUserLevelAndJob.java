package client.party;

import client.character.Char;
import connection.OutPacket;
import enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class UpdateUserLevelAndJob implements PartyResultInfo {

    public Char updatedUser;

    @Override
    public PartyResultType getType() {
        return PartyResultType.UpdateUserLevelAndJob;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(updatedUser.getId());
        outPacket.encodeInt(updatedUser.getLevel());
        outPacket.encodeInt(updatedUser.getJob());
    }
}
