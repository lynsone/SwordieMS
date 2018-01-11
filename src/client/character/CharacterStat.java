package client.character;

import connection.OutPacket;
import constants.JobConstants;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SystemTime;

import util.FileTime;
import util.Util;

import javax.persistence.*;

/**
 * Created by Tim on 2/18/2017.
 */
@Entity
@Table(name = "characterStats")
public class CharacterStat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "characterId")
    private int characterId;
    @Column(name = "characterIdForLog")
    private int characterIdForLog;
    @Column(name = "worldIdForLog")
    private int worldIdForLog;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private int gender;
    @Column(name = "skin")
    private int skin;
    @Column(name = "face")
    private int face;
    @Column(name = "hair")
    private int hair;
    @Column(name = "mixBaseHairColor")
    private int mixBaseHairColor;
    @Column(name = "mixAddHairColor")
    private int mixAddHairColor;
    @Column(name = "mixHairBaseProb")
    private int mixHairBaseProb;
    @Column(name = "level")
    private int level;
    @Column(name = "job")
    private int job;
    @Column(name = "str")
    private int str;
    @Column(name = "dex")
    private int dex;
    @Column(name = "inte")
    private int inte;
    @Column(name = "luk")
    private int luk;
    @Column(name = "hp")
    private int hp;
    @Column(name = "maxHp")
    private int maxHp;
    @Column(name = "mp")
    private int mp;
    @Column(name = "maxMp")
    private int maxMp;
    @Column(name = "ap")
    private int ap;
    @Column(name = "sp")
    private int sp;
    @Column(name = "exp")
    private long exp;
    @Column(name = "pop")
    private int pop; // fame
    @Column(name = "money")
    private long money;
    @Column(name = "wp")
    private int wp;
    @OneToOne
    @JoinColumn(name = "extendSP")
    private ExtendSP extendSP;
    @Column(name = "posMap")
    private long posMap;
    @Column(name = "portal")
    private int portal;
    @Column(name = "subJob")
    private int subJob;
    @Column(name = "defFaceAcc")
    private int defFaceAcc;
    @Column(name = "fatigue")
    private int fatigue;
    @Column(name = "lastFatigueUpdateTime")
    private int lastFatigueUpdateTime;
    @Column(name = "charismaExp")
    private int charismaExp;
    @Column(name = "insightExp")
    private int insightExp;
    @Column(name = "willExp")
    private int willExp;
    @Column(name = "craftExp")
    private int craftExp;
    @Column(name = "senseExp")
    private int senseExp;
    @Column(name = "charmExp")
    private int charmExp;
    @OneToOne
    @JoinColumn(name = "nonCombatStatDayLimit")
    private NonCombatStatDayLimit nonCombatStatDayLimit;
    @Column(name = "pvpExp")
    private int pvpExp;
    @Column(name = "pvpGrade")
    private int pvpGrade;
    @Column(name = "pvpPoint")
    private int pvpPoint;
    @Column(name = "pvpModeLevel")
    private int pvpModeLevel;
    @Column(name = "pvpModeType")
    private int pvpModeType;
    @Column(name = "eventPoint")
    private int eventPoint;
    @Column(name = "albaActivityID")
    private int albaActivityID;
    @OneToOne
    @JoinColumn(name = "albaStartTime")
    private FileTime albaStartTime;
    @Column(name = "albaDuration")
    private int albaDuration;
    @Column(name = "albaSpecialReward")
    private int albaSpecialReward;
    @Column(name = "burning")
    private boolean burning;
    @OneToOne
    @JoinColumn(name = "characterCard")
    private CharacterCard characterCard;
    @OneToOne
    @JoinColumn(name = "accountLastLogout")
    private SystemTime accountLastLogout;
    @OneToOne
    @JoinColumn(name = "lastLogout")
    private FileTime lastLogout;
    @Column(name = "gachExp")
    private int gachExp;

    public CharacterStat() {
        extendSP = new ExtendSP(5);
        nonCombatStatDayLimit = new NonCombatStatDayLimit();
        albaStartTime = new FileTime(0);
        lastLogout = new FileTime(0);
        characterCard = new CharacterCard(0, 0, (byte) 0);
        accountLastLogout = new SystemTime(1970, 1);
        // TODO fill in default vals
    }

    public CharacterStat(String name, int job) {
        this();
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public short getAp() {
        return (short) ap;
    }

    public short getDex() {
        return (short) dex;
    }

    public short getHp() {
        return (short) hp;
    }

    public short getInt() {
        return (short) inte;
    }

    public short getJob() {
        return (short) job;
    }

    public short getLevel() {
        return (short) level;
    }

    public short getCharismaExp() {
        return (short) charismaExp;
    }

    public short getLuk() {
        return (short) luk;
    }

    public short getMaxHp() {
        return (short) maxHp;
    }

    public short getMaxMp() {
        return (short) maxMp;
    }

    public short getMp() {
        return (short) mp;
    }

    public short getPop() {
        return (short) pop;
    }

    public short getSp() {
        return (short) sp;
    }

    public short getStr() {
        return (short) str;
    }

    public short getWp() {
        return (short) wp;
    }

    public long getExp() {
        return (short) exp;
    }

    public long getMoney() {
        return (short) money;
    }

    public ExtendSP getExtendSP() {
        return extendSP;
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getCharacterIdForLog() {
        return characterId;
    }

    public int getFace() {
        return face;
    }

    public int getGender() {
        return gender;
    }

    public int getHair() {
        return hair;
    }

    public int getMixAddHairColor() {
        return mixAddHairColor;
    }

    public int getMixBaseHairColor() {
        return mixBaseHairColor;
    }

    public int getMixHairBaseProb() {
        return mixHairBaseProb;
    }

    public int getSkin() {
        return skin;
    }

    public int getWorldIdForLog() {
        return worldIdForLog;
    }

    public short getCharmExp() {
        return (short) charmExp;
    }

    public short getCraftExp() {
        return (short) craftExp;
    }

    public int getAlbaActivityID() {
        return albaActivityID;
    }

    public int getEventPoint() {
        return eventPoint;
    }

    public int getPortal() {
        return portal;
    }

    public int getAlbaDuration() {
        return albaDuration;
    }

    public short getInsightExp() {
        return (short) insightExp;
    }

    public int getAlbaSpecialReward() {
        return albaSpecialReward;
    }

    public int getPvpExp() {
        return pvpExp;
    }

    public int getPvpGrade() {
        return pvpGrade;
    }

    public int getPvpModeLevel() {
        return pvpModeLevel;
    }

    public int getPvpModeType() {
        return pvpModeType;
    }

    public int getPvpPoint() {
        return pvpPoint;
    }

    public short getSenseExp() {
        return (short) senseExp;
    }

    public short getWillExp() {
        return (short) willExp;
    }

    public long getPosMap() {
        return posMap == 0 ? 931000000 : posMap;
    }

    public CharacterCard getCharacterCard() {
        return characterCard;
    }

    public NonCombatStatDayLimit getNonCombatStatDayLimit() {
        return nonCombatStatDayLimit;
    }

    public FileTime getAlbaStartTime() {
        return albaStartTime;
    }

    public int getDefFaceAcc() {
        return defFaceAcc;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getLastFatigueUpdateTime() {
        return lastFatigueUpdateTime;
    }

    public int getSubJob() {
        return subJob;
    }

    public SystemTime getAccountLastLogout() {
        return accountLastLogout;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCharacterId());
        outPacket.encodeInt(getCharacterIdForLog());
        outPacket.encodeInt(getWorldIdForLog());
        outPacket.encodeString(getName(), 13);
        outPacket.encodeByte(getGender());
        outPacket.encodeByte(getSkin());
        outPacket.encodeInt(getFace());
        outPacket.encodeInt(getHair());
        outPacket.encodeByte(getMixBaseHairColor());
        outPacket.encodeByte(getMixAddHairColor());
        outPacket.encodeByte(getMixHairBaseProb());
        outPacket.encodeByte(getLevel());
        outPacket.encodeShort(getJob());
        outPacket.encodeShort(getStr());
        outPacket.encodeShort(getDex());
        outPacket.encodeShort(getInt());
        outPacket.encodeShort(getLuk());
        outPacket.encodeInt(getHp());
        outPacket.encodeInt(getMaxHp());
        outPacket.encodeInt(getMp());
        outPacket.encodeInt(getMaxMp());
        outPacket.encodeShort(getAp());
        if (JobConstants.isExtendSpJob(getJob())) {
            getExtendSP().encode(outPacket);
        } else {
            outPacket.encodeShort(getSp());
        }
        outPacket.encodeLong(getExp());
        outPacket.encodeInt(getPop());
        outPacket.encodeInt(getWp()); // Waru
        outPacket.encodeInt(getGachExp());
        outPacket.encodeInt((int) getPosMap());
        outPacket.encodeByte(getPortal());
        outPacket.encodeInt(0); // TODO figure out
        outPacket.encodeShort(getSubJob());
        if (JobConstants.isDemon(getJob()) || JobConstants.isXenon(getJob())) {
            outPacket.encodeInt(getDefFaceAcc());
        }
        outPacket.encodeByte(getFatigue());
        outPacket.encodeInt(getLastFatigueUpdateTime());
        outPacket.encodeInt(getCharismaExp());
        outPacket.encodeInt(getInsightExp());
        outPacket.encodeInt(getWillExp());
        outPacket.encodeInt(getCraftExp());
        outPacket.encodeInt(getSenseExp());
        outPacket.encodeInt(getCharmExp());
        getNonCombatStatDayLimit().encode(outPacket);

        outPacket.encodeInt(getPvpExp());
        outPacket.encodeByte(getPvpGrade());
        outPacket.encodeInt(getPvpPoint());
        outPacket.encodeByte(2);
        /* Fuck that, setting the above byte lower than 2 will make all 3rd and 4th job that have the property
         ((skillID % 10000) / 10000 == 0) be bugged (you see the level, but can't actually use it). ?????????????*/

        outPacket.encodeByte(getPvpModeType());
        outPacket.encodeInt(getEventPoint());
        outPacket.encodeByte(getAlbaActivityID()); // part time job
        getAlbaStartTime().encode(outPacket);
        outPacket.encodeInt(getAlbaDuration());
        outPacket.encodeByte(getAlbaSpecialReward());
        getCharacterCard().encode(outPacket);
        getLastLogout().encode(outPacket);
        outPacket.encodeByte(isBurning()); // bBurning
    }

    public FileTime getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(FileTime lastLogout) {
        this.lastLogout = lastLogout;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGachExp() {
        return gachExp;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setCharacterIdForLog(int characterIdForLog) {
        this.characterIdForLog = characterIdForLog;
    }

    public void setWorldIdForLog(int worldIdForLog) {
        this.worldIdForLog = worldIdForLog;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public void setMixAddHairColor(int mixAddHairColor) {
        this.mixAddHairColor = mixAddHairColor;
    }

    public void setMixHairBaseProb(int mixHairBaseProb) {
        this.mixHairBaseProb = mixHairBaseProb;
    }

    public void setMixBaseHairColor(int mixBaseHairColor) {
        this.mixBaseHairColor = mixBaseHairColor;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setInt(int inte) {
        this.inte = inte;
    }

    public void setLuk(int luk) {
        this.luk = luk;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setWp(int wp) {
        this.wp = wp;
    }

    public void setPosMap(long posMap) {
        this.posMap = posMap;
    }

    public void setPortal(int portal) {
        this.portal = portal;
    }

    public void setSubJob(int subJob) {
        this.subJob = subJob;
    }

    public void setDefFaceAcc(int defFaceAcc) {
        this.defFaceAcc = defFaceAcc;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setLastFatigueUpdateTime(int lastFatigueUpdateTime) {
        this.lastFatigueUpdateTime = lastFatigueUpdateTime;
    }

    public void setCharismaExp(int charismaExp) {
        this.charismaExp = charismaExp;
    }

    public void setInsightExp(int insightExp) {
        this.insightExp = insightExp;
    }

    public void setWillExp(int willExp) {
        this.willExp = willExp;
    }

    public void setCraftExp(int craftExp) {
        this.craftExp = craftExp;
    }

    public void setSenseExp(int senseExp) {
        this.senseExp = senseExp;
    }

    public void setCharmExp(int charmExp) {
        this.charmExp = charmExp;
    }

    public void setPvpExp(int pvpExp) {
        this.pvpExp = pvpExp;
    }

    public void setPvpGrade(int pvpGrade) {
        this.pvpGrade = pvpGrade;
    }

    public void setPvpPoint(int pvpPoint) {
        this.pvpPoint = pvpPoint;
    }

    public void setPvpModeLevel(int pvpModeLevel) {
        this.pvpModeLevel = pvpModeLevel;
    }

    public void setPvpModeType(int pvpModeType) {
        this.pvpModeType = pvpModeType;
    }

    public void setEventPoint(int eventPoint) {
        this.eventPoint = eventPoint;
    }

    public void setAlbaActivityID(int albaActivityID) {
        this.albaActivityID = albaActivityID;
    }

    public void setAlbaDuration(int albaDuration) {
        this.albaDuration = albaDuration;
    }

    public void setAlbaSpecialReward(int albaSpecialReward) {
        this.albaSpecialReward = albaSpecialReward;
    }

    public void setGachExp(int gachExp) {
        this.gachExp = gachExp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExtendSP(ExtendSP extendSP) {
        this.extendSP = extendSP;
    }

    public void setNonCombatStatDayLimit(NonCombatStatDayLimit nonCombatStatDayLimit) {
        this.nonCombatStatDayLimit = nonCombatStatDayLimit;
    }

    public void setAlbaStartTime(FileTime albaStartTime) {
        this.albaStartTime = albaStartTime;
    }

    public void setCharacterCard(CharacterCard characterCard) {
        this.characterCard = characterCard;
    }

    public void setAccountLastLogout(SystemTime accountLastLogout) {
        this.accountLastLogout = accountLastLogout;
    }

    public void updateDB(Session session, Transaction tx) {
        getExtendSP().updateDB(session, tx);
        getCharacterCard().updateDB(session, tx);
        getAlbaStartTime().updateDB(session, tx);
        getLastLogout().updateDB(session, tx);
        getNonCombatStatDayLimit().updateDB(session, tx);
        getAccountLastLogout().updateDB(session, tx);
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        getExtendSP().createInDB(session, tx);
        getCharacterCard().createInDB(session, tx);
        getAlbaStartTime().createInDB(session, tx);
        getLastLogout().createInDB(session, tx);
        getNonCombatStatDayLimit().createInDB(session, tx); // see what hibernate does with it
        getAccountLastLogout().createInDB(session, tx);
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        getExtendSP().deleteFromDB(session, tx);
        getCharacterCard().deleteFromDB(session, tx);
        getAlbaStartTime().deleteFromDB(session, tx);
        getLastLogout().deleteFromDB(session, tx);
        getNonCombatStatDayLimit().deleteFromDB(session, tx);
        getAccountLastLogout().deleteFromDB(session, tx);
        session.delete(this);
    }
}

