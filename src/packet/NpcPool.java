package packet;

import client.life.Npc;
import connection.OutPacket;
import handling.OutHeader;

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

    public static OutPacket npcMove(int objectID, byte oneTimeAction, byte chatIdx, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.NPC_MOVE);

        outPacket.encodeInt(objectID);
        outPacket.encodeByte(oneTimeAction);
        outPacket.encodeByte(chatIdx);
        outPacket.encodeInt(duration);

        return outPacket;
    }
}
