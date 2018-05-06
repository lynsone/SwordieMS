package net.swordie.ms.client.life;

import net.swordie.ms.client.character.AvatarLook;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.SkillInfo;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.field.Field;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.EventManager;
import net.swordie.ms.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * Created on 1/6/2018.
 */
public class Summon extends Life {

    private int charID;
    private int skillID;
    private int bulletID;
    private int summonTerm;
    private byte charLevel;
    private byte slv;
    private byte assistType;
    private byte enterType;
    private byte teslaCoilState;
    private boolean flyMob;
    private boolean beforeFirstAttack;
    private boolean jaguarActive;
    private boolean attackActive;
    private short curFoothold;
    private AvatarLook avatarLook;
    List<Position> teslaCoilPositions = new ArrayList<>();
    private byte moveAbility;
    private Position[] kishinPositions = new Position[2];
    private int maxHP;
    private int hp;

    public Summon(int objectId) {
        super(objectId);
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

    public byte getCharLevel() {
        return charLevel;
    }

    public void setCharLevel(byte charLevel) {
        this.charLevel = charLevel;
    }

    public byte getSlv() {
        return slv;
    }

    public void setSlv(byte slv) {
        this.slv = slv;
    }

    public int getBulletID() {
        return bulletID;
    }

    public void setBulletID(int bulletID) {
        this.bulletID = bulletID;
    }

    public int getSummonTerm() {
        return summonTerm;
    }

    public void setSummonTerm(int summonTerm) {
        this.summonTerm = 1000 * summonTerm;
    }

    public byte getAssistType() {
        return assistType;
    }

    public void setAssistType(byte assistType) {
        this.assistType = assistType;
    }

    public byte getEnterType() {
        return enterType;
    }

    public void setEnterType(byte enterType) {
        this.enterType = enterType;
    }

    public byte getTeslaCoilState() {
        return teslaCoilState;
    }

    public void setTeslaCoilState(byte teslaCoilState) {
        this.teslaCoilState = teslaCoilState;
    }

    public boolean isFlyMob() {
        return flyMob;
    }

    public void setFlyMob(boolean flyMob) {
        this.flyMob = flyMob;
    }

    public boolean isBeforeFirstAttack() {
        return beforeFirstAttack;
    }

    public void setBeforeFirstAttack(boolean beforeFirstAttack) {
        this.beforeFirstAttack = beforeFirstAttack;
    }

    public boolean isJaguarActive() {
        return jaguarActive;
    }

    public void setJaguarActive(boolean jaguarActive) {
        this.jaguarActive = jaguarActive;
    }

    public boolean isAttackActive() {
        return attackActive;
    }

    public void setAttackActive(boolean attackActive) {
        this.attackActive = attackActive;
    }

    public short getCurFoothold() {
        return curFoothold;
    }

    public void setCurFoothold(short curFoothold) {
        this.curFoothold = curFoothold;
    }

    public AvatarLook getAvatarLook() {
        return avatarLook;
    }

    public void setAvatarLook(AvatarLook avatarLook) {
        this.avatarLook = avatarLook;
    }

    public List<Position> getTeslaCoilPositions() {
        return teslaCoilPositions;
    }

    public void setTeslaCoilPositions(List<Position> teslaCoilPositions) {
        this.teslaCoilPositions = teslaCoilPositions;
    }

    public byte getMoveAbility() {
        return moveAbility;
    }

    public void setMoveAbility(byte moveAbility) {
        this.moveAbility = moveAbility;
    }

    public static Summon getSummonBy(Char chr, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Summon summon = new Summon(-1);
        summon.setCharID(chr.getId());
        summon.setSkillID(skillID);
        summon.setSlv(slv);
        summon.setSummonTerm(si.getValue(SkillStat.time, slv));
        summon.setCharLevel((byte) chr.getStat(Stat.level));
        summon.setPosition(chr.getPosition().deepCopy());
        summon.setMoveAction((byte) 1);
        summon.setCurFoothold((short) chr.getField().findFootHoldBelow(summon.getPosition()).getId());
        summon.setMoveAbility((byte) 1);
        summon.setAssistType((byte) 1);
        summon.setEnterType((byte) 1);
        summon.setBeforeFirstAttack(false);
        summon.setTemplateId(skillID);
        summon.setAttackActive(true);
        return summon;
    }

    public Position[] getKishinPositions() {
        return kishinPositions;
    }

    public void setKishinPositions(Position[] kishinPositions) {
        this.kishinPositions = kishinPositions;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        Field field = getField();
        if (getSummonTerm() > 0) {
            ScheduledFuture sf = EventManager.addEvent(() -> field.removeLife(getObjectId(), true), getSummonTerm());
            field.addLifeSchedule(this, sf);
        }
        field.broadcastPacket(CField.summonedCreated(getCharID(), this));
    }
}
