package client.life;

import client.Client;
import client.character.skills.Option;
import client.character.skills.Skill;
import client.character.skills.SkillInfo;
import connection.OutPacket;
import enums.MobStat;
import loaders.SkillData;
import packet.CField;
import packet.MobPool;
import server.EventManager;

import java.util.*;
import java.util.stream.Collectors;

import static client.character.skills.SkillStat.*;
import static enums.MobStat.*;


public class MobTemporaryStat {
    private List<BurnedInfo> burnedInfos = new ArrayList<>();
    private Map<Integer, Timer> burnCancelTimers = new HashMap<>();
    private Map<Integer, Timer> burnTimers = new HashMap<>();
    private String linkTeam;
    private Comparator mobStatComper = (o1, o2) -> {
        MobStat k1 = (MobStat) o1;
        MobStat k2 = (MobStat) o2;
        int res = 0;
        if (k1.getPosition() < k2.getPosition()) {
            res = -1;
        } else if (k1.getPosition() > k2.getPosition()) {
            res = 1;
        } else {
            if (k1.getVal() < k2.getVal()) {
                res = -1;
            } else if (k1.getVal() > k2.getVal()) {
                res = 1;
            }
        }
        return res;
    };
    private TreeMap<MobStat, Option> currentStatVals = new TreeMap<>(mobStatComper);
    private TreeMap<MobStat, Option> newStatVals = new TreeMap<>(mobStatComper);
    private TreeMap<MobStat, Option> removedStatVals = new TreeMap<>(mobStatComper);
    private Map<MobStat, Timer> timers = new HashMap<>();
    private Mob mob;

    public MobTemporaryStat(Mob mob) {
        this.mob = mob;
    }

    public MobTemporaryStat deepCopy() {
        MobTemporaryStat copy = new MobTemporaryStat(getMob());
        copy.setBurnedInfos(new ArrayList<>());
        for (BurnedInfo bi : getBurnedInfos()) {
            copy.getBurnedInfos().add(bi.deepCopy());
        }
        copy.setLinkTeam(getLinkTeam());
        copy.mobStatComper = getMobStatComper();
        for (MobStat ms : getCurrentStatVals().keySet()) {
            copy.addStatOptions(ms, getCurrentStatVals().get(ms).deepCopy());
        }
        return copy;
    }

    public Option getNewOptionsByMobStat(MobStat mobStat) {
        return getNewStatVals().getOrDefault(mobStat, null);
    }

    public Option getCurrentOptionsByMobStat(MobStat mobStat) {
        return getCurrentStatVals().getOrDefault(mobStat, null);
    }

    public Option getRemovedOptionsByMobStat(MobStat mobStat) {
        return getRemovedStatVals().getOrDefault(mobStat, null);
    }

