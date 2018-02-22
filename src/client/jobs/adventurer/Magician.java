package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.*;
import connection.InPacket;
import constants.JobConstants;
import enums.*;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;
import java.util.List;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

//TODO Bishop Skills
//TODO FP - Ignite doens't create AoE
//TODO FP - Viral Slime shouldnt follow like a Summon
//TODO IL&FP - Elemental Adaptation - Buff

/**
 * Created on 12/14/2017.
 */
public class Magician extends Job {
    public static final int MAPLE_RETURN = 1281;

    public static final int MAGIC_GUARD = 2001002;
    public static final int MP_EATER_FP = 2100000;
    public static final int POISON_BREATH = 2101005;
    public static final int MAGIC_BOOSTER_FP = 2101008;
    public static final int MEDITATION_FP = 2101001;
    public static final int IGNITE = 2101010;
    public static final int BURNING_MAGIC = 2110000;
    public static final int POISON_MIST = 2111003;
    public static final int TELEPORT_MASTERY_FP = 2111007;
    public static final int ELEMENTAL_DECREASE_FP = 2111008;
    public static final int VIRAL_SLIME = 2111010;
    public static final int PARALYZE = 2121006;
    public static final int MIST_ERUPTION = 2121003;
    public static final int FLAME_HAZE = 2121011;
    public static final int INFINITY_FP = 2121004;
    public static final int IFRIT = 2121005;
    public static final int MAPLE_WARRIOR_FP = 2121000;
    public static final int CHILLING_STEP = 2201009;
    public static final int COLD_BEAM = 2201008;
    public static final int FREEZING_CRUSH = 2200011;   //TODO Set stacks on mobs, gain buff from those stacks
    public static final int FROST_CLUTCH = 2220015;
    public static final int MAGIC_BOOSTER_IL = 2201010;
    public static final int MEDITATION_IL = 2201001;
    public static final int ICE_STRIKE = 2211002;
    public static final int GLACIER_CHAIN = 2211010;
    public static final int THUNDER_STORM = 2211011;
    public static final int TELEPORT_MASTERY_IL = 2211007;
    public static final int ELEMENTAL_DECREASE_IL = 2211008;
    public static final int CHAIN_LIGHTNING = 2221006;
    public static final int FREEZING_BREATH = 2221011;
    public static final int INFINITY_IL = 2221004;
    public static final int ELQUINES = 2221005;
    public static final int HEAL = 2301002;
    public static final int MAGIC_BOOSTER_BISH = 2301008;
    public static final int BLESS = 2301004;
    public static final int HOLY_MAGIC_SHELL = 2311009;
    public static final int TELEPORT_MASTERY_BISH = 2311007;
    public static final int HOLY_FOUNTAIN = 2311011;
    public static final int DIVINE_PROTECTION = 2311012;
    public static final int MYSTIC_DOOR = 2311002;
    public static final int HOLY_SYMBOL = 2311003;
    public static final int ADV_BLESSING = 2321005;
    public static final int BAHAMUT = 2321003;
    public static final int INFINITY_BISH = 2321004;
    public static final int MAPLE_WARRIOR_BISH = 2321000;
    public static final int MAPLE_WARRIOR_IL = 2221000;
    public static final int ARCANE_AIM_FP = 2120010;
    public static final int ARCANE_AIM_IL = 2220010;
    public static final int ARCANE_AIM_BISH = 2320011;

    //Hypers
    public static final int EPIC_ADVENTURE_FP = 2121053;
    public static final int EPIC_ADVENTURE_IL = 2221053;
    public static final int EPIC_ADVENTURE_BISH = 2321053;
    public static final int ABSOLUTE_ZERO_AURA = 2121054;
    public static final int INFERNO_AURA = 2221054;
    public static final int RIGHTEOUSLY_INDIGNANT = 2321054;
    public static final int HEAVENS_DOOR = 2321052;
    public static final int MEGIDDO_FLAME = 2121052;
    public static final int MEGIDDO_FLAME_ATOM = 2121055;



    private int[] addedSkills = new int[] {
            MAPLE_RETURN,
    };

    private final int[] buffs = new int[]{
            MAGIC_GUARD,
            IGNITE,
            MAGIC_BOOSTER_FP,
            MEDITATION_FP,
            TELEPORT_MASTERY_FP,
            ELEMENTAL_DECREASE_FP,
            INFINITY_FP,
            IFRIT,
            MAPLE_WARRIOR_FP,
            MEDITATION_FP,
            CHILLING_STEP,
            MAGIC_BOOSTER_IL,
            MEDITATION_IL,
            THUNDER_STORM,
            TELEPORT_MASTERY_IL,
            ELEMENTAL_DECREASE_IL,
            INFINITY_IL,
            ELQUINES,
            MAPLE_WARRIOR_IL,
            VIRAL_SLIME,
            MAGIC_BOOSTER_BISH,
            BLESS,
            HOLY_MAGIC_SHELL,
            TELEPORT_MASTERY_BISH,
            DIVINE_PROTECTION,
            MYSTIC_DOOR,
            HOLY_SYMBOL,
            ADV_BLESSING,
            MAPLE_WARRIOR_BISH,
            INFINITY_BISH,

            EPIC_ADVENTURE_FP,
            EPIC_ADVENTURE_IL,
            EPIC_ADVENTURE_BISH,
            ABSOLUTE_ZERO_AURA,
            INFERNO_AURA,
            RIGHTEOUSLY_INDIGNANT,
            HEAVENS_DOOR,
    };

