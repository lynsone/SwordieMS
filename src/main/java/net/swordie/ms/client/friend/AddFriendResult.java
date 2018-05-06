package net.swordie.ms.client.friend;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.FriendType;

/**
 * Created on 3/31/2018.
 */
public class AddFriendResult implements FriendResult {

    private Friend friend;

    public AddFriendResult(Friend friend) {
        this.friend = friend;
    }

    @Override
    public FriendType getType() {
        return FriendType.FriendRes_NotifyChange_FriendInfo;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(friend.getFriendID());
        outPacket.encodeInt(friend.getFriendAccountID());
        friend.encode(outPacket);
    }
}
