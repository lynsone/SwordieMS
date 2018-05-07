package net.swordie.ms.client.party.updates;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.result.PartyResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.party.result.PartyResultType;

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
