package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.Stat;
import loaders.SkillData;
import packet.WvsContext;
import server.EventManager;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

//TODO Shadower - Critical Growth //
//TODO DB - Mirror Image

//nFlipTheCoin = stack icon

/**
 * Created on 12/14/2017.
 */
public class Thief extends Job {

    // Rogue
    public static final int HASTE = 4001005; //Buff
    public static final int DARK_SIGHT = 4001003; //Buff


    // Night Lord
    public static final int ASSASSINS_MARK = 4101011; //Buff
    public static final int CLAW_BOOSTER = 4101003; //Buff

    public static final int SHADOW_PARTNER_NL = 4111002; //Buff
    public static final int SHADOW_STARS = 4111009; //Buff
    public static final int DARK_FLARE_NL = 4111007; //Summon
    public static final int SHADOW_WEB = 4111003; //Special Attack (Dot + Bind)

    public static final int MAPLE_WARRIOR_NL = 4121000; //Buff
    public static final int SHOWDOWN = 4121017; //Special Attack
    public static final int SUDDEN_RAID_NL = 4121016; //Special Attack
    public static final int FRAILTY_CURSE = 4121015; //Summon


    // Shadower
    public static final int STEAL = 4201004; //Special Attack (Steal Debuff)?
    public static final int DAGGER_BOOSTER = 4201002; //Buff
    public static final int MESOGUARD = 4201011; //Buff
    public static final int CRITICAL_GROWTH = 4200013; //Passive Crit increasing buff

    public static final int SHADOW_PARTNER_SHAD = 4211008; //Buff
    public static final int DARK_FLARE_SHAD = 4211007; //Summon
    public static final int PICK_POCKET = 4211003; //Buff

    public static final int BOOMERANG_STAB = 4221007; //Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_SHAD = 4221000; //Buff
    public static final int SHADOWER_INSTINCT = 4221013; //Buff //Stacks (Body Count)
    public static final int SUDDEN_RAID_SHAD = 4221010; //Special Attack


    // Dual Blade
    public static final int SELF_HASTE = 4301003; //Buff

    public static final int KATARA_BOOSTER = 4311009; //Buff

    public static final int FLYING_ASSAULTER = 4321006; //Special Attack (Stun Debuff)
    public static final int FLASHBANG = 4321002; //Special Attack

    public static final int CHAINS_OF_HELL = 4331006; //Special Attack (Stun Debuff)
    public static final int MIRROR_IMAGE = 4331002; //Buff

    public static final int FINAL_CUT = 4341002; //Special Attack
    public static final int SUDDEN_RAID_DB = 4341011; //Special Attack
    public static final int MAPLE_WARRIOR_DB = 4341000; //Buff
    public static final int MIRRORED_TARGET = 4341006; //Summon


    //Hyper skills
    public static final int EPIC_ADVENTURE_NL = 4121053;
    public static final int EPIC_ADVENTURE_SHAD = 4221053;
    public static final int EPIC_ADVENTURE_DB = 4341053;
    public static final int BLEED_DART = 4121054;
    public static final int FLIP_THE_COIN = 4221054; //TODO Method
    public static final int BLADE_CLONE = 4341054;
    public static final int ASURAS_ANGER = 4341053;


    private int crit;
    private final int MAX_CRIT = 100;

    private int[] buffs = new int[]{
            HASTE,
            DARK_SIGHT,
            ASSASSINS_MARK,
            CLAW_BOOSTER,
            SHADOW_PARTNER_NL,
            SHADOW_STARS,
            DARK_FLARE_NL,
            FRAILTY_CURSE,
            MAPLE_WARRIOR_NL,


            DAGGER_BOOSTER,
            MESOGUARD,
            SHADOW_PARTNER_SHAD,
            DARK_FLARE_SHAD,
            PICK_POCKET,
            SHADOWER_INSTINCT,
            MAPLE_WARRIOR_SHAD,

            SELF_HASTE,
            KATARA_BOOSTER,
            MIRROR_IMAGE,
            FINAL_CUT,
            MIRRORED_TARGET,
            MAPLE_WARRIOR_DB,


            EPIC_ADVENTURE_NL,
            EPIC_ADVENTURE_SHAD,
            EPIC_ADVENTURE_DB,
            BLEED_DART,
            FLIP_THE_COIN,
            BLADE_CLONE,
    };

