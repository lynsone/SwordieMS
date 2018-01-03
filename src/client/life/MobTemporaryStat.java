package client.life;

import connection.OutPacket;
import enums.MobStat;
import loaders.MobData;
import util.Util;

import java.util.*;


public class MobTemporaryStat {
    private List<BurnedInfo> burnedInfos = new ArrayList<>();
    private String linkTeam;
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

    public MobTemporaryStat deepCopy() {
        MobTemporaryStat copy = new MobTemporaryStat();
        copy.setBurnedInfos(new ArrayList<>());
        for(BurnedInfo bi : getBurnedInfos()) {
            copy.getBurnedInfos().add(bi.deepCopy());
        }
        copy.setLinkTeam(getLinkTeam());
        copy.mobStatComper = getMobStatComper();
        for(MobStat ms : getStatVals().keySet()) {
            for(Map.Entry<String, Integer> entry : getStatVals().get(ms).entrySet()) {
                copy.addStatVal(ms, entry.getKey(), entry.getValue());
            }
        }
        return copy;
    }
    
    public int getSingleStatValue(MobStat mobStat, String name) {
        if(getStatVals().containsKey(mobStat) && getStatVals().get(mobStat).containsKey(name)) {
            return getStatVals().get(mobStat).get(name);
        } else {
            return 0;
        }
    }

