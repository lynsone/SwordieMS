package net.swordie.ms.connection.packet;

import net.swordie.ms.client.life.Npc;
import net.swordie.ms.client.life.movement.Movement;
import net.swordie.ms.client.life.movement.Movement3;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;

import java.util.List;

/**
 * Created on 2/19/2018.
 */
public class NpcPool {

    public static OutPacket npcEnterField(Npc npc) {
        OutPacket outPacket = new OutPacket(OutHeader.NPC_ENTER_FIELD);

        outPacket.encodeInt(npc.getObjectId());
        outPacket.encodeInt(npc.getTemplateId());
        npc.encode(outPacket);

        return outPacket;
    }

    public static OutPacket npcLeaveField(Npc npc) {
        OutPacket outPacket = new OutPacket(OutHeader.NPC_LEAVE_FIELD);

        outPacket.encodeInt(npc.getObjectId());

        return outPacket;
    }

    public static OutPacket npcChangeController(Npc npc, boolean controller) {
        OutPacket outPacket = new OutPacket(OutHeader.NPC_CHANGE_CONTROLLER);

        outPacket.encodeByte(controller);
        outPacket.encodeInt(npc.getObjectId());
        outPacket.encodeInt(npc.getTemplateId());
        npc.encode(outPacket);

        return outPacket;
    }

    public static OutPacket npcMove(int objectID, byte oneTimeAction, byte chatIdx, int duration, boolean move,
                                    Position oldPos, Position oldVPos, int encodedGatherDuration,
                                    List<Movement> movements, byte keyPadState) {
        OutPacket outPacket = new OutPacket(OutHeader.NPC_MOVE);

        outPacket.encodeInt(objectID);
        outPacket.encodeByte(oneTimeAction);
        outPacket.encodeByte(chatIdx);
        outPacket.encodeInt(duration);

        if (move) {
            outPacket.encodePosition(oldPos);
            outPacket.encodePosition(oldVPos);
            outPacket.encodeInt(encodedGatherDuration);
            outPacket.encodeByte(movements.size());
            for (Movement m : movements) {
                m.encode(outPacket);
            }
            outPacket.encodeByte(keyPadState);
        }

        return outPacket;
    }
}
