package net.swordie.ms.enums;

/**
 * Created on 6/8/2018.
 */
public enum EliteState {
    NORMAL(0),
    IDK(1),
    ELITE_BOSS(2),
    BONUS_STAGE(3),
    ;

    private int val;

    EliteState(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
