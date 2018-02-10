package packet;

import client.character.skills.LarknessManager;
import connection.OutPacket;
import enums.ChatMsgColour;
import handling.OutHeader;

/**
 * Created on 1/2/2018.
 */
public class UserLocal {

    public static OutPacket chatMsg(ChatMsgColour colour, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.CHAT_MSG);

        outPacket.encodeShort(colour.getVal());
        outPacket.encodeString(msg);

        return outPacket;
    }

    public static OutPacket jaguarActive(boolean active) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_ACTIVE);

        outPacket.encodeByte(active);

        return outPacket;
    }

    public static OutPacket jaguarSkill(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_SKILL);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket incLarknessReponse(LarknessManager larknessManager) {
        OutPacket outPacket = new OutPacket(OutHeader.INC_LARKNESS_RESPONSE);

        larknessManager.encode(outPacket);

        return outPacket;
    }
}
