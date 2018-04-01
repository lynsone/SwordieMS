package client.friend;

import connection.OutPacket;
import enums.FriendType;

/**
 * Created on 3/31/2018.
 */
public interface FriendResult {

    FriendType getType();

    void encode(OutPacket outPacket);
}