    public void encode(OutPacket outPacket) {
        // DecodeBuffer(12) + MobStat::DecodeTemporary
        int[] mask = getMask();
//        for (int m : mask) {
//            outPacket.encodeInt(0);
//        }
        MobStat ms;
        outPacket.encodeInt(0x4000);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(100);
        outPacket.encodeInt(100);
        outPacket.encodeShort(10);
//        outPacket.encodeByte(90);

//        for (Map.Entry<MobStat, Map<String, Integer>> entry : getStatVals().entrySet()) {
//            MobStat mobStat = entry.getKey();
//            Map<String, Integer> values = entry.getValue();
//            switch (mobStat) {
//                case PAD:
//                case PDR:
//                case MAD:
//                case MDR:
//                case ACC:
//                case EVA:
//                case Speed:
//                case Stun:
//                case Freeze:
//                case Poison:
//                case Seal:
//                case Darkness:
//                case PowerUp:
//                case MagicUp:
//                case PGuardUp:
//                case MGuardUp:
//                case PImmune:
//                case MImmune:
//                case Web:
//                case HardSkin:
//                case Ambush:
//                case Venom:
//                case Blind:
//                case SealSkill:
//                case Dazzle:
//                case PCounter:
//                case MCounter:
//                case RiseByToss:
//                case BodyPressure:
//                case Weakness:
//                case Showdown:
//                case MagicCrash:
//                case DamagedElemAttr:
//                case Dark:
//                case Mystery:
//                case AddDamParty:
//                case HitCriDamR:
//                case Fatality:
//                case Lifting:
//                case DeadlyCharge:
//                case Smite:
//                case AddDamSkill:
//                case Incizing:
//                case DodgeBodyAttack:
//                case DebuffHealing:
//                case AddDamSkill2:
//                case BodyAttack:
//                case TempMoveAbility:
//                case FixDamRBuff:
//                case ElementDarkness:
//                case AreaInstallByHit:
//                case BMageDebuff:
//                case JaguarProvoke:
//                case JaguarBleeding:
//                case DarkLightning:
//                case PinkBeanFlowerPot:
//                case BattlePvPHelenaMark:
//                case PsychicLock:
//                case PsychicLockCoolTime:
//                case PsychicGroundMark:
//                case PowerImmune:
//                case PsychicForce:
//                case MultiPMDR:
//                case ElementResetBySummon:
//                case BahamutLightElemAddDam:
//                case BossPropPlus:
//                case MultiDamSkill:
//                case RWLiftPress:
//                case RWChoppingHammer:
//                case TimeBomb:
//                case Treasure:
//                case AddEffect:
//                case Invincible:
//                case Explosion:
//                case HangOver:
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "n" + mobStat.toString()));
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "r" + mobStat.toString()));
//                    outPacket.encodeShort(getSingleStatValue(mobStat, "t" + mobStat.toString()));
//            }
//            if (hasMobStat(PDR)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cPDR"));
//            }
//            if (hasMobStat(MDR)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cMDR"));
//            }
//            if (hasMobStat(PCounter)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wPCounter"));
//            }
//            if (hasMobStat(MCounter)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wMCounter"));
//            }
//            if (hasMobStat(PCounter) || hasMobStat(MCounter)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nCounterProb"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "bCounterDelay"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nAggroRank"));
//            }
//            if (hasMobStat(Fatality)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wFatality"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uFatality"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pFatality"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "yFatality"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "mFatality"));
//            }
//            if (hasMobStat(ExtraBuffStat)) {
//                outPacket.encodeByte(values.size() > 0);
//                if (values.size() > 0) {
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "nPAD"));
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "nMAD"));
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "nPDR"));
//                    outPacket.encodeInt(getSingleStatValue(mobStat, "nMDR"));
//                }
//            }
//            if (hasMobStat(DeadlyCharge)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pDeadlyCharge"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wDeadlyCharge"));
//            }
//            if (hasMobStat(Incizing)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wIncizing"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uIncizing"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pIncizing"));
//            }
//            if (hasMobStat(Speed)) {
//                outPacket.encodeByte(getSingleStatValue(mobStat, "mSpeed"));
//            }
//            if (hasMobStat(BMageDebuff)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cBMageDebuff"));
//            }
//            if (hasMobStat(DarkLightning)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cDarkLightning"));
//            }
//            if (hasMobStat(BattlePvPHelenaMark)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cBattlePvPHelenaMark"));
//            }
//            if (hasMobStat(MultiPMDR)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cMultiPMDR"));
//            }
//            if (hasMobStat(Freeze)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cFreeze"));
//            }
//            if (hasMobStat(BurnedInfo)) {
//                outPacket.encodeByte(getBurnedInfos().size());
//                for (BurnedInfo bi : getBurnedInfos()) {
//                    bi.encode(outPacket);
//                }
//            }
//            if (hasMobStat(InvincibleBalog)) {
//                outPacket.encodeByte(getSingleStatValue(mobStat, "nInvincible"));
//                outPacket.encodeByte(getSingleStatValue(mobStat, "bBalogDisable"));
//            }
//            if (hasMobStat(ExchangeAttack)) {
//                outPacket.encodeByte(getSingleStatValue(mobStat, "bExchangeAttack"));
//            }
//            if (hasMobStat(AddDamParty)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wAddDamParty"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pAddDamParty"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cAddDamParty"));
//            }
//            if (hasMobStat(LinkTeam)) {
//                outPacket.encodeString(getLinkTeam());
//            }
//            if (hasMobStat(SoulExplosion)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nSoulExplosion"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rSoulExplosion"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wSoulExplosion"));
//            }
//            if (hasMobStat(SeperateSoulP)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nSeperateSoulP"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rSeperateSoulP"));
//                outPacket.encodeShort(getSingleStatValue(mobStat, "tSeperateSoulP"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wSeperateSoulP"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uSeperateSoulP"));
//            }
//            if (hasMobStat(SeperateSoulC)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nSeperateSoulC"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rSeperateSoulC"));
//                outPacket.encodeShort(getSingleStatValue(mobStat, "tSeperateSoulC"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wSeperateSoulC"));
//            }
//            if (hasMobStat(Ember)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nEmber"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rEmber"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "tEmber"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wEmber"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uEmber"));
//            }
//            if (hasMobStat(TrueSight)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "tTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uTrueSight"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wTrueSight"));
//            }
//            if (hasMobStat(MultiDamSkill)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cMultiDamSkill"));
//            }
//            if (hasMobStat(Laser)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "nLaser"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "rLaser"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "tLaser"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wLaser"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uLaser"));
//            }
//            if (hasMobStat(ElementResetBySummon)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cElementResetBySummon"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pElementResetBySummon"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "uElementResetBySummon"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "wElementResetBySummon"));
//            }
//            if (hasMobStat(BahamutLightElemAddDam)) {
//                outPacket.encodeInt(getSingleStatValue(mobStat, "pBahamutLightElemAddDam"));
//                outPacket.encodeInt(getSingleStatValue(mobStat, "cBahamutLightElemAddDam"));
//            }
//        }
    }

    public int[] getMask() {
        int[] res = new int[3];
        for(MobStat mobStat : getStatVals().keySet()) {
            res[mobStat.getPosition()] |= mobStat.getVal();
//            System.out.println(mobStat);
        }
//        System.out.println(String.format("Mob stat mask is %d %d %d, in String format:", res[0], res[1], res[2]));
        OutPacket outPacket = new OutPacket();
        for (int i = 0; i < res.length; i++) {
            outPacket.encodeInt(res[i]);
        }
//        System.out.println(Util.readableByteArray(outPacket.getData()));
        return res;
    }

    public static void main(String[] args) {
        MobData.getMobById(100100).getTemporaryStat().getMask();
    }
    
    public boolean hasMobStat(MobStat mobStat) {
        return getStatVals().keySet().contains(mobStat);
    }

    public Map<MobStat, Map<String, Integer>> getStatVals() {
        return statVals;
    }

    public void addStatVal(MobStat mobStat, String name, Integer value) {
        if(getStatVals().containsKey(mobStat)) {
            getStatVals().get(mobStat).put(name, value);
        } else {
            Map<String, Integer> values = new HashMap<>();
            values.put(name, value);
            getStatVals().put(mobStat, values);
        }
    }

    public void addStatValMap(MobStat mobStat, Map<String, Integer> values) {
        getStatVals().put(mobStat, values);
    }

    public List<BurnedInfo> getBurnedInfos() {
        return burnedInfos;
    }

    public void setBurnedInfos(List<MobTemporaryStat.BurnedInfo> burnedInfos) {
        this.burnedInfos = burnedInfos;
    }

    public Comparator getMobStatComper() {
        return mobStatComper;
    }

    public String getLinkTeam() {
        return linkTeam;
    }

    public void setLinkTeam(String linkTeam) {
        this.linkTeam = linkTeam;
    }

    public class BurnedInfo {
        private int characterId, skillId, damage, interval, end, dotAnimation, dotCount, superPos, attackDelay, dotTickIdx, dotTickDamR;

        public BurnedInfo deepCopy() {
            BurnedInfo copy = new BurnedInfo();
            copy.setCharacterId(getCharacterId());
            copy.setSkillId(getSkillId());
            copy.setDamage(getDamage());
            copy.setInterval(getInterval());
            copy.setEnd(getEnd());
            copy.setDotAnimation(getDotAnimation());
            copy.setDotCount(getDotCount());
            copy.setSuperPos(getSuperPos());
            copy.setAttackDelay(getAttackDelay());
            copy.setDotTickIdx(getDotTickIdx());
            copy.setDotTickDamR(getDotTickDamR());
            return copy;
        }

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
}
