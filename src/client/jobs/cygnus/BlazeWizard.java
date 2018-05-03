package client.jobs.cygnus;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.MoveAbility;
import loaders.SkillData;
import packet.UserLocal;
import packet.WvsContext;
import server.EventManager;
import util.Position;
import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class BlazeWizard extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_HARMONY_INT = 10000248;

    public static final int ORBITAL_FLAME = 12001020;
    public static final int GREATER_ORBITAL_FLAME = 12100020;
    public static final int GRAND_ORBITAL_FLAME = 12110020;
    public static final int FINAL_ORBITAL_FLAME = 12120006;

    public static final int ORBITAL_FLAME_ATOM = 12000026;
    public static final int GREATER_ORBITAL_FLAME_ATOM = 12100028;
    public static final int GRAND_ORBITAL_FLAME_ATOM = 12110028;
    public static final int FINAL_ORBITAL_FLAME_ATOM = 12120010;



    public static final int IGNITION = 12101024; //Buff TODO (DoT&AoE)
    public static final int FLASHFIRE = 12101025; //Special Skill //TODO
    public static final int WORD_OF_FIRE = 12101023; //Buff
    public static final int CONTROLLED_BURN = 12101022; //Special Skill //TODO

    public static final int CINDER_MAELSTROM = 12111022; //Special Skill //TODO
    public static final int PHOENIX_RUN = 12111023; //Special Buff //TODO

    public static final int BURNING_CONDUIT = 12121005;
    public static final int FIRES_OF_CREATION_FOX = 12120014; //Buff //TODO give a buff
    public static final int FIRES_OF_CREATION_LION = 12120013; //Buff //TODO give a buff
    public static final int FLAME_BARRIER = 12121003; //Buff //TODO gives Kanna's Flame Barrier
    public static final int CALL_OF_CYGNUS_BW = 12121000; //Buff

    public static final int GLORY_OF_THE_GUARDIANS_BW = 12121053;

    //Flame Elements
    public static final int FLAME_ELEMENT = 12000022;
    public static final int GREATER_FLAME_ELEMENT = 12100026;
    public static final int GRAND_FLAME_ELEMENT = 12110024;
    public static final int FINAL_FLAME_ELEMENT = 12120007;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_INT,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
    };

    private int[] buffs = new int[] {
            IGNITION,
            WORD_OF_FIRE,
            PHOENIX_RUN,
            FIRES_OF_CREATION_FOX,
            FIRES_OF_CREATION_LION,
            FLAME_BARRIER,
            CALL_OF_CYGNUS_BW,
            GLORY_OF_THE_GUARDIANS_BW,
    };

    boolean used;
    Position chrPos;
    int prevmap;
    private HashMap<Mob,ScheduledFuture> hashMap = new HashMap<>();
    private ScheduledFuture schFuture;

    public BlazeWizard(Char chr) {
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
            case WORD_OF_FIRE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FLAME_BARRIER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case CALL_OF_CYGNUS_BW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case IGNITION:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(WizardIgnite, o1);
                break;
            case FIRES_OF_CREATION_FOX:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.FOLLOW.getVal());
                field.spawnSummon(summon);
                break;
            case FIRES_OF_CREATION_LION:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.FOLLOW.getVal());
                field.spawnSummon(summon);
                break;

            case CINDER_MAELSTROM:  //Special Summon    //TODO
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility((byte) 0);
                field.spawnSummon(summon);
                break;

            case GLORY_OF_THE_GUARDIANS_BW:
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
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
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
            handleIgnite(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ORBITAL_FLAME_ATOM:
            case GREATER_ORBITAL_FLAME_ATOM:
            case GRAND_ORBITAL_FLAME_ATOM:
            case FINAL_ORBITAL_FLAME_ATOM:
                summonFlameElement();
                break;
        }
    }

    private void handleIgnite(AttackInfo attackInfo) {  //TODO only registers Explosion attack if >1 mob is hit
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(tsm.hasStat(WizardIgnite)) {
            Skill skill = chr.getSkill(IGNITION);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    //mts.createAndAddBurnedInfo(chr.getId(), skill, 1);


                    if(hashMap.get(mob) != null && !hashMap.get(mob).isDone()) {
                        hashMap.get(mob).cancel(true);
                    }

                    schFuture = EventManager.addEvent(() ->
                            c.write(UserLocal.explosionAttack(12100029, mob.getPosition(), mob.getObjectId(), 10)), 10, TimeUnit.SECONDS);

                    hashMap.put(mob, schFuture);


                    o.nOption = 1;
                    o.rOption = skill.getSkillId();
                    o.tOption = 10;
                    o.wOption = 10;
                    mts.addStatOptionsAndBroadcast(MobStat.Ember, o);
                }
            }
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
            switch(skillID) {
                case CINDER_MAELSTROM:
                    //TODO
                    break;
                case FLASHFIRE:
                    Position flamepos = chr.getPosition();
                    if(used == true) {
                        if(chr.getFieldID() != prevmap) {
                            //Set Blink
                            prevmap = chr.getFieldID();
                            c.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                            chrPos = chr.getPosition();
                            used = true;
                        } else {
                            //Clear Blink + Teleport
                            c.write(WvsContext.flameWizardFlareBlink(chr, chrPos, true));
                            used = false;
                        }
                    } else {
                        //Set Blink
                        prevmap = chr.getFieldID();
                        c.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                        chrPos = chr.getPosition();
                        used = true;
                    }
                    break;
                case CONTROLLED_BURN:
                    int healmp = si.getValue(x, slv);
                    int healpercent = (chr.getMaxMP() * healmp) / 100;
                    chr.healMP(healpercent);
                    break;
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case BURNING_CONDUIT:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 15);
                    chr.getField().spawnAffectedArea(aa);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBlazeWizard(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    private void summonFlameElement() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.getOptByCTSAndSkill(MAD, getFlameElement()) == null) {
            Option o1 = new Option();
            Skill skill = chr.getSkill(FLAME_ELEMENT);
            SkillInfo si = SkillData.getSkillInfoById(getFlameElement());
            byte slv = (byte) chr.getSkill(getFlameElement()).getCurrentLevel();
            Summon summon;
            Field field;
            field = c.getChr().getField();
            summon = Summon.getSummonBy(chr, getFlameElement(), slv);
            summon.setFlyMob(true);
            summon.setAttackActive(false);
            summon.setAssistType((byte) 0);
            field.spawnSummon(summon);

            o1.nOption = si.getValue(x, slv);
            o1.rOption = getFlameElement();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(MAD, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private int getFlameElement() {
        int skill = 0;
        if(chr.hasSkill(FLAME_ELEMENT)) {
            skill = FLAME_ELEMENT;
        }
        if(chr.hasSkill(GREATER_FLAME_ELEMENT)) {
            skill = GREATER_FLAME_ELEMENT;
        }
        if(chr.hasSkill(GRAND_FLAME_ELEMENT)) {
            skill = GRAND_FLAME_ELEMENT;
        }
        if(chr.hasSkill(FINAL_FLAME_ELEMENT)) {
            skill = FINAL_FLAME_ELEMENT;
        }
        return skill;
    }
}
