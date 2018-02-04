package packet;

import client.character.Char;
import connection.OutPacket;
import handling.OutHeader;

/**
 * Created on 2/3/2018.
 */
public class UserRemote {
    public static OutPacket setActiveNickItem(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_NICK_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getNickItem());

        return outPacket;
    }
}
