package client.character.skills;

import org.apache.log4j.LogManager;

/**
 * Created on 1/2/2018.
 */
public enum CharacterTemporaryStat {
    IndiePAD(0x80000000, 0),
    IndieMAD(0x40000000, 0),
    IndiePDD(0x20000000, 0),
    IndieMDD(0x10000000, 0),
    IndieMHP(0x8000000, 0),
    IndieMHPR(0x4000000, 0),
    IndieMMP(0x2000000, 0),
    IndieMMPR(0x1000000, 0), // 8

    IndieACC(0x800000, 0),
    IndieEVA(0x400000, 0),
    IndieJump(0x200000, 0),
    IndieSpeed(0x100000, 0),
    IndieAllStat(0x80000, 0),
    IndieDodgeCriticalTime(0x40000, 0),
    IndieEXP(0x20000, 0),
    IndieBooster(0x10000, 0), // 16

    IndieFixedDamageR(0x8000, 0),
    PyramidStunBuff(0x4000, 0),
    PyramidFrozenBuff(0x2000, 0),
    PyramidFireBuff(0x1000, 0),
    PyramidBonusDamageBuff(0x800, 0),
    IndieRelaxEXP(0x400, 0),
    IndieSTR(0x200, 0),
    IndieDEX(0x100, 0), // 24

    IndieINT(0x80, 0),
    IndieLUK(0x40, 0),
    IndieDamR(0x20, 0),
    IndieScriptBuff(0x10, 0),
    IndieMDF(0x8, 0),
    IndieMaxDamageOver(0x4, 0),
    IndieAsrR(0x2, 0),
    IndieTerR(0x1, 0), // 32

    IndieCr(0x80000000, 1),
    IndiePDDR(0x40000000, 1),
    IndieCrMax(0x20000000, 1),
    IndieBDR(0x10000000, 1),
    IndieStatR(0x8000000, 1),
    IndieStance(0x4000000, 1),
    IndieIgnoreMobpdpR(0x2000000, 1),
    IndieEmpty(0x1000000, 1), // 40

    IndiePADR(0x800000, 1),
    IndieMADR(0x400000, 1),
    IndieCrMaxR(0x200000, 1),
    IndieEVAR(0x100000, 1),
    IndieMDDR(0x80000, 1),
    IndieDrainHP(0x40000, 1),
    IndiePMdR(0x20000, 1),
    IndieMaxDamageOverR(0x10000, 1), // 48

    IndieForceJump(0x8000, 1),
    IndieForceSpeed(0x4000, 1),
    IndieQrPointTerm(0x2000, 1),
    IndieUNK1(0x1000, 1),
    IndieUNK2(0x800, 1),
    IndieUNK3(0x400, 1),
    IndieUNK4(0x200, 1),
    IndieUNK5(0x100, 1), // 56

    IndieStatCount(0x80, 1),
    PAD(0x40, 1),
    PDD(0x20, 1),
    MAD(0x10, 1),
    MDD(0x8, 1),
    ACC(0x4, 1),
    EVA(0x2, 1),
    Craft(0x1, 1), // 64

    Speed(0x80000000, 2),
    Jump(0x40000000, 2),
    MagicGuard(0x20000000, 2),
    DarkSight(0x10000000, 2),
    Booster(0x8000000, 2),
    PowerGuard(0x4000000, 2),
    MaxHP(0x2000000, 2),
    MaxMP(0x1000000, 2), // 72

    Invincible(0x800000, 2),
    SoulArrow(0x400000, 2),
    Stun(0x200000, 2),
    Poison(0x100000, 2),
    Seal(0x80000, 2),
    Darkness(0x40000, 2),
    ComboCounter(0x20000, 2),
    WeaponCharge(0x10000, 2), // 80

    HolySymbol(0x8000, 2),
    MesoUp(0x4000, 2),
    ShadowPartner(0x2000, 2),
    PickPocket(0x1000, 2),
    MesoGuard(0x800, 2),
    Thaw(0x400, 2),
    Weakness(0x200, 2),
    Curse(0x100, 2),