    public Magician(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        byte slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        handleIgnite(attackInfo, chr, tsm, slv);
        //handleMegiddo(attackInfo);

        if (hasHitMobs) {
            handleArcaneAim();
            handleFreezingCrush(attackInfo, slv);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case POISON_BREATH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case POISON_MIST:
                AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                int x = attackInfo.forcedX;
                int y = attackInfo.forcedY;
                aa.setPosition(new Position(x, y));
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                chr.getField().spawnAffectedArea(aa);
                break;
            case TELEPORT_MASTERY_FP:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Stun, o1);
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case FLAME_HAZE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Invincible, o1);
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Speed, o1);
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case IFRIT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                }
                break;
            case PARALYZE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case COLD_BEAM:
            case ICE_STRIKE:
            case GLACIER_CHAIN:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 5;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
                break;
            case TELEPORT_MASTERY_IL:
            case CHAIN_LIGHTNING:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case MIST_ERUPTION:
                for(int id : attackInfo.mists) {
                    Field field = chr.getField();
                    field.removeLife(id);
                }
                break;
            case HEAL:
                //TODO doesn't heal
                break;
        }

    }

    private void handleMegiddoFlame() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(MEGIDDO_FLAME);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        List<Life> lifes = field.getLifesInRect(rect);
        for(Life life : lifes) {
            if(life instanceof Mob) {
                int mobID = ((Mob) life).getRefImgMobID(); //
                int inc = ForceAtomEnum.DA_ORB.getInc();
                int type = ForceAtomEnum.DA_ORB.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                        0, 500, (int) System.currentTimeMillis(), 1, 0,
                        new Position(0, -100));
                chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                        true, mobID, MEGIDDO_FLAME_ATOM, forceAtomInfo, new Rect(), 0, 300,
                        life.getPosition(), MEGIDDO_FLAME_ATOM, life.getPosition()));
            }
        }
    }

    private void handleArcaneAim() {
        Skill skill = chr.getSkill(getArcaneAimSkill());
        if(skill == null) {
            return;
        }
        SkillInfo arcaneAimInfo = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        int arcaneAimProp = arcaneAimInfo.getValue(prop, slv);
        if(!Util.succeedProp(arcaneAimProp)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        int amount = 1;
        if (tsm.hasStat(ArcaneAim)) {
            amount = tsm.getOption(ArcaneAim).nOption;
            if (amount < arcaneAimInfo.getValue(y, slv)) {
                amount++;
            }
        }
        o.nOption = amount;
        o.rOption = 2320011;
        o.tOption = 5; // No Time Variable
        tsm.putCharacterStatValue(ArcaneAim, o);
        o1.nOption = arcaneAimInfo.getValue(ignoreMobpdpR, slv);
        o1.rOption = 2320011;
        o1.tOption = 5; // No Time Variable
        tsm.putCharacterStatValue(IgnoreMobpdpR, o1);
        o2.nOption = ( amount * arcaneAimInfo.getValue(x, slv));
        o2.rOption = 2320011;
        o2.tOption = 5; // No Time Variable
        tsm.putCharacterStatValue(DamR, o2);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleIgnite(AttackInfo attackInfo, Char chr, TemporaryStatManager tsm, int slv) {
        if(tsm.hasStat(WizardIgnite)) {
            SkillInfo igniteInfo = getInfo(IGNITE);
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(igniteInfo.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                    aa.setMobOrigin((byte) 1);
                    aa.setCharID(chr.getId());
                    aa.setPosition(mob.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(igniteInfo.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                }
            }
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            switch(skillID) {
                case MAPLE_RETURN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case FREEZING_BREATH:
                    break;
                case MEGIDDO_FLAME:
                    handleMegiddoFlame();
                    break;
                case HEAVENS_DOOR:
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    tsm.putCharacterStatValue(HeavensDoor, o1);
                    c.write(WvsContext.temporaryStatSet(tsm));
                    break;
                case HOLY_FOUNTAIN: //User_Create_Holidom_Request  needs to be created
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;


            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(MagicGuard)) {
            Skill skill = chr.getSkill(MAGIC_GUARD);
            SkillInfo si = SkillData.getSkillInfoById(MAGIC_GUARD);
            int dmgPerc = si.getValue(x, skill.getCurrentLevel());
            int dmg = hitInfo.HPDamage;
            int mpDmg = (int) (dmg * (dmgPerc / 100D));
            mpDmg = chr.getStat(Stat.mp) - mpDmg < 0 ? chr.getStat(Stat.mp) : mpDmg;
            hitInfo.HPDamage = dmg - mpDmg;
            hitInfo.MPDamage = mpDmg;
        }
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(MagicGuard, o1);
                break;
            case MAGIC_BOOSTER_FP:
            case MAGIC_BOOSTER_IL:
            case MAGIC_BOOSTER_BISH:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
//                o1.nValue = si.getValue(x, slv);
//                o1.nReason = skillID;
//                o1.tStart= (int) System.currentTimeMillis();
//                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case MEDITATION_FP:
            case MEDITATION_IL:
                o1.nValue = si.getValue(indieMad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                break;
            case IGNITE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(WizardIgnite, o1);
                break;
            case ELEMENTAL_DECREASE_FP:
            case ELEMENTAL_DECREASE_IL:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case TELEPORT_MASTERY_FP:
            case TELEPORT_MASTERY_IL:
            case TELEPORT_MASTERY_BISH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(TeleportMasteryOn, o1);
                break;
            case INFINITY_FP:
            case INFINITY_IL:
            case INFINITY_BISH:
                o1.nOption = si.getValue(damage, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Infinity, o1);
                break;
            case VIRAL_SLIME:   //TODO
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.FIND_NEAREST_MOB.getVal());
                field.spawnSummon(summon);
                break;
            case BLESS: //TODO
                break;
            case ADV_BLESSING: //TODO
                break;
            case HOLY_SYMBOL: //TODO
                break;
            case HOLY_MAGIC_SHELL: //TODO
                //HolyMagicShell
                break;
            case IFRIT:
            case ELQUINES:
            case BAHAMUT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.FOLLOW.getVal());
                field.spawnSummon(summon);
                break;
            case MAPLE_WARRIOR_FP:
            case MAPLE_WARRIOR_IL:
            case MAPLE_WARRIOR_BISH:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case THUNDER_STORM:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 1);
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true);
                summon.setFlyMob(true);
                field.spawnSummon(summon);
//                o1.nReason = skillID;
//                o1.tTerm = si.getValue(time, slv);
//                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case CHILLING_STEP:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(ChillingStep, o1);
                break;

            case EPIC_ADVENTURE_FP:
            case EPIC_ADVENTURE_IL:
            case EPIC_ADVENTURE_BISH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                break;
            case ABSOLUTE_ZERO_AURA:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(FireAura, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(Stance, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(DamAbsorbShield, o3);
                o4.nOption = si.getValue(v, slv);
                o4.rOption = skillID;
                o4.tOption = 0;
                tsm.putCharacterStatValue(AsrR, o4);
                tsm.putCharacterStatValue(TerR, o4);
                break;
            case INFERNO_AURA:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(IceAura, o1);
                break;
            case RIGHTEOUSLY_INDIGNANT: //TODO recovey amount (Heal?) hp %
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(VengeanceOfAngel, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieMAD, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indiePMdR, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = 0;
                tsm.putCharacterStatValue(IndiePMdR, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieMaxDamageOver, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = 0;
                tsm.putCharacterStatValue(IndieMaxDamageOver, o4);
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieBooster, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = 0;
                tsm.putCharacterStatValue(IndieBooster, o5);
                o6.nOption = si.getValue(ignoreMobpdpR, slv);
                o6.rOption = skillID;
                o6.tOption = 0;
                tsm.putCharacterStatValue(IgnoreMobpdpR, o6);
                o7.nOption = si.getValue(w, slv);
                o7.rOption = skillID;
                o7.tOption = 0;
                tsm.putCharacterStatValue(ElementalReset, o7);
                break;

        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum jobEnum = JobConstants.JobEnum.getJobById(id);
        switch(jobEnum) {
            case MAGICIAN:
            case FP_WIZARD:
            case FP_MAGE:
            case FP_ARCHMAGE:
            case IL_WIZARD:
            case IL_MAGE:
            case IL_ARCHMAGE:
            case CLERIC:
            case PRIEST:
            case BISHOP:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    private int getArcaneAimSkill() {
        int res = 0;
        if (chr.hasSkill(ARCANE_AIM_FP)) {
            res = ARCANE_AIM_FP;
        } else if (chr.hasSkill(ARCANE_AIM_IL)) {
            res =  ARCANE_AIM_IL;
        } else if (chr.hasSkill(ARCANE_AIM_BISH)) {
            res =  ARCANE_AIM_BISH;
        }
        return res;
    }

    private void handleFreezingCrush(AttackInfo attackInfo, byte slv) {
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(FROST_CLUTCH);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            //if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                o1.nOption = 1;
                o1.rOption = FROST_CLUTCH;
                o1.tOption = 15; //si.getValue(subTime, slv);
                o1.mOption = 2; //Should be Amount
                mts.addStatOptionsAndBroadcast(MobStat.Speed, o1); // IDA says it's Speed, but it doesn't broadcast the Debuff over the mob
            //}
        }
    }

}

