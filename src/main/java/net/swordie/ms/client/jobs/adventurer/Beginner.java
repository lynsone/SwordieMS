package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.Stat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 12/14/2017.
 */
public class Beginner extends Job {
    public Beginner(Char chr) {
        super(chr);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

        super.handleAttack(c, attackInfo);
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {

    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        return job == JobConstants.JobEnum.BEGINNER;
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public boolean isBuff(int skillID) {
        return false;
    }

    @Override
    public void handleLevelUp() {
        if (chr.getLevel() < 10) {
            chr.addStat(Stat.mhp, 500);
            chr.addStat(Stat.mmp, 500);
            chr.addStat(Stat.str, 5);
            chr.addSpToJobByCurrentLevel(3);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.mhp, chr.getStat(Stat.mhp));
            stats.put(Stat.mmp, chr.getStat(Stat.mmp));
            stats.put(Stat.str, (short) chr.getStat(Stat.str));
            stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
            chr.write(WvsContext.statChanged(stats));
        } else {
            super.handleLevelUp();
        }
    }
}
