package client.life.movement;

import connection.InPacket;
import connection.OutPacket;
import util.Position;

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
