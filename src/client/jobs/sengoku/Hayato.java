package client.jobs.sengoku;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import connection.InPacket;
import constants.JobConstants;
import enums.MobStat;
import loaders.SkillData;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {

    //TODO Hayato's Broken
    //TODO SP doesn't get filled in
    //TODO 38s on Character Select Menu

    public static final int QUICK_DRAW = 40011288; //Linked to hayato's Sword Energy
    public static final int SUMMER_RAIN = 40011289; //Attack+Buff
    public static final int MASTER_OF_BLADES = 40010000;
    public static final int SHIMADA_HEART = 40010067;

    public static final int BATTOUJUTSU_ADVANCE = 41001010; //not sure what this skill does

    public static final int KATANA_BOOSTER = 41101005; //Buff
    public static final int MILITARY_MIGHT = 41101003; //Buff

    public static final int IRON_SKIN = 41121003; //Buff
    public static final int AKATSUKI_HERO = 41121005; //Buff
    public static final int TORNADO_BLADE = 41121017; //Attack (Stun Debuff)

    private int[] addedSkills = new int[] {
            QUICK_DRAW,
            SUMMER_RAIN,
            MASTER_OF_BLADES,
            SHIMADA_HEART,
    };

    private int[] buffs = new int[] {
            QUICK_DRAW,
            SUMMER_RAIN,
            BATTOUJUTSU_ADVANCE,
            KATANA_BOOSTER,
            MILITARY_MIGHT,
            IRON_SKIN,
            AKATSUKI_HERO,
    };

    public Hayato(Char chr) {
        super(chr);
        for (int id : addedSkills) {
            if (!chr.hasSkill(id)) {
                Skill skill = SkillData.getSkillDeepCopyById(id);
                skill.setCurrentLevel(skill.getMasterLevel());
                chr.addSkill(skill);
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
        Option o4 = new Option();
        Option o5 = new Option();
        switch (skillID) {
            case QUICK_DRAW:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(BladeStance, o1);
                break;
            case KATANA_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SUMMER_RAIN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                break;
            case MILITARY_MIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(y, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o1); //Indie
                o3.nOption = si.getValue(speed, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o3);
                o4.nOption = si.getValue(jump, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o4);
                o5.nOption = si.getValue(padX, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PAD, o5);
                break;
            case IRON_SKIN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o1);
                break;
            case AKATSUKI_HERO:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case TORNADO_BLADE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(subProp, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;

        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {

    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.HAYATO.getJobId() ||
                (id >= JobConstants.JobEnum.HAYATO1.getJobId() && id <= JobConstants.JobEnum.HAYATO4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
