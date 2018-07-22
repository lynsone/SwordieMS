package net.swordie.ms.client.party.updates;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.result.PartyResultInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.party.result.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class UpdateMemberLoggedIn implements PartyResultInfo {

    public Char updatedChar;

    public UpdateMemberLoggedIn(Char chr) {
        this.updatedChar = chr;
    }

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
