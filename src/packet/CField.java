package packet;

import client.life.ForcedMobStat;
import client.life.Mob;
import client.life.ShootingMoveStat;
import connection.OutPacket;
import handling.OutHeader;

public class CField {

    public OutPacket mobEnterField(Mob mob, boolean isOnMap) {
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
        mob.getMobTemporaryStat().encode(outPacket);
        if(!isOnMap) {
            // CMob::Init
            outPacket.encodePoint(mob.getPosition());
            outPacket.encodeByte(mob.getMoveAction());
            outPacket.encodeByte(0); // banban boss?
            outPacket.encodeShort(mob.getCurFoodhold().getId());
            outPacket.encodeShort(mob.getHomeFoothold().getId());
            byte appearType = mob.getAppearType();
            outPacket.encodeByte(appearType);
            if(appearType == -3 || appearType >= 0) {
                // init -> -2, -1 else
                outPacket.encodeInt(mob.getOption());
            }
            outPacket.encodeByte(mob.getTeamForMCarnival());
            outPacket.encodeLong(mob.getHp());
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
            int ownerAID = mob.getLifeReleaseOwnerAID();
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
        }
        return outPacket;
    }
}
