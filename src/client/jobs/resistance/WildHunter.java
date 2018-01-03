package client.jobs.resistance;

import client.Client;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;

/**
 * Created on 12/14/2017.
 */
public class WildHunter extends Job {
    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short job) {
        return false;
    }
}