    Slow(0x80, 2),
    Morph(0x40, 2),
    Regen(0x20, 2),
    BasicStatUp(0x10, 2),
    Stance(0x8, 2),
    SharpEyes(0x4, 2),
    ManaReflection(0x2, 2),
    Attract(0x1, 2),

    NoBulletConsume(0x80000000, 3),
    Infinity(0x40000000, 3),
    AdvancedBless(0x20000000, 3),
    IllusionStep(0x10000000, 3),
    Blind(0x8000000, 3),
    Concentration(0x4000000, 3),
    BanMap(0x2000000, 3),
    MaxLevelBuff(0x1000000, 3),

    MesoUpByItem(0x800000, 3),
    Ghost(0x400000, 3),
    Barrier(0x200000, 3),
    ReverseInput(0x100000, 3),
    ItemUpByItem(0x80000, 3),
    RespectPImmune(0x40000, 3),
    RespectMImmune(0x20000, 3),
    DefenseAtt(0x10000, 3),

    DefenseState(0x8000, 3),
    DojangBerserk(0x4000, 3),
    DojangInvincible(0x2000, 3),
    DojangShield(0x1000, 3),
    SoulMasterFinal(0x800, 3),
    WindBreakerFinal(0x400, 3),
    ElementalReset(0x200, 3),
    HideAttack(0x100, 3),

    EventRate(0x80, 3),
    ComboAbilityBuff(0x40, 3),
    ComboDrain(0x20, 3),
    ComboBarrier(0x10, 3),
    BodyPressure(0x8, 3),
    RepeatEffect(0x4, 3),
    ExpBuffRate(0x2, 3),
    StopPortion(0x1, 3),

    StopMotion(0x80000000, 4),
    Fear(0x40000000, 4),
    HiddenPieceOn(0x20000000, 4),
    MagicShield(0x10000000, 4),
    MagicResistance(0x8000000, 4),
    SoulStone(0x4000000, 4),
    Flying(0x2000000, 4),
    Frozen(0x1000000, 4),

    AssistCharge(0x800000, 4),
    Enrage(0x400000, 4),
    DrawBack(0x200000, 4),
    NotDamaged(0x100000, 4),
    FinalCut(0x80000, 4),
    HowlingAttackDamage(0x40000, 4),
    BeastFormDamageUp(0x20000, 4),
    Dance(0x10000, 4),

    EMHP(0x8000, 4),
    EMMP(0x4000, 4),
    EPAD(0x2000, 4),
    EMAD(0x1000, 4),
    EPDD(0x800, 4),
    EMDD(0x400, 4),
    Guard(0x200, 4),
    Cyclone(0x100, 4),

    HowlingCritical(0x80, 4),
    HowlingMaxMP(0x40, 4),
    HowlingDefence(0x20, 4),
    HowlingEvasion(0x10, 4),
    Conversion(0x8, 4),
    Revive(0x4, 4),
    PinkbeanMinibeenMove(0x2, 4),
    Sneak(0x1, 4),

    Mechanic(0x80000000, 5),
    BeastFormMaxHP(0x40000000, 5),
    Dice(0x20000000, 5),
    BlessingArmor(0x10000000, 5),
    DamR(0x8000000, 5),
    TeleportMasteryOn(0x4000000, 5),
    CombatOrders(0x2000000, 5),
    Beholder(0x1000000, 5),

    DispelItemOption(0x800000, 5),
    Inflation(0x400000, 5),
    OnixDivineProtection(0x200000, 5),
    Web(0x100000, 5),
    Bless(0x80000, 5),
    TimeBomb(0x40000, 5),
    DisOrder(0x20000, 5),
    Thread(0x10000, 5),

    Team(0x8000, 5),
    Explosion(0x4000, 5),
    BuffLimit(0x2000, 5),
    STR(0x1000, 5),
    INT(0x800, 5),
    DEX(0x400, 5),
    LUK(0x200, 5),
    DispelItemOptionByField(0x100, 5),

