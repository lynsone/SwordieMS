package packet;

import client.character.Char;
import client.life.movement.Movement;
import connection.OutPacket;
import handling.OutHeader;
import util.Position;

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

    public static OutPacket move(Char chr, int encodedGatherDuration, Position oldPos, Position oldVPos,
                                 byte exceptionObject, List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_MOVE);

        outPacket.encodeInt(chr.getId());
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeInt(encodedGatherDuration);
        for(Movement m : movements) {
            m.encode(outPacket);
        }

        return outPacket;
    }
}
