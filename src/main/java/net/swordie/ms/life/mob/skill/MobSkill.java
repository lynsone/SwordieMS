package net.swordie.ms.life.mob.skill;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.packet.MobPool;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.swordie.ms.life.mob.skill.MobSkillStat.*;

/**
 * Created on 2/28/2018.
 */
public class MobSkill {
    private static final Logger log = LogManager.getRootLogger();
    private int skillSN;
    private byte action;
    private int level;
    private int effectAfter;
    private int skillAfter;
    private byte priority;
    private boolean firstAttack;
    private boolean onlyFsm;
    private boolean onlyOtherSkill;
    private int skillForbid;
    private int afterDelay;
    private int fixDamR;
    private boolean doFirst;
    private int preSkillIndex;
    private int preSkillCount;
    private String info;
    private String text;
    private boolean afterDead;
    private int afterAttack = -1;
    private int afterAttackCount;
    private int castTime;
    private int coolTime;
    private int delay;
    private int useLimit;
    private String speak;
    private int skillID;
    private int disease;

    public int getSkillSN() {
        return skillSN;
    }

    public void setSkillSN(int skillSN) {
        this.skillSN = skillSN;
    }

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEffectAfter() {
        return effectAfter;
    }

    public void setEffectAfter(int effectAfter) {
        this.effectAfter = effectAfter;
    }

    public int getSkillAfter() {
        return skillAfter;
    }

