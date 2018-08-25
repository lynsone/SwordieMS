package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 6/7/2018.
 */
public enum UserEffectType {
    LevelUp(0),
    SkillUse(1),
    SkillUseBySummoned(2),
    SkillAffected(3),
    SkillAffected_Ex(4),
    SkillAffected_Select(5),
    SkillSpecialAffected(6),
    Quest(7),
    Pet(8),
    SkillSpecial(9),
    Resist(10),
    ProtectOnDieItemUse(11),
    PlayPortalSE(12),
    JobChanged(13),
    QuestComplete(14),
    IncDecHPEffect(15),
    BuffItemEffect(16),
    SquibEffect(17),
    MonsterBookCardGet(18),
    LotteryUse(19),
    ItemLevelUp(20),
    ItemMaker(21),
    ExpItemConsumed(22),
    FieldExpItemConsumed(23),
    ReservedEffect(24),
    UnkAtm1(25),
    UpgradeTombItemUse(26),
    BattlefieldItemUse(27),
    UnkAtm2(28),
    AvatarOriented(29),
    AvatarOrientedRepeat(30),
    AvatarOrientedMultipleRepeat(31),
    IncubatorUse(32),
    PlaySoundWithMuteBGM(33),
    PlayExclSoundWithDownBGM(34),
    SoulStoneUse(35),
    IncDecHPEffect_EX(36),
    IncDecHPRegenEffect(37),
    EffectUOL(38),
    PvPRage(39),
    PvPChampion(40),
    PvPGradeUp(41),
    PvPRevive(42),
    JobEffect(43),
    FadeInOut(44),
    MobSkillHit(45),
    AswanSiegeAttack(46),
    BlindEffect(47),
    BossShieldCount(48),
    ResetOnStateForOnOffSkill(49),
    JewelCraft(50),
    ConsumeEffect(51),
    PetBuff(52),
    LotteryUIResult(53),
    LeftMonsterNumber(54),
    ReservedEffectRepeat(55),
    RobbinsBomb(56),
    SkillMode(57),
    ActQuestComplete(58),
    Point(59),
    SpeechBalloon(60),
    TextEffect(61),
    SkillPreLoopEnd(62),
    Aiming(63),
    PickUpItem(64),
    BattlePvP_IncDecHp(65),
    BiteAttack_ReceiveSuccess(66),
    BiteAttack_ReceiveFail(67),
    IncDecHPEffect_Delayed(68),
    Lightness(69),
    SetUsed(70),
    ;

    private byte val;

    UserEffectType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static UserEffectType getTypeByVal(int val) {
        return Arrays.stream(values()).filter(uet -> uet.getVal() == val).findAny().orElse(null);
    }
}
