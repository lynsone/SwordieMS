package client.jobs;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.AttackInfo;
import client.character.skills.SkillInfo;
import connection.InPacket;
import loaders.SkillData;

/**
 * Created on 1/2/2018.
 */
public abstract class Job {
    protected Char chr;

    public Job(Char chr) {
        this.chr = chr;
    }

    public abstract void handleAttack(Client c, AttackInfo attackInfo);

    public abstract void handleSkill(Client c, int skillID, byte slv, InPacket inPacket);

    public void handleHit(Client c, InPacket inPacket){

    }

    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo){};

    public abstract boolean isHandlerOfJob(short id);

    public SkillInfo getInfo(int skillID) {
        return SkillData.getSkillInfoById(skillID);
    }

    protected Char getChar() {
        return chr;
    }
}
