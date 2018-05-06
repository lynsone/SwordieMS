package packet;

import client.character.NpcScriptInfo;
import client.character.ScriptManager;
import connection.OutPacket;
import enums.NpcMessageType;
import enums.ScriptType;
import handling.OutHeader;

/**
 * Created on 2/19/2018.
 */
public class ScriptMan {

    public static OutPacket scriptMessage(ScriptManager sm, NpcMessageType nmt) {
        NpcScriptInfo nsi = sm.getNpcScriptInfo();

        OutPacket outPacket = new OutPacket(OutHeader.SCRIPT_MESSAGE);

        outPacket.encodeByte(nsi.getSpeakerType());
        outPacket.encodeInt(sm.getParentIDByScriptType(ScriptType.NPC));
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
            case SayOk:
            case SayNext:
            case SayPrev:
                if((nsi.getParam() & 4) != 0) {
                    outPacket.encodeInt(nsi.getOverrideSpeakerTemplateID());
                }
                outPacket.encodeString(nsi.getText());
                outPacket.encodeByte(nmt.isPrevPossible());
                outPacket.encodeByte(nmt.isNextPossible());
                outPacket.encodeInt(nmt.getDelay());
                break;
            case AskMenu:
            case AskYesNo:
                if((nsi.getParam() & 4) != 0) {
                    outPacket.encodeInt(nsi.getOverrideSpeakerTemplateID());
                }
                outPacket.encodeString(nsi.getText());
                break;
            case SayImage:
                String[] images = nsi.getImages();
                outPacket.encodeByte(images.length);
                for(String image : images) {
                    outPacket.encodeString(image);
                }
                break;
            case AskText:
                if((nsi.getParam() & 4) != 0) {
                    outPacket.encodeInt(nsi.getOverrideSpeakerTemplateID());
                }
                outPacket.encodeString(nsi.getText());
                outPacket.encodeString(nsi.getDefaultText());
                outPacket.encodeShort(nsi.getMin());
                outPacket.encodeShort(nsi.getMax());
                break;
            case AskNumber:
                outPacket.encodeString(nsi.getText());
                outPacket.encodeInt(nsi.getDefaultNumber());
                outPacket.encodeInt(nsi.getMin());
                outPacket.encodeInt(nsi.getMax());
                break;
            case InitialQuiz:
                outPacket.encodeByte(nsi.getType());
                if(nsi.getType() != 1) {
                    outPacket.encodeString(nsi.getTitle());
                    outPacket.encodeString(nsi.getProblemText());
                    outPacket.encodeString(nsi.getHintText());
                    outPacket.encodeInt(nsi.getMin());
                    outPacket.encodeInt(nsi.getMax());
                    outPacket.encodeInt(nsi.getTime()); // in seconds
                }
                break;
            case InitialSpeedQuiz:
                outPacket.encodeByte(nsi.getType());
                if(nsi.getType() != 1) {
                    outPacket.encodeInt(nsi.getQuizType());
                    outPacket.encodeInt(nsi.getAnswer());
                    outPacket.encodeInt(nsi.getCorrectAnswers());
                    outPacket.encodeInt(nsi.getRemaining());
                    outPacket.encodeInt(nsi.getTime()); // in seconds
                }
                break;
            case ICQuiz:
                outPacket.encodeByte(nsi.getType());
                if(nsi.getType() != 1) {
                    outPacket.encodeString(nsi.getText());
                    outPacket.encodeString(nsi.getHintText());
                    outPacket.encodeInt(nsi.getTime()); // in seconds
                }
                break;
            case AskAvatar:
                outPacket.encodeByte(nsi.isAngelicBuster());
                outPacket.encodeByte(nsi.isZeroBeta());
                outPacket.encodeString(nsi.getText());
                outPacket.encodeByte(0); // Some int array, no clue what it stands for
                break;
        }

        return outPacket;
    }

}
