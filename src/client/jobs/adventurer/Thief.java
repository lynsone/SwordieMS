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
import enums.ChatMsgColour;
import enums.ForceAtomEnum;
import enums.MobStat;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import server.EventManager;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;
import java.util.List;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

//TODO DB - Mirror Image

//nFlipTheCoin = stack icon

/**
 * Created on 12/14/2017.
 */
public class Thief extends Job {
    public static final int MAPLE_RETURN = 1281;

    // Rogue
    public static final int HASTE = 4001005; //Buff
    public static final int DARK_SIGHT = 4001003; //Buff


    // Night Lord
    public static final int ASSASSINS_MARK = 4101011; //Buff (ON/OFF)
    //public static final int ASSASSINS_MARK_ATOM = 4100012;
    public static final int ASSASSINS_MARK_ATOM = 4120019;
    public static final int CLAW_BOOSTER = 4101003; //Buff

    public static final int SHADOW_PARTNER_NL = 4111002; //Buff
    public static final int SHADOW_STARS = 4111009; //Buff
    public static final int DARK_FLARE_NL = 4111007; //Summon
    public static final int SHADOW_WEB = 4111003; //Special Attack (Dot + Bind)

    public static final int MAPLE_WARRIOR_NL = 4121000; //Buff
    public static final int SHOWDOWN = 4121017; //Special Attack
    public static final int SUDDEN_RAID_NL = 4121016; //Special Attack
    public static final int FRAILTY_CURSE = 4121015; //AoE
    public static final int NIGHT_LORD_MARK = 4120018;


    // Shadower
    public static final int STEAL = 4201004; //Special Attack (Steal Debuff)?
    public static final int DAGGER_BOOSTER = 4201002; //Buff
    public static final int MESOGUARD = 4201011; //Buff
    public static final int CRITICAL_GROWTH = 4200013; //Passive Crit increasing buff

    public static final int SHADOW_PARTNER_SHAD = 4211008; //Buff
    public static final int DARK_FLARE_SHAD = 4211007; //Summon
    public static final int PICK_POCKET = 4211003; //Buff
    public static final int MESO_EXPLOSION = 4211006; //CreateForceAtom Attack
    public static final int MESO_EXPLOSION_ATOM = 4210014; // ?

    public static final int BOOMERANG_STAB = 4221007; //Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_SHAD = 4221000; //Buff
    public static final int SHADOWER_INSTINCT = 4221013; //Buff //Stacks (Body Count)
    public static final int SUDDEN_RAID_SHAD = 4221010; //Special Attack
    public static final int SMOKE_SCREEN = 4221006; //Affected Area
    public static final int PRIME_CRITICAL = 4220015; //Passive Buff


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
    public static final int FLIP_THE_COIN = 4221054;
    public static final int BLADE_CLONE = 4341054;
    public static final int ASURAS_ANGER = 4341053;


    //public AffectedArea aa;

    private int critAmount;
    private int supposedCrit;
    private final int MAX_CRIT = 100;

    private int[] addedSkills = new int[] {
            MAPLE_RETURN,
    };

