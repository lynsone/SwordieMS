package client.jobs.sengoku;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {

    //TODO Hayato's Broken

    public Hayato(Char chr) {
        super(chr);
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
        return id == JobConstants.JobEnum.HAYATO.getJobId() ||
                (id >= JobConstants.JobEnum.HAYATO1.getJobId() && id <= JobConstants.JobEnum.HAYATO4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
