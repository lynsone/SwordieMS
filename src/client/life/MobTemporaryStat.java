package client.life;

import connection.OutPacket;
import enums.MobStat;

import java.util.*;

import static enums.MobStat.*;

public class MobTemporaryStat {
    private List<BurnedInfo> burnedInfos = new ArrayList<>();
    private String linkTeam;

    public String getLinkTeam() {
        return linkTeam;
    }

    public void setLinkTeam(String linkTeam) {
        this.linkTeam = linkTeam;
    }

    public class BurnedInfo {
        private int characterId, skillId, damage, interval, end, dotAnimation, dotCount, superPos, attackDelay, dotTickIdx, dotTickDamR;

        public int getCharacterId() {
            return characterId;
        }

        public void setCharacterId(int characterId) {
            this.characterId = characterId;
        }

        public int getSkillId() {
            return skillId;
        }

        public void setSkillId(int skillId) {
            this.skillId = skillId;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getDotAnimation() {
            return dotAnimation;
        }

        public void setDotAnimation(int dotAnimation) {
            this.dotAnimation = dotAnimation;
        }

        public int getDotCount() {
            return dotCount;
        }

        public void setDotCount(int dotCount) {
            this.dotCount = dotCount;
        }

        public int getSuperPos() {
            return superPos;
        }

        public void setSuperPos(int superPos) {
            this.superPos = superPos;
        }

        public int getAttackDelay() {
            return attackDelay;
        }

        public void setAttackDelay(int attackDelay) {
            this.attackDelay = attackDelay;
        }

        public int getDotTickIdx() {
            return dotTickIdx;
        }

        public void setDotTickIdx(int dotTickIdx) {
            this.dotTickIdx = dotTickIdx;
        }

        public int getDotTickDamR() {
            return dotTickDamR;
        }

        public void setDotTickDamR(int dotTickDamR) {
            this.dotTickDamR = dotTickDamR;
        }

        public void encode(OutPacket outPacket) {
            outPacket.encodeInt(getCharacterId());
            outPacket.encodeInt(getSkillId());
            outPacket.encodeInt(getDamage());
            outPacket.encodeInt(getInterval());
            outPacket.encodeInt(getEnd());
            outPacket.encodeInt(getDotAnimation());
            outPacket.encodeInt(getDotCount());
            outPacket.encodeInt(getSuperPos());
            outPacket.encodeInt(getAttackDelay());
            outPacket.encodeInt(getDotTickIdx());
            outPacket.encodeInt(getDotTickDamR());
        }
    }
    private Comparator mobStatComper = (o1, o2) -> {
        MobStat k1 = (MobStat) o1;
        MobStat k2 = (MobStat) o2;
        int res = 0;
        if(k1.getPosition() < k2.getPosition()) {
            res = -1;
        } else if(k1.getPosition() > k2.getPosition()) {
            res = 1;
        } else {
              if(k1.getVal() < k2.getVal()) {
                  res = -1;
              } else if(k1.getVal() > k2.getVal()) {
                  res = 1;
              }
        }
        return 0;
    };

    private TreeMap statVals = new TreeMap<>(mobStatComper);