    DarkTornado(0x80, 5),
    PVPDamage(0x40, 5),
    PvPScoreBonus(0x20, 5),
    PvPInvincible(0x10, 5),
    PvPRaceEffect(0x8, 5),
    WeaknessMdamage(0x4, 5),
    Frozen2(0x2, 5),
    PVPDamageSkill(0x1, 5),

    AmplifyDamage(0x80000000, 6),
    IceKnight(0x40000000, 6),
    Shock(0x20000000, 6),
    InfinityForce(0x10000000, 6),
    IncMaxHP(0x8000000, 6),
    IncMaxMP(0x4000000, 6),
    HolyMagicShell(0x2000000, 6),
    KeyDownTimeIgnore(0x1000000, 6),

    ArcaneAim(0x800000, 6),
    MasterMagicOn(0x400000, 6),
    AsrR(0x200000, 6),
    TerR(0x100000, 6),
    DamAbsorbShield(0x80000, 6),
    DevilishPower(0x40000, 6),
    Roulette(0x20000, 6),
    SpiritLink(0x10000, 6),

    AsrRByItem(0x8000, 6),
    Event(0x4000, 6),
    CriticalBuff(0x2000, 6),
    DropRate(0x1000, 6),
    PlusExpRate(0x800, 6),
    ItemInvincible(0x400, 6),
    Awake(0x200, 6),
    ItemCritical(0x100, 6),

    ItemEvade(0x80, 6),
    Event2(0x40, 6),
    VampiricTouch(0x20, 6),
    DDR(0x10, 6),
    IncCriticalDamMin(0x8, 6),
    IncCriticalDamMax(0x4, 6),
    IncTerR(0x2, 6),
    IncAsrR(0x1, 6),

    DeathMark(0x80000000, 7),
    UsefulAdvancedBless(0x40000000, 7),
    Lapidification(0x20000000, 7),
    VenomSnake(0x10000000, 7),
    CarnivalAttack(0x8000000, 7),
    CarnivalDefence(0x4000000, 7),
    CarnivalExp(0x2000000, 7),
    SlowAttack(0x1000000, 7),

    PyramidEffect(0x800000, 7),
    KillingPoint(0x400000, 7),
    HollowPointBullet(0x200000, 7),
    KeyDownMoving(0x100000, 7),
    IgnoreTargetDEF(0x80000, 7),
    ReviveOnce(0x40000, 7),
    Invisible(0x20000, 7),
    EnrageCr(0x10000, 7),

    EnrageCrDamMin(0x8000, 7),
    Judgement(0x4000, 7),
    DojangLuckyBonus(0x2000, 7),
    PainMark(0x1000, 7),
    Magnet(0x800, 7),
    MagnetArea(0x400, 7),
    VampDeath(0x200, 7),
    BlessingArmorIncPAD(0x100, 7),

    KeyDownAreaMoving(0x80, 7),
    Larkness(0x40, 7),
    StackBuff(0x20, 7),
    BlessOfDarkness(0x10, 7),
    AntiMagicShell(0x8, 7),
    LifeTidal(0x4, 7),
    HitCriDamR(0x2, 7),
    SmashStack(0x1, 7),

    PartyBarrier(0x80000000, 8),
    ReshuffleSwitch(0x40000000, 8),
    SpecialAction(0x20000000, 8),
    VampDeathSummon(0x10000000, 8),
    StopForceAtomInfo(0x8000000, 8),
    SoulGazeCriDamR(0x4000000, 8),
    SoulRageCount(0x2000000, 8),
    PowerTransferGauge(0x1000000, 8),

    AffinitySlug(0x800000, 8),
    Trinity(0x400000, 8),
    IncMaxDamage(0x200000, 8),
    BossShield(0x100000, 8),
    MobZoneState(0x80000, 8),
    GiveMeHeal(0x40000, 8),
    TouchMe(0x20000, 8),
    Contagion(0x10000, 8),

