package net.swordie.ms.connection.packet;

import net.swordie.ms.client.life.Drop;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.DropEnterType;
import net.swordie.ms.enums.DropLeaveType;
import net.swordie.ms.enums.ItemGrade;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;

/**
 * Created on 2/21/2018.
 */
public class DropPool {

    public static OutPacket dropEnterField(Drop drop, Position dropPosition, int charID) {
        return DropPool.dropEnterField(drop, DropEnterType.FLOATING, 100, 0, 100,
                (byte) 2, dropPosition, charID, dropPosition, 0, true, (short) 0, false,
                (byte) 0, 0, false);
    }

    public static OutPacket dropEnterField(Drop drop, Position dropPositionFrom, Position dropPositionTo, int charID) {
        return DropPool.dropEnterField(drop, DropEnterType.FLOATING, 100, 0, 100,
                (byte) 2, dropPositionTo, charID, dropPositionFrom, 0, true, (short) 0, false,
                (byte) 0, 0, false);
    }

    public static OutPacket dropEnterField(Drop drop, DropEnterType dropEnterType, int rand, int dropMotionType,
                                           int dropSpeed, byte ownType, Position dropPos, int sourceID,
                                           Position tempPos, int delay, boolean unkBool, short fallingVY,
                                           boolean fadeInEffect, byte makeType, int collisionPickup, boolean prepareCollisionPickUp) {
        OutPacket outPacket = new OutPacket(OutHeader.DROP_ENTER_FIELD);

        outPacket.encodeByte(drop.getDropType().getVal());
        outPacket.encodeByte(dropEnterType.getVal());
        outPacket.encodeInt(drop.getObjectId());

        outPacket.encodeByte(drop.isMoney());
        outPacket.encodeInt(dropMotionType); // 2 = Horizontal, 4 = Vertical movement
        outPacket.encodeInt(dropSpeed);
        outPacket.encodeInt(rand);
        outPacket.encodeInt(drop.getItem() == null ? drop.getMoney() : drop.getItem().getItemId());
        outPacket.encodeInt(drop.getOwnerID());
        outPacket.encodeByte(ownType); // 3 = high drop
        outPacket.encodePosition(dropPos);
        outPacket.encodeInt(sourceID);
        byte enterType = dropEnterType.getVal();
        if(enterType != 2) {
            outPacket.encodePosition(tempPos);
            outPacket.encodeInt(delay);
        }
        outPacket.encodeByte(drop.isExplosiveDrop());
        // TODO: Fake money: if ( !v8->bIsMoney || (v34 = v8->nInfo == 0, bFakeMoney = 1, !v34) )
        if(!drop.isMoney()) {
            FileTime expireTime = drop.getExpireTime();
            if(expireTime == null) {
                expireTime = FileTime.getFileTimeFromType(FileTime.Type.PERMANENT);
            }
            outPacket.encodeFT(expireTime);
        }
        outPacket.encodeByte(drop.isByPet());
        outPacket.encodeByte(unkBool);
        outPacket.encodeShort(fallingVY);
        outPacket.encodeByte(fadeInEffect);
        outPacket.encodeByte(makeType);
        outPacket.encodeInt(collisionPickup); // decode4, but is bCollisionPickUp?
        outPacket.encodeByte(ItemGrade.getHiddenGradeByVal(drop.getItemGrade()).getVal());
        outPacket.encodeByte(prepareCollisionPickUp);

        return outPacket;
    }

    public static OutPacket dropLeaveField(int dropID, int charID) {
        return dropLeaveField(DropLeaveType.CHAR_PICKUP_1, charID, dropID, (short) 0, 0, 0);
    }

    public static OutPacket dropLeaveField(DropLeaveType dropLeaveType, int pickupID, int dropID, short delay, int petID, int key) {
        OutPacket outPacket = new OutPacket(OutHeader.DROP_LEAVE_FIELD);

        outPacket.encodeByte(dropLeaveType.getVal());
        outPacket.encodeInt(dropID);

        switch(dropLeaveType) {
            case CHAR_PICKUP_1:
            case CHAR_PICKUP_2:
                outPacket.encodeInt(pickupID);
                break;
            case PET_PICKUP:
                outPacket.encodeInt(pickupID);
                outPacket.encodeInt(petID);
            case DELAYED_PICKUP:
                outPacket.encodeShort(delay);
                break;
            case ABSORB:
                outPacket.encodeInt(key);
                break;
        }

        return outPacket;
    }
}
