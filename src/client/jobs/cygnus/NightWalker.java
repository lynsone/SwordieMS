package client.jobs.cygnus;

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
import enums.*;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class NightWalker extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_HARMONY_LUK = 10000249;

    public static final int LUCKY_SEVEN = 14001020;
    public static final int DARK_ELEMENTAL = 14001021; //Buff (Mark of Darkness)
    public static final int HASTE = 14001022; //Buff
    public static final int DARK_SIGHT = 14001023; //Buff

    public static final int THROWING_BOOSTER = 14101022; //Buff


    public static final int DARK_SERVANT = 14111024; //Buff
    public static final int SPIRIT_PROJECTION = 14111025; //Buff
    public static final int DARKNESS_ASCENDING = 14110030; //Special Buff
    public static final int SHADOW_SPARK = 14111023;

    public static final int DARK_OMEN = 14121003; //Summon
    public static final int SHADOW_STITCH = 14121004; //Special Attack (Bind Debuff)
    public static final int CALL_OF_CYGNUS_NW = 14121000; //Buff
    public static final int VITALITY_SIPHON = 14120009;

    public static final int GLORY_OF_THE_GUARDIANS_NW = 14121053;
    public static final int SHADOW_ILLUSION = 14121054;
    public static final int DOMINION = 14121052;

    //Bats
    public static final int SHADOW_BAT = 14001027; //Buff (Shadow Bats) (ON/OFF)
    public static final int SHADOW_BAT_ATOM = 14000028; //TODO  or 28 or 29         //28 registers (looks like AffinityI or II
    public static final int BAT_AFFINITY = 14100027; //Summon Upgrade
    public static final int BAT_AFFINITY_II = 14110029; //Summon Upgrade
    public static final int BAT_AFFINITY_III = 14120008; //Summon Upgrade


    //Attacks
    public static final int QUINTUPLE_STAR = 14121001;
    public static final int QUINTUPLE_STAR_FINISHER = 14121002;

    public static final int QUAD_STAR = 14111020;
    public static final int QUAD_STAR_FINISHER = 14111021;

    public static final int TRIPLE_THROW = 14101020;
    public static final int TRIPLE_THROW_FINISHER = 14101021;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_LUK,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
    };

    private int[] buffs = new int[] {
            DARK_ELEMENTAL,
            HASTE,
            DARK_SIGHT,
            SHADOW_BAT,
            THROWING_BOOSTER,
            DARK_SERVANT,
            SPIRIT_PROJECTION,
            DARKNESS_ASCENDING,
            CALL_OF_CYGNUS_NW,
            DARK_OMEN,
            GLORY_OF_THE_GUARDIANS_NW,
            SHADOW_ILLUSION,
            DOMINION,
    };

    private Summon bats;
    private int batcount;

    public static int getOriginalSkillByID(int skillID) {
        switch(skillID) {
            case TRIPLE_THROW_FINISHER:
                return TRIPLE_THROW;
            case QUAD_STAR_FINISHER:
                return QUAD_STAR;
            case QUINTUPLE_STAR_FINISHER:
                return QUINTUPLE_STAR;
        }
        return skillID; // no original skill linked with this one
    }

    public NightWalker(Char chr) {
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

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case DARK_ELEMENTAL:
                // 'prop' % to create a mark of darkness (Debuff on Mobs)
                // stacks 'x' times
                // 'y' %DEF ignored
                // 'time' duration
                //TODO

                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementDarkness, o1);
                break;
            case HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                o3.nOption = si.getValue(er, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o3);
                // SpeedMax
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case THROWING_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case DARK_SERVANT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowServant, o1);
                break;
            case SPIRIT_PROJECTION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case DARKNESS_ASCENDING:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarknessAscension, o1);
                break;
            case CALL_OF_CYGNUS_NW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case SHADOW_BAT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(NightWalkerBat, o1);
                //spawnBats(skillID, slv);
                break;

            case GLORY_OF_THE_GUARDIANS_NW:
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
            case SHADOW_ILLUSION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowIllusion, o1);
                break;

            case DARK_OMEN:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 0);
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true);
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }


    //Shadow Bat Handling
    private void handleBatForceAtom(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(NightWalkerBat)) {
            if(batcount > 0) {
                SkillInfo si = SkillData.getSkillInfoById(SHADOW_BAT);
                for(int i=0; i<batcount; i++) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        int tw1prop = 100;//  SkillData.getSkillInfoById(SHADOW_BAT).getValue(prop, slv);   //TODO Change
                        if (Util.succeedProp(tw1prop)) {
                            removeBat();    //TODO Doesn't remove bats
                            int mobID = mai.mobId;
                            int inc = ForceAtomEnum.NIGHT_WALKER_BAT.getInc();
                            int type = ForceAtomEnum.NIGHT_WALKER_BAT.getForceAtomType();
                            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 30, 30,
                                    0, 0, (int) System.currentTimeMillis(), 1, 0,
                                    new Position());
                            chr.getClient().write(CField.createForceAtom(false, 0, chr.getId(), type,
                                    true, mobID, SHADOW_BAT_ATOM, forceAtomInfo, new Rect(), 0, 300,
                                    mob.getPosition(), SHADOW_BAT_ATOM, mob.getPosition()));    //TODO mob.getPosition()  Gives a NPE on certain Skills

                        }
                    }
                }
            }
        }
    }

    private void handleShadowBat(TemporaryStatManager tsm, int skillID, byte slv, AttackInfo attackInfo) {
        if(tsm.hasStat(NightWalkerBat)) {
            if(Util.succeedProp(40)) {
                spawnBat(skillID, slv);
            }
            if(batcount > 0) {
                if (Util.succeedProp(60)) {
                    handleBatForceAtom(skillID, slv, attackInfo);
                }
            }
        }
    }

    private void spawnBat(int skillID, byte slv) {
        if(batcount < 5) {
            Field field;
            bats = Summon.getSummonBy(c.getChr(), getBatType(chr), slv);
            field = c.getChr().getField();
            bats.setFlyMob(true);
            bats.setMoveAbility(MoveAbility.FLY_AROUND_CHAR.getVal());
            field.spawnAddSummon(bats);
            batcount++;
        }
    }

    private void removeBat() {  //TODO doesn't remove bats
        //c.write(CField.summonedRemoved(bats, LeaveType.ANIMATION));
        Field field = c.getChr().getField();
        field.removeLife(getBatType(chr));
        batcount = batcount -1;
    }

    private int getBatType(Char chr) {
        int batType = SHADOW_BAT;
        if(chr.hasSkill(BAT_AFFINITY)) {
            batType = BAT_AFFINITY;
        }
        if(chr.hasSkill(BAT_AFFINITY_II)) {
            batType = BAT_AFFINITY_II;
        }
        if(chr.hasSkill(BAT_AFFINITY_III)) {
            batType = BAT_AFFINITY_III;
        }
        return batType;
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
        if(hasHitMobs) {
            //if(skillID != 0) {  //SkillID of ShadowBat itself = 0
                handleShadowBat(tsm, getOriginalSkillByID(skillID), slv, attackInfo);
            //}
        }
        if (chr.hasSkill(14120009)) {
            if (hasHitMobs && Util.succeedProp(SkillData.getSkillInfoById(14001021).getValue(prop, slv))) { //TODO get Mark of Darkness Prop (needs a handler)
                handleSiphonVitality(getOriginalSkillByID(skillID), tsm, c);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case SHADOW_STITCH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);

                        handleSiphonVitality(skill.getSkillId(), tsm, c);
                }
                break;

            case DOMINION:  //TODO
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time ,slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                c.write(WvsContext.temporaryStatSet(tsm));
                break;

            //TODO case All attacks give DoT debuff
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
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
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }


    private void handleSiphonVitality(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo siphonInfo = SkillData.getSkillInfoById(14120009);
        int amount = 1;
        if(tsm.hasStat(ElementDarkness)) {
            if (tsm.hasStat(SiphonVitality)) {
                amount = tsm.getOption(SiphonVitality).nOption;
                if (amount < getMaxSiphon(chr)) {
                    amount++;
                }
            }
            o.nOption = amount;
            o.rOption = 14120009;
            o.tOption = siphonInfo.getValue(time, siphonInfo.getCurrentLevel());
            tsm.putCharacterStatValue(SiphonVitality, o);
            o1.nOption = (amount * siphonInfo.getValue(y, siphonInfo.getCurrentLevel()));
            o1.rOption = 14120009;
            o1.tOption = siphonInfo.getValue(time, siphonInfo.getCurrentLevel());
            tsm.putCharacterStatValue(MaxHP, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private int getMaxSiphon(Char chr) { //TODO Doesn't function correctly      stays at 5 stacks
        Skill skill = null;
        if (chr.hasSkill(14120009)) {
            skill = chr.getSkill(14120009);
        }
        return SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case NIGHTWALKER1:
            case NIGHTWALKER2:
            case NIGHTWALKER3:
            case NIGHTWALKER4:
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
