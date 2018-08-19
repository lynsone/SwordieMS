package net.swordie.ms.client.character.items;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.ItemInfo;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;

import javax.persistence.*;
import java.util.*;

/**
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "equips")
@PrimaryKeyJoinColumn(name = "itemId")
public class Equip extends Item {
    private long serialNumber;
    private String title;
    @JoinColumn(name = "equippedDate")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private FileTime equippedDate = FileTime.fromType(FileTime.Type.PLAIN_ZERO);
    private int prevBonusExpRate;
    private short tuc;
    private short cuc;
    private short iStr;
    private short iDex;
    private short iInt;
    private short iLuk;
    private short iMaxHp;
    private short iMaxMp;
    private short iPad;
    private short iMad;
    private short iPDD;
    private short iMDD;
    private short iAcc;
    private short iEva;
    private short iCraft;
    private short iSpeed;
    private short iJump;
    private short attribute;
    private short levelUpType;
    private short level;
    private short exp;
    private short durability;
    private short iuc;
    private short iPvpDamage;
    private short iReduceReq;
    private short specialAttribute;
    private short durabilityMax;
    private short iIncReq;
    private short growthEnchant;
    private short psEnchant;
    private short bdr;
    private short imdr;
    private short damR;
    private short statR;
    private short cuttable;
    private short exGradeOption;
    private short itemState;
    private short chuc;
    private short soulOptionId;
    private short soulSocketId;
    private short soulOption;
    private short rStr;
    private short rDex;
    private short rInt;
    private short rLuk;
    private short rLevel;
    private short rJob;
    private short rPop;
    @ElementCollection
    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "equipID"))
    @Column(name = "optionID")
    private List<Integer> options = new ArrayList<>(); // base + add pot + anvil
    private int specialGrade;
    private boolean fixedPotential;
    private boolean noPotential;
    private boolean tradeBlock;
    @Column(name = "isOnly")
    private boolean only;
    private boolean notSale;
    private int attackSpeed;
    private int price;
    private int charmEXP;
    private boolean expireOnLogout;
    private int setItemID;
    private boolean exItem;
    private boolean equipTradeBlock;
    private String iSlot;
    private String vSlot;
    private int fixedGrade;
    @Transient
    private Map<EnchantStat, Integer> enchantStats = new HashMap<>();
    @ElementCollection
    @CollectionTable(name = "sockets", joinColumns = @JoinColumn(name = "equipID"))
    @Column(name = "socketID")
    @OrderColumn(name = "ord")
    private short[] sockets = new short[3];

    public Equip() {
        super();
    }

    public Equip(int itemId, int bagIndex, long cashItemSerialNumber, FileTime dateExpire, long serialNumber,
                 String title, FileTime equippedDate, int prevBonusExpRate, short tuc, short cuc, short iStr,
                 short iDex, short iInt, short iLuk, short iMaxHp, short iMaxMp, short iPad, short iMad, short iPDD,
                 short iMDD, short iAcc, short iEva, short iCraft, short iSpeed, short iJump, short attribute,
                 short levelUpType, short level, short exp, short durability, short iuc, short iPvpDamage,
                 short iReduceReq, short specialAttribute, short durabilityMax, short iIncReq, short growthEnchant,
                 short psEnchant, short bdr, short imdr, short damR, short statR, short cuttable, short exGradeOption,
                 short itemState, short chuc, short soulOptionId, short soulSocketId, short soulOption,
                 short rStr, short rDex, short rInt, short rLuk, short rLevel, short rJob, short rPop, boolean isCash,
                 String iSlot, String vSlot, int fixedGrade, List<Integer> options, int specialGrade, boolean fixedPotential,
                 boolean noPotential, boolean tradeBlock, boolean only, boolean notSale, int attackSpeed, int price, int charmEXP,
                 boolean expireOnLogout, int setItemID, boolean exItem, boolean hasEquipTradeBlock, String owner) {
        super(itemId, bagIndex, cashItemSerialNumber, dateExpire, InvType.EQUIP, isCash, Type.EQUIP);
        this.serialNumber = serialNumber;
        this.title = title;
        this.equippedDate = equippedDate;
        this.prevBonusExpRate = prevBonusExpRate;
        this.tuc = tuc;
        this.cuc = cuc;
        this.iStr = iStr;
        this.iDex = iDex;
        this.iInt = iInt;
        this.iLuk = iLuk;
        this.iMaxHp = iMaxHp;
        this.iMaxMp = iMaxMp;
        this.iPad = iPad;
        this.iMad = iMad;
        this.iPDD = iPDD;
        this.iMDD = iMDD;
        this.iAcc = iAcc;
        this.iEva = iEva;
        this.iCraft = iCraft;
        this.iSpeed = iSpeed;
        this.iJump = iJump;
        this.attribute = attribute;
        this.levelUpType = levelUpType;
        this.level = level;
        this.exp = exp;
        this.durability = durability;
        this.iuc = iuc;
        this.iPvpDamage = iPvpDamage;
        this.iReduceReq = iReduceReq;
        this.specialAttribute = specialAttribute;
        this.durabilityMax = durabilityMax;
        this.iIncReq = iIncReq;
        this.growthEnchant = growthEnchant;
        this.psEnchant = psEnchant;
        this.bdr = bdr;
        this.imdr = imdr;
        this.damR = damR;
        this.statR = statR;
        this.cuttable = cuttable;
        this.exGradeOption = exGradeOption;
        this.itemState = itemState;
        this.chuc = chuc;
        this.soulOptionId = soulOptionId;
        this.soulSocketId = soulSocketId;
        this.soulOption = soulOption;
        this.rStr = rStr;
        this.rDex = rDex;
        this.rInt = rInt;
        this.rLuk = rLuk;
        this.rLevel = rLevel;
        this.rJob = rJob;
        this.rPop = rPop;
        this.iSlot = iSlot;
        this.vSlot = vSlot;
        this.fixedGrade = fixedGrade;
        this.options = options;
        this.specialGrade = specialGrade;
        this.fixedPotential = fixedPotential;
        this.noPotential = noPotential;
        this.tradeBlock = tradeBlock;
        this.only = only;
        this.notSale = notSale;
        this.attackSpeed = attackSpeed;
        this.price = price;
        this.charmEXP = charmEXP;
        this.expireOnLogout = expireOnLogout;
        this.setItemID = setItemID;
        this.exItem = exItem;
        equipTradeBlock = hasEquipTradeBlock;
        this.setOwner(owner);
    }

    public Equip deepCopy() {
        Equip ret = new Equip();
        ret.quantity = quantity;
        ret.bagIndex = bagIndex;
        ret.serialNumber = serialNumber;
        ret.title = title;
        ret.equippedDate = equippedDate.deepCopy();
        ret.prevBonusExpRate = prevBonusExpRate;
        ret.tuc = tuc;
        ret.cuc = cuc;
        ret.iStr = iStr;
        ret.iDex = iDex;
        ret.iInt = iInt;
        ret.iLuk = iLuk;
        ret.iMaxHp = iMaxHp;
        ret.iMaxMp = iMaxMp;
        ret.iPad = iPad;
        ret.iMad = iMad;
        ret.iPDD = iPDD;
        ret.iMDD = iMDD;
        ret.iAcc = iAcc;
        ret.iEva = iEva;
        ret.iCraft = iCraft;
        ret.iSpeed = iSpeed;
        ret.iJump = iJump;
        ret.attribute = attribute;
        ret.levelUpType = levelUpType;
        ret.level = level;
        ret.exp = exp;
        ret.durability = durability;
        ret.iuc = iuc;
        ret.iPvpDamage = iPvpDamage;
        ret.iReduceReq = iReduceReq;
        ret.specialAttribute = specialAttribute;
        ret.durabilityMax = durabilityMax;
        ret.iIncReq = iIncReq;
        ret.growthEnchant = growthEnchant;
        ret.psEnchant = psEnchant;
        ret.bdr = bdr;
        ret.imdr = imdr;
        ret.damR = damR;
        ret.statR = statR;
        ret.cuttable = cuttable;
        ret.exGradeOption = exGradeOption;
        ret.itemState = itemState;
        ret.chuc = chuc;
        ret.soulOptionId = soulOptionId;
        ret.soulSocketId = soulSocketId;
        ret.soulOption = soulOption;
        ret.rStr = rStr;
        ret.rDex = rDex;
        ret.rInt = rInt;
        ret.rLuk = rLuk;
        ret.rLevel = rLevel;
        ret.rJob = rJob;
        ret.rPop = rPop;
        ret.iSlot = iSlot;
        ret.vSlot = vSlot;
        ret.fixedGrade = fixedGrade;
        ret.options = new ArrayList<>();
        ret.options.addAll(options);
        ret.specialGrade = specialGrade;
        ret.fixedPotential = fixedPotential;
        ret.noPotential = noPotential;
        ret.tradeBlock = tradeBlock;
        ret.only = only;
        ret.notSale = notSale;
        ret.attackSpeed = attackSpeed;
        ret.price = price;
        ret.charmEXP = charmEXP;
        ret.expireOnLogout = expireOnLogout;
        ret.setItemID = setItemID;
        ret.exItem = exItem;
        ret.equipTradeBlock = equipTradeBlock;
        ret.setOwner(getOwner());
        ret.itemId = itemId;
        ret.cashItemSerialNumber = cashItemSerialNumber;
        ret.dateExpire = dateExpire.deepCopy();
        ret.invType = invType;
        ret.type = type;
        ret.isCash = isCash;
        System.arraycopy(sockets, 0, ret.sockets, 0, sockets.length);
        return ret;
    }

    public long getSerialNumber() {
        return getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FileTime getEquippedDate() {
        return equippedDate;
    }

    public int getPrevBonusExpRate() {
        return prevBonusExpRate;
    }

    // scroll slots
    public short getTuc() {
        return tuc;
    }

    public short getCuc() {
        return cuc;
    }

    public void setCuc(short cuc) {
        this.cuc = cuc;
    }

    public short getiStr() {
        return iStr;
    }

    public void setiStr(short iStr) {
        this.iStr = iStr;
    }

    public short getiDex() {
        return iDex;
    }

    public void setiDex(short iDex) {
        this.iDex = iDex;
    }

    public short getiInt() {
        return iInt;
    }

    public void setiInt(short iInt) {
        this.iInt = iInt;
    }

    public short getiLuk() {
        return iLuk;
    }

    public void setiLuk(short iLuk) {
        this.iLuk = iLuk;
    }

    public short getiMaxHp() {
        return iMaxHp;
    }

    public void setiMaxHp(short iMaxHp) {
        this.iMaxHp = iMaxHp;
    }

    public short getiMaxMp() {
        return iMaxMp;
    }

    public void setiMaxMp(short iMaxMp) {
        this.iMaxMp = iMaxMp;
    }

    public short getiPad() {
        return iPad;
    }

    public void setiPad(short iPad) {
        this.iPad = iPad;
    }

    public short getiMad() {
        return iMad;
    }

    public void setiMad(short iMad) {
        this.iMad = iMad;
    }

    public short getiPDD() {
        return iPDD;
    }

    public void setiPDD(short iPDD) {
        this.iPDD = iPDD;
    }

    public short getiMDD() {
        return iMDD;
    }

    public void setiMDD(short iMDD) {
        this.iMDD = iMDD;
    }

    public short getiAcc() {
        return iAcc;
    }

    public void setiAcc(short iAcc) {
        this.iAcc = iAcc;
    }

    public short getiEva() {
        return iEva;
    }

    public void setiEva(short iEva) {
        this.iEva = iEva;
    }

    public short getiCraft() {
        return iCraft;
    }

    public void setiCraft(short iCraft) {
        this.iCraft = iCraft;
    }

    public short getiSpeed() {
        return iSpeed;
    }

    public void setiSpeed(short iSpeed) {
        this.iSpeed = iSpeed;
    }

    public short getiJump() {
        return iJump;
    }

    public void setiJump(short iJump) {
        this.iJump = iJump;
    }

    public short getAttribute() {
        return attribute;
    }

    public void setAttribute(short attribute) {
        this.attribute = attribute;
    }

    public void addAttribute(EquipAttribute ea) {
        short attr = getAttribute();
        attr |= ea.getVal();
        setAttribute(attr);
    }

    public short getLevelUpType() {
        return levelUpType;
    }

    public void setLevelUpType(short levelUpType) {
        this.levelUpType = levelUpType;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public short getExp() {
        return exp;
    }

    public void setExp(short exp) {
        this.exp = exp;
    }

    public short getDurability() {
        return durability;
    }

    public void setDurability(short durability) {
        this.durability = durability;
    }

    public short getIuc() {
        return iuc;
    }

    public void setIuc(short iuc) {
        this.iuc = iuc;
    }

    public short getiPvpDamage() {
        return iPvpDamage;
    }

    public void setiPvpDamage(short iPvpDamage) {
        this.iPvpDamage = iPvpDamage;
    }

    public short getiReduceReq() {
        return iReduceReq;
    }

    public void setiReduceReq(short iReduceReq) {
        this.iReduceReq = iReduceReq;
    }

    public short getSpecialAttribute() {
        return specialAttribute;
    }

    public void setSpecialAttribute(short specialAttribute) {
        this.specialAttribute = specialAttribute;
    }

    public void addSpecialAttribute(EquipSpecialAttribute esa) {
        short attr = getSpecialAttribute();
        attr |= esa.getVal();
        setSpecialAttribute(attr);
    }

    public short getExGradeOption() {
        return exGradeOption;
    }

    public void setExGradeOption(short exGradeOption) {
        this.exGradeOption = exGradeOption;
    }

    public short getCuttable() {
        return cuttable;
    }

    public void setCuttable(short cuttable) {
        this.cuttable = cuttable;
    }

    public short getStatR() {
        return statR;
    }

    public void setStatR(short statR) {
        this.statR = statR;
    }

    public short getDamR() {
        return damR;
    }

    public void setDamR(short damR) {
        this.damR = damR;
    }

    public short getImdr() {
        return imdr;
    }

    public void setImdr(short imdr) {
        this.imdr = imdr;
    }

    public short getBdr() {
        return bdr;
    }

    public void setBdr(short bdr) {
        this.bdr = bdr;
    }

    public short getPsEnchant() {
        return psEnchant;
    }

    public void setPsEnchant(short psEnchant) {
        this.psEnchant = psEnchant;
    }

    public short getGrowthEnchant() {
        return growthEnchant;
    }

    public void setGrowthEnchant(short growthEnchant) {
        this.growthEnchant = growthEnchant;
    }

    public short getiIncReq() {
        return iIncReq;
    }

    public void setiIncReq(short iIncReq) {
        this.iIncReq = iIncReq;
    }

    public short getDurabilityMax() {
        return durabilityMax;
    }

    public void setDurabilityMax(short durabilityMax) {
        this.durabilityMax = durabilityMax;
    }

    public short getItemState() {
        return itemState;
    }

    public void setItemState(short itemState) {
        this.itemState = itemState;
    }

    public short getGrade() {
        ItemGrade ig = ItemGrade.getGradeByVal(Math.min(getBaseGrade(), getBonusGrade()));
        switch(ig) {
            case HIDDEN_RARE:
            case HIDDEN_EPIC:
            case HIDDEN_UNIQUE:
            case HIDDEN_LEGENDARY:
                return ig.getVal();
        }
        return (short) Math.max(getBaseGrade(), getBonusGrade());
    }

    public short getBaseGrade() {
        return ItemGrade.getGradeByOption(getOptionBase(0)).getVal();
    }

    public short getBonusGrade() {
        return ItemGrade.getGradeByOption(getOptionBonus(0)).getVal();
    }


    public short getChuc() {
        return chuc;
    }

    public void setChuc(short chuc) {
        this.chuc = chuc;
        recalcEnchantmentStats();
    }

    public short getSoulOptionId() {
        return soulOptionId;
    }

    public void setSoulOptionId(short soulOptionId) {
        this.soulOptionId = soulOptionId;
    }

    public short getSoulSocketId() {
        return soulSocketId;
    }

    public void setSoulSocketId(short soulSocketId) {
        this.soulSocketId = soulSocketId;
    }

    public short getSoulOption() {
        return soulOption;
    }

    public void setSoulOption(short soulOption) {
        this.soulOption = soulOption;
    }

    public short getrPop() {
        return rPop;
    }

    public void setrPop(short rPop) {
        this.rPop = rPop;
    }

    public short getrJob() {
        return rJob;
    }

    public void setrJob(short rJob) {
        this.rJob = rJob;
    }

    public short getrLevel() {
        return rLevel;
    }

    public void setrLevel(short rLevel) {
        this.rLevel = rLevel;
    }

    public short getrLuk() {
        return rLuk;
    }

    public void setrLuk(short rLuk) {
        this.rLuk = rLuk;
    }

    public short getrInt() {
        return rInt;
    }

    public void setrInt(short rInt) {
        this.rInt = rInt;
    }

    public short getrDex() {
        return rDex;
    }

    public void setrDex(short rDex) {
        this.rDex = rDex;
    }

    public short getrStr() {
        return rStr;
    }

    public void setrStr(short rStr) {
        this.rStr = rStr;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public String getiSlot() {
        return iSlot;
    }

    public void setiSlot(String iSlot) {
        this.iSlot = iSlot;
    }

    public String getvSlot() {
        return vSlot;
    }

    public void setvSlot(String vSlot) {
        this.vSlot = vSlot;
    }

    public int getSpecialGrade() {
        return specialGrade;
    }

    public boolean isFixedPotential() {
        return fixedPotential;
    }

    public boolean isNoPotential() {
        return noPotential;
    }

    public boolean isTradeBlock() {
        return tradeBlock;
    }

    public boolean isOnly() {
        return only;
    }

    public boolean isNotSale() {
        return notSale;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getPrice() {
        return price;
    }

    public int getCharmEXP() {
        return charmEXP;
    }

    public boolean isExpireOnLogout() {
        return expireOnLogout;
    }

    public int getSetItemID() {
        return setItemID;
    }

    public int getFixedGrade() {
        return fixedGrade;
    }

    public boolean isExItem() {
        return exItem;
    }

    public boolean isEquipTradeBlock() {
        return equipTradeBlock;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEquippedDate(FileTime equippedDate) {
        this.equippedDate = equippedDate;
    }

    public void setPrevBonusExpRate(int prevBonusExpRate) {
        this.prevBonusExpRate = prevBonusExpRate;
    }

    public void setTuc(short tuc) {
        this.tuc = tuc;
    }

    public void setSpecialGrade(int specialGrade) {
        this.specialGrade = specialGrade;
    }

    public void setFixedPotential(boolean fixedPotential) {
        this.fixedPotential = fixedPotential;
    }

    public void setNoPotential(boolean noPotential) {
        this.noPotential = noPotential;
    }

    public void setTradeBlock(boolean tradeBlock) {
        this.tradeBlock = tradeBlock;
    }

    public void setOnly(boolean only) {
        this.only = only;
    }

    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCharmEXP(int charmEXP) {
        this.charmEXP = charmEXP;
    }

    public void setExpireOnLogout(boolean expireOnLogout) {
        this.expireOnLogout = expireOnLogout;
    }

    public void setSetItemID(int setItemID) {
        this.setItemID = setItemID;
    }

    public void setExItem(boolean exItem) {
        this.exItem = exItem;
    }

    public void setEquipTradeBlock(boolean equipTradeBlock) {
        this.equipTradeBlock = equipTradeBlock;
    }

    public void setFixedGrade(int fixedGrade) {
        this.fixedGrade = fixedGrade;
    }

    public void encode(OutPacket outPacket) {
        // GW_ItemSlotBase
        super.encode(outPacket);
        // GW_ItemSlotEquip
//        outPacket.encodeLong(getSerialNumber());
//        outPacket.encodeString(getTitle(), 13);
//        getEquippedDate().encode(outPacket);
//        outPacket.encodeInt(getPrevBonusExpRate());
        // GW_ItemSlotEquipBase
        int mask = getStatMask(0);
        outPacket.encodeInt(mask);
        if(hasStat(EquipBaseStat.tuc)) {
            outPacket.encodeByte(getTuc());
        }
        if(hasStat(EquipBaseStat.cuc)) {
            outPacket.encodeByte(getCuc());
        }
        if(hasStat(EquipBaseStat.iStr)) {
            outPacket.encodeShort(getiStr() + getEnchantStat(EnchantStat.STR));
        }
        if(hasStat(EquipBaseStat.iDex)) {
            outPacket.encodeShort(getiDex() + getEnchantStat(EnchantStat.DEX));
        }
        if(hasStat(EquipBaseStat.iInt)) {
            outPacket.encodeShort(getiInt() +  + getEnchantStat(EnchantStat.INT));
        }
        if(hasStat(EquipBaseStat.iLuk)) {
            outPacket.encodeShort(getiLuk() + getEnchantStat(EnchantStat.LUK));
        }
        if(hasStat(EquipBaseStat.iMaxHP)) {
            outPacket.encodeShort(getiMaxHp() + getEnchantStat(EnchantStat.MHP));
        }
        if(hasStat(EquipBaseStat.iMaxMP)) {
            outPacket.encodeShort(getiMaxMp() + getEnchantStat(EnchantStat.MMP));
        }
        if(hasStat(EquipBaseStat.iPAD)) {
            outPacket.encodeShort(getiPad() + getEnchantStat(EnchantStat.PAD));
        }
        if(hasStat(EquipBaseStat.iMAD)) {
            outPacket.encodeShort(getiMad() + getEnchantStat(EnchantStat.MAD));
        }
        if(hasStat(EquipBaseStat.iPDD)) {
            outPacket.encodeShort(getiPDD() + getEnchantStat(EnchantStat.PDD));
        }
        if(hasStat(EquipBaseStat.iMDD)) {
            outPacket.encodeShort(getiMDD() + getEnchantStat(EnchantStat.MDD));
        }
        if(hasStat(EquipBaseStat.iACC)) {
            outPacket.encodeShort(getiAcc() + getEnchantStat(EnchantStat.ACC));
        }
        if(hasStat(EquipBaseStat.iEVA)) {
            outPacket.encodeShort(getiEva() + getEnchantStat(EnchantStat.EVA));
        }
        if(hasStat(EquipBaseStat.iCraft)) {
            outPacket.encodeShort(getiCraft());
        }
        if(hasStat(EquipBaseStat.iSpeed)) {
            outPacket.encodeShort(getiSpeed() + getEnchantStat(EnchantStat.SPEED));
        }
        if(hasStat(EquipBaseStat.iJump)) {
            outPacket.encodeShort(getiJump() + getEnchantStat(EnchantStat.JUMP));
        }
        if(hasStat(EquipBaseStat.attribute)) {
            outPacket.encodeShort(getAttribute());
        }
        if(hasStat(EquipBaseStat.levelUpType)) {
            outPacket.encodeByte(getLevelUpType());
        }
        if(hasStat(EquipBaseStat.level)) {
            outPacket.encodeByte(getLevel());
        }
        if(hasStat(EquipBaseStat.exp)) {
            outPacket.encodeLong(getExp());
        }
        if(hasStat(EquipBaseStat.durability)) {
            outPacket.encodeInt(getDurability());
        }
        if(hasStat(EquipBaseStat.iuc)) {
            outPacket.encodeInt(getIuc()); // hammer
        }
        if(hasStat(EquipBaseStat.iPvpDamage)) {
            outPacket.encodeShort(getiPvpDamage());
        }
        if(hasStat(EquipBaseStat.specialAttribute)) {
            outPacket.encodeShort(getSpecialAttribute());
        }
        if(hasStat(EquipBaseStat.durabilityMax)) {
            outPacket.encodeInt(getDurabilityMax());
        }
        if(hasStat(EquipBaseStat.iIncReq)) {
            outPacket.encodeByte(getrLevel());
        }
        if(hasStat(EquipBaseStat.growthEnchant)) {
            outPacket.encodeByte(getGrowthEnchant()); // ygg
        }
        if(hasStat(EquipBaseStat.psEnchant)) {
            outPacket.encodeByte(getPsEnchant()); // final strike
        }
        if(hasStat(EquipBaseStat.bdr)) {
            outPacket.encodeByte(getBdr()); // bd
        }
        if(hasStat(EquipBaseStat.imdr)) {
            outPacket.encodeByte(getImdr()); // ied
        }
        outPacket.encodeInt(getStatMask(1)); // mask 2
        if(hasStat(EquipBaseStat.damR)) {
            outPacket.encodeByte(getDamR()); // td
        }
        if(hasStat(EquipBaseStat.statR)) {
            outPacket.encodeByte(getStatR()); // as
        }
        if(hasStat(EquipBaseStat.cuttable)) {
            outPacket.encodeByte(getCuttable()); // sok
        }
        if(hasStat(EquipBaseStat.exGradeOption)) {
            outPacket.encodeLong(getExGradeOption());
        }
        if(hasStat(EquipBaseStat.itemState)) {
            outPacket.encodeInt(getItemState());
        }
        // GW_ItemSlotEquipOpt
        outPacket.encodeString(getOwner());
        outPacket.encodeByte(getGrade());
        outPacket.encodeByte(getChuc());
        for (int i = 0; i < 7; i++) {
            outPacket.encodeShort(getOptions().get(i)); // 7x, last is fusion anvil
        }
        short socketMask = 0; // 0b0nnn_kkkb: from right to left: boolean active, k empty, n has socket
        for (int i = 0; i < getSockets().length; i++) {
            int socket = getSockets()[i];
            // Self made numbers for socket: 3 == empty (since 0 is already taken for STR+1, similar for 1/2)
            if (socket != 0) {
                socketMask |= 1;
                socketMask |= 1 << i + 1;
                if (socket != ItemConstants.EMPTY_SOCKET_ID) {
                    socketMask |= 1 << (i + 4); // 3 sockets, look at the comment at socketMask.
                }
            }
        }
        outPacket.encodeShort(socketMask); // socket state, 0 = nothing, 0xFF = see loop
        for(int i = 0; i < 3; i++) {
            outPacket.encodeShort(getSockets()[i]); // sockets 0 through 2 (-1 = none, 0 = empty, >0 = filled
        }
        outPacket.encodeLong(getId()); // ?
        outPacket.encodeInt(-1); // ?
        // GW_CashItemOption
        outPacket.encodeLong(getCashItemSerialNumber());
        outPacket.encodeFT(getDateExpire());
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME));
        for (int i = 0; i < 2; i++) {
            outPacket.encodeLong(0);
        }
        outPacket.encodeShort(getSoulOptionId()); // soul ID
        outPacket.encodeShort(getSoulSocketId()); // enchanter ID
        outPacket.encodeShort(getSoulOption()); // optionID (same as potentials)
    }

    private boolean hasStat(EquipBaseStat ebs) {
        return getBaseStat(ebs) != 0;
    }

    private int getStatMask(int pos) {
        int mask = 0;
        for (EquipBaseStat ebs : EquipBaseStat.values()) {
            if (getBaseStat(ebs) != 0 && ebs.getPos() == pos) {
                mask |= ebs.getVal();
            }
        }
        return mask;
    }

    public void setBaseStat(EquipBaseStat equipBaseStat, long amount) {
        switch(equipBaseStat){
            case tuc:
                setTuc((short) amount);
                break;
            case cuc:
                setCuc((short) amount);
                break;
            case iStr:
                setiStr((short) amount);
                break;
            case iDex:
                setiDex((short) amount);
                break;
            case iInt:
                setiInt((short) amount);
                break;
            case iLuk:
                setiLuk((short) amount);
                break;
            case iMaxHP:
                setiMaxHp((short) amount);
                break;
            case iMaxMP:
                setiMaxMp((short) amount);
                break;
            case iPAD:
                setiPad((short) amount);
                break;
            case iMAD:
                setiMad((short) amount);
                break;
            case iPDD:
                setiPDD((short) amount);
                break;
            case iMDD:
                setiMDD((short) amount);
                break;
            case iACC:
                setiAcc((short) amount);
                break;
            case iEVA:
                setiEva((short) amount);
                break;
            case iCraft:
                setiCraft((short) amount);
                break;
            case iSpeed:
                setiSpeed((short) amount);
                break;
            case iJump:
                setiJump((short) amount);
                break;
            case attribute:
                setAttribute((short) amount);
                break;
            case levelUpType:
                setLevelUpType((short) amount);
                break;
            case level:
                setLevel((short) amount);
                break;
            case exp:
                setExp((short) amount);
                break;
            case durability:
                setDurability((short) amount);
                break;
            case iuc:
                setIuc((short) amount);
                break;
            case iPvpDamage:
                setiPvpDamage((short) amount);
                break;
            case iReduceReq:
                setiReduceReq((short) amount);
                break;
            case specialAttribute:
                setSpecialAttribute((short) amount);
                break;
            case durabilityMax:
                setDurabilityMax((short) amount);
                break;
            case iIncReq:
                setiIncReq((short) amount);
                break;
            case growthEnchant:
                setGrowthEnchant((short) amount);
                break;
            case psEnchant:
                setPsEnchant((short) amount);
                break;
            case bdr:
                setBdr((short) amount);
                break;
            case imdr:
                setImdr((short) amount);
                break;
            case damR:
                setDamR((short) amount);
                break;
            case statR:
                setStatR((short) amount);
                break;
            case cuttable:
                setCuttable((short) amount);
                break;
            case exGradeOption:
                setExGradeOption((short) amount);
                break;
            case itemState:
                setItemState((short) amount);
                break;
        }
    }

    public long getBaseStat(EquipBaseStat equipBaseStat) {
        switch(equipBaseStat){
            case tuc:
                return getTuc();
            case cuc:
                return getCuc();
            case iStr:
                return getiStr();
            case iDex:
                return getiDex();
            case iInt:
                return getiInt();
            case iLuk:
                return getiLuk();
            case iMaxHP:
                return getiMaxHp();
            case iMaxMP:
                return getiMaxMp();
            case iPAD:
                return getiPad();
            case iMAD:
                return getiMad();
            case iPDD:
                return getiPDD();
            case iMDD:
                return getiMDD();
            case iACC:
                return getiAcc();
            case iEVA:
                return getiEva();
            case iCraft:
                return getiCraft();
            case iSpeed:
                return getiSpeed();
            case iJump:
                return getiJump();
            case attribute:
                return getAttribute();
            case levelUpType:
                return getLevelUpType();
            case level:
                return getLevel();
            case exp:
                return getExp();
            case durability:
                return getDurability();
            case iuc:
                return getIuc();
            case iPvpDamage:
                return getiPvpDamage();
            case iReduceReq:
                return getiReduceReq();
            case specialAttribute:
                return getSpecialAttribute();
            case durabilityMax:
                return getDurabilityMax();
            case iIncReq:
                return getiIncReq();
            case growthEnchant:
                return getGrowthEnchant();
            case psEnchant:
                return getPsEnchant();
            case bdr:
                return getBdr();
            case imdr:
                return getImdr();
            case damR:
                return getDamR();
            case statR:
                return getStatR();
            case cuttable:
                return getCuttable();
            case exGradeOption:
                return getExGradeOption();
            case itemState:
                return getItemState();
            default: return 0;
        }
    }

    public void addStat(EquipBaseStat stat, int amount) {
        int cur = (int) getBaseStat(stat);
        int newStat = cur + amount >= 0 ? cur + amount : 0; // stat cannot be negative
        setBaseStat(stat, newStat);
    }

    public boolean hasAttribute(EquipAttribute equipAttribute) {
        return (getAttribute() & equipAttribute.getVal()) != 0;
    }

    public boolean hasSpecialAttribute(EquipSpecialAttribute equipSpecialAttribute) {
        return (getSpecialAttribute() & equipSpecialAttribute.getVal()) != 0;
    }

    public void removeAttribute(EquipAttribute equipAttribute) {
        if(!hasAttribute(equipAttribute)) {
            return;
        }
        short attr = getAttribute();
        attr ^= equipAttribute.getVal();
        setAttribute(attr);
    }

    public void removeSpecialAttribute(EquipSpecialAttribute equipSpecialAttribute) {
        if(!hasSpecialAttribute(equipSpecialAttribute)) {
            return;
        }
        short attr = getSpecialAttribute();
        attr ^= equipSpecialAttribute.getVal();
        setSpecialAttribute(attr);
    }

    public TreeMap<EnchantStat, Integer> getHyperUpgradeStats() {
        Comparator<EnchantStat> comparator = Comparator.comparingInt(EnchantStat::getVal);
        TreeMap<EnchantStat, Integer> res = new TreeMap<>(comparator);
        for(EnchantStat es : EnchantStat.values()) {
            int curAmount = (int) getBaseStat(es.getEquipBaseStat());
            if(curAmount > 0) {
                res.put(es, GameConstants.getEnchantmentValByChuc(es, getChuc(), curAmount));
            }
        }
        return res;
    }

    public int[] getOptionBase() {
        return new int[] {getOptions().get(0), getOptions().get(1), getOptions().get(2)};
    }

    public int getOptionBase(int num) {
        return getOptions().get(num);
    }

    public int setOptionBase(int num, int val) {
        return getOptions().set(num, val);
    }

    public int[] getOptionBonus() {
        return new int[] {getOptions().get(3), getOptions().get(4), getOptions().get(5)};
    }

    public int getOption(int num, boolean bonus) {
        return bonus ? getOptionBonus(num) : getOptionBase(num);
    }

    public int getOptionBonus(int num) {
        return getOptions().get(num + 3);
    }

    public void setOptionBonus(int num, int val) {
        getOptions().set(num + 3, val);
    }

    public void setOption(int num, int val, boolean bonus) {
        if (bonus) {
            setOptionBonus(num, val);
        } else {
            setOptionBase(num, val);
        }
    }

    public int getRandomOption(boolean bonus, int line) {
        List<Integer> data = ItemConstants.getWeightedOptionsByEquip(this, bonus, line);
        return data.get(Util.getRandom(data.size() - 1));
    }

    /**
     * Resets the potential of this equip's base options. Takes the value of an ItemGrade (1-4), and sets the appropriate values.
     * Also calculates if a third line should be added.
     * @param val The value of the item's grade (HIDDEN_RARE~HIDDEN_LEGENDARY).getVal().
     * @param thirdLineChance The chance of a third line being added.
     */
    public void setHiddenOptionBase(short val, int thirdLineChance) {
        if (!ItemConstants.canEquipHavePotential(this)) {
            return;
        }

        int max = 3;
        if(getOptionBase(3) == 0) {
            // If this equip did not have a 3rd line already, thirdLineChance to get it
            if(Util.succeedProp(100 - thirdLineChance)) {
                max = 2;
            }
        }
        for (int i = 0; i < max; i++) {
            setOptionBase(i, -val);
        }
    }

    public void setHiddenOptionBonus(short val, int thirdLineChance) {
        if (!ItemConstants.canEquipHavePotential(this)) {
            return;
        }

        int max = 3;
        if(getOptionBonus(3) == 0) {
            // If this equip did not have a 3rd line already, thirdLineChance to get it
            if(Util.succeedProp(100 - thirdLineChance)) {
                max = 2;
            }
        }
        for (int i = 0; i < max; i++) {
            setOptionBonus(i, -val);
        }
    }

    public void releaseOptions(boolean bonus) {
        if (!ItemConstants.canEquipHavePotential(this)) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(getOption(i, bonus) < 0) {
                setOption(i, getRandomOption(bonus, i), bonus);
            }
        }
    }

    public int getAnvilId() {
        return getOptions().get(6); // Anvil
    }

    public Map<EnchantStat, Integer> getEnchantStats() {
        return enchantStats;
    }

    public void putEnchantStat(EnchantStat es, int val) {
        getEnchantStats().put(es, val);
    }

    public void setEnchantStats(Map<EnchantStat, Integer> enchantStats) {
        this.enchantStats = enchantStats;
    }

    public void recalcEnchantmentStats() {
        getEnchantStats().clear();
        for (int i = 1; i <= getChuc(); i++) {
            for(EnchantStat es : getHyperUpgradeStats().keySet()) {
                putEnchantStat(es, getEnchantStats().getOrDefault(es, 0) +
                        GameConstants.getEnchantmentValByChuc(es, (short) i, (int) getBaseStat(es.getEquipBaseStat())));
            }
        }
    }

    /**
     * Returns the current value of an EnchantStat. Zero if absent.
     * @param es The EnchantStat to get
     * @return the corresponding stat value
     */
    public int getEnchantStat(EnchantStat es) {
        return getEnchantStats().getOrDefault(es, 0);
    }

    public short[] getSockets() {
        return sockets;
    }

    public void setSockets(short[] sockets) {
        this.sockets = sockets;
    }

    public int getBaseStat(BaseStat baseStat) {
        // TODO Potentials + sockets
        int res = 0;
        switch (baseStat) {
            case str:
                res += getiStr();
                break;
            case dex:
                res += getiDex();
                break;
            case inte:
                res += getiInt();
                break;
            case luk:
                res += getiLuk();
                break;
            case pad:
                res += getiPad();
                break;
            case mad:
                res += getiMad();
                break;
            case pdd:
                res += getiPDD();
                break;
            case mdd:
                res += getiMDD();
                break;
            case mhp:
                res += getiMaxHp();
                break;
            case mmp:
                res += getiMaxMp();
                break;
            case fd:
                res += getDamR();
                break;
            case bd:
                res += getBdr();
                break;
            case ied:
                res += getImdr();
                break;
            case eva:
                res += getiEva();
                break;
            case acc:
                res += getiAcc();
                break;
            case speed:
                res += getiSpeed();
                break;
            case jump:
                res += getiJump();
                break;
            case booster:
                res += getAttackSpeed();
                break;
            case strR:
            case dexR:
            case intR:
            case lukR:
                res += getStatR();
                break;
        }
        return res;
    }

    @Override
    public boolean isTradable() {
        return !hasAttribute(EquipAttribute.UNTRADABLE);
    }

    public void applyInnocenceScroll() {
        Equip defaultEquip = ItemData.getEquipDeepCopyFromID(getItemId(), false);
        for (EquipBaseStat ebs : EquipBaseStat.values()) {
            if (ebs != EquipBaseStat.attribute && ebs != EquipBaseStat.growthEnchant && ebs != EquipBaseStat.psEnchant) {
                setBaseStat(ebs, defaultEquip.getBaseStat(ebs));
            }
        }
        setChuc((short) 0);
        recalcEnchantmentStats();
    }

    public boolean hasUsedSlots() {
        Equip defaultEquip = ItemData.getEquipDeepCopyFromID(getItemId(), false);
        return defaultEquip.getTuc() != getTuc();
    }
}
