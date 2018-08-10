package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by Asura on 10-8-2018.
 */
public enum MiniRoomType {
    PlaceItem(0),
    PlaceItem_2(1),
    PlaceItem_3(2),
    PlaceItem_4(3),

    SetMesos(4),
    SetMesos_2(5),
    SetMesos_3(6),
    SetMesos_4(7),

    CancelTrade(28),
    ;

    private byte val;

    MiniRoomType(int val) {this.val = (byte) val;}

    public byte getVal() {return val;}

    public static MiniRoomType getByVal(byte val) {
        return Arrays.stream(values()).filter(mrt -> mrt.getVal() == val).findAny().orElse(null);
    }
}
