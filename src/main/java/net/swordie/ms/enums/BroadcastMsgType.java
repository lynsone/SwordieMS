package net.swordie.ms.enums;

/**
 * Created by Asura on 16-6-2018.
 */
public enum BroadcastMsgType {
    Notice(0),
    PopUpMessage(1),
    DarkBlueOnLightBlue(2),
    Megaphone(3),

    PartyChat(5),

    ItemMegaphone(8), // Holds item info
    ItemMegaphoneNoItem(9),
    TripleMegaphone(10),

    WhiteYellow(17), // Holds item info

    Yellow(21), // Holds item info

    MegaphoneNoMessage(23),
    BalloonMessage(24),
    ;

    private byte val;

    BroadcastMsgType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
