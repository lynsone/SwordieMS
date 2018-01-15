package client.jobs.cygnus;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.Stat;
import loaders.SkillData;
import packet.WvsContext;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class BlazeWizard extends Job {

    public static final int IGNITION = 12101024; //Buff TODO (DoT&AoE)
    public static final int FLASHFIRE = 12101025; //Special Skill
    public static final int WORD_OF_FIRE = 12101023; //Buff
    public static final int CONTROLLED_BURN = 121010232; //Special Skill

    public static final int CINDER_MAELSTROM = 12111022; //Special Skill
    public static final int PHOENIX_RUN = 12111023; //Special Buff

    public static final int BURNING_CONDUIT = 12121005; // TODO AoE Skill
    public static final int FIRES_OF_CREATION_FOX = 12120014; //Buff
    public static final int FIRES_OF_CREATION_LION = 12120013; //Buff
    public static final int FLAME_BARRIER = 12121003; //Buff
    public static final int CALL_OF_CYGNUS_BW = 12121000; //Buff

    private int[] buffs = new int[] {
            IGNITION,
            WORD_OF_FIRE,
            PHOENIX_RUN,
            BURNING_CONDUIT,
            FIRES_OF_CREATION_FOX,
            FIRES_OF_CREATION_LION,
            FLAME_BARRIER,
            CALL_OF_CYGNUS_BW,
    };

    public BlazeWizard(Char chr) {
        super(chr);
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
            case FLAME_BARRIER:  //TODO  Explodes afterwards   y = mobCount on Explosion
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case BURNING_CONDUIT: //TODO Area of Effect Buff
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2); //Indie
                break;
            case CALL_OF_CYGNUS_BW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case FIRES_OF_CREATION_FOX:
            case FIRES_OF_CREATION_LION:
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
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

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
                    //TODO
                    break;
                case CONTROLLED_BURN:
                    //TODO
                    break;

            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case NOBLESSE:
            case BLAZEWIZARD1:
            case BLAZEWIZARD2:
            case BLAZEWIZARD3:
            case BLAZEWIZARD4:
                return true;
            default:
                return false;
        }
    }
}
