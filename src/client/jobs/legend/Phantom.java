package client.jobs.legend;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import loaders.SkillData;
import packet.WvsContext;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Phantom extends Job {

    public static final int IMPECCABLE_MEMORY_I = 24001001; //TODO

    public static final int IMPECCABLE_MEMORY_II = 24101001; //TODO
    public static final int CANE_BOOSTER = 24101005; //Buff

    public static final int IMPECCABLE_MEMORY_III = 24111001; //TODO
    public static final int FINAL_FEINT = 24111002; //Buff (Unlimited Duration) Gone upon Death
    public static final int BAD_LUCK_WARD = 24111003; //Buff

    public static final int IMPECCABLE_MEMORY_IV = 24121001; //TODO
    public static final int PRIERE_DARIA = 24121004; //Buff
    public static final int VOL_DAME = 24121007; // Special Buff TODO
    public static final int MAPLE_WARRIOR_PH = 24121008; //Buff


    private final int[] buffs = new int[] {
            IMPECCABLE_MEMORY_I,
            IMPECCABLE_MEMORY_II,
            CANE_BOOSTER,
            IMPECCABLE_MEMORY_III,
            FINAL_FEINT,
            BAD_LUCK_WARD,
            IMPECCABLE_MEMORY_IV,
            PRIERE_DARIA,
            VOL_DAME,
            MAPLE_WARRIOR_PH,
    };

    public Phantom(Char chr) {
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
        switch (skillID) {
            case CANE_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FINAL_FEINT:
                //TODO
                break;
            case BAD_LUCK_WARD:
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o4);
                break;
            case PRIERE_DARIA:
                o1.nOption = si.getValue(damR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nOption = si.getValue(ignoreMobpdpR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time , slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                break;
            case MAPLE_WARRIOR_PH:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
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
                case VOL_DAME:
                    //TODO
                    break;
                case IMPECCABLE_MEMORY_I:
                case IMPECCABLE_MEMORY_II:
                case IMPECCABLE_MEMORY_III:
                case IMPECCABLE_MEMORY_IV:
              //case IMPECCABLE_MEMORY_H
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
            case PHANTOM:
            case PHANTOM1:
            case PHANTOM2:
            case PHANTOM3:
            case PHANTOM4:
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