    public Thief(Char chr) {
        super(chr);
        //critinterval();
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
            Char chr = c.getChr();
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Skill skill = chr.getSkill(attackInfo.skillId);
            int skillID = 0;
            SkillInfo si = null;
            boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
            int slv = 0;
            if (skill != null) {
                si = SkillData.getSkillInfoById(skill.getSkillId());
                slv = skill.getCurrentLevel();
                skillID = skill.getSkillId();
            }
            if (chr.getJob() == 422) {
                if (chr.hasSkill(4221013)) {
                    if (tsm.hasStat(IgnoreMobpdpR)) {
                        if(hasHitMobs) {
                            if(skill == null) {
                                handleShadowerInstinct(4221016, tsm, c);
                            }
                            handleShadowerInstinct(skill.getSkillId(), tsm, c);
                        }
                    }
                }
            }

            //critinterval(); //TODO uncomment

            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (attackInfo.skillId) {
                case STEAL:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            //mts.addStatOptionsAndBroadcast(MobStat.STEAL, o1); //TODO Steal MobStat
                            // Unsure
                        }
                    }
                    break;
                case SHADOW_WEB:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }else{
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                        }
                    }
                    break;
                case SHOWDOWN:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Showdown, o1);
                            // Unsure
                    }
                    break;
                case SUDDEN_RAID_DB:
                case SUDDEN_RAID_SHAD:
                case SUDDEN_RAID_NL:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }       // TODO DoT  or  Affect on Hit Effect?
                    break;
                case FLASHBANG:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = si.getValue(x, slv); //TODO -x?   ("Decreases Acc by 20%", but WzFile has 20 as positive)
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                        }
                    }
                    break;
                case BOOMERANG_STAB:
                case FLYING_ASSAULTER:
                case CHAINS_OF_HELL:
                    for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                    break;

                case FINAL_CUT:
                    o1.nOption = si.getValue(w, slv);
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(FinalCut, o1);
                    o2.nOption = 1;
                    o2.rOption = skillID;
                    o2.tOption = 3;
                    tsm.putCharacterStatValue(NotDamaged, o2);
                    c.write(WvsContext.temporaryStatSet(tsm));
                    break;
            }
        }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
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
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                //case
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int curTime = (int) System.currentTimeMillis();
        Summon summon;
        Field field;
        switch (skillID) {
            case HASTE: //TODO 38s
            case SELF_HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                //tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                //tsm.putCharacterStatValue(Jump, o2);
                // SpeedMax?
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case ASSASSINS_MARK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NightLordMark, o1);
                break;
            case CLAW_BOOSTER:
            case DAGGER_BOOSTER:
            case KATARA_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SHADOW_PARTNER_NL:
            case SHADOW_PARTNER_SHAD:
            case MIRROR_IMAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case MAPLE_WARRIOR_DB:
            case MAPLE_WARRIOR_NL:
            case MAPLE_WARRIOR_SHAD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case SHADOW_STARS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                // Unsure about this Buff
                break;
            case MESOGUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MesoGuard, o1);
                break;
            case PICK_POCKET:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PickPocket, o1);
                // Unsure about this Buff
                break;
            case SHADOWER_INSTINCT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PAD, o1);
                o2.nOption = si.getValue(ignoreMobpdpR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                break;
            case MIRRORED_TARGET: //TODO Crashes - not 38*
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 0); // 0 = Stationary | 1 = Moves with Player
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(false); // false = Doesn't Attack | true = Attacks
                field.spawnSummon(summon);
                break;
            case DARK_FLARE_NL:
            case DARK_FLARE_SHAD:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 0); // 0 = Stationary | 1 = Moves with Player
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true); // false = Doesn't Attack | true = Attacks
                field.spawnSummon(summon);
                break;

            case EPIC_ADVENTURE_DB:
            case EPIC_ADVENTURE_NL:
            case EPIC_ADVENTURE_SHAD:
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

            case BLEED_DART:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BleedingToxin, o1);
                break;

            case FLIP_THE_COIN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(FlipTheCoin, o1);

            case BLADE_CLONE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(WindBreakerFinal, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }


    //TODO Fix Critical Growth
    private void handleCritGrowth(int skillID, TemporaryStatManager tsm, Client c) {    //Crit rate increase = x
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo critGrowthInfo = SkillData.getSkillInfoById(CRITICAL_GROWTH);
        int amount = 1;
        if (chr.hasSkill(CRITICAL_GROWTH)) {
            amount = tsm.getOption(CriticalGrowing).nOption;
            if(amount < 100) {
                amount++;
            } else {
                amount = 1;
            }
        }
        o.nOption = (critGrowthInfo.getValue(x, critGrowthInfo.getCurrentLevel()) * amount);
        o.rOption = CRITICAL_GROWTH;
        o.tOption = 0;
        tsm.putCharacterStatValue(CriticalGrowing, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }


    private void critinterval() {
        int skillID = 0;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        handleCritGrowth(skillID, tsm, c);
        EventManager.addEvent(this, "critinterval", 3000);
    }


    private void handleShadowerInstinct(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo InstinctInfo = SkillData.getSkillInfoById(4221013);
        int amount = 1;
        if (tsm.hasStat(KillingPoint)) {
            if (chr.hasSkill(4221013)) {
                amount = tsm.getOption(KillingPoint).nOption;
                if (amount < 5) {
                    amount++;
                }
            }
        }
        o.nOption = amount;
        tsm.putCharacterStatValue(KillingPoint, o);
        o1.nOption = (amount * InstinctInfo.getValue(kp, InstinctInfo.getCurrentLevel()));
        o1.rOption = 4221013;
        o1.tOption = InstinctInfo.getValue(time, InstinctInfo.getCurrentLevel());
        tsm.putCharacterStatValue(PAD, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        //return id >= JobConstants.JobEnum.THIEF.getJobId() && id <= JobConstants.JobEnum.BLADE_MASTER.getJobId();
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case THIEF:
            case ASSASSIN:
            case HERMIT:
            case NIGHTLORD:
            case BANDIT:
            case CHIEFBANDIT:
            case SHADOWER:
            case BLADE_RECRUIT:
            case BLADE_ACOLYTE:
            case BLADE_SPECIALIST:
            case BLADE_LORD:
            case BLADE_MASTER:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

}