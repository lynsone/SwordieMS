package client.character;

import enums.NpcMessageType;

/**
 * Created on 2/19/2018.
 */
public class NpcScriptInfo {
    private byte speakerType = 4; // ?
    private int overrideSpeakerTemplateID;
    private byte param;
    private byte color;
    private String text;
    private NpcMessageType messageType;

    public byte getSpeakerType() {
        return speakerType;
    }

    public void setSpeakerType(byte speakerType) {
        this.speakerType = speakerType;
    }

    public int getOverrideSpeakerTemplateID() {
        return overrideSpeakerTemplateID;
    }

    public void setOverrideSpeakerTemplateID(int overrideSpeakerTemplateID) {
        this.overrideSpeakerTemplateID = overrideSpeakerTemplateID;
    }

    public byte getParam() {
        return param;
    }

    public void setParam(byte param) {
        this.param = param;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessageType(NpcMessageType messageType) {
        this.messageType = messageType;
    }

    public NpcMessageType getMessageType() {
        return messageType;
    }
}