    public void encode(OutPacket outPacket) {
        // DecodeBuffer(12) + MobStat::DecodeTemporary
        int[] mask = getNewMask();
        for (int i = 0; i < mask.length; i++) {
            outPacket.encodeInt(mask[i]);
        }

        for (Map.Entry<MobStat, Option> entry : getNewStatVals().entrySet()) {
            MobStat mobStat = entry.getKey();
            Option option = entry.getValue();
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
                    outPacket.encodeInt(getNewOptionsByMobStat(mobStat).nOption);
                    outPacket.encodeInt(getNewOptionsByMobStat(mobStat).rOption);
                    outPacket.encodeShort(getNewOptionsByMobStat(mobStat).tOption / 500);
            }
        }
        if (hasNewMobStat(PDR)) {
            outPacket.encodeInt(getNewOptionsByMobStat(PDR).cOption);
        }
        if (hasNewMobStat(MDR)) {
            outPacket.encodeInt(getNewOptionsByMobStat(MDR).cOption);
        }
        if (hasNewMobStat(PCounter)) {
            outPacket.encodeInt(getNewOptionsByMobStat(PCounter).wOption);
        }
        if (hasNewMobStat(MCounter)) {
            outPacket.encodeInt(getNewOptionsByMobStat(MCounter).wOption);
        }
        if (hasNewMobStat(PCounter)) {
            outPacket.encodeInt(getNewOptionsByMobStat(PCounter).mOption); // nCounterProb
            outPacket.encodeInt(getNewOptionsByMobStat(PCounter).bOption); // bCounterDelay
            outPacket.encodeInt(getNewOptionsByMobStat(PCounter).nReason); // nAggroRank
        } else if (hasNewMobStat(MCounter)) {
            outPacket.encodeInt(getNewOptionsByMobStat(MCounter).mOption); // nCounterProb
            outPacket.encodeInt(getNewOptionsByMobStat(MCounter).bOption); // bCounterDelay
            outPacket.encodeInt(getNewOptionsByMobStat(MCounter).nReason); // nAggroRank
        }
        if (hasNewMobStat(Fatality)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Fatality).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Fatality).uOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Fatality).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Fatality).yOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Fatality).mOption);
        }
        if (hasNewMobStat(Explosion)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Explosion).wOption);
        }
        if (hasNewMobStat(ExtraBuffStat)) {
            List<Option> values = getNewOptionsByMobStat(ExtraBuffStat).extraOpts;
            outPacket.encodeByte(values.size() > 0);
            if (values.size() > 0) {
                outPacket.encodeInt(getNewOptionsByMobStat(ExtraBuffStat).extraOpts.get(0).nOption); // nPAD
                outPacket.encodeInt(getNewOptionsByMobStat(ExtraBuffStat).extraOpts.get(0).mOption); // nMAD
                outPacket.encodeInt(getNewOptionsByMobStat(ExtraBuffStat).extraOpts.get(0).xOption); // nPDR
                outPacket.encodeInt(getNewOptionsByMobStat(ExtraBuffStat).extraOpts.get(0).yOption); // nMDR
            }
        }
        if (hasNewMobStat(DeadlyCharge)) {
            outPacket.encodeInt(getNewOptionsByMobStat(DeadlyCharge).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(DeadlyCharge).pOption);
        }
        if (hasNewMobStat(Incizing)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Incizing).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Incizing).uOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Incizing).pOption);
        }
        if (hasNewMobStat(Speed)) {
            outPacket.encodeByte(getNewOptionsByMobStat(Speed).mOption);
        }
        if (hasNewMobStat(BMageDebuff)) {
            outPacket.encodeInt(getNewOptionsByMobStat(BMageDebuff).cOption);
        }
        if (hasNewMobStat(DarkLightning)) {
            outPacket.encodeInt(getNewOptionsByMobStat(DarkLightning).cOption);
        }
        if (hasNewMobStat(BattlePvPHelenaMark)) {
            outPacket.encodeInt(getNewOptionsByMobStat(BattlePvPHelenaMark).cOption);
        }
        if (hasNewMobStat(MultiPMDR)) {
            outPacket.encodeInt(getNewOptionsByMobStat(MultiPMDR).cOption);
        }
        if (hasNewMobStat(Freeze)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Freeze).cOption);
        }
        if (hasNewMobStat(BurnedInfo)) {
            outPacket.encodeByte(getBurnedInfos().size());
            for (BurnedInfo bi : getBurnedInfos()) {
                bi.encode(outPacket);
            }
        }
        if (hasNewMobStat(InvincibleBalog)) {
            outPacket.encodeByte(getNewOptionsByMobStat(InvincibleBalog).nOption);
            outPacket.encodeByte(getNewOptionsByMobStat(InvincibleBalog).bOption);
        }
        if (hasNewMobStat(ExchangeAttack)) {
            outPacket.encodeByte(getNewOptionsByMobStat(ExchangeAttack).bOption);
        }
        if (hasNewMobStat(AddDamParty)) {
            outPacket.encodeInt(getNewOptionsByMobStat(AddDamParty).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(AddDamParty).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(AddDamParty).cOption);
        }
        if (hasNewMobStat(LinkTeam)) {
            outPacket.encodeString(getLinkTeam());
        }
        if (hasNewMobStat(SoulExplosion)) {
            outPacket.encodeInt(getNewOptionsByMobStat(SoulExplosion).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(SoulExplosion).rOption);
            outPacket.encodeInt(getNewOptionsByMobStat(SoulExplosion).wOption);
        }
        if (hasNewMobStat(SeperateSoulP)) {
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulP).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulP).rOption);
            outPacket.encodeShort(getNewOptionsByMobStat(SeperateSoulP).tOption / 500);
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulP).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulP).uOption);
        }
        if (hasNewMobStat(SeperateSoulC)) {
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulC).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulC).rOption);
            outPacket.encodeShort(getNewOptionsByMobStat(SeperateSoulC).tOption / 500);
            outPacket.encodeInt(getNewOptionsByMobStat(SeperateSoulC).wOption);
        }
        if (hasNewMobStat(Ember)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Ember).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Ember).rOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Ember).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Ember).tOption / 500);
            outPacket.encodeInt(getNewOptionsByMobStat(Ember).uOption);
        }
        if (hasNewMobStat(TrueSight)) {
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).rOption);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).tOption / 500);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).cOption);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).uOption);
            outPacket.encodeInt(getNewOptionsByMobStat(TrueSight).wOption);
        }
        if (hasNewMobStat(MultiDamSkill)) {
            outPacket.encodeInt(getNewOptionsByMobStat(MultiDamSkill).cOption);
        }
        if (hasNewMobStat(Laser)) {
            outPacket.encodeInt(getNewOptionsByMobStat(Laser).nOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Laser).rOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Laser).tOption / 500);
            outPacket.encodeInt(getNewOptionsByMobStat(Laser).wOption);
            outPacket.encodeInt(getNewOptionsByMobStat(Laser).uOption);
        }
        if (hasNewMobStat(ElementResetBySummon)) {
            outPacket.encodeInt(getNewOptionsByMobStat(ElementResetBySummon).cOption);
            outPacket.encodeInt(getNewOptionsByMobStat(ElementResetBySummon).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(ElementResetBySummon).uOption);
            outPacket.encodeInt(getNewOptionsByMobStat(ElementResetBySummon).wOption);
        }
        if (hasNewMobStat(BahamutLightElemAddDam)) {
            outPacket.encodeInt(getNewOptionsByMobStat(BahamutLightElemAddDam).pOption);
            outPacket.encodeInt(getNewOptionsByMobStat(BahamutLightElemAddDam).cOption);
        }
        getNewStatVals().clear();
    }

    private int[] getMaskByCollection(Map<MobStat, Option> map) {
        int[] res = new int[3];
        for (MobStat mobStat : map.keySet()) {
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

    public int[] getNewMask() {
        return getMaskByCollection(getNewStatVals());
    }

    public int[] getCurrentMask() {
        return getMaskByCollection(getCurrentStatVals());
    }

    public int[] getRemovedMask() {
        return getMaskByCollection(getRemovedStatVals());
    }

    public boolean hasNewMobStat(MobStat mobStat) {
        return getNewStatVals().keySet().contains(mobStat);
    }

    public boolean hasCurrentMobStat(MobStat mobStat) {
        return getCurrentStatVals().keySet().contains(mobStat);
    }

    public boolean hasBurnFromSkill(int skillID) {
        return getBurnBySkill(skillID) != null;
    }

    public BurnedInfo getBurnBySkill(int skillID) {
        BurnedInfo res = null;
        for (BurnedInfo bi : getBurnedInfos()) {
            if (bi.getSkillId() == skillID) {
                res = bi;
            }
        }
        return res; // wow no lambda for once
    }

    public boolean hasRemovedMobStat(MobStat mobStat) {
        return getRemovedStatVals().keySet().contains(mobStat);
    }

    public Map<MobStat, Option> getCurrentStatVals() {
        return currentStatVals;
    }

    public TreeMap<MobStat, Option> getNewStatVals() {
        return newStatVals;
    }

    public TreeMap<MobStat, Option> getRemovedStatVals() {
        return removedStatVals;
    }

    public void removeMobStat(MobStat mobStat, Boolean fromTimer) {
        getRemovedStatVals().put(mobStat, getCurrentStatVals().get(mobStat));
        getCurrentStatVals().remove(mobStat);
        getMob().getField().broadcastPacket(MobPool.mobStatReset(getMob(), (byte) 1, false));
        getTimers().remove(mobStat);
        if (!fromTimer && getTimers().containsKey(mobStat)) {
            getTimers().get(mobStat).cancel();
            getTimers().remove(mobStat);
        } else {
            getTimers().remove(mobStat);
        }
    }

    public void removeBurnedInfo(Integer charId, Boolean fromTimer) {
        List<BurnedInfo> biList = getBurnedInfos().stream().filter(bi -> bi.getCharacterId() == charId).collect(Collectors.toList());
        getBurnedInfos().removeAll(biList);
        getRemovedStatVals().put(BurnedInfo, getCurrentOptionsByMobStat(BurnedInfo));
        if (getBurnedInfos().size() == 0) {
            getCurrentStatVals().remove(BurnedInfo);
        }
        getMob().getField().broadcastPacket(MobPool.mobStatReset(getMob(), (byte) 1, false, biList));
        if (!fromTimer) {
            getBurnCancelTimers().get(charId).cancel();
            getBurnCancelTimers().remove(charId);
            getBurnTimers().get(charId).cancel();
            getBurnTimers().remove(charId);
        } else {
            getBurnCancelTimers().remove(charId);
            getBurnTimers().remove(charId);
        }
    }

    public void addStatOptionsAndBroadcast(MobStat mobStat, Option option) {
        addStatOptions(mobStat, option);
        mob.getField().broadcastPacket(MobPool.mobStatSet(getMob(), (short) 0));
    }

    public void addStatOptions(MobStat mobStat, Option option) {
        option.tTerm *= 1000;
        option.tOption *= 1000;
        int tAct = option.tOption > 0 ? option.tOption : option.tTerm;
        getNewStatVals().put(mobStat, option);
        getCurrentStatVals().put(mobStat, option);
        if (tAct > 0 && mobStat != BurnedInfo) {
            if (getTimers().containsKey(mobStat)) {
                getTimers().get(mobStat).cancel();
            }
            Timer t = EventManager.addEvent(this, "removeMobStat", tAct, mobStat, true);
            getTimers().put(mobStat, t);
        }
    }


    public List<BurnedInfo> getBurnedInfos() {
        return burnedInfos;
    }

    public void setBurnedInfos(List<BurnedInfo> burnedInfos) {
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

    public boolean hasNewMovementAffectingStat() {
        return getNewStatVals().keySet().stream().anyMatch(MobStat::isMovementAffectingStat);
    }

    public boolean hasCurrentMovementAffectingStat() {
        return getCurrentStatVals().keySet().stream().anyMatch(MobStat::isMovementAffectingStat);
    }

    public boolean hasRemovedMovementAffectingStat() {
        return getRemovedStatVals().keySet().stream().anyMatch(MobStat::isMovementAffectingStat);
    }

    public Map<MobStat, Timer> getTimers() {
        if (timers == null) {
            timers = new HashMap<>();
        }
        return timers;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    public void clear() {
        for (Timer t : getBurnTimers().values()) {
            t.cancel();
        }
        getBurnTimers().clear();
        for (Timer t : getBurnCancelTimers().values()) {
            t.cancel();
        }
        getBurnCancelTimers().clear();
        for (Timer t : getTimers().values()) {
            t.cancel();
        }
        getTimers().clear();
        getCurrentStatVals().forEach((ms, o) -> removeMobStat(ms, false));
    }

    public void createAndAddBurnedInfo(int charId, Skill skill, int max) {
        BurnedInfo bu = getBurnedInfos().stream().
                filter(b -> b.getSkillId() == skill.getId() && b.getCharacterId() == charId)
                .findFirst().orElse(null);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        BurnedInfo bi = new BurnedInfo();
        bi.setCharacterId(charId);
        bi.setSkillId(skill.getSkillId());
        bi.setDamage(si.getValue(dot, slv));
        bi.setInterval(si.getValue(dotInterval, slv) * 1000);
        int time = si.getValue(dotTime, slv) * 1000;
        bi.setEnd((int) (System.currentTimeMillis() + time));
        bi.setDotCount(time / bi.getInterval());
        bi.setSuperPos(si.getValue(dotSuperpos, slv));
        bi.setAttackDelay(0);
        bi.setDotTickIdx(0);
        bi.setDotTickDamR(si.getValue(dot, slv));
        bi.setDotAnimation(bi.getAttackDelay() + bi.getInterval() + time);
        bi.setStartTime((int) System.currentTimeMillis());
        bi.setLastUpdate((int) System.currentTimeMillis());
        if (bu != null) {
            removeBurnedInfo(charId, false);
        }
        getBurnedInfos().add(bi);
        addStatOptionsAndBroadcast(MobStat.BurnedInfo, new Option());
        Timer t = EventManager.addEvent(this, "removeBurnedInfo", time, charId, true);
        Timer burn = EventManager.addRecurringEvent(getMob(), "damage", bi.getInterval(), bi.getDotCount(),
                (long) bi.getDamage());
        getBurnCancelTimers().put(charId, t);
        getBurnTimers().put(charId, burn);
    }

    public Map<Integer, Timer> getBurnCancelTimers() {
        return burnCancelTimers;
    }

    public Map<Integer, Timer> getBurnTimers() {
        return burnTimers;
    }
}
