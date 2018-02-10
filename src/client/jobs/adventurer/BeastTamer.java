package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import loaders.SkillData;
import packet.WvsContext;

/**
 * Created on 12/14/2017.
 */
public class BeastTamer extends Job {

    public static final int HOMEWARD_BOUND = 110001514;

    public static final int BEAR_MODE = 110001501; //MobZoneState?

    private int[] buffs = new int[] {
            BEAR_MODE,
    };

    public BeastTamer(Char chr) {
        super(chr);
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

        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {

    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case BEAST_TAMER:
            case BEAST_TAMER_1:
            case BEAST_TAMER_2:
            case BEAST_TAMER_3:
            case BEAST_TAMER_4:
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
