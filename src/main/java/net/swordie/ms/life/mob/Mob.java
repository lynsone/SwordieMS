package net.swordie.ms.life.mob;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.ExpIncreaseInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.jobs.adventurer.Magician;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyDamageInfo;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.MobPool;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.EliteState;
import net.swordie.ms.enums.TextEffectType;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.skill.MobSkill;
import net.swordie.ms.life.mob.skill.ShootingMoveStat;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Mob extends Life {

    private boolean sealedInsteadDead, patrolMob;
    private int option, effectItemID, patrolScopeX1, patrolScopeX2, detectX, senseX, phase, curZoneDataType;
    private int refImgMobID, lifeReleaseOwnerAID, afterAttack, currentAction, scale, eliteGrade, eliteType, targetUserIdFromServer;
    private long hp;
    private long mp;
    private byte calcDamageIndex = 1, moveAction, appearType, teamForMCarnival;
    private Position prevPos;
    private Foothold curFoodhold;
    private Foothold homeFoothold;
    private String lifeReleaseOwnerName = "", lifeReleaseMobName = "";
    private ShootingMoveStat shootingMoveStat;
    private ForcedMobStat forcedMobStat;
    private MobTemporaryStat temporaryStat;
    private int firstAttack;
    private int summonType;
    private int category;
    private String mobType = "";
    private int link;
    private double fs;
    private String elemAttr = "";
    private int hpTagColor;
    private int hpTagBgcolor;
    private boolean HPgaugeHide;
    private int rareItemDropLevel;
    private boolean boss;
    private int hpRecovery;
    private int mpRecovery;
    private boolean undead;
    private int mbookID;
    private boolean noRegen;
    private int chaseSpeed;
    private int explosiveReward;
    private int flySpeed;
    private boolean invincible;
    private boolean hideName;
    private boolean hideHP;
    private String changeableMobType = "";
    private boolean changeable;
    private boolean noFlip;
    private boolean tower;
    private boolean partyBonusMob;
    private int wp;
    private boolean useReaction;
    private boolean publicReward;
    private boolean minion;
    private boolean forward;
    private boolean isRemoteRange;
    private boolean ignoreFieldOut;
    private boolean ignoreMoveImpact;
    private int summonEffect;
    private boolean skeleton;
    private boolean hideUserDamage;
    private int fixedDamage;
    private boolean individualReward;
    private int removeAfter;
    private boolean notConsideredFieldSet;
    private String fixedMoveDir = "";
    private boolean noDoom;
    private boolean useCreateScript;
    private boolean knockback;
    private boolean blockUserMove;
    private int bodyDisease;
    private int bodyDiseaseLevel;
    private int point;
    private int partyBonusR;
    private boolean removeQuest;
    private int passiveDisease;
    private int coolDamageProb;
    private int coolDamage;
    private int damageRecordQuest;
    private int sealedCooltime;
    private int willEXP;
    private boolean onFieldSetSummon;
    private boolean userControll;
    private boolean noDebuff;
    private boolean targetFromSvr;
    private int charismaEXP;
    private boolean isSplit;
    private int splitLink;
    private Map<Char, Long> damageDone = new HashMap<>();
    private Set<DropInfo> drops = new HashSet<>();
    private List<MobSkill> skills = new ArrayList<>();
    private Set<Integer> quests = new HashSet<>();
    private Set<Integer> revives = new HashSet<>();
    private Map<Integer, Long> skillCooldowns = new HashMap<>();
    private long nextPossibleSkillTime = 0;
    private List<Tuple<Integer, Integer>> eliteSkills = new ArrayList<>();
    private boolean selfDestruction;

    public Mob(int templateId, int objectId) {
        super(objectId);
        super.templateId = templateId;
        forcedMobStat = new ForcedMobStat();
        temporaryStat = new MobTemporaryStat(this);
        scale = 100;
        calcDamageIndex = 1;
    }

    public Mob deepCopy() {
        Mob copy = new Mob(getTemplateId(), getObjectId());
        // start life
        copy.setLifeType(getLifeType());
        copy.setTemplateId(getTemplateId());
        copy.setX(getX());
        copy.setY(getY());
        copy.setMobTime(getMobTime());
        copy.setF(getF());
        copy.setHide(isHide());
        copy.setFh(getFh());
        copy.setCy(getCy());
        copy.setRx0(getRx0());
        copy.setRx1(getRx1());
        copy.setLimitedName(getLimitedName());
        copy.setUseDay(isUseDay());
        copy.setUseNight(isUseNight());
        copy.setHold(isHold());
        copy.setNoFoothold(isNoFoothold());
        copy.setDummy(isDummy());
        copy.setSpine(isSpine());
        copy.setMobTimeOnDie(isMobTimeOnDie());
        copy.setRegenStart(getRegenStart());
        copy.setMobAliveReq(getMobAliveReq());
        // end life
        copy.setSealedInsteadDead(isSealedInsteadDead());
        copy.setPatrolMob(isPatrolMob());
        copy.setOption(getOption());
        copy.setEffectItemID(getEffectItemID());
        copy.setPatrolScopeX1(getPatrolScopeX1());
        copy.setPatrolScopeX2(getPatrolScopeX2());
        copy.setDetectX(getDetectX());
        copy.setSenseX(getSenseX());
        copy.setPhase(getPhase());
        copy.setCurZoneDataType(getCurZoneDataType());
        copy.setRefImgMobID(getRefImgMobID());
        copy.setLifeReleaseOwnerAID(getLifeReleaseOwnerAID());
        copy.setAfterAttack(getAfterAttack());
        copy.setCurrentAction(getCurrentAction());
        copy.setScale(getScale());
        copy.setEliteGrade(getEliteGrade());
        copy.setEliteType(getEliteType());
        copy.setTargetUserIdFromServer(getTargetUserIdFromServer());
        copy.setHp(getHp());
        copy.setMaxHp(getMaxHp());
        copy.setCalcDamageIndex(getCalcDamageIndex());
        copy.setMoveAction(getMoveAction());
        copy.setAppearType(getAppearType());
        copy.setTeamForMCarnival(getTeamForMCarnival());
        if (getPrevPos() != null) {
            copy.setPrevPos(getPrevPos().deepCopy());
        }
        if (getCurFoodhold() != null) {
            copy.setCurFoodhold(getCurFoodhold().deepCopy());
        }
        if (getHomeFoothold() != null) {
            copy.setHomeFoothold(getHomeFoothold().deepCopy());
        }
        copy.setLifeReleaseOwnerName(getLifeReleaseOwnerName());
        copy.setLifeReleaseMobName(getLifeReleaseMobName());
        copy.setShootingMoveStat(null);
        if (getForcedMobStat() != null) {
            copy.setForcedMobStat(getForcedMobStat().deepCopy());
        }
        if (getTemporaryStat() != null) {
            copy.setTemporaryStat(getTemporaryStat().deepCopy());
        }
        copy.setFirstAttack(getFirstAttack());
        copy.setSummonType(getSummonType());
        copy.setCategory(getCategory());
        copy.setMobType(getMobType());
        copy.setLink(getLink());
        copy.setFs(getFs());
        copy.setElemAttr(getElemAttr());
        copy.setHpTagColor(getHpTagColor());
        copy.setHpTagBgcolor(getHpTagBgcolor());
        copy.setHPgaugeHide(isHPgaugeHide());
        copy.setRareItemDropLevel(getRareItemDropLevel());
        copy.setBoss(isBoss());
        copy.setHpRecovery(getHpRecovery());
        copy.setMpRecovery(getMpRecovery());
        copy.setUndead(isUndead());
        copy.setMbookID(getMbookID());
        copy.setNoRegen(isNoRegen());
        copy.setChaseSpeed(getChaseSpeed());
        copy.setExplosiveReward(getExplosiveReward());
        copy.setFlySpeed(getFlySpeed());
        copy.setInvincible(isInvincible());
        copy.setHideName(isHideName());
        copy.setHideHP(isHideHP());
        copy.setChangeableMobType(getChangeableMobType());
        copy.setChangeable(isChangeable());
        copy.setNoFlip(isNoFlip());
        copy.setTower(isTower());
        copy.setPartyBonusMob(isPartyBonusMob());
        copy.setWp(getWp());
        copy.setUseReaction(isUseReaction());
        copy.setPublicReward(isPublicReward());
        copy.setMinion(isMinion());
        copy.setForward(isForward());
        copy.setIsRemoteRange(isRemoteRange());
        copy.setIgnoreFieldOut(isIgnoreFieldOut());
        copy.setIgnoreMoveImpact(isIgnoreMoveImpact());
        copy.setSummonEffect(getSummonEffect());
        copy.setSkeleton(isSkeleton());
        copy.setHideUserDamage(isHideUserDamage());
        copy.setFixedDamage(getFixedDamage());
        copy.setIndividualReward(isIndividualReward());
        copy.setRemoveAfter(getRemoveAfter());
        copy.setNotConsideredFieldSet(isNotConsideredFieldSet());
        copy.setFixedMoveDir(getFixedMoveDir());
        copy.setNoDoom(isNoDoom());
        copy.setUseCreateScript(isUseCreateScript());
        copy.setKnockback(isKnockback());
        copy.setBlockUserMove(isBlockUserMove());
        copy.setBodyDisease(getBodyDisease());
        copy.setBodyDiseaseLevel(getBodyDiseaseLevel());
        copy.setPoint(getPoint());
        copy.setPartyBonusR(getPartyBonusR());
        copy.setRemoveQuest(isRemoveQuest());
        copy.setPassiveDisease(getPassiveDisease());
        copy.setCoolDamageProb(getCoolDamageProb());
        copy.setCoolDamage(getCoolDamage());
        copy.setDamageRecordQuest(getDamageRecordQuest());
        copy.setSealedCooltime(getSealedCooltime());
        copy.setWillEXP(getWillEXP());
        copy.setOnFieldSetSummon(isOnFieldSetSummon());
        copy.setUserControll(isUserControll());
        copy.setNoDebuff(isNoDebuff());
        copy.setTargetFromSvr(isTargetFromSvr());
        copy.setCharismaEXP(getCharismaEXP());
        copy.setMp(getMp());
        copy.setMaxMp(getMaxMp());
        copy.setDrops(getDrops()); // doesn't get mutated, so should be fine
        for (MobSkill ms : getSkills()) {
            copy.addSkill(ms);
        }
        for (int rev : getRevives()) {
            copy.addRevive(rev);
        }
        for (int i : getQuests()) {
            copy.addQuest(i);
        }
        if (copy.getDrops().stream().noneMatch(di -> di.getMoney() > 0)) {
            copy.getDrops().add(new DropInfo(0, 1000, 0,
                    GameConstants.MIN_MONEY_MULT * getForcedMobStat().getLevel(),
                    GameConstants.MAX_MONEY_MULT * getForcedMobStat().getLevel()
                    ));
        }
        return copy;
    }

    public Set<DropInfo> getDrops() {
        if (drops == null) {
            drops = new HashSet<>();
        }
        return drops;
    }

    public void setDrops(Set<DropInfo> drops) {
        this.drops = drops;
    }

    public boolean isSealedInsteadDead() {
        return sealedInsteadDead;
    }

    public void setSealedInsteadDead(boolean sealedInsteadDead) {
        this.sealedInsteadDead = sealedInsteadDead;
    }

    public ForcedMobStat getForcedMobStat() {
        return forcedMobStat;
    }

    public void setForcedMobStat(ForcedMobStat forcedMobStat) {
        this.forcedMobStat = forcedMobStat;
    }

    public boolean isPatrolMob() {
        return patrolMob;
    }

    public void setPatrolMob(boolean patrolMob) {
        this.patrolMob = patrolMob;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getEffectItemID() {
        return effectItemID;
    }

    public void setEffectItemID(int effectItemID) {
        this.effectItemID = effectItemID;
    }

    public int getPatrolScopeX1() {
        return patrolScopeX1;
    }

    public void setPatrolScopeX1(int patrolScopeX1) {
        this.patrolScopeX1 = patrolScopeX1;
    }

    public int getPatrolScopeX2() {
        return patrolScopeX2;
    }

    public void setPatrolScopeX2(int patrolScopeX2) {
        this.patrolScopeX2 = patrolScopeX2;
    }

    public int getDetectX() {
        return detectX;
    }

    public void setDetectX(int detectX) {
        this.detectX = detectX;
    }

    public int getSenseX() {
        return senseX;
    }

    public void setSenseX(int senseX) {
        this.senseX = senseX;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getCurZoneDataType() {
        return curZoneDataType;
    }

    public void setCurZoneDataType(int curZoneDataType) {
        this.curZoneDataType = curZoneDataType;
    }

    public int getRefImgMobID() {
        return refImgMobID;
    }

    public void setRefImgMobID(int refImgMobID) {
        this.refImgMobID = refImgMobID;
    }

    public int getLifeReleaseOwnerAID() {
        return lifeReleaseOwnerAID;
    }

    public void setLifeReleaseOwnerAID(int lifeReleaseOwnerAID) {
        this.lifeReleaseOwnerAID = lifeReleaseOwnerAID;
    }

    public int getAfterAttack() {
        return afterAttack;
    }

    public void setAfterAttack(int afterAttack) {
        this.afterAttack = afterAttack;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getEliteGrade() {
        return eliteGrade;
    }

    public void setEliteGrade(int eliteGrade) {
        this.eliteGrade = eliteGrade;
    }

    public int getEliteType() {
        return eliteType;
    }

    public void setEliteType(int eliteType) {
        this.eliteType = eliteType;
    }

    public int getTargetUserIdFromServer() {
        return targetUserIdFromServer;
    }

    public void setTargetUserIdFromServer(int targetUserIdFromServer) {
        this.targetUserIdFromServer = targetUserIdFromServer;
    }

    public long getHp() {
        return hp;
    }

    public int getHpComparedToMaxHP() {
        if (getMaxHp() <= Integer.MAX_VALUE) {
            return (int) getHp();
        } else {
            return (int) (getHp() * (((double) Integer.MAX_VALUE) / getMaxHp()));
        }
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public byte getCalcDamageIndex() {
        return calcDamageIndex;
    }

    public void setCalcDamageIndex(byte calcDamageIndex) {
        this.calcDamageIndex = calcDamageIndex;
    }

    public byte getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    public byte getAppearType() {
        return appearType;
    }

    public void setAppearType(byte appearType) {
        this.appearType = appearType;
    }

    public byte getTeamForMCarnival() {
        return teamForMCarnival;
    }

    public void setTeamForMCarnival(byte teamForMCarnival) {
        this.teamForMCarnival = teamForMCarnival;
    }

    public Position getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(Position prevPos) {
        this.prevPos = prevPos;
    }

    public Foothold getCurFoodhold() {
        return curFoodhold;
    }

    public void setCurFoodhold(Foothold curFoodhold) {
        this.curFoodhold = curFoodhold;
    }

    public String getLifeReleaseOwnerName() {
        return lifeReleaseOwnerName;
    }

    public void setLifeReleaseOwnerName(String lifeReleaseOwnerName) {
        this.lifeReleaseOwnerName = lifeReleaseOwnerName;
    }

    public String getLifeReleaseMobName() {
        return lifeReleaseMobName;
    }

    public void setLifeReleaseMobName(String lifeReleaseMobName) {
        this.lifeReleaseMobName = lifeReleaseMobName;
    }

    public ShootingMoveStat getShootingMoveStat() {
        return shootingMoveStat;
    }

    public void setShootingMoveStat(ShootingMoveStat shootingMoveStat) {
        this.shootingMoveStat = shootingMoveStat;
    }

    public Foothold getHomeFoothold() {
        return homeFoothold;
    }

    public void setHomeFoothold(Foothold homeFoothold) {
        this.homeFoothold = homeFoothold;
    }

    public long getMaxHp() {
        return getForcedMobStat().getMaxHP();
    }

    public void setMaxHp(long maxHp) {
        getForcedMobStat().setMaxHP(maxHp);
    }

    public long getMp() {
        return mp;
    }

    public void setMp(long mp) {
        this.mp = mp;
    }

    public long getMaxMp() {
        return getForcedMobStat().getMaxMP();
    }

    public void setMaxMp(long maxMp) {
        getForcedMobStat().setMaxMP(maxMp);
    }

    public void setTemporaryStat(MobTemporaryStat temporaryStat) {
        this.temporaryStat = temporaryStat;
    }

    public MobTemporaryStat getTemporaryStat() {
        return temporaryStat;
    }

    public void setFirstAttack(int firstAttack) {
        this.firstAttack = firstAttack;
    }

    public int getFirstAttack() {
        return firstAttack;
    }

    public void setSummonType(int summonType) {
        this.summonType = summonType;
    }

    public int getSummonType() {
        return summonType;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setMobType(String mobType) {
        this.mobType = mobType;
    }

    public String getMobType() {
        return mobType;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setFs(double fs) {
        this.fs = fs;
    }

    public double getFs() {
        return fs;
    }

    public void setElemAttr(String elemAttr) {
        this.elemAttr = elemAttr;
    }

    public String getElemAttr() {
        return elemAttr;
    }

    public void setHpTagColor(int hpTagColor) {
        this.hpTagColor = hpTagColor;
    }

    public int getHpTagColor() {
        return hpTagColor;
    }

    public void setHpTagBgcolor(int hpTagBgcolor) {
        this.hpTagBgcolor = hpTagBgcolor;
    }

    public int getHpTagBgcolor() {
        return hpTagBgcolor;
    }

    public void setHPgaugeHide(boolean HPgaugeHide) {
        this.HPgaugeHide = HPgaugeHide;
    }

    public boolean isHPgaugeHide() {
        return HPgaugeHide;
    }

    public void setRareItemDropLevel(int rareItemDropLevel) {
        this.rareItemDropLevel = rareItemDropLevel;
    }

    public int getRareItemDropLevel() {
        return rareItemDropLevel;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setHpRecovery(int hpRecovery) {
        this.hpRecovery = hpRecovery;
    }

    public int getHpRecovery() {
        return hpRecovery;
    }

    public void setMpRecovery(int mpRecovery) {
        this.mpRecovery = mpRecovery;
    }

    public int getMpRecovery() {
        return mpRecovery;
    }

    public void setUndead(boolean undead) {
        this.undead = undead;
    }

    public boolean isUndead() {
        return undead;
    }

    public void setMbookID(int mbookID) {
        this.mbookID = mbookID;
    }

    public int getMbookID() {
        return mbookID;
    }

    public void setNoRegen(boolean noRegen) {
        this.noRegen = noRegen;
    }

    public boolean isNoRegen() {
        return noRegen;
    }

    public void setChaseSpeed(int chaseSpeed) {
        this.chaseSpeed = chaseSpeed;
    }

    public int getChaseSpeed() {
        return chaseSpeed;
    }

    public void setExplosiveReward(int explosiveReward) {
        this.explosiveReward = explosiveReward;
    }

    public int getExplosiveReward() {
        return explosiveReward;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setHideName(boolean hideName) {
        this.hideName = hideName;
    }

    public boolean isHideName() {
        return hideName;
    }

    public void setHideHP(boolean hideHP) {
        this.hideHP = hideHP;
    }

    public boolean isHideHP() {
        return hideHP;
    }

    public void setChangeableMobType(String changeableMobType) {
        this.changeableMobType = changeableMobType;
    }

    public String getChangeableMobType() {
        return changeableMobType;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setNoFlip(boolean noFlip) {
        this.noFlip = noFlip;
    }

    public boolean isNoFlip() {
        return noFlip;
    }

    public void setTower(boolean tower) {
        this.tower = tower;
    }

    public boolean isTower() {
        return tower;
    }

    public void setPartyBonusMob(boolean partyBonusMob) {
        this.partyBonusMob = partyBonusMob;
    }

    public boolean isPartyBonusMob() {
        return partyBonusMob;
    }

    public void setWp(int wp) {
        this.wp = wp;
    }

    public int getWp() {
        return wp;
    }

    public void setUseReaction(boolean useReaction) {
        this.useReaction = useReaction;
    }

    public boolean isUseReaction() {
        return useReaction;
    }

    public void setPublicReward(boolean publicReward) {
        this.publicReward = publicReward;
    }

    public boolean isPublicReward() {
        return publicReward;
    }

    public void setMinion(boolean minion) {
        this.minion = minion;
    }

    public boolean isMinion() {
        return minion;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isForward() {
        return forward;
    }

    public void setIsRemoteRange(boolean isRemoteRange) {
        this.isRemoteRange = isRemoteRange;
    }

    public boolean isRemoteRange() {
        return isRemoteRange;
    }

    public void setRemoteRange(boolean isRemoteRange) {
        this.isRemoteRange = isRemoteRange;
    }

    public void setIgnoreFieldOut(boolean ignoreFieldOut) {
        this.ignoreFieldOut = ignoreFieldOut;
    }

    public boolean isIgnoreFieldOut() {
        return ignoreFieldOut;
    }

    public void setIgnoreMoveImpact(boolean ignoreMoveImpact) {
        this.ignoreMoveImpact = ignoreMoveImpact;
    }

    public boolean isIgnoreMoveImpact() {
        return ignoreMoveImpact;
    }

    public void setSummonEffect(int summonEffect) {
        this.summonEffect = summonEffect;
    }

    public int getSummonEffect() {
        return summonEffect;
    }

    public void setSkeleton(boolean skeleton) {
        this.skeleton = skeleton;
    }

    public boolean isSkeleton() {
        return skeleton;
    }

    public void setHideUserDamage(boolean hideUserDamage) {
        this.hideUserDamage = hideUserDamage;
    }

    public boolean isHideUserDamage() {
        return hideUserDamage;
    }

    public void setFixedDamage(int fixedDamage) {
        this.fixedDamage = fixedDamage;
    }

    public int getFixedDamage() {
        return fixedDamage;
    }

    public void setIndividualReward(boolean individualReward) {
        this.individualReward = individualReward;
    }

    public boolean isIndividualReward() {
        return individualReward;
    }

    public void setRemoveAfter(int removeAfter) {
        this.removeAfter = removeAfter;
    }

    public int getRemoveAfter() {
        return removeAfter;
    }

    public void setNotConsideredFieldSet(boolean notConsideredFieldSet) {
        this.notConsideredFieldSet = notConsideredFieldSet;
    }

    public boolean isNotConsideredFieldSet() {
        return notConsideredFieldSet;
    }

    public void setFixedMoveDir(String fixedMoveDir) {
        this.fixedMoveDir = fixedMoveDir;
    }

    public String getFixedMoveDir() {
        return fixedMoveDir;
    }

    public void setNoDoom(boolean noDoom) {
        this.noDoom = noDoom;
    }

    public boolean isNoDoom() {
        return noDoom;
    }

    public void setUseCreateScript(boolean useCreateScript) {
        this.useCreateScript = useCreateScript;
    }

    public boolean isUseCreateScript() {
        return useCreateScript;
    }

    public void setKnockback(boolean knockback) {
        this.knockback = knockback;
    }

    public boolean isKnockback() {
        return knockback;
    }

    public void setBlockUserMove(boolean blockUserMove) {
        this.blockUserMove = blockUserMove;
    }

    public boolean isBlockUserMove() {
        return blockUserMove;
    }

    public void setBodyDisease(int bodyDisease) {
        this.bodyDisease = bodyDisease;
    }

    public int getBodyDisease() {
        return bodyDisease;
    }

    public void setBodyDiseaseLevel(int bodyDiseaseLevel) {
        this.bodyDiseaseLevel = bodyDiseaseLevel;
    }

    public int getBodyDiseaseLevel() {
        return bodyDiseaseLevel;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPartyBonusR(int partyBonusR) {
        this.partyBonusR = partyBonusR;
    }

    public int getPartyBonusR() {
        return partyBonusR;
    }

    public void setRemoveQuest(boolean removeQuest) {
        this.removeQuest = removeQuest;
    }

    public boolean isRemoveQuest() {
        return removeQuest;
    }

    public void setPassiveDisease(int passiveDisease) {
        this.passiveDisease = passiveDisease;
    }

    public int getPassiveDisease() {
        return passiveDisease;
    }

    public void setCoolDamageProb(int coolDamageProb) {
        this.coolDamageProb = coolDamageProb;
    }

    public int getCoolDamageProb() {
        return coolDamageProb;
    }

    public void setCoolDamage(int coolDamage) {
        this.coolDamage = coolDamage;
    }

    public int getCoolDamage() {
        return coolDamage;
    }

    public void setDamageRecordQuest(int damageRecordQuest) {
        this.damageRecordQuest = damageRecordQuest;
    }

    public int getDamageRecordQuest() {
        return damageRecordQuest;
    }

    public void setSealedCooltime(int sealedCooltime) {
        this.sealedCooltime = sealedCooltime;
    }

    public int getSealedCooltime() {
        return sealedCooltime;
    }

    public void setWillEXP(int willEXP) {
        this.willEXP = willEXP;
    }

    public int getWillEXP() {
        return willEXP;
    }

    public void setOnFieldSetSummon(boolean onFieldSetSummon) {
        this.onFieldSetSummon = onFieldSetSummon;
    }

    public boolean isOnFieldSetSummon() {
        return onFieldSetSummon;
    }

    public void setUserControll(boolean userControll) {
        this.userControll = userControll;
    }

    public boolean isUserControll() {
        return userControll;
    }

    public void setNoDebuff(boolean noDebuff) {
        this.noDebuff = noDebuff;
    }

    public boolean isNoDebuff() {
        return noDebuff;
    }

    public void setTargetFromSvr(boolean targetFromSvr) {
        this.targetFromSvr = targetFromSvr;
    }

    public boolean isTargetFromSvr() {
        return targetFromSvr;
    }

    public void setCharismaEXP(int charismaEXP) {
        this.charismaEXP = charismaEXP;
    }

    public int getCharismaEXP() {
        return charismaEXP;
    }

    public void setSplit(boolean isSplit) {
        this.isSplit = isSplit;
    }

    public boolean isSplit() {
        return isSplit;
    }

    public void setSplitLink(int splitLinkID) {
        this.splitLink = splitLinkID;
    }

    public int getSplitLink() {
        return splitLink;
    }

    public Set<Integer> getRevives() {
        return revives;
    }

    public void setRevives(Set<Integer> revives) {
        this.revives = revives;
    }

    public void addRevive(int revive) {revives.add(revive);}

    /**
     * Damages a mob.
     * @param totalDamage the total damage that should be applied to the mob
     */
    public void damage(Long totalDamage) {
        long maxHP = getMaxHp();
        long oldHp = getHp();
        long newHp = oldHp - totalDamage;
        setHp(newHp);
        double percDamage = ((double) newHp / maxHP);
        newHp = newHp > Integer.MAX_VALUE ? Integer.MAX_VALUE : newHp;
        if (newHp <= 0) {
            die();
            if (isBoss()) {
                getField().broadcastPacket(CField.fieldEffect(FieldEffect.mobHPTagFieldEffect(this)));
            }
        } else if (isBoss()) {
            getField().broadcastPacket(CField.fieldEffect(FieldEffect.mobHPTagFieldEffect(this)));
        } else {
            getField().broadcastPacket(MobPool.mobHpIndicator(getObjectId(), (byte) (percDamage * 100)));
        }
    }

    private void die() {
        Field field = getField();
        getField().broadcastPacket(MobPool.mobLeaveField(getObjectId(), DeathType.ANIMATION_DEATH));
        if (!isNotRespawnable()) { // double negative
            EventManager.addEvent(() -> field.respawn(this),
                    (long) (GameConstants.BASE_MOB_RESPAWN_RATE * (1 / field.getMobRate())));
            field.putLifeController(this, null);
        } else {
            getField().removeLife(getObjectId());
        }
        if(isSplit()) {
            return;
        }
        distributeExp();
        dropDrops(); // xd
        setPosition(getHomePosition());
        for (Char chr : getDamageDone().keySet()) {
            chr.getQuestManager().handleMobKill(this);
            chr.getTemporaryStatManager().addSoulMPFromMobDeath();
        }
        getDamageDone().clear();
        if (field.canSpawnElite() && getEliteType() == 0 && !isNotRespawnable() &&
                Util.succeedProp(GameConstants.ELITE_MOB_SPAWN_CHANCE, 1000)) {
            spawnEliteVersion();
        } else if (getEliteType() == 1) {
            field.incrementEliteKillCount();
            String msg = null;
            if (field.getKilledElites() >= GameConstants.ELITE_BOSS_REQUIRED_KILLS) {
                field.setKilledElites(field.getKilledElites() % GameConstants.ELITE_BOSS_REQUIRED_KILLS);
                int bossTemplate = Util.getRandomFromList(GameConstants.ELITE_BOSS_TEMPLATES);
                Mob mob = MobData.getMobDeepCopyById(bossTemplate);
                mob.setEliteType(3);
                mob.setNotRespawnable(true);
                mob.setMaxHp(MobData.getMobDeepCopyById(getTemplateId()).getMaxHp() * GameConstants.ELITE_BOSS_HP_RATE);
                mob.setHp(mob.getMaxHp());
                mob.setHomeFoothold(getCurFoodhold().deepCopy());
                mob.setCurFoodhold(getCurFoodhold().deepCopy());
                mob.setPosition(getPosition().deepCopy());
                mob.setHomePosition(getPosition().deepCopy());
                field.spawnLife(mob, null);
                field.setEliteState(EliteState.ELITE_BOSS);
                field.broadcastPacket(CField.eliteState(EliteState.ELITE_BOSS, false, GameConstants.ELITE_BOSS_BGM,
                        null, null));
            } else if (field.getKilledElites() >= GameConstants.ELITE_MOB_DARK_NOTIFICATION) {
                msg = "You feel something in the dark energy...";
            } else {
                msg = "The dark energy is still here. It's making the place quite grim.";
            }
            Effect effect = Effect.createFieldTextEffect(msg, 75, 2000, 4,
                    new Position(0, -200), 1, 4, TextEffectType.BlackFadedBrush, 0, 0);
            getField().broadcastPacket(User.effect(effect));
        } else if (getEliteType() == 3) {
            field.broadcastPacket(CField.eliteState(EliteState.NORMAL, true, null, null, null));
            field.setEliteState(EliteState.NORMAL);
        }

        //TEST
        reviveMob();
    }

    private void dropDrops() {
        getField().drop(getDrops(), getField().getFootholdById(getFh()), getPosition(), getMostDamageChar().getId());
    }

    public Map<Char, Long> getDamageDone() {
        return damageDone;
    }

    /**
     * Adds a damage amount to the given Char's current damage. Purely used for keeping track of total damage done by
     * a Char.
     * @param chr the Char the damage originates from
     * @param damage the damage done
     */
    public void addDamage(Char chr, long damage) {
        long cur = 0;
        if (getDamageDone().containsKey(chr)) {
            cur = getDamageDone().get(chr);
        }
        if (damage <= getHp()) {
            cur += damage;
        } else {
            cur += getHp();
        }
        getDamageDone().put(chr, cur);
    }

    public void distributeExp() {
        long exp = getForcedMobStat().getExp();
        long totalDamage = getDamageDone().values().stream().mapToLong(l -> l).sum();
        Map<Party, PartyDamageInfo> damagePercPerParty = new HashMap<>();
        for (Char chr : getDamageDone().keySet()) {
            double damagePerc = getDamageDone().get(chr) / (double) totalDamage;
            long appliedExp = (long) (exp * damagePerc);

            if(getField().getBurningFieldLevel() > 0) {
                ExpIncreaseInfo eei = new ExpIncreaseInfo();
                int burningFieldBonusExp = (int) (appliedExp * getField().getBonusExpByBurningFieldLevel()  /  100);
                eei.setRestFieldBonusExp(burningFieldBonusExp);
                eei.setRestFieldExpRate(getField().getBonusExpByBurningFieldLevel());
                eei.setLastHit(true);
                eei.setIncEXP((int) appliedExp);
                chr.addExp((appliedExp + burningFieldBonusExp), eei);
            } else {
                chr.addExp(appliedExp);
            }

            Party party = chr.getParty();
            if (party != null) {
                if (!damagePercPerParty.containsKey(party)) {
                    damagePercPerParty.put(party, new PartyDamageInfo(party, this));
                }
                damagePercPerParty.get(party).addDamageInfo(chr, damagePerc);
            }
        }

        for (PartyDamageInfo pdi : damagePercPerParty.values()) {
            pdi.distributeExp();
        }
    }

    public Char getMostDamageChar() {
        Tuple<Char, Long> max = new Tuple<>(null, (long) -1);
        for (Map.Entry<Char, Long> entry : getDamageDone().entrySet()) {
            Char chr = entry.getKey();
            long damage = entry.getValue();
            if (max == null || damage > max.getRight()) {
                max.setLeft(chr);
                max.setRight(damage);
            }
        }
        return max.getLeft();
    }

    public List<MobSkill> getSkills() {
        return skills;
    }

    public void addSkill(MobSkill skill) {
        getSkills().add(skill);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mob) {
            Mob mob = (Mob) obj;
            return mob.getTemplateId() == getTemplateId() && mob.getObjectId() == getObjectId() && mob.getField().equals(getField());
        }
        return false;
    }

    public Set<Integer> getQuests() {
        return quests;
    }

    public void setQuests(Set<Integer> quests) {
        this.quests = quests;
    }

    public void addQuest(int questID) {
        getQuests().add(questID);
    }

    public void soulSplitMob(Char chr, Mob origin, int duration, Skill skill) {
        Field field = chr.getField();
        Position position = origin.getPosition();

        Mob copy = MobData.getMobDeepCopyById(origin.getTemplateId());
        copy.setSplit(true);
        copy.setPosition(position);
        copy.setHp(origin.getHp());
        copy.setMaxHp(origin.getMaxHp());
        copy.setMp(origin.getMp());
        copy.setMaxMp(origin.getMaxMp());
        copy.setNotRespawnable(true);
        copy.setField(field);

        copy.setSplitLink(origin.getObjectId());
        //copy.setDrops(null);

        field.spawnLife(copy, null);

        MobTemporaryStat mtsCopy = copy.getTemporaryStat();
        Option o1 = new Option();
        Option o2 = new Option();
        o2.nOption = 1;
        o2.rOption = skill.getSkillId();
        o2.tOption = duration;
        mtsCopy.addStatOptions(MobStat.Freeze, o2);
        o1.nOption = 1;
        o1.rOption = skill.getSkillId();
        o1.tOption = duration;
        mtsCopy.addStatOptionsAndBroadcast(MobStat.TrueSight, o1);
        EventManager.addEvent(() -> removeSoulSplitLife(chr, origin, copy), duration, TimeUnit.SECONDS);
    }

    public void removeSoulSplitLife(Char chr, Mob origin, Mob copy) {
        chr.getField().removeLife(copy.getObjectId());
        chr.getField().broadcastPacket(MobPool.mobLeaveField(copy.getObjectId(), DeathType.ANIMATION_DEATH));
        origin.setSplit(false);
    }

    public boolean isAlive() {
        return getHp() <= 0;
    }

    public void reviveMob() {
        if(getRevives().size() > 0) {
            for(int reviveTemplateID : getRevives()) {
                Mob mob = MobData.getMobDeepCopyById(reviveTemplateID);
                mob.setNotRespawnable(true);
                mob.setPosition(getPosition());
                getField().spawnLife(mob, null);
            }
        }
    }

    private Map<Integer, Long> getSkillCooldowns() {
        return skillCooldowns;
    }

    public boolean hasSkillOnCooldown(int skillID, int slv) {
        return System.currentTimeMillis() < getSkillCooldowns().getOrDefault(skillID | (slv << 16), 0L);
    }

    public void putSkillCooldown(int skillID, int slv, long nextUseableTime) {
        getSkillCooldowns().put(skillID | (slv << 16), nextUseableTime);
    }

    public boolean hasSkillDelayExpired() {
        return System.currentTimeMillis() > getNextPossibleSkillTime();
    }

    /**
     * Sets when a next skill can be used (in ms from current time).
     * @param delay The delay until the next skill can be used
     */
    public void setSkillDelay(long delay) {
        setNextPossibleSkillTime(getNextPossibleSkillTime() + delay);
    }

    private long getNextPossibleSkillTime() {
        return nextPossibleSkillTime;
    }

    private void setNextPossibleSkillTime(long nextPossibleSkillTime) {
        this.nextPossibleSkillTime = nextPossibleSkillTime;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        setTemporaryStat(new MobTemporaryStat(this));
        Field field = getField();
        Position pos = getPosition();
        Foothold fh = field.getFootholdById(getFh());
        if (fh == null) {
            fh = field.findFootHoldBelow(pos);
        }
        if (fh == null) {
            // Some weird edge case where the mob is spawned on some weird foothold
            return;
        }
        setHomeFoothold(fh.deepCopy());
        setCurFoodhold(fh.deepCopy());
        Char controller = getField().getLifeToControllers().get(this);
        if (onlyChar == null) {
            for (Char chr : field.getChars()) {
                chr.write(MobPool.mobEnterField(this, false));
                chr.write(MobPool.mobChangeController(this, false, controller == chr));
            }
        } else {
            onlyChar.getClient().write(MobPool.mobEnterField(this, false));
            onlyChar.getClient().write(MobPool.mobChangeController(this, false, controller == onlyChar));
        }
    }

    public boolean isInfestedByViralSlime() {
        MobTemporaryStat mts = getTemporaryStat();
        return mts.hasBurnFromSkill(Magician.VIRAL_SLIME);
    }

    public void spawnEliteVersion() {
        Mob elite = MobData.getMobDeepCopyById(getTemplateId());
        elite.setHomePosition(getPosition().deepCopy());
        elite.setPosition(getPosition().deepCopy());
        elite.setCurFoodhold(getCurFoodhold().deepCopy());
        elite.setHomeFoothold(getCurFoodhold().deepCopy());
        elite.setNotRespawnable(true);
        List<Triple<Integer, Double, Double>> eliteInfos = GameConstants.getEliteInfoByMobLevel(elite.getForcedMobStat().getLevel());
        Triple<Integer, Double, Double> eliteInfo = Util.getRandomFromList(eliteInfos);
        int eliteGrade = eliteInfo.getLeft();
        long newHp = (long) (eliteInfo.getMiddle() * elite.getMaxHp());
        long newExp = (long) (eliteInfo.getRight() * elite.getForcedMobStat().getExp());
        elite.setEliteType(1);
        elite.setEliteGrade(eliteGrade);
        Map<Integer, Integer> possibleSkillsMap = SkillData.getEliteMobSkillsByGrade(eliteGrade);
        List<Tuple<Integer, Integer>> possibleSkills = new ArrayList<>();
        possibleSkillsMap.forEach((k, v) -> possibleSkills.add(new Tuple(k, v)));
        for (int i = 0; i < GameConstants.ELITE_MOB_SKILL_COUNT; i++) {
            Tuple<Integer, Integer> randomSkill = Util.getRandomFromList(possibleSkills);
            elite.addEliteSkill(randomSkill.getLeft(), randomSkill.getRight());
            possibleSkills.remove(randomSkill);
        }
        elite.setMaxHp(newHp);
        elite.setHp(newHp);
        elite.getForcedMobStat().setExp(newExp);
        getField().setNextEliteSpawnTime(System.currentTimeMillis() + GameConstants.ELITE_MOB_RESPAWN_TIME * 1000);
        getField().spawnLife(elite, null);
    }

    public void spawnEliteMobRuneOfDarkness() {
        Mob elite = MobData.getMobDeepCopyById(getTemplateId());
        elite.setHomePosition(getPosition().deepCopy());
        elite.setPosition(getPosition().deepCopy());
        elite.setCurFoodhold(getCurFoodhold().deepCopy());
        elite.setHomeFoothold(getCurFoodhold().deepCopy());
        elite.setNotRespawnable(true);
        List<Triple<Integer, Double, Double>> eliteInfos = GameConstants.getEliteInfoByMobLevel(elite.getForcedMobStat().getLevel());
        Triple<Integer, Double, Double> eliteInfo = Util.getRandomFromList(eliteInfos);
        int eliteGrade = eliteInfo.getLeft();
        long newHp = (long) (eliteInfo.getMiddle() * elite.getMaxHp());
        long newExp = (long) (eliteInfo.getRight() * elite.getForcedMobStat().getExp());
        elite.setEliteType(1);
        elite.setEliteGrade(eliteGrade);
        Map<Integer, Integer> possibleSkillsMap = SkillData.getEliteMobSkillsByGrade(eliteGrade);
        List<Tuple<Integer, Integer>> possibleSkills = new ArrayList<>();
        possibleSkillsMap.forEach((k, v) -> possibleSkills.add(new Tuple(k, v)));
        for (int i = 0; i < GameConstants.ELITE_MOB_SKILL_COUNT; i++) {
            Tuple<Integer, Integer> randomSkill = Util.getRandomFromList(possibleSkills);
            elite.addEliteSkill(randomSkill.getLeft(), randomSkill.getRight());
            possibleSkills.remove(randomSkill);
        }
        elite.setMaxHp(newHp);
        elite.setHp(newHp);
        elite.getForcedMobStat().setExp(newExp);
        getField().spawnLife(elite, null);
    }

    public List<Tuple<Integer, Integer>> getEliteSkills() {
        return eliteSkills;
    }

    public void addEliteSkill(int skillID, int skillLevel) {
        MobSkill ms = new MobSkill();
        ms.setSkillID(-1);
        ms.setSkill(skillID);
        ms.setLevel(skillLevel);
        addSkill(ms);
        getEliteSkills().add(new Tuple<>(skillID, skillID));
    }

    public void setSelfDestruction(boolean selfDestruction) {
        this.selfDestruction = selfDestruction;
    }

    public boolean isSelfDestruction() {
        return selfDestruction;
    }
}
