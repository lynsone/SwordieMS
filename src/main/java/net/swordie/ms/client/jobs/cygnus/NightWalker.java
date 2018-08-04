package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.life.Summon;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.Random;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class NightWalker extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_SHIFT2 = 10000252;
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
    public static final int SHADOW_BAT_ATOM = 14000028;
    public static final int BAT_AFFINITY = 14100027; //Summon Upgrade
    public static final int BAT_AFFINITY_II = 14110029; //Summon Upgrade
    public static final int BAT_AFFINITY_III = 14120008; //Summon Upgrade

    //Dark Elemental
    public static final int ADAPTIVE_DARKNESS = 14100026;
    public static final int ADAPTIVE_DARKNESS_II = 14110028;
    public static final int ADAPTIVE_DARKNESS_III = 14120007;

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
            ELEMENTAL_SHIFT2,
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
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
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

/*  TODO    WVS_CRASH_CALLBACK error upon summoning
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.FIND_NEAREST_MOB.getVal());
                field.spawnSummon(summon);
*/
                break;
            case SPIRIT_PROJECTION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case DARKNESS_ASCENDING:
                if(tsm.hasStat(DarknessAscension)) {
                    return;
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
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
/*
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.FIND_NEAREST_MOB.getVal());
                field.spawnSummon(summon);
*/
                break;

            case DARK_OMEN:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility((byte) 0);
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
        super.handleBuff(c, inPacket, skillID, slv);
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
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
                            int mobID = mai.mobId;
                            int position = new Random().nextInt(80);
                            int inc = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getInc();
                            int type = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getForceAtomType();
                            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 3, 3,
                                    90, 0, (int) System.currentTimeMillis(), 1, 0,
                                    new Position(-20, position));
                            chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                                    true, mobID, SHADOW_BAT_ATOM, forceAtomInfo, new Rect(), 0, 300,
                                    mob.getPosition(), SHADOW_BAT_ATOM, mob.getPosition()));    //TODO mob.getPosition()  QUINT_THROW giving NPE
                        }
                    }
                }
            }
        }
    }

    private void handleShadowBat(TemporaryStatManager tsm, int skillID, byte slv, AttackInfo attackInfo) {
        if(tsm.hasStat(NightWalkerBat)) {
            if(Util.succeedProp(85)) {
                spawnBat(skillID, slv);
            }
            if(batcount > 0) {
                if (Util.succeedProp(30)) {
                    handleBatForceAtom(skillID, slv, attackInfo);
                    removeBat();   //TODO Doesn't remove bats properly
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

    private void removeBat() {
        //c.write(CField.summonedRemoved(bats, LeaveType.ANIMATION));
        Field field = c.getChr().getField();
        c.write(Summoned.summonedRemoved(bats, LeaveType.ANIMATION));
        //field.removeLife(getBatType(chr),true);
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
            if(tsm.hasStat(ElementDarkness)) {
                handleDarkElemental(attackInfo, slv);
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
                }
                break;
            case DOMINION:  //TODO Max Darkness Stack whilst active,
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time ,slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                o2.nOption = 100;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time ,slv);
                tsm.putCharacterStatValue(CriticalBuff, o2);
                o3.nOption = 100;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time ,slv);
                tsm.putCharacterStatValue(Stance, o3);
                c.write(WvsContext.temporaryStatSet(tsm));
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void handleDarkElemental(AttackInfo attackInfo, byte slv) {

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(ElementDarkness)) {
            Option o1 = new Option();
            Option o2 = new Option();
            Skill skill = chr.getSkill(attackInfo.skillId);
            int skillID = skill.getSkillId();
            int amount = 1;
            SkillInfo si = SkillData.getSkillInfoById(DARK_ELEMENTAL);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(getDarkEleProp())) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();

                    if (mts.hasCurrentMobStat(MobStat.ElementDarkness)) {
                        amount = mts.getCurrentOptionsByMobStat(MobStat.ElementDarkness).nOption;
                        if (amount < getMaxDarkEleStack()) {
                            amount++;
                        }
                    }
                    o1.nOption = amount;
                    o1.rOption = DARK_ELEMENTAL;
                    o1.tOption = 15;
                    mts.addStatOptionsAndBroadcast(MobStat.ElementDarkness, o1);

                    o2.nOption = 1;
                    o2.rOption = DARK_ELEMENTAL;
                    o2.tOption = 15; //si.getValue(subTime, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Blind, o2);  //To Show the Stack Effect

                    //mts.createAndAddBurnedInfo(chr.getId(), skill, 1); //TODO Uncomment gives NPE

                    //handle Vitality Siphon
                    if (chr.hasSkill(VITALITY_SIPHON)) {
                        handleSiphonVitality(getOriginalSkillByID(skillID), tsm, c);
                    }
                }
            }
        }
    }

    private int getMaxDarkEleStack() {
        int maxStack = 2;
        if(chr.hasSkill(DARK_ELEMENTAL)) {
            maxStack = 2;
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS)) {
            maxStack += 1;
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS_II)) {
            maxStack += 1;
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS_III)) {
            maxStack += 1;
        }
        return maxStack;
    }

    private int getDarkEleProp() {
        SkillInfo dei = SkillData.getSkillInfoById(DARK_ELEMENTAL);
        SkillInfo ad1 = SkillData.getSkillInfoById(ADAPTIVE_DARKNESS);
        SkillInfo ad2 = SkillData.getSkillInfoById(ADAPTIVE_DARKNESS_II);
        SkillInfo ad3 = SkillData.getSkillInfoById(ADAPTIVE_DARKNESS_III);
        int prop = dei.getValue(SkillStat.prop, dei.getCurrentLevel());
        if(chr.hasSkill(DARK_ELEMENTAL)) {
            prop = dei.getValue(SkillStat.prop, dei.getCurrentLevel());
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS)) {
            prop += ad1.getValue(SkillStat.prop, ad1.getCurrentLevel());
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS_II)) {
            prop += ad2.getValue(SkillStat.prop, ad1.getCurrentLevel());
        }
        if(chr.hasSkill(ADAPTIVE_DARKNESS_III)) {
            prop += ad3.getValue(SkillStat.prop, ad1.getCurrentLevel());
        }
        return prop;
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }


    private void handleSiphonVitality(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo siphonInfo = SkillData.getSkillInfoById(VITALITY_SIPHON);
        Skill skill = chr.getSkill(VITALITY_SIPHON);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if(tsm.hasStat(ElementDarkness)) {
            if (tsm.hasStat(SiphonVitality)) {
                amount = tsm.getOption(SiphonVitality).nOption;
                if (amount < getMaxSiphon(chr)) {
                    amount++;
                }
            }
            o.nOption = amount;
            o.rOption = VITALITY_SIPHON;
            o.tOption = siphonInfo.getValue(time, slv);
            tsm.putCharacterStatValue(SiphonVitality, o);
            o1.nOption = (amount * siphonInfo.getValue(y, slv));
            o1.rOption = VITALITY_SIPHON;
            o1.tOption = siphonInfo.getValue(time, slv);
            tsm.putCharacterStatValue(MaxHP, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private int getMaxSiphon(Char chr) { //TODO Doesn't function correctly      stays at 5 stacks
        Skill skill = null;
        if (chr.hasSkill(14120009)) {
            skill = chr.getSkill(14120009);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isNightWalker(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
