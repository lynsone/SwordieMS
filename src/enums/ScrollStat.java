package enums;

import java.util.Arrays;

/**
 * Created on 1/9/2018.
 */
public enum ScrollStat {
    success,
    incSTR,
    incDEX,
    incINT,
    incLUK,
    incPAD,
    incMAD,
    incPDD,
    incMDD,
    incACC,
    incEVA,
    incMHP,
    incMMP,
    incSpeed,
    incJump,
    incIUC,
    incPERIOD,
    incReqLevel,
    reqRUC,
    randOption,
    randStat,
    tuc,
    speed,
    forceUpgrade,
    cursed,
    maxSuperiorEqp,
    ;
    public static ScrollStat getScrollStatByString(String name) {
        return Arrays.stream(values()).filter(ss -> ss.toString().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
