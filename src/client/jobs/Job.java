package client.jobs;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.AttackInfo;
import client.character.skills.SkillInfo;
import connection.InPacket;
import enums.Stat;
import loaders.SkillData;
import packet.WvsContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 1/2/2018.
 */
public abstract class Job {
    protected Char chr;
    protected Client c;

    public Job(Char chr) {
        this.chr = chr;
        this.c = chr.getClient();
    }

    public abstract void handleAttack(Client c, AttackInfo attackInfo);

    public abstract void handleSkill(Client c, int skillID, byte slv, InPacket inPacket);

    /**
     * Handles the initial part of a hit, the initial packet processing.
     * @param c The client
     * @param inPacket The packet to be processed
     */
    public void handleHit(Client c, InPacket inPacket){
        inPacket.decodeInt(); // tick
        int idk1 = inPacket.decodeInt();
        byte idk2 = inPacket.decodeByte(); // -1?
        byte idk3 = inPacket.decodeByte();
        int damage = inPacket.decodeInt();
        short idk4 = inPacket.decodeShort();
        int templateID = inPacket.decodeInt();
        int mobID = inPacket.decodeInt();
        HitInfo hitInfo = new HitInfo();
        hitInfo.HPDamage = damage;
        hitInfo.templateID = templateID;
        hitInfo.mobID = mobID;
        handleHit(c, inPacket, hitInfo);
        handleHit(c, hitInfo);
    }

    /**
     * The final part of the hit process. Assumes the correct info (wrt buffs for example) is already in <code>hitInfo</code>.
     * @param c The client
     * @param hitInfo The completed hitInfo
     */
    public void handleHit(Client c, HitInfo hitInfo){
        Char chr = c.getChr();
        int curHP = chr.getStat(Stat.hp);
        int newHP = curHP - hitInfo.HPDamage;
        if(newHP <= 0) {
            // TODO Dying
            curHP = chr.getStat(Stat.mhp);
        } else {
            curHP = newHP;
        }
        Map<Stat, Object> stats = new HashMap<>();
        chr.setStat(Stat.hp, curHP);
        stats.put(Stat.hp, curHP);

        int curMP = chr.getStat(Stat.mp);
        int newMP = curMP - hitInfo.MPDamage;
        if(newMP < 0) {
            // should not happen
            curMP = 0;
        } else {
            curMP = newMP;
        }
        chr.setStat(Stat.mp, curMP);
        stats.put(Stat.mp, curMP);
        c.write(WvsContext.statChanged(stats));
    }

    /**
     * Handles the 'middle' part of hit processing, namely the job-specific stuff like Magic Guard, and puts this info
     * in <code>hitInfo</code>.
     * @param c The client
     * @param inPacket The packet to be processed
     * @param hitInfo The hit info that should be altered if necessary
     */
    public abstract void handleHit(Client c, InPacket inPacket, HitInfo hitInfo);

    public abstract boolean isHandlerOfJob(short id);

    public SkillInfo getInfo(int skillID) {
        return SkillData.getSkillInfoById(skillID);
    }

    protected Char getChar() {
        return chr;
    }

    public abstract int getFinalAttackSkill();

    public void handleLevelUp() {
        chr.addStat(Stat.mhp, 500);
        chr.addStat(Stat.mmp, 500);
        chr.addStat(Stat.ap, 5);
    }

    public abstract boolean isBuff(int skillID);
}
