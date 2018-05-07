package net.swordie.ms.client.character.skills;

import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/13/2018.
 */
public class PsychicLockBall {
    public boolean success;
    public int localKey;
    public Mob mob;
    public int psychicLockKey;
    public short stuffID;
    public short usableCount;
    public byte posRelID;
    public Position start;
    public Position rel;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(success);
        outPacket.encodeInt(localKey);
        outPacket.encodeInt(psychicLockKey);
        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeShort(stuffID);
        outPacket.encodeInt((int) mob.getMaxHp());
        outPacket.encodeInt((int) mob.getHp());
        outPacket.encodeByte(posRelID);
        outPacket.encodePositionInt(start);
        outPacket.encodePositionInt(rel);
    }
}
