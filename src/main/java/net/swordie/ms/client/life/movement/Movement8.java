package net.swordie.ms.client.life.movement;

import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class Movement8 extends MovementBase {
    public Movement8(InPacket inPacket, byte command) {
        super();
        this.command = command;
        this.position = new Position(0, 0);

        this.stat = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodeByte(getStat());
    }
}
