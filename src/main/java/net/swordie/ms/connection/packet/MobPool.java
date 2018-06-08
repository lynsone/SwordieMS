package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.life.mob.*;
import net.swordie.ms.life.mob.skill.BurnedInfo;
import net.swordie.ms.life.mob.skill.ShootingMoveStat;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;

import java.util.List;

/**
 * Created on 2/28/2018.
 */
public class MobPool {
    public static OutPacket mobEnterField(Mob mob, boolean hasBeenInit) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_ENTER_FIELD);

        outPacket.encodeByte(mob.isSealedInsteadDead());
        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeByte(mob.getCalcDamageIndex());
        outPacket.encodeInt(mob.getTemplateId());
        ForcedMobStat fms = mob.getForcedMobStat();
        outPacket.encodeByte(fms != null);
        if(fms != null) {
            fms.encode(outPacket);
        }
        mob.getTemporaryStat().encode(outPacket);
        if(!hasBeenInit) {
            // CMob::Init
            outPacket.encodePosition(mob.getPosition());
            outPacket.encodeByte(mob.getMoveAction());
//            outPacket.encodeByte(0); // banban boss?
            outPacket.encodeShort(mob.getCurFoodhold().getId());
            outPacket.encodeShort(mob.getHomeFoothold().getId());
            byte appearType = mob.getAppearType();
            outPacket.encodeShort(appearType);
            if(appearType == -3 || appearType >= 0) {
                // init -> -2, -1 else
                outPacket.encodeInt(mob.getOption());
            }
            outPacket.encodeByte(mob.getTeamForMCarnival());
            outPacket.encodeInt(mob.getHp() > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mob.getHp());
            outPacket.encodeInt(mob.getEffectItemID());
            if(mob.isPatrolMob()) {
                outPacket.encodeInt(mob.getPatrolScopeX1());
                outPacket.encodeInt(mob.getPatrolScopeX2());
                outPacket.encodeInt(mob.getDetectX());
                outPacket.encodeInt(mob.getSenseX());
            }
            outPacket.encodeInt(mob.getPhase());
            outPacket.encodeInt(mob.getCurZoneDataType());
            outPacket.encodeInt(mob.getRefImgMobID());
            outPacket.encodeInt(0); // ?
            int ownerAID = mob.getLifeReleaseOwnerAID();
            outPacket.encodeByte(ownerAID > 0);
            if(ownerAID > 0) {
                outPacket.encodeInt(ownerAID);
                outPacket.encodeString(mob.getLifeReleaseOwnerName());
                outPacket.encodeString(mob.getLifeReleaseMobName());
            }
            outPacket.encodeInt(mob.getAfterAttack());
            outPacket.encodeInt(mob.getCurrentAction());
            outPacket.encodeByte(mob.isLeft());
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // ?
                outPacket.encodeInt(0); // extra time?
            }
            outPacket.encodeInt(mob.getScale());
            outPacket.encodeInt(mob.getEliteGrade());
            if(mob.getEliteGrade() >= 0) {
                outPacket.encodeInt(mob.getEliteSkills().size());
                for (Tuple<Integer, Integer> skill : mob.getEliteSkills()) {
                    outPacket.encodeInt(skill.getLeft()); // skill id
                    outPacket.encodeInt(skill.getRight()); // skill level
                }
                outPacket.encodeInt(mob.getEliteType()); // 1 normal, 3 elite boss probably
            }
            ShootingMoveStat sms = mob.getShootingMoveStat();
            outPacket.encodeByte(sms != null);
            if(sms != null) {
                sms.encode(outPacket);
            }
            size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // nType
                outPacket.encodeInt(0); // key?
            }
            outPacket.encodeInt(mob.getTargetUserIdFromServer());
            outPacket.encodeInt(0);
        }
        return outPacket;
    }

    public static OutPacket mobChangeController(Mob mob, boolean hasBeenInit, boolean isController) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CHANGE_CONTROLLER);
        outPacket.encodeByte(isController);
        outPacket.encodeInt(mob.getObjectId());
        if(isController) {
            outPacket.encodeByte(mob.getCalcDamageIndex());
            outPacket.encodeInt(mob.getTemplateId());
            ForcedMobStat fms = mob.getForcedMobStat();
            outPacket.encodeByte(fms != null);
            if(fms != null) {
                fms.encode(outPacket);
            }
            mob.getTemporaryStat().encode(outPacket);
            if(!hasBeenInit) {
                // CMob::Init
                outPacket.encodePosition(mob.getPosition());
                outPacket.encodeByte(mob.getMoveAction());
//                outPacket.encodeByte(0); // banban boss?
                outPacket.encodeShort(mob.getCurFoodhold().getId());
                outPacket.encodeShort(mob.getHomeFoothold().getId());
                byte appearType = mob.getAppearType();
                outPacket.encodeShort(appearType);
                if(appearType == -3 || appearType >= 0) {
                    // init -> -2, -1 else
                    outPacket.encodeInt(mob.getOption());
                }
                outPacket.encodeByte(mob.getTeamForMCarnival());
                outPacket.encodeInt(mob.getHp() > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mob.getHp());
                outPacket.encodeInt(mob.getEffectItemID());
                if(mob.isPatrolMob()) {
                    outPacket.encodeInt(mob.getPatrolScopeX1());
                    outPacket.encodeInt(mob.getPatrolScopeX2());
                    outPacket.encodeInt(mob.getDetectX());
                    outPacket.encodeInt(mob.getSenseX());
                }
                outPacket.encodeInt(mob.getPhase());
                outPacket.encodeInt(mob.getCurZoneDataType());
                outPacket.encodeInt(mob.getRefImgMobID());
                outPacket.encodeInt(0); // ?
                int ownerAID = mob.getLifeReleaseOwnerAID();
                outPacket.encodeByte(ownerAID > 0);
                if(ownerAID > 0) {
                    outPacket.encodeInt(ownerAID);
                    outPacket.encodeString(mob.getLifeReleaseOwnerName());
                    outPacket.encodeString(mob.getLifeReleaseMobName());
                }
                outPacket.encodeInt(mob.getAfterAttack());
                outPacket.encodeInt(mob.getCurrentAction());
                outPacket.encodeByte(mob.isLeft());
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // ?
                    outPacket.encodeInt(0); // extra time?
                }
                outPacket.encodeInt(mob.getScale());
                outPacket.encodeInt(mob.getEliteGrade());
                if(mob.getEliteGrade() >= 0) {
                    size = 0;
                    outPacket.encodeInt(size);
                    for (int i = 0; i < size; i++) {
                        outPacket.encodeInt(0); // first skillID?
                        outPacket.encodeInt(0); // second skillID?
                    }
                    outPacket.encodeInt(mob.getEliteType()); // 1 normal, 3 elite boss probably
                }
                ShootingMoveStat sms = mob.getShootingMoveStat();
                outPacket.encodeByte(sms != null);
                if(sms != null) {
                    sms.encode(outPacket);
                }
                size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nType
                    outPacket.encodeInt(0); // key?
                }
                outPacket.encodeInt(mob.getTargetUserIdFromServer());
                outPacket.encodeInt(0);
            }
        }

        return outPacket;
    }

    public static OutPacket mobLeaveField(int id, byte deadType){
        OutPacket outPacket = new OutPacket(OutHeader.MOB_LEAVE_FIELD);

        outPacket.encodeInt(id);
        outPacket.encodeByte(deadType);

        return outPacket;
    }

    public static OutPacket mobDamaged(int mobID, long damage, int templateID, byte type, int hp, int maxHp) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_DAMAGED);

        outPacket.encodeInt(mobID);
        outPacket.encodeByte(type);
        damage = damage > Integer.MAX_VALUE ? Integer.MAX_VALUE : damage;
        outPacket.encodeInt((int) damage);
        if(templateID / 10000 == 250 || templateID / 10000 == 251) {
            outPacket.encodeInt(hp);
            outPacket.encodeInt(maxHp);
        }

        return outPacket;
    }

    public static OutPacket mobHpIndicator(int objectId, byte percDamage) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_HP_INDICATOR);

        outPacket.encodeInt(objectId);
        outPacket.encodeByte(percDamage);

        return outPacket;
    }

    public static OutPacket mobCtrlAck(Mob mob, boolean nextAttackPossible, short moveID, int skillID, byte slv, int forcedAttack) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CONTROL_ACK);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeShort(moveID);
        outPacket.encodeByte(nextAttackPossible);
        outPacket.encodeInt((int) mob.getMp());
        outPacket.encodeInt(skillID);
        outPacket.encodeByte(slv);
        outPacket.encodeInt(forcedAttack);

        return outPacket;
    }

    public static OutPacket mobCtrlChange(Char chr, Mob mob, boolean isController) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CHANGE_CONTROLLER);

        outPacket.encodeByte(isController);
        outPacket.encodeInt(mob.getObjectId());
        if(isController) {
            outPacket.encodeByte(1); // controlling type
        }

        return outPacket;

    }

    public static OutPacket mobStatSet(Mob mob, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_STAT_SET);
        MobTemporaryStat mts = mob.getTemporaryStat();
        boolean hasMovementStat = mts.hasNewMovementAffectingStat();
        outPacket.encodeInt(mob.getObjectId());
        mts.encode(outPacket);
        outPacket.encodeShort(delay);
        outPacket.encodeByte(1); // nCalcDamageStatIndex
        if(hasMovementStat) {
            outPacket.encodeByte(0); // ?
        }

        return outPacket;
    }

    public static OutPacket mobStatReset(Mob mob, byte byteCalcDamageStatIndex, boolean sn) {
        return mobStatReset(mob, byteCalcDamageStatIndex, sn, null);
    }

    public synchronized static OutPacket mobStatReset(Mob mob, byte calcDamageStatIndex, boolean sn, List<BurnedInfo> biList) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_STAT_RESET);
        MobTemporaryStat resetStats = mob.getTemporaryStat();
        int[] mask = resetStats.getRemovedMask();
        outPacket.encodeInt(mob.getObjectId());
        for (int i = 0; i < 3; i++) {
            outPacket.encodeInt(mask[i]);
        }
        if(resetStats.hasRemovedMobStat(MobStat.BurnedInfo)) {
            if(biList == null) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            } else {
                int dotCount = biList.stream().mapToInt(BurnedInfo::getDotCount).sum();
                outPacket.encodeInt(dotCount);
                outPacket.encodeInt(biList.size());
                for(BurnedInfo bi : biList) {
                    outPacket.encodeInt(bi.getCharacterId());
                    outPacket.encodeInt(bi.getSuperPos());
                }
            }
            resetStats.getBurnedInfos().clear();
        }
        outPacket.encodeByte(calcDamageStatIndex);
        if(resetStats.hasRemovedMovementAffectingStat()) {
            outPacket.encodeByte(sn);
        }
        resetStats.getRemovedStatVals().clear();
        return outPacket;
    }

    public static OutPacket mobSpecialEffectBySkill(Mob mob, int skillID, int charId, short hit) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_SPECIAL_EFFECT_BY_SKILL);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(charId);
        outPacket.encodeShort(hit);

        return outPacket;
    }

    public static OutPacket mobAffected(Mob mob, int skillID, int slv, boolean userSkill, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_AFFECTED);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(skillID);
        outPacket.encodeShort(delay);
        outPacket.encodeByte(userSkill);
        outPacket.encodeInt(slv);

        return outPacket;
    }

    public static OutPacket mobMove(Mob mob, MobSkillAttackInfo msai, List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_MOVE);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeByte(msai.actionAndDirMask);
        outPacket.encodeByte(msai.actionAndDir);
        outPacket.encodeInt(msai.targetInfo);
        outPacket.encodeByte(msai.multiTargetForBalls.size());
        for(Position pos : msai.multiTargetForBalls) {
            outPacket.encodePosition(pos);
        }
        outPacket.encodeByte(msai.randTimeForAreaAttacks.size());
        for(short s : msai.randTimeForAreaAttacks) {
            outPacket.encodeShort(s);
        }
        outPacket.encodeInt(msai.encodedGatherDuration);
        outPacket.encodePosition(msai.oldPos);
        outPacket.encodePosition(msai.oldVPos);
        outPacket.encodeByte(movements.size());
        for(Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(0);


        /*
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeInt(encodedGatherDuration);
        outPacket.encodeByte(movements.size());
        for(Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(0);
        */


        return outPacket;
    }
}