    public void setSkillAfter(int skillAfter) {
        this.skillAfter = skillAfter;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean getFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public void setOnlyFsm(boolean onlyFsm) {
        this.onlyFsm = onlyFsm;
    }

    public boolean isOnlyFsm() {
        return onlyFsm;
    }

    public void setOnlyOtherSkill(boolean onlyOtherSkill) {
        this.onlyOtherSkill = onlyOtherSkill;
    }

    public boolean isOnlyOtherSkill() {
        return onlyOtherSkill;
    }

    public void setSkillForbid(int skillForbid) {
        this.skillForbid = skillForbid;
    }

    public int getSkillForbid() {
        return skillForbid;
    }

    public void setAfterDelay(int afterDelay) {
        this.afterDelay = afterDelay;
    }

    public int getAfterDelay() {
        return afterDelay;
    }

    public void setFixDamR(int fixDamR) {
        this.fixDamR = fixDamR;
    }

    public int getFixDamR() {
        return fixDamR;
    }

    public void setDoFirst(boolean doFirst) {
        this.doFirst = doFirst;
    }

    public boolean isDoFirst() {
        return doFirst;
    }

    public void setPreSkillIndex(int preSkillIndex) {
        this.preSkillIndex = preSkillIndex;
    }

    public int getPreSkillIndex() {
        return preSkillIndex;
    }

    public void setPreSkillCount(int preSkillCount) {
        this.preSkillCount = preSkillCount;
    }

    public int getPreSkillCount() {
        return preSkillCount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info == null ? "" : info;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setAfterDead(boolean afterDead) {
        this.afterDead = afterDead;
    }

    public boolean isAfterDead() {
        return afterDead;
    }

    public void setAfterAttack(int afterAttack) {
        this.afterAttack = afterAttack;
    }

    public int getAfterAttack() {
        return afterAttack;
    }

    public void setAfterAttackCount(int afterAttackCount) {
        this.afterAttackCount = afterAttackCount;
    }

    public int getAfterAttackCount() {
        return afterAttackCount;
    }

    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public int getCastTime() {
        return castTime;
    }

    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    public int getCoolTime() {
        return coolTime;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setUseLimit(int useLimit) {
        this.useLimit = useLimit;
    }

    public int getUseLimit() {
        return useLimit;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getSpeak() {
        return speak == null ? "" : speak;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobSkill mobSkill = (MobSkill) o;
        return skillSN == mobSkill.skillSN &&
                skillID == mobSkill.skillID &&
                level == mobSkill.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillSN, skillID, level);
    }

    public void applyEffect(Mob mob) {
        MobTemporaryStat mts = mob.getTemporaryStat();
        short skill = (short) getSkillID();
        short slv = (short) getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skill, slv);
        MobSkillID msID = MobSkillID.getMobSkillIDByVal(skill);
        Field field = mob.getField();
        Option o = new Option(skill);
        o.slv = slv;
        o.tOption = msi.getSkillStatIntValue(time);
        Option o2 = new Option(skill);
        o2.slv = slv;
        o2.tOption = msi.getSkillStatIntValue(time);
        Rect rect = null;
        Set<Mob> mobs = new HashSet<>();
        Set<Char> chars = new HashSet<>();
        if (msi.getLt() != null) {
            rect = new Rect(msi.getLt(), msi.getRb());
            if (mob.isFlip()) {
                rect.horizontalFlipAround(mob.getPosition().getX());
            }
            mobs.addAll(mob.getField().getMobsInRect(rect));
            chars.addAll(mob.getField().getCharsInRect(rect));
        }
        switch (msID) {
            case PowerUp:
            case PowerUpM:
            case Pad:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PowerUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.PowerUp, o);
                }
                break;
            case MagicUp:
            case MagicUpM:
            case Mad:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MagicUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.MagicUp, o);
                }
                break;
            case PGuardUp:
            case PGuardUpM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PGuardUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.PGuardUp, o);
                }
                break;
            case MGuardUp:
            case MGuardUpM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MGuardUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.MGuardUp, o);
                }
                break;
            case Haste:
            case HasteM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.Speed, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.Speed, o);
                }
                break;
            case HealM:
                for (Mob m : mobs) {
                    m.heal(msi.getSkillStatIntValue(x));
                }
                break;
            case Seal:
            case Darkness:
            case Weakness:
            case Stun:
            case Curse:
            case Poison:
            case Slow:
            case Undead:
            case Fear:
            case PainMark:
                Char chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr != null) {
                    applyEffect(chr);
                }
                if (msID == MobSkillID.Fear && slv == 26) {
                    // Black Mage P2 mass skill
                }
                break;
            case Dispel:

                break;
            case Attract:

                break;
            case AreaFire:
            case AreaPoison:
            case AreaForce:
            case AreaTimezone:
            case AreaTosp:
            case AreaAbnormal:
            case AreaMobBuff:
            case AreaWarning:
            case AreaForceFromUser:
                AffectedArea aa = AffectedArea.getMobAA(mob, skill, slv, msi);
                field.spawnAffectedArea(aa);
                break;

            case PhysicalImmune:
                if (!mts.hasCurrentMobStat(MobStat.PImmune) && !mts.hasCurrentMobStat(MobStat.MImmune)) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.PImmune, o);
                }
                break;
            case MagicImmune:
                if (!mts.hasCurrentMobStat(MobStat.PImmune) && !mts.hasCurrentMobStat(MobStat.MImmune)) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.MImmune, o);
                }
                break;
            case Teleport:
                int xPos = msi.getSkillStatIntValue(x);
                int yPos = msi.getSkillStatIntValue(y);
                // probably not the right logic
                if (xPos != 0 && yPos != 0) {
                    mob.teleport(xPos, yPos);
                } else {
                    // xPos == skillAfter (both x)
                    mob.getField().getLifeToControllers().get(mob).write(MobPool.teleportRequest(1, null));
                }
                break;
            case PMCounter:
                o.nOption = msi.getSkillStatIntValue(x);
                o.mOption = 100;
                o.bOption = msi.getSkillStatIntValue(MobSkillStat.delay);
                o.wOption = msi.getSkillStatIntValue(y);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PCounter, o);
                break;
            case Magnet:
                o.nOption = msi.getSkillStatIntValue(x);
                for (Char c : chars) {
                    TemporaryStatManager tsm = c.getTemporaryStatManager();
                    tsm.putCharacterStatValue(CharacterTemporaryStat.Magnet, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case Summon:
            case Summon2:
                if (afterDead && mob.getHp() > 0) {
                    break;
                }
                Position spawnPos = mob.getPosition();
                if (msi.getLt() != null) {
                    rect = new Rect(msi.getLt(), msi.getRb());
                    spawnPos = rect.getRandomPositionInside();
                }
                Set<Mob> spawnedMobs = field.getMobs().stream()
                        .filter(m -> m.getMobSpawnerId() == mob.getObjectId())
                        .collect(Collectors.toSet());
                for (int mobId : msi.getInts()) {
                    long spawnedSize = spawnedMobs.stream().filter(m -> m.getTemplateId() == mobId).count();
                    int maxSpawned = msi.getSkillStatIntValue(limit);
                    if (maxSpawned == 0) {
                        maxSpawned = 100;
                    }
                    if (spawnedSize < maxSpawned) {
                        Mob m = mob.getField().spawnMob(mobId, spawnPos.getX(), spawnPos.getY(), false, 0);
                        m.setMobSpawnerId(mob.getObjectId());
                    }
                }
                break;
            case CastingBar:
                field.broadcastPacket(MobPool.castingBarSkillStart(1,
                        msi.getSkillStatIntValue(castingTime), false, false));
                break;
            case BounceAttack:
                mob.getField().broadcastPacket(MobPool.createBounceAttackSkill(mob, msi, false));
                break;
            case LaserAttack:
                if (!mts.hasCurrentMobStat(MobStat.Laser)) {
                    o.nOption = 1;
                    o.wOption = msi.getSkillStatIntValue(w);
                    o.uOption = msi.getSkillStatIntValue(z);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.Laser, o);
                }
                break;
            case LaserControl:
                // Not needed? Automatically handled well by the controller
                break;
            case Unk:
                log.warn(String.format("[MobSkill::applyEffect] Unknown mob skillID %d, slv = %d", skill, slv));
                break;
            default:
                log.warn(String.format("[MobSkill::applyEffect] Unhandled mob skillID %s, slv = %d", msID, getLevel()));
                break;
        }
    }
    public void applyEffect(Char chr) {
        short skill = (short) getSkillID();
        if (skill == 0) {
            skill = (short) getDisease();
        }
        short level = (short) getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skill, level);
        MobSkillID msID = MobSkillID.getMobSkillIDByVal(skill);
        Field field = chr.getField();
        Option o = new Option(skill);
        o.slv = level;
        o.tOption = msi.getSkillStatIntValue(time);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        boolean appliedDisease = Util.succeedProp(msi.getSkillStatIntValue(prop))
                && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr));
        switch (msID) {
            case Seal:
            case Darkness:
            case Weakness:
            case Stun:
            case Curse:
            case Slow:
            case Fear:
                if (appliedDisease) {
                    o.nOption = 1;
                    tsm.putCharacterStatValueFromMobSkill(msID.getAffectedCTS(), o);
                    tsm.sendSetStatPacket();
                }
                break;
            case PainMark:
            case Poison:
                if (appliedDisease) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    tsm.putCharacterStatValueFromMobSkill(msID.getAffectedCTS(), o);
                    tsm.sendSetStatPacket();
                }
                break;
            case Undead:
                if (appliedDisease) {
                    o.nOption = 1;
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Undead, o);
                    TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.Undead);
                    tsb.setNOption(o.nOption);
                    tsb.setROption(skill << level | 16);
                    tsb.setExpireTerm(o.tOption);
                    tsm.sendSetStatPacket();
                }
                break;
            case Unk:
                log.warn(String.format("[MobSkill::applyEffect(Char)] Unknown mob skillID %d, slv = %d", skill, level));
                break;
            default:
                log.warn(String.format("[MobSkill::applyEffect(Char)] Unhandled mob skillID %s, slv = %d", msID, getLevel()));
                break;
        }
    }

    public int getDisease() {
        return disease;
    }

    public void setDisease(int disease) {
        this.disease = disease;
    }
}
