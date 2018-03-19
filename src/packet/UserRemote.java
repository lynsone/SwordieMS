package packet;

import client.character.Char;
import client.life.movement.Movement;
import connection.OutPacket;
import handling.OutHeader;

import java.util.List;

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

    public static OutPacket move(Char chr, List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_MOVE);

        outPacket.encodeInt(chr.getId());
        for(Movement m : movements) {
            m.encode(outPacket);
        }

        return outPacket;
    }
}
