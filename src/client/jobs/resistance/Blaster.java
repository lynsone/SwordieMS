package client.jobs.resistance;

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

import static client.character.skills.CharacterTemporaryStat.Booster;
import static client.character.skills.CharacterTemporaryStat.IndieStatR;
import static client.character.skills.CharacterTemporaryStat.RWCylinder;
import static client.character.skills.SkillStat.time;
import static client.character.skills.SkillStat.x;

/**
 * Created on 12/14/2017.
 */
public class Blaster extends Job {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int REVOLVING_CANNON = 37001001;
    public static final int REVOLVING_CANNON_RELOAD = 37000010;
    public static final int HAMMER_SMASH = 37111000;
    public static final int HAMMER_SMASH_CHARGE = 37110001;

    public static final int ARM_CANNON_BOOST = 37101003;
    public static final int MAPLE_WARRIOR_BLASTER = 37121006;

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
    };

    private int[] buffs = new int[] {
            ARM_CANNON_BOOST,
            MAPLE_WARRIOR_BLASTER,
    };
    private int ammo = getMaxAmmo();

    private int gauge = 0;

    public Blaster(Char chr) {
        super(chr);
        updateCylinder();
        for (int id : addedSkills) {
            if (!chr.hasSkill(id)) {
                Skill skill = SkillData.getSkillDeepCopyById(id);
                skill.setCurrentLevel(skill.getMasterLevel());
                chr.addSkill(skill);
            }
        }
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
            switch(skillID) {
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case ARM_CANNON_BOOST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case MAPLE_WARRIOR_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.BLASTER_1.getJobId() && id <= JobConstants.JobEnum.BLASTER_4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void updateCylinder() {
        Option o = new Option();
        o.nOption = 1;
        o.bOption = ammo;
        o.cOption = gauge;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.putCharacterStatValue(RWCylinder, o);
        chr.getClient().write(WvsContext.temporaryStatSet(tsm));
    }

    private int getMaxAmmo() {
        return chr.hasSkill(37120008) ? 6 :
                chr.hasSkill(37110007) ? 5 :
                chr.hasSkill(37100007) ? 4 :
                3;
    }
}
