package client.party;

import connection.OutPacket;
import enums.PartyResultType;

/**
 * Created on 3/19/2018.
 */
public class PartyMessageResult implements PartyResultInfo {

    public String message;
    public PartyResultType msgType;

    public PartyMessageResult() {
    }

    public PartyMessageResult(PartyResultType msgType) {
        this.msgType = msgType;
    }

    public PartyMessageResult(String message, PartyResultType msgType) {
        this.message = message;
        this.msgType = msgType;
    }

    @Override
    public PartyResultType getType() {
        return msgType;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeString(message);
    }
}
