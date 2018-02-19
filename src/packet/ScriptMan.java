package packet;

import client.character.NpcScriptInfo;
import client.character.ScriptManager;
import connection.OutPacket;
import enums.NpcMessageType;
import handling.OutHeader;

/**
 * Created on 2/19/2018.
 */
public class ScriptMan {

    public static OutPacket scriptMessage(ScriptManager sm, NpcMessageType nmt) {
        NpcScriptInfo nsi = sm.getNpcScriptInfo();

        OutPacket outPacket = new OutPacket(OutHeader.SCRIPT_MESSAGE);

        outPacket.encodeByte(nsi.getSpeakerType());
        outPacket.encodeInt(sm.getParentID());
        int overrideTemplate = nsi.getOverrideSpeakerTemplateID();
        outPacket.encodeByte(overrideTemplate > 0);
        if(overrideTemplate > 0) {
            outPacket.encodeInt(overrideTemplate);
        }
        outPacket.encodeByte(nmt.getVal());
        outPacket.encodeByte(nsi.getParam());
        outPacket.encodeByte(nsi.getColor());

        switch(nmt) {
            case Say:
            case AskMenu:
                if((nsi.getParam() & 4) != 0) {
                    outPacket.encodeInt(nsi.getOverrideSpeakerTemplateID());
                }
                outPacket.encodeString(nsi.getText());
                outPacket.encodeByte(false);
                outPacket.encodeByte(true);
                outPacket.encodeInt(nmt.getDelay());
                break;
        }

        return outPacket;
    }

}