    ComboUnlimited(0x8000, 8),
    SoulExalt(0x4000, 8),
    IgnorePCounter(0x2000, 8),
    IgnoreAllCounter(0x1000, 8),
    IgnorePImmune(0x800, 8),
    IgnoreAllImmune(0x400, 8),
    FinalJudgement(0x200, 8),
    IceAura(0x100, 8),

    FireAura(0x80, 8),
    VengeanceOfAngel(0x40, 8),
    HeavensDoor(0x20, 8),
    Preparation(0x10, 8),
    BullsEye(0x8, 8),
    IncEffectHPPotion(0x4, 8),
    IncEffectMPPotion(0x2, 8),
    BleedingToxin(0x1, 8),

    IgnoreMobDamR(0x80000000, 9),
    Asura(0x40000000, 9),
    FlipTheCoin(0x20000000, 9),
    UnityOfPower(0x10000000, 9),
    Stimulate(0x8000000, 9),
    ReturnTeleport(0x4000000, 9),
    DropRIncrease(0x2000000, 9),
    IgnoreMobpdpR(0x1000000, 9),

    BdR(0x800000, 9),
    CapDebuff(0x400000, 9),
    Exceed(0x200000, 9),
    DiabolikRecovery(0x100000, 9),
    FinalAttackProp(0x80000, 9),
    ExceedOverload(0x40000, 9),
    OverloadCount(0x20000, 9),
    BuckShot(0x10000, 9),

    FireBomb(0x8000, 9),
    HalfstatByDebuff(0x4000, 9),
    SurplusSupply(0x2000, 9),
    SetBaseDamage(0x1000, 9),
    EVAR(0x800, 9),
    NewFlying(0x400, 9),
    AmaranthGenerator(0x200, 9),
    OnCapsule(0x100, 9),

    CygnusElementSkill(0x80, 9),
    StrikerHyperElectric(0x40, 9),
    EventPointAbsorb(0x20, 9),
    EventAssemble(0x10, 9),
    StormBringer(0x8, 9),
    ACCR(0x4, 9),
    DEXR(0x2, 9),
    Albatross(0x1, 9),

    Translucence(0x80000000, 10),
    PoseType(0x40000000, 10),
    LightOfSpirit(0x20000000, 10),
    ElementSoul(0x10000000, 10),
    GlimmeringTime(0x8000000, 10),
    TrueSight(0x4000000, 10),
    SoulExplosion(0x2000000, 10),
    SoulMP(0x1000000, 10),

    FullSoulMP(0x800000, 10),
    SoulSkillDamageUp(0x400000, 10),
    ElementalCharge(0x200000, 10), // 0x200000
    Restoration(0x100000, 10),
    CrossOverChain(0x80000, 10),
    ChargeBuff(0x40000, 10),
    Reincarnation(0x20000, 10),
    KnightsAura(0x10000, 10),

    ChillingStep(0x8000, 10),
    DotBasedBuff(0x4000, 10),
    BlessEnsenble(0x2000, 10),
    ComboCostInc(0x1000, 10),
    ExtremeArchery(0x800, 10),
    NaviFlying(0x400, 10),
    QuiverCatridge(0x200, 10),
    AdvancedQuiver(0x100, 10),

    UserControlMob(0x80, 10),
    ImmuneBarrier(0x40, 10),
    ArmorPiercing(0x20, 10),
    ZeroAuraStr(0x10, 10),
    ZeroAuraSpd(0x8, 10),
    CriticalGrowing(0x4, 10),
    QuickDraw(0x2, 10),
    BowMasterConcentration(0x1, 10),

    TimeFastABuff(0x80000000, 11),
    TimeFastBBuff(0x40000000, 11),
    GatherDropR(0x20000000, 11),
    AimBox2D(0x10000000, 11),
    IncMonsterBattleCaptureRate(0x8000000, 11),
    CursorSniping(0x4000000, 11),
    DebuffTolerance(0x2000000, 11),
    DotHealHPPerSecond(0x1000000, 11),

