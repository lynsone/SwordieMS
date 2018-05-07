package net.swordie.ms.life.movement;

import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class Movement3 extends MovementBase {
    public Movement3(InPacket inPacket, byte command) {
        super();
        this.command = command;

        short xv = inPacket.decodeShort();
        short xy = inPacket.decodeShort();
        vPosition = new Position(xv, xy);

        if (command == 21 || command == 22) {
            footStart = inPacket.decodeShort();
        }

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getVPosition());
        if (getCommand() == 21 || getCommand() == 22) {
            outPacket.encodeShort(getFootStart());
        }
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }
}