    private int[] buffs = new int[]{
            HASTE,
            DARK_SIGHT,
            ASSASSINS_MARK,
            CLAW_BOOSTER,
            SHADOW_PARTNER_NL,
            SHADOW_STARS,
            DARK_FLARE_NL,
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

        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (chr.getJob() >= JobConstants.JobEnum.BANDIT.getJobId() && chr.getJob() <= JobConstants.JobEnum.SHADOWER.getJobId()) {
                EventManager.addFixedRateEvent(this::incrementCritGrowing, 0, 2000);
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

        if (chr.getJob() >= JobConstants.JobEnum.ASSASSIN.getJobId() && chr.getJob() <= JobConstants.JobEnum.NIGHTLORD.getJobId()) {
            if(hasHitMobs) {
                //NightLord's Mark & ForceAtom
                if(chr.hasSkill(ASSASSINS_MARK)) {
                    setMarkonMob(attackInfo);
                    handleMark(attackInfo);
                }
            }
        }


        if (chr.getJob() >= JobConstants.JobEnum.BANDIT.getJobId() && chr.getJob() <= JobConstants.JobEnum.SHADOWER.getJobId()) {
            if(hasHitMobs) {
                //Critical Growth & Prime Critical
                if(chr.hasSkill(CRITICAL_GROWTH)) {
                    incrementCritGrowing();
                }

                //Flip of the Coin
                if(chr.hasSkill(FLIP_THE_COIN)) {
                    handleFlipTheCoinActivation(tsm);
                }

                //Shadower Instinct
                if (chr.hasSkill(SHADOWER_INSTINCT)) {
                    if (tsm.hasStat(IgnoreMobpdpR)) {
                        if(skill == null) {
                            handleShadowerInstinct(4221016, tsm, c);
                        }
                        handleShadowerInstinct(skill.getSkillId(), tsm, c);
                    }
                }
            }
        }

        if (chr.getJob() >= JobConstants.JobEnum.BLADE_RECRUIT.getJobId() && chr.getJob() <= JobConstants.JobEnum.BLADE_MASTER.getJobId()) {
            if(hasHitMobs) {

            }
        }
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
                }
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

            case ASURAS_ANGER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(Asura, o1);
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
                case MAPLE_RETURN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case SMOKE_SCREEN:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case FRAILTY_CURSE:
                    SkillInfo fci = SkillData.getSkillInfoById(skillID);
                    int lt1 = si.getValue(lt, slv);
                    int rb1 = si.getValue(rb, slv);
                    AffectedArea aa2 = AffectedArea.getPassiveAA(skillID, slv);
                    aa2.setMobOrigin((byte) 0);
                    aa2.setCharID(chr.getId());
                    aa2.setPosition(chr.getPosition());
                    aa2.setRect(aa2.getPosition().getRectAround(fci.getRects().get(0)));
                    if (chr.isLeft()) {
                        aa2.setFlip(false);
                    } else {
                        aa2.setFlip(true);
                    }
                    aa2.setDelay((short) 9);
                    chr.getField().spawnAffectedArea(aa2);
                    break;
                case MESO_EXPLOSION:
                    handleMesoExplosion();
                    break;
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
            case HASTE:
            case SELF_HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                        // SpeedMax?
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case ASSASSINS_MARK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
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
            case MIRRORED_TARGET: //Special Summon   //TODO Crashes - not 38*
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility((byte) 0);
                summon.setAssistType((byte) 0);
                summon.setAttackActive(false);
                field.spawnSummon(summon);
                break;
            case DARK_FLARE_NL:
            case DARK_FLARE_SHAD:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility((byte) 0);
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
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                //TODO DoT
                break;
            case FLIP_THE_COIN:
                handleFlipTheCoinStacking(tsm, c);
                c.write(WvsContext.flipTheCoinEnabled((byte) 0));
                break;
            case BLADE_CLONE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(WindBreakerFinal, o1);
                o2.nOption = 10;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o2);
                //TODO Final Attack Proc%
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleMesoExplosion() { //TODO
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(MESO_EXPLOSION);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        List<Life> lifes = field.getLifesInRect(rect);
        for(Life life : lifes) {
            if(life instanceof Mob) {
                int mobID = ((Mob) life).getRefImgMobID(); //
                int inc = ForceAtomEnum.FLYING_MESO.getInc();
                int type = ForceAtomEnum.FLYING_MESO.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                        0, 0, (int) System.currentTimeMillis(), 1, 0,
                        new Position());
                chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                        true, mobID, MESO_EXPLOSION, forceAtomInfo, new Rect(), 0, 300,
                        life.getPosition(), MESO_EXPLOSION, life.getPosition()));
            }
        }
    }

    private void handleFlipTheCoinStacking(TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        SkillInfo FlipTheCoinInfo = SkillData.getSkillInfoById(FLIP_THE_COIN);
        int amount = 1;
        if(tsm.hasStat(FlipTheCoin)) {
            amount = tsm.getOption(FlipTheCoin).nOption;
            if(amount < FlipTheCoinInfo.getValue(y, 1)) {
                amount++;
            }
        }
        o.nOption = amount;
        o.rOption = FLIP_THE_COIN;
        o.tOption = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(FlipTheCoin, o);

        //Stats
        o1.nOption = (amount * FlipTheCoinInfo.getValue(x, 1));
        o1.rOption = FLIP_THE_COIN;
        o1.tOption = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(CriticalBuff, o1);
        o2.nReason = FLIP_THE_COIN;
        o2.nValue = (amount * FlipTheCoinInfo.getValue(indieDamR, 1));
        o2.tStart = (int) System.currentTimeMillis();
        o2.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(IndieDamR, o2);
        o3.nReason = FLIP_THE_COIN;
        o3.nValue = (amount * FlipTheCoinInfo.getValue(indieMaxDamageOver, 1));
        o3.tStart = (int) System.currentTimeMillis();
        o3.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(IndieMaxDamageOver, o3);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleFlipTheCoinActivation(TemporaryStatManager tsm) {    //TODO  Change to proc on Critical Hits
        if(tsm.getOption(FlipTheCoin).nOption < 5) {
            if (Util.succeedProp(50)) { //Proc on Crit<<<
                c.write(WvsContext.flipTheCoinEnabled((byte) 1));
            }
        }
    }

    private void updatecrit() {
        if(chr.hasSkill(PRIME_CRITICAL)) {
            supposedCrit = supposedCrit + 4;
        } else {
            supposedCrit = supposedCrit + 2;
        }
        Option o = new Option();
        int critGrowth = critAmount;
        o.nOption = (getPrimeCritMulti() * critGrowth);
        o.rOption = getCritGrowIcon();
        chr.getTemporaryStatManager().putCharacterStatValue(CriticalGrowing, o);
        chr.getTemporaryStatManager().sendSetStatPacket();
    }

    private void incrementCritGrowth(int stackIncrease) {
        if(supposedCrit > 100) {
            critAmount = 1; //TODO returns to starting crit% even if another crit buff is active
            supposedCrit = 0;
        } else {
            critAmount += stackIncrease;
        }
        critAmount = Math.min(MAX_CRIT, critAmount);
        updatecrit();
    }

    public void incrementCritGrowing() {
        incrementCritGrowth(1);   //Crit Growing
    }

    private int getCritGrowIcon() {
        if(chr.hasSkill(PRIME_CRITICAL)) {
            return PRIME_CRITICAL;
        } else {
            return CRITICAL_GROWTH;
        }
    }

    private int getPrimeCritMulti() {
        int multiplier = 2;
        if(chr.hasSkill(PRIME_CRITICAL)) {
            multiplier = 4;
        }
        return multiplier;
    }

    private void handleShadowerInstinct(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo InstinctInfo = SkillData.getSkillInfoById(SHADOWER_INSTINCT);
        Skill skill = chr.getSkill(SHADOWER_INSTINCT);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if (tsm.hasStat(KillingPoint)) {
            if (chr.hasSkill(SHADOWER_INSTINCT)) {
                amount = tsm.getOption(KillingPoint).nOption;
                if (amount < 5) {
                    amount++;
                }
            }
        }
        o.nOption = amount;
        tsm.putCharacterStatValue(KillingPoint, o);
        o1.nOption = (amount * InstinctInfo.getValue(kp, slv));
        o1.rOption = SHADOWER_INSTINCT;
        o1.tOption = InstinctInfo.getValue(time, slv);
        tsm.putCharacterStatValue(PAD, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public boolean isBuff(int skillID) {
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


    private void handleMark(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(ASSASSINS_MARK);
        SkillInfo si = SkillData.getSkillInfoById(ASSASSINS_MARK);
        byte slv = (byte) skill.getCurrentLevel();
        int skillId = skill.getSkillId();
        if(tsm.hasStat(NightLordMark)) {

            if (attackInfo.skillId != ASSASSINS_MARK_ATOM) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    int mobID = mai.mobId;
                    Rect rect = new Rect(
                            new Position(
                                    mob.getPosition().getX() - 5000,
                                    mob.getPosition().getY() - 5000),
                            new Position(
                                    mob.getPosition().getX() + 5000,
                                    mob.getPosition().getY() + 5000)
                    );

                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if(mts.hasBurnFromSkill(getCurMarkLv())) {
                        for (int i = 0; i < 6; i++) {
                            int anglez = (360 / 6) * i;

                            int inc = ForceAtomEnum.ASSASSIN_MARK.getInc();
                            int type = ForceAtomEnum.ASSASSIN_MARK.getForceAtomType();

                            if(chr.hasSkill(NIGHT_LORD_MARK)) {
                                inc = ForceAtomEnum.NIGHTLORD_MARK.getInc();
                                type = ForceAtomEnum.NIGHTLORD_MARK.getForceAtomType();
                            }

                            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 30, 4,
                                    anglez, 200, (int) System.currentTimeMillis(), 1, 0,
                                    new Position());
                            chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), mobID, type,
                                    true, mobID, ASSASSINS_MARK_ATOM, forceAtomInfo, rect, 0, 300,
                                    mob.getPosition(), chr.getBulletIDForAttack(), mob.getPosition()));
                        }
                    }
                }
            }
        }
    }

    private void setMarkonMob(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(getCurMarkLv());
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(getCurMarkLv());
        byte slv = (byte) skill.getCurrentLevel();
        int markprop = si.getValue(prop, slv);
        if(tsm.hasStat(NightLordMark)) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(markprop)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                }
            }
        }
    }

    private int getCurMarkLv() {
        int supgrade = 0;
        if(chr.hasSkill(ASSASSINS_MARK)) {
            supgrade = ASSASSINS_MARK;
        }
        if(chr.hasSkill(NIGHT_LORD_MARK)) {
            supgrade = NIGHT_LORD_MARK;
        }
        return supgrade;
    }

}