    SpiritGuard(0x800000, 11),
    PreReviveOnce(0x400000, 11),
    SetBaseDamageByBuff(0x200000, 11),
    LimitMP(0x100000, 11),
    ReflectDamR(0x80000, 11),
    ComboTempest(0x40000, 11),
    MHPCutR(0x20000, 11),
    MMPCutR(0x10000, 11),

    SelfWeakness(0x8000, 11),
    ElementDarkness(0x4000, 11),
    FlareTrick(0x2000, 11),
    Ember(0x1000, 11),
    Dominion(0x800, 11),
    SiphonVitality(0x400, 11),
    DarknessAscension(0x200, 11),
    BossWaitingLinesBuff(0x100, 11),

    DamageReduce(0x80, 11),
    ShadowServant(0x40, 11),
    ShadowIllusion(0x20, 11),
    KnockBack(0x10, 11),
    AddAttackCount(0x8, 11),
    ComplusionSlant(0x4, 11),
    JaguarSummoned(0x2, 11),
    JaguarCount(0x1, 11),

    SSFShootingAttack(0x80000000, 12),
    DevilCry(0x40000000, 12),
    ShieldAttack(0x20000000, 12),
    BMageAura(0x10000000, 12),
    DarkLighting(0x8000000, 12),
    AttackCountX(0x4000000, 12),
    BMageDeath(0x2000000, 12),
    BombTime(0x1000000, 12),

    NoDebuff(0x800000, 12),
    BattlePvPMikeShield(0x400000, 12),
    BattlePvPMikeBugle(0x200000, 12),
    XenonAegisSystem(0x100000, 12),
    AngelicBursterSoulSeeker(0x80000, 12),
    HiddenPossession(0x40000, 12),
    NightWalkerBat(0x20000, 12),
    NightLordMark(0x10000, 12),

    WizardIgnite(0x8000, 12),
    FireBarrier(0x4000, 12),
    ChangeFoxMan(0x2000, 12),
    BattlePvPHelenaMark(0x1000, 12),
    BattlePvPHelenaWindSpirit(0x800, 12),
    BattlePvPLangEProtection(0x400, 12),
    BattlePvPLeeMalNyunScaleUp(0x200, 12),
    BattlePvPRevive(0x100, 12),

    PinkbeanAttackBuff(0x80, 12),
    PinkbeanRelax(0x40, 12),
    PinkbeanRollingGrade(0x20, 12),
    PinkbeanYoYoStack(0x10, 12),
    RandAreaAttack(0x8, 12),
    NextAttackEnhance(0x4, 12),
    AranBeyonderDamAbsorb(0x2, 12),
    AranCombotempastOption(0x1, 12),

    NautilusFinalAttack(0x80000000, 13),
    ViperTimeLeap(0x40000000, 13),
    RoyalGuardState(0x20000000, 13),
    RoyalGuardPrepare(0x10000000, 13),
    MichaelSoulLink(0x8000000, 13),
    MichaelStanceLink(0x4000000, 13),
    TriflingWhimOnOff(0x2000000, 13),
    AddRangeOnOff(0x1000000, 13),

    KinesisPsychicPoint(0x800000, 13),
    KinesisPsychicOver(0x400000, 13),
    KinesisPsychicShield(0x200000, 13),
    KinesisIncMastery(0x100000, 13),
    KinesisPsychicEnergeShield(0x80000, 13),
    BladeStance(0x40000, 13),
    DebuffActiveSkillHPCon(0x20000, 13),
    DebuffIncHP(0x10000, 13),

    BowMasterMortalBlow(0x8000, 13),
    AngelicBursterSoulResonance(0x4000, 13),
    Fever(0x2000, 13),
    IgnisRore(0x1000, 13),
    RpSiksin(0x800, 13),
    TeleportMasteryRange(0x400, 13),
    FixCoolTime(0x200, 13),
    IncMobRateDummy(0x100, 13),