    public void encode(OutPacket outPacket) {
        // DecodeBuffer(12) + MobStat::DecodeTemporary
        int[] mask = getMask();
        for (int m : mask) {
            outPacket.encodeInt(m);
        }
        for (Map.Entry<MobStat, Map<String, Integer>> entry : getStatVals().entrySet()) {
            MobStat mobStat = entry.getKey();
            Map<String, Integer> values = entry.getValue();
            switch (mobStat) {
                case PAD:
                case PDR:
                case MAD:
                case MDR:
                case ACC:
                case EVA:
                case Speed:
                case Stun:
                case Freeze:
                case Poison:
                case Seal:
                case Darkness:
                case PowerUp:
                case MagicUp:
                case PGuardUp:
                case MGuardUp:
                case PImmune:
                case MImmune:
                case Web:
                case HardSkin:
                case Ambush:
                case Venom:
                case Blind:
                case SealSkill:
                case Dazzle:
                case PCounter:
                case MCounter:
                case RiseByToss:
                case BodyPressure:
                case Weakness:
                case Showdown:
                case MagicCrash:
                case DamagedElemAttr:
                case Dark:
                case Mystery:
                case AddDamParty:
                case HitCriDamR:
                case Fatality:
                case Lifting:
                case DeadlyCharge:
                case Smite:
                case AddDamSkill:
                case Incizing:
                case DodgeBodyAttack:
                case DebuffHealing:
                case AddDamSkill2:
                case BodyAttack:
                case TempMoveAbility:
                case FixDamRBuff:
                case ElementDarkness:
                case AreaInstallByHit:
                case BMageDebuff:
                case JaguarProvoke:
                case JaguarBleeding:
                case DarkLightning:
                case PinkBeanFlowerPot:
                case BattlePvPHelenaMark:
                case PsychicLock:
                case PsychicLockCoolTime:
                case PsychicGroundMark:
                case PowerImmune:
                case PsychicForce:
                case MultiPMDR:
                case ElementResetBySummon:
                case BahamutLightElemAddDam:
                case BossPropPlus:
                case MultiDamSkill:
                case RWLiftPress:
                case RWChoppingHammer:
                case TimeBomb:
                case Treasure:
                case AddEffect:
                case Invincible:
                case Explosion:
                case HangOver:
                    outPacket.encodeInt(values.get("n" + mobStat.toString()));
                    outPacket.encodeInt(values.get("r" + mobStat.toString()));
                    outPacket.encodeShort(values.get("t" + mobStat.toString()));
            }
            if (hasMobStat(PDR)) {
                outPacket.encodeInt(values.get("cPDR"));
            }
            if (hasMobStat(MDR)) {
                outPacket.encodeInt(values.get("cMDR"));
            }
            if (hasMobStat(PCounter)) {
                outPacket.encodeInt(values.get("wPCounter"));
            }
            if (hasMobStat(MCounter)) {
                outPacket.encodeInt(values.get("wMCounter"));
            }
            if (hasMobStat(PCounter) || hasMobStat(MCounter)) {
                outPacket.encodeInt(values.get("nCounterProb"));
                outPacket.encodeInt(values.get("bCounterDelay"));
                outPacket.encodeInt(values.get("nAggroRank"));
            }
            if (hasMobStat(Fatality)) {
                outPacket.encodeInt(values.get("wFatality"));
                outPacket.encodeInt(values.get("uFatality"));
                outPacket.encodeInt(values.get("pFatality"));
                outPacket.encodeInt(values.get("yFatality"));
                outPacket.encodeInt(values.get("mFatality"));
            }
            if (hasMobStat(ExtraBuffStat)) {
                outPacket.encodeByte(values.size() > 0);
                if (values.size() > 0) {
                    outPacket.encodeInt(values.get("nPAD"));
                    outPacket.encodeInt(values.get("nMAD"));
                    outPacket.encodeInt(values.get("nPDR"));
                    outPacket.encodeInt(values.get("nMDR"));
                }
            }
            if (hasMobStat(DeadlyCharge)) {
                outPacket.encodeInt(values.get("pDeadlyCharge"));
                outPacket.encodeInt(values.get("wDeadlyCharge"));
            }
            if (hasMobStat(Incizing)) {
                outPacket.encodeInt(values.get("wIncizing"));
                outPacket.encodeInt(values.get("uIncizing"));
                outPacket.encodeInt(values.get("pIncizing"));
            }
            if (hasMobStat(Speed)) {
                outPacket.encodeByte(values.get("mSpeed"));
            }
            if (hasMobStat(BMageDebuff)) {
                outPacket.encodeInt(values.get("cBMageDebuff"));
            }
            if (hasMobStat(DarkLightning)) {
                outPacket.encodeInt(values.get("cDarkLightning"));
            }
            if (hasMobStat(BattlePvPHelenaMark)) {
                outPacket.encodeInt(values.get("cBattlePvPHelenaMark"));
            }
            if (hasMobStat(MultiPMDR)) {
                outPacket.encodeInt(values.get("cMultiPMDR"));
            }
            if (hasMobStat(Freeze)) {
                outPacket.encodeInt(values.get("cFreeze"));
            }
            if (hasMobStat(BurnedInfo)) {
                outPacket.encodeByte(getBurnedInfos().size());
                for (BurnedInfo bi : getBurnedInfos()) {
                    bi.encode(outPacket);
                }
            }
            if (hasMobStat(InvincibleBalog)) {
                outPacket.encodeByte(values.get("nInvincible"));
                outPacket.encodeByte(values.get("bBalogDisable"));
            }
            if (hasMobStat(ExchangeAttack)) {
                outPacket.encodeByte(values.get("bExchangeAttack"));
            }
            if (hasMobStat(AddDamParty)) {
                outPacket.encodeInt(values.get("wAddDamParty"));
                outPacket.encodeInt(values.get("pAddDamParty"));
                outPacket.encodeInt(values.get("cAddDamParty"));
            }
            if (hasMobStat(LinkTeam)) {
                outPacket.encodeString(getLinkTeam());
            }
            if (hasMobStat(SoulExplosion)) {
                outPacket.encodeInt(values.get("nSoulExplosion"));
                outPacket.encodeInt(values.get("rSoulExplosion"));
                outPacket.encodeInt(values.get("wSoulExplosion"));
            }
            if (hasMobStat(SeperateSoulP)) {
                outPacket.encodeInt(values.get("nSeperateSoulP"));
                outPacket.encodeInt(values.get("rSeperateSoulP"));
                outPacket.encodeShort(values.get("tSeperateSoulP"));
                outPacket.encodeInt(values.get("wSeperateSoulP"));
                outPacket.encodeInt(values.get("uSeperateSoulP"));
            }
            if (hasMobStat(SeperateSoulC)) {
                outPacket.encodeInt(values.get("nSeperateSoulC"));
                outPacket.encodeInt(values.get("rSeperateSoulC"));
                outPacket.encodeShort(values.get("tSeperateSoulC"));
                outPacket.encodeInt(values.get("wSeperateSoulC"));
            }
            if (hasMobStat(Ember)) {
                outPacket.encodeInt(values.get("nEmber"));
                outPacket.encodeInt(values.get("rEmber"));
                outPacket.encodeInt(values.get("tEmber"));
                outPacket.encodeInt(values.get("wEmber"));
                outPacket.encodeInt(values.get("uEmber"));
            }
            if (hasMobStat(TrueSight)) {
                outPacket.encodeInt(values.get("nTrueSight"));
                outPacket.encodeInt(values.get("rTrueSight"));
                outPacket.encodeInt(values.get("tTrueSight"));
                outPacket.encodeInt(values.get("cTrueSight"));
                outPacket.encodeInt(values.get("pTrueSight"));
                outPacket.encodeInt(values.get("uTrueSight"));
                outPacket.encodeInt(values.get("wTrueSight"));
            }
            if (hasMobStat(MultiDamSkill)) {
                outPacket.encodeInt(values.get("cMultiDamSkill"));
            }
            if (hasMobStat(Laser)) {
                outPacket.encodeInt(values.get("nLaser"));
                outPacket.encodeInt(values.get("rLaser"));
                outPacket.encodeInt(values.get("tLaser"));
                outPacket.encodeInt(values.get("wLaser"));
                outPacket.encodeInt(values.get("uLaser"));
            }
            if (hasMobStat(ElementResetBySummon)) {
                outPacket.encodeInt(values.get("cElementResetBySummon"));
                outPacket.encodeInt(values.get("pElementResetBySummon"));
                outPacket.encodeInt(values.get("uElementResetBySummon"));
                outPacket.encodeInt(values.get("wElementResetBySummon"));
            }
            if (hasMobStat(BahamutLightElemAddDam)) {
                outPacket.encodeInt(values.get("pBahamutLightElemAddDam"));
                outPacket.encodeInt(values.get("cBahamutLightElemAddDam"));
            }
        }
    }

    public int[] getMask() {
        int[] res = new int[3];
        for(MobStat mobStat : getStatVals().keySet()) {
            res[mobStat.getPosition()] |= mobStat.getVal();
        }
        return res;
    }
    
    public boolean hasMobStat(MobStat mobStat) {
        return getStatVals().keySet().contains(mobStat);
    }

    public Map<MobStat, Map<String, Integer>> getStatVals() {
        return statVals;
    }

    public void addStatVal(MobStat mobStat, Map<String, Integer> values) {
        getStatVals().put(mobStat, values);
    }

    public List<BurnedInfo> getBurnedInfos() {
        return burnedInfos;
    }
}
