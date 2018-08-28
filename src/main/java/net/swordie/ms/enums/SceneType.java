package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by Asura on 28-8-2018.
 */
public enum SceneType {
    FieldEffect(0),

    Warp(2),

    playBGM(5),
    unknown(6),
    // more but unknown as of now.
    ;

    private int val;

    SceneType(int val) {this.val = val;}

    public int getVal() {return val;}

    public static SceneType getByVal(int val) {
        return Arrays.stream(values()).filter(st -> st.getVal() == val).findAny().orElse(null);
    }
}
