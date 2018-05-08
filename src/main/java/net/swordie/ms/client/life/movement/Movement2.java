package net.swordie.ms.client.life.movement;


import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class Movement2 extends MovementBase {
    public Movement2(InPacket inPacket, byte command) {
        super();
        this.command = command;

        short x = inPacket.decodeShort();
        short y = inPacket.decodeShort();
        position = new Position(x, y);

        short xv = inPacket.decodeShort();
        short xy = inPacket.decodeShort();
        vPosition = new Position(xv, xy);

        fh = inPacket.decodeShort();

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getPosition());
        outPacket.encodePosition(getVPosition());
        outPacket.encodeShort(getFh());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }
}