    AdrenalinBoost(0x80, 13),
    AranSmashSwing(0x40, 13),
    AranDrain(0x20, 13),
    AranBoostEndHunt(0x10, 13),
    HiddenHyperLinkMaximization(0x8, 13),
    RWCylinder(0x4, 13),
    RWCombination(0x2, 13),
    RWMagnumBlow(0x1, 13),

    RWBarrier(0x80000000, 14),
    RWBarrierHeal(0x40000000, 14),
    RWMaximizeCannon(0x20000000, 14),
    RWOverHeat(0x10000000, 14),
    UsingScouter(0x8000000, 14),
    RWMovingEvar(0x4000000, 14),
    Stigma(0x2000000, 14),
    Unk0(0x1000000, 14),

    Unk1(0x800000, 14), // Takes effect of skill
    Unk2(0x400000, 14),
    Unk3(0x200000, 14),
    Unk4(0x100000, 14),
    Unk5(0x80000, 14), // 38
    HayatoStance(0x40000, 14),
    HayatoStanceBonus(0x20000, 14),
    EyeForEye(0x10000, 14),

    WillowDodge(0x8000, 14),
    Unk20(0x4000, 14),
    HayatoPAD(0x2000, 14),
    HayatoHPR(0x1000, 14),
    HayatoMPR(0x800, 14),
    HayatoBooster(0x400, 14),
    Unk70(0x200, 14), // Not a temp stat? Doesn't give buff
    Unk80(0x100, 14), // No buff
    
    Jinsoku(0x80, 14),
    HayatoCr(0x40, 14),
    HakuBlessing(0x20, 14), // Adds range
    HayatoBoss(0x10, 14), // n% boss att
    BattoujutsuAdvance(0x8, 14),
    Unk600(0x4, 14),
    Unk700(0x2, 14),
    BlackHeartedCurse(0x1, 14), // Kanna Hyper Shield

    BeastMode(0x80000000, 15), // BeastModes
    TeamRoar(0x40000000, 15), // BT Hyper
    Unk3000(0x20000000, 15),
    Unk4000(0x10000000, 15),
    Unk5000(0x8000000, 15),
    Unk6000(0x4000000, 15),
    Unk7000(0x2000000, 15),
    Unk8000(0x1000000, 15), // TableFlip

    Unk10000(0x800000, 15),
    Unk20000(0x400000, 15),
    Unk30000(0x200000, 15),
    Unk40000(0x100000, 15),
    EnergyCharged(0x80000, 15),
    DashSpeed(0x40000, 15),
    DashJump(0x20000, 15),
    RideVehicle(0x10000, 15),
    
