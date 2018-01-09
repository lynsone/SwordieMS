package enums;

/**
 * Created on 1/7/2018.
 */
public enum ForceAtomEnum {
    PHANTOM_CARD_1(1, 1),
    PHANTOM_CARD_2(1, 2),
    KAISER_WEAPON_THROW_1(2, 1),
    KAISER_WEAPON_THROW_2(2, 2),
    KAISER_WEAPON_THROW_MORPH_1(2, 3),
    KAISER_WEAPON_THROW_MORPH_2(2, 4),
    AB_ORB(3, 1),
    DA_ORB(3, 2),
    NETHER_SHIELD(3, 3),
    RABBIT_ORB(3, 4), // Shade I think
    FLAMING_RABBIT_ORB(3, 5), // same for 4, but insta disappear (byMob?)
    XENON_ROCKET_1(5, 1),
    XENON_ROCKET_2(5, 2),
    XENON_ROCKET_3(6, 1),
    WA_ARROW_1(7, 1),
    WA_ARROW_2(7, 2),
    WA_ARROW_HYPER(8, 1),
    KANNA_ORB_1(9, 1), // to char
    KANNA_ORB_2(9, 2),
    BM_ARROW(10, 1),
    GREEN_ORB(11, 1),
    FLYING_MESO(12, 1),
    BLUE_RABBIT_ORB(13, 1),
    RED_RABBIT_ORB(13, 2),
    YELLOW_ORB_TO_SELF(14, 1),
    NIGHT_WALKER_BAT(15, 1),
    NIGHT_WALKER_FROM_MOB(16, 1),
    ORBITAL_FLAME_1(17, 1),
    ORBITAL_FLAME_3(17, 2),
    ORBITAL_FLAME_2(17, 3),
    ORBITAL_FLAME_4(17, 4),
    STAR_1(18, 1),
    STAR_2(18, 2),
    KINESIS_ORB(18, 3), // ?
    KINESIS_SMALL_ORB(18, 4), // ?
    YELLOW_BLACK_ORB(18, 6),
    PURPLE_BLACK_ORB(18, 10),
    MECH_ROCKET(19, 1),
    MECH_MEGA_ROCKET_1(20, 1),
    MECH_MEGA_ROCKET_2(20, 2),
    KINESIS_ORB_REAL(22, 1),
    FAST_STAR_ORB(24, 1),
    TRANSPARENT_AB_ORB(25, 1), // same for 26, but that disappears

    ;

    private int forceAtomType;
    private int inc;

    ForceAtomEnum(int forceAtomType, int inc) {
        this.forceAtomType = forceAtomType;
        this.inc = inc;
    }

    public int getForceAtomType() {
        return forceAtomType;
    }

    public void setForceAtomType(int forceAtomType) {
        this.forceAtomType = forceAtomType;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }
}
