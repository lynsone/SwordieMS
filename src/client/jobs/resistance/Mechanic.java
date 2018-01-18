package client.jobs.resistance;

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
import loaders.SkillData;
import packet.WvsContext;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.Booster;
import static client.character.skills.CharacterTemporaryStat.IndieStatR;
import static client.character.skills.CharacterTemporaryStat.PowerGuard;
import static client.character.skills.SkillStat.time;
import static client.character.skills.SkillStat.x;

/**
 * Created on 12/14/2017.
 */
public class Mechanic extends Job {

    public static final int HUMANOID_MECH = 35001002; //Mech Suit
    public static final int TANK_MECH = 35111003; //Tank Mech Suit

    public static final int MECHANIC_RAGE = 35101006; //Buff
    public static final int PERFECT_ARMOR = 35101007; //Buff (ON/OFF)
    public static final int OPEN_PORTAL_GX9 = 35101005; //Special Skill (Summon/Portal)
    public static final int ROBO_LAUNCHER_RM7 = 35101012; //Summon

    public static final int ROCK_N_SHOCK = 35111002; //Special Summon
    public static final int ROLL_OF_THE_DICE = 35111013; //Special Buff
    public static final int SUPPORT_UNIT_HEX = 35111008; //Summon

    public static final int BOTS_N_TOTS = 35121009; //Special Summon
    public static final int MAPLE_WARRIOR_MECH = 35121007; //Buff

    private int[] buffs = new int[] {
            HUMANOID_MECH,
            TANK_MECH,

            MECHANIC_RAGE,
            PERFECT_ARMOR,
            ROLL_OF_THE_DICE,
            MAPLE_WARRIOR_MECH,

            OPEN_PORTAL_GX9, //Summon
            SUPPORT_UNIT_HEX, //Summon
            ROBO_LAUNCHER_RM7, //Summon
            ROCK_N_SHOCK, //Summon
            BOTS_N_TOTS, //Summon
    };


    public Mechanic(Char chr) {
        super(chr);
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
        Summon summon;
        Field field;
        switch (skillID) {
            case HUMANOID_MECH:
            case TANK_MECH:
                //TODO
                break;

            case MECHANIC_RAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case PERFECT_ARMOR:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0; //(ON/OFF)
                tsm.putCharacterStatValue(PowerGuard, o1);
                break;
            case ROLL_OF_THE_DICE:
                //TODO
                break;
            case MAPLE_WARRIOR_MECH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case OPEN_PORTAL_GX9:
                //TODO
                break;
            case SUPPORT_UNIT_HEX:
                //TODO
                break;
            case ROBO_LAUNCHER_RM7:
                //TODO
                break;
            case ROCK_N_SHOCK:
                //TODO
                break;
            case BOTS_N_TOTS:
                //TODO
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

            }
        }
    }
    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.MECHANIC_1.getJobId() && id <= JobConstants.JobEnum.MECHANIC_4.getJobId();
    }
}