    PartyBooster(0x8000, 15),
    GuidedBullet(0x4000, 15),
    Undead(0x2000, 15),
    RideVehicleExpire(0x1000, 15),
    
//    Unk50000(0x80000, 15),
//    Unk60000(0x40000, 15),
//    Unk70000(0x20000, 15),
//    Unk80000(0x10000, 15),
//
//    Unk100000(0x8000, 15),
//    Unk200000(0x4000, 15),
//    Unk300000(0x2000, 15),
//    Unk400000(0x1000, 15),
//    Unk500000(0x800, 15),
//    Unk600000(0x400, 15),
//    Unk700000(0x200, 15),
//    Unk800000(0x100, 15),
//
//    Unk1000000(0x80, 15),
//    Unk2000000(0x40, 15),
//    Unk3000000(0x20, 15),
//    Unk4000000(0x10, 15),
//    Unk5000000(0x8, 15),
//    Unk6000000(0x4, 15),
//    Unk7000000(0x2, 15),
//    Unk8000000(0x1, 15),
//
//    Unk10000000(0x80000000, 16),
//    Unk20000000(0x40000000, 16),
//    Unk30000000(0x20000000, 16),
//    Unk40000000(0x10000000, 16),
//    Unk50000000(0x8000000, 16),
//    Unk60000000(0x4000000, 16),
//    Unk70000000(0x2000000, 16),
//    Unk80000000(0x1000000, 16),
//
//    Unk100000000(0x800000, 16),
//    Unk200000000(0x400000, 16),
//    Unk300000000(0x200000, 16),
//    Unk400000000(0x100000, 16),
//    Unk500000000(0x80000, 16),
//    Unk600000000(0x40000, 16),
//    Unk700000000(0x20000, 16),
//    Unk800000000(0x10000, 16),
//
//    Unk1000000000(0x8000, 16),
//    Unk2000000000(0x4000, 16),
//    Unk3000000000(0x2000, 16),
//    Unk4000000000(0x1000, 16),
//    Unk5000000000(0x800, 16),
//    Unk6000000000(0x400, 16),
//    Unk7000000000(0x200, 16),
//    Unk8000000000(0x100, 16),
//
//    Unk10000000000(0x80, 16),
//    Unk20000000000(0x40, 16),
//    Unk30000000000(0x20, 16),
//    Unk40000000000(0x10, 16),
//    Unk50000000000(0x8, 16),
//    Unk60000000000(0x4, 16),
//    Unk70000000000(0x2, 16),
//    Unk80000000000(0x1, 16),
//    
//    EnergyCharged(0x1000000, 14),
//
//    DashSpeed(0x800000, 14),
//    DashJump(0x400000, 14),
//    RideVehicle(0x200000, 14),
//    PartyBooster(0x100000, 14),
//    GuidedBullet(0x80000, 14),
//    Undead(0x40000, 14),
//    RideVehicleExpire(0x20000, 14),
    ;

    private int val;
    private int pos;
    public static final int length = 17;
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    CharacterTemporaryStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    public boolean isEncodeInt() {
        switch (this) {
            case CarnivalDefence:
            case SpiritLink:
            case DojangLuckyBonus:
            case SoulGazeCriDamR:
            case PowerTransferGauge:
            case ReturnTeleport:
            case ShadowPartner:
            case IncMaxDamage:
            case Unk8000:
            case SetBaseDamage:
            case QuiverCatridge:
            case ImmuneBarrier:
            case NaviFlying:
            case Dance:
            case SetBaseDamageByBuff:
            case DotHealHPPerSecond:
            case MagnetArea:
                return true;
            default:
                return false;
        }
    }

    public boolean isIndie() {
        return toString().toLowerCase().contains("indie");
    }

    public boolean isMovingEffectingStat() {
        switch (this) {
            case Speed:
            case Jump:
            case Stun:
            case Weakness:
            case Slow:
            case Morph:
            case Ghost:
            case BasicStatUp:
            case Attract:
            case DashSpeed:
            case DashJump:
            case Flying:
            case Frozen:
            case Frozen2:
            case Lapidification:
            case IndieSpeed:
            case IndieJump:
            case KeyDownMoving:
            case Mechanic:
            case Magnet:
            case MagnetArea:
            case VampDeath:
            case VampDeathSummon:
            case GiveMeHeal:
            case DarkTornado:
            case NewFlying:
            case NaviFlying:
            case UserControlMob:
            case Dance:
            case SelfWeakness:
            case BattlePvPHelenaWindSpirit:
            case BattlePvPLeeMalNyunScaleUp:
            case TouchMe:
            case IndieForceSpeed:
            case IndieForceJump:
                return true;
            default:
                return false;
        }
    }

    public int getVal() {
        return val;
    }

    public int getPos() {
        return pos;
    }

    public static void mainNoMainPls(String[] args) {
        int a = 0x1eb;
        int val = 1 << (31 - (a & 0x1f));
        int pos = a >> 5;
        log.debug(String.format("value 0x%04x, pos %d", val, pos));
        for(CharacterTemporaryStat cts : values()) {
            if(cts.getVal() == val && cts.getPos() == pos) {
                log.debug("Corresponds to " + cts);
            }
        }
    }
}
