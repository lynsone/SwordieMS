package enums;

import java.util.Arrays;

/**
 * Created on 3/18/2018.
 */
public enum MobSkillID {
    // Got these from Odin
    Unk(-1),
    PowerUp(100),
    MagicUp(101),
    PGuardUp(102),
    MGuardUp(103),
    ConsumeForHeal(105),
    PowerUp2(110),
    MagicUp2(111),
    PDDUp2(112),
    MDDUp2(113),
    Heal(114),
    Speed(115),
    Dispel(127),
    Banish(129),
    Mist(131),
    PImmune(140),
    MImmune(141),
    Immune(142),
    PReflect(143),
    MReflect(144),
    Reflect(145),
    PowerUp3(150),
    MagicUp3(151),
    PDDUp3(152),
    MDDUp3(153),
    Acc(154),
    Eva(155),
    Speed2(156),
    Seal(157),
    Tornado(173),
    Teleport(184),
    Summon(200),
    ;

    private int val;

    MobSkillID(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static MobSkillID getMobSkillIDByVal(int val) {
        return Arrays.stream(values()).filter(m -> m.getVal() == val).findFirst().orElse(Unk);
    }
}
