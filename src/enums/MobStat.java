package enums;

public enum MobStat {
    PAD(0x80000000, 0),
    PDR(0x40000000, 0),
    MAD(0x20000000, 0),
    MDR(0x10000000, 0),
    ACC(0x8000000, 0),
    EVA(0x4000000, 0),
    Speed(0x2000000, 0),
    Stun(0x1000000, 0),

    Freeze(0x800000, 0),
    Poison(0x400000, 0),
    Seal(0x200000, 0),
    Darkness(0x100000, 0),
    PowerUp(0x80000, 0),
    MagicUp(0x40000, 0),
    PGuardUp(0x20000, 0),
    MGuardUp(0x10000, 0),

    PImmune(0x8000, 0),
    MImmune(0x4000, 0),
    Web(0x2000, 0),
    HardSkin(0x1000, 0),
    Ambush(0x800, 0),
    Venom(0x400, 0),
    Blind(0x200, 0),
    SealSkill(0x100, 0),

    Dazzle(0x80, 0),
    PCounter(0x40, 0),
    MCounter(0x20, 0),
    RiseByToss(0x10, 0),
    BodyPressure(0x8, 0),
    Weakness(0x4, 0),
    Showdown(0x2, 0),
    MagicCrash(0x1, 0),

    DamagedElemAttr(0x80000000, 1),
    Dark(0x40000000, 1),
    Mystery(0x20000000, 1),
    AddDamParty(0x10000000, 1),
    HitCriDamR(0x8000000, 1),
    Fatality(0x4000000, 1),
    Lifting(0x2000000, 1),
    DeadlyCharge(0x1000000, 1),

    Smite(0x800000, 1),
    AddDamSkill(0x400000, 1),
    Incizing(0x200000, 1),
    DodgeBodyAttack(0x100000, 1),
    DebuffHealing(0x80000, 1),
    AddDamSkill2(0x40000, 1),
    BodyAttack(0x20000, 1),
    TempMoveAbility(0x10000, 1),

    FixDamRBuff(0x8000, 1),
    ElementDarkness(0x4000, 1),
    AreaInstallByHit(0x2000, 1),
    BMageDebuff(0x1000, 1),
    JaguarProvoke(0x800, 1),
    JaguarBleeding(0x400, 1),
    DarkLightning(0x200, 1),
    PinkBeanFlowerPot(0x100, 1),

    BattlePvPHelenaMark(0x80, 1),
    PsychicLock(0x40, 1),
    PsychicLockCoolTime(0x20, 1),
    PsychicGroundMark(0x10, 1),
    PowerImmune(0x80, 1),
    PsychicForce(0x4, 1),
    MultiPMDR(0x2, 1),
    ElementResetBySummon(0x1, 1),

    BahamutLightElemAddDam(0x80000000, 2), // 0000 0000 | 0000 0001 | 0000 0000 | 0000 0000
    BossPropPlus(0x40000000, 2),
    MultiDamSkill(0x20000000, 2),
    RWLiftPress(0x10000000, 2),
    RWChoppingHammer(0x8000000, 2),
    TimeBomb(0x4000000, 2),
    Treasure(0x2000000, 2),
    AddEffect(0x1000000, 2),

    Invincible(0x800000, 2),
    Explosion(0x400000, 2),
    HangOver(0x200000, 2),
    BurnedInfo(0x40000, 2), // 0x1000 -> 0x400
    InvincibleBalog(0x40000, 2), // 0x800
    ExchangeAttack(0x40000, 2),
    ExtraBuffStat(0x20000, 2),
    LinkTeam(0x10000, 2),

    SoulExplosion(0x8000, 2), // { Might be wrong --v
    SeperateSoulP(0x4000, 2),
    SeperateSoulC(0x2000, 2),
    Ember(0x1000, 2),
    TrueSight(0x800, 2),
    Laser(0x400, 2),
    ;

    private int val, position;

    MobStat(int val, int position) {
        this.val = val;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getVal() {
        if(this == BurnedInfo) {
            return 0x40000;
        }
        return val;
    }

    public boolean isMovementAffectingStat() {
        switch(this) {
            case Speed:
            case Stun:
            case Freeze:
            case RiseByToss:
            case Lifting:
            case Smite:
            case TempMoveAbility:
            case RWLiftPress:
                return true;
            default:
                return false;
        }
    }
}
