package client.life;

import client.character.Char;
import client.character.skills.AttackInfo;
import client.character.skills.Skill;
import client.character.skills.SkillInfo;
import client.jobs.adventurer.Archer;
import client.jobs.adventurer.Magician;
import enums.MobStat;
import loaders.SkillData;
import util.Position;
import util.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/6/2018.
 */
public class AffectedArea extends Life {

    private Rect rect;
    private int charID;
    private int skillID;
    private int force;
    private int option;
    private int elemAttr;
    private int idk;
    private byte slv;
    private byte mobOrigin;
    private short delay;
    private boolean flip;

    public AffectedArea(int objectId) {
        super(objectId);
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getElemAttr() {
        return elemAttr;
    }

    public void setElemAttr(int elemAttr) {
        this.elemAttr = elemAttr;
    }

    public int getIdk() {
        return idk;
    }

    public void setIdk(int idk) {
        this.idk = idk;
    }

    public byte getSlv() {
        return slv;
    }

    public void setSlv(byte slv) {
        this.slv = slv;
    }

    public byte getMobOrigin() {
        return mobOrigin;
    }

    public void setMobOrigin(byte mobOrigin) {
        this.mobOrigin = mobOrigin;
    }

    public short getDelay() {
        return delay;
    }

    public void setDelay(short delay) {
        this.delay = delay;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public static AffectedArea getAffectedArea(AttackInfo attackInfo) {
        AffectedArea aa = new AffectedArea(-1);
        aa.setSkillID(attackInfo.skillId);
        aa.setSlv(attackInfo.slv);
        aa.setElemAttr(attackInfo.elemAttr);
        aa.setForce(attackInfo.force);
        aa.setOption(attackInfo.option);
        return aa;
    }

    public void handleMobInside(Mob mob) {
        Char chr = getField().getCharByID(getCharID());
        if(chr == null) {
            return;
        }
        int skillID = getSkillID();
        Skill skill = chr.getSkill(getSkillID());
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        MobTemporaryStat mts = mob.getTemporaryStat();
        switch(skillID) {
            case Magician.POISON_MIST:
            case Archer.FLAME_SURGE:
                if(!mts.hasBurnFromSkill(skillID)) {
                    mts.createAndAddBurnedInfo(getCharID(), skill, 1);
                }
                break;
        }
    }
}
