package client.life.movement;

import connection.InPacket;
import connection.OutPacket;
import util.Position;

/**
 * Created on 1/2/2018.
 */
public class Movement7 extends MovementBase {
    public Movement7(InPacket inPacket, byte command) {
        super();
        this.command = command;

        short x = inPacket.decodeShort();
        short y = inPacket.decodeShort();
        position = new Position(x, y);

        short vx = inPacket.decodeShort();
        short vy = inPacket.decodeShort();
        vPosition = new Position(vx, vy);

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getPosition());
        outPacket.encodePosition(getVPosition());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }
}
