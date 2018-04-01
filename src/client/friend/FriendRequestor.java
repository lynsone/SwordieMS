package client.friend;

import connection.OutPacket;

/**
 * Created on 3/31/2018.
 */
public class FriendRequestor {
    private int charID;
    private int accID;

    public FriendRequestor() {
    }

    public FriendRequestor(int charID) {
        this.charID = charID;
    }

    public FriendRequestor(int charID, int accID) {
        this.charID = charID;
        this.accID = accID;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCharID());
        outPacket.encodeInt(getAccID());
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }
}
