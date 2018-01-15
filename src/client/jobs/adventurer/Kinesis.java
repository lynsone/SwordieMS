package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import connection.InPacket;
import constants.JobConstants;
import enums.ForceAtomEnum;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;

import static client.character.skills.SkillStat.*;
import static client.character.skills.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Kinesis extends Job {
    public final static int PSYCHIC_FORCE = 142001000;
    public final static int MENTAL_SHIELD = 142001007;
    public final static int ESP_BOOSTER = 142001003;
    public final static int ULTIMATE_METAL_PRESS = 142001002;
    public final static int PSYCHIC_BLAST_FWD = 142100000;
    public final static int PSYCHIC_BLAST_DOWN = 142100001;
    public final static int PSYCHIC_ASSAULT_FWD = 142110000;
    public final static int PSYCHIC_ASSAULT_DOWN = 142110001;
    public final static int PSYCHIC_DRAIN = 142101009; // TODO, AffectedArea?
    public final static int PSYCHIC_ARMOR = 142101004;
    public final static int ULTIMATE_DEEP_IMPACT = 142101003;
    public final static int PSYCHIC_BULWARK = 142110009;
    public final static int PURE_POWER = 142101005;
    public final static int PSYCHIC_REINFORCEMENT = 142111008;
    public final static int KINETIC_JAUNT = 142111010;
    public final static int ULTIMATE_TRAINWRECK = 142111007;
    public final static int KINETIC_COMBO = 142110011;
    public final static int MIND_BREAK = 142121004;
    public final static int ULTIMATE_PSYCHIC_SHOT = 142120002;
    public final static int ULTIMATE_BPM = 142120002;
    public final static int PRESIDENTS_ORDERS = 142121016;
    public final static int PSYCHIC_CHARGER = 142121008;
    public final static int TELEPATH_TACTICS = 142121006;
    public final static int MENTAL_TEMPEST = 142121030;

    private final static int MAX_PP = 30;

    private final int[] buffs = new int[]{
            ESP_BOOSTER,
            MENTAL_SHIELD,
            PSYCHIC_ARMOR,
            PURE_POWER,
            PSYCHIC_BULWARK,
            PSYCHIC_REINFORCEMENT,
            PRESIDENTS_ORDERS,
            TELEPATH_TACTICS,
            KINETIC_JAUNT,
    };

    private final int[] nonOrbSkills = new int[] {
            ULTIMATE_METAL_PRESS,
            ULTIMATE_BPM,
            ULTIMATE_DEEP_IMPACT,
            ULTIMATE_PSYCHIC_SHOT,
            ULTIMATE_TRAINWRECK,
            PSYCHIC_FORCE,
            PSYCHIC_DRAIN,
            MENTAL_TEMPEST,
            KINETIC_COMBO,
    };

    public Kinesis(Char chr) {
        super(chr);
        pp = 0;
    }

    private int pp;

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        if(skill == null) {
            switch(attackInfo.skillId) {
                case PSYCHIC_ASSAULT_DOWN:
                    skill = chr.getSkill(PSYCHIC_ASSAULT_FWD);
                    break;
                case PSYCHIC_BLAST_DOWN:
                    skill = chr.getSkill(PSYCHIC_BLAST_FWD);
                    break;
            }
        }
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
            handleOrb(skillID, slv, attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        handlePPAttack(skillID, slv, si);
        switch (attackInfo.skillId) {
            case PSYCHIC_FORCE:
            case PSYCHIC_BLAST_FWD:
            case PSYCHIC_BLAST_DOWN:
            case PSYCHIC_ASSAULT_FWD:
            case PSYCHIC_ASSAULT_DOWN:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                }
                break;
            case MIND_BREAK:
                int count = 0;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob.isBoss()) {
                        count += si.getValue(x, slv);
                    } else {
                        count++;
                    }
                }
                count = count > si.getValue(w, slv) ? si.getValue(w, slv) : count;
                o1.nValue = count * si.getValue(indiePMdR, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o1);
                c.write(WvsContext.temporaryStatSet(tsm));
                break;
        }
    }

    private void handleOrb(int skillID, byte slv, AttackInfo attackInfo) {
        if(Arrays.asList(nonOrbSkills).contains(skillID)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(KINETIC_COMBO);
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                int mobID = mai.mobId;
                ForceAtomEnum fae = ForceAtomEnum.KINESIS_ORB_REAL;
                int curTime = Util.getCurrentTime();
                ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), 15, 15,
                        0, 0, curTime, 0, skillID, new Position(0, 0));
                c.write(CField.createForceAtom(false, 0, chr.getId(), fae.getForceAtomType(), true,
                        mobID, KINETIC_COMBO, fai, null, 0, 0, null, 0, null));
            }
        }
    }

    private void handlePPAttack(int skillID, byte slv, SkillInfo si) {
        if(si == null) {
            return;
        }
        int ppRec = si.getValue(ppRecovery, slv);
        addPP(ppRec);
        int ppCons = si.getValue(ppCon, slv);
        substractPP(ppCons);
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int curTime = (int) System.currentTimeMillis();
        switch (skillID) {
            case ESP_BOOSTER:
//                o1.nValue = si.getValue(indieBooster, slv);
//                o1.nReason = skillID;
//                o1.tStart = curTime;
//                o1.tTerm = si.getValue(time, slv);
//                tsm.putCharacterStatValue(IndieBooster, o1);
                o1.nOption = si.getValue(indieBooster, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                break;
            case MENTAL_SHIELD:
                o1.nOption = si.getValue(x, slv);
                o1.nReason = skillID;
                tsm.putCharacterStatValue(KinesisPsychicEnergeShield, o1);
                break;
            case PSYCHIC_ARMOR:
            case PSYCHIC_BULWARK:
                int t = SkillData.getSkillInfoById(PSYCHIC_ARMOR).getValue(time, slv);
                System.out.println(t);
                o1.nValue = si.getValue(indiePdd, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = t;
                tsm.putCharacterStatValue(IndiePDD, o1);
                o2.nValue = si.getValue(indieMdd, slv);
                o2.nReason = skillID;
                o2.tStart = curTime;
                o2.tTerm = t;
                tsm.putCharacterStatValue(IndieMDD, o2);
                o3.nOption = si.getValue(stanceProp, slv);
                o3.rOption = skillID;
                o3.tOption = t;
                tsm.putCharacterStatValue(IndieStance, o3);
                break;
            case PURE_POWER:
                o1.nValue = si.getValue(indieDamR, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case PSYCHIC_REINFORCEMENT:
                o1.nValue = si.getValue(indieMadR, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMADR, o1);
                break;
            case PRESIDENTS_ORDERS:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case TELEPATH_TACTICS:
                o1.nValue = si.getValue(indieMad, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMADR, o1);
                o2.nValue = si.getValue(indieDamR, slv);
                o2.nReason = skillID;
                o2.tStart = curTime;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;
            case KINETIC_JAUNT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NewFlying, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            switch(skillID) {
                case PSYCHIC_CHARGER:
                    int add = (MAX_PP - getPp()) / 2;
                    addPP(add);
                    break;
            }
        }
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }


    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(KinesisPsychicShield)) {
            hitInfo.HPDamage = (int) (hitInfo.HPDamage * (tsm.getOption(KinesisPsychicEnergeShield).nOption / 100D));
            substractPP(1);
        }
        if(getPp() <= 0) {
            tsm.removeStat(KinesisPsychicShield, false);
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case KINESIS_0:
            case KINESIS_1:
            case KINESIS_2:
            case KINESIS_3:
            case KINESIS_4:
                return true;
            default:
                return false;
        }
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public void addPP(int amount) {
        pp = pp + amount > MAX_PP ? MAX_PP : pp + amount;
        sendPPPacket();
    }

    public void substractPP(int amount) {
        pp = pp - amount < 0 ? 0 : pp - amount;
        sendPPPacket();
    }

    private void sendPPPacket() {
        Option o = new Option();
        o.nOption = pp;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.putCharacterStatValue(KinesisPsychicPoint, o);
        chr.getClient().write(WvsContext.temporaryStatSet(tsm));
    }
}
