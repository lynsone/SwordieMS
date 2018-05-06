package client.friend;

import connection.OutPacket;
import enums.FriendType;

/**
 * Created on 3/31/2018.
 */
public class FriendResultMsg implements FriendResult {

    private FriendType type;
    private String name;

    public FriendResultMsg(FriendType type) {
        this.type = type;
    }

    public FriendResultMsg(FriendType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public FriendType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeString(name);
    }
}
