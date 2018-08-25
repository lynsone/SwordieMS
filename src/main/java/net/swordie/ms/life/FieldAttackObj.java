package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.DropPool;
import net.swordie.ms.connection.packet.FieldAttackObjPool;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.util.Position;

import java.util.concurrent.TimeUnit;

/**
 * @author Sjonnie
 * Created on 8/19/2018.
 */
public class FieldAttackObj extends Life implements Encodable {

    private int ownerID;
    private int reserveID;

    public FieldAttackObj(int templateId) {
        super(templateId);
    }

    public FieldAttackObj(int skillID, int ownerID, Position position, boolean flip) {
        super(skillID);
        this.ownerID = ownerID;
        setPosition(position);
        setFlip(flip ? 1 : 0);
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getObjectId());
        outPacket.encodeInt(getTemplateId());
        outPacket.encodeInt(getOwnerID());
        outPacket.encodeInt(getReserveID());
        outPacket.encodePositionInt(getPosition());
        outPacket.encodeByte(getFlip());
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getReserveID() {
        return reserveID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        onlyChar.write(FieldAttackObjPool.objCreate(this));
    }
}
