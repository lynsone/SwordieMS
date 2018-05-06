package net.swordie.ms.client.life.movement;

import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class Movement6 extends MovementBase {
    public Movement6(InPacket inPacket, byte command) {
        super();
        this.command = command;
        this.position = new Position(0, 0);

        short xv = inPacket.decodeShort();
        short xy = inPacket.decodeShort();
        vPosition = new Position(xv, xy);

        // I'm not sure about this, it' needs testing. <- mushy
        // Should be fallStart (foothold fall start)?
        footStart = inPacket.decodeShort();

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getVPosition());
        outPacket.encodeShort(getFootStart());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }
}
