package client.life.movement;

import connection.OutPacket;
import util.Position;

/**
 * Created on 1/2/2018.
 * These classes + children/parents are basically the same as Mushy, credits to @MaxCloud.
 */
public interface Movement {
    void encode(OutPacket outPacket);
    Position getPosition();

    byte getCommand();

    byte getMoveAction();

    byte getForcedStop();

    byte getStat();

    short getFh();

    short getFootStart();

    short getDuration();

    Position getVPosition();

    Position getOffset();
}
