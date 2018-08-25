package net.swordie.ms.enums;

/**
 * Created on 1/6/2018.
 */
public enum MoveAbility {
    STATIC(0),
    FOLLOW(1),
    ROAM_AROUND(2),
    FLY_AROUND_CHAR(3),
    FLY_AWAY(4),
    FLY_AROUND_POSITION(5),
    THROW(6),
    FIND_NEAREST_MOB(7),
    SHADOW_SERVANT(8),
    JAGUAR(11),

    ;

    private byte val;

    MoveAbility(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
