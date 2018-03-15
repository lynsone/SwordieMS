package loaders;

import enums.InvType;
import enums.ScrollStat;
import enums.SpecStat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 1/9/2018.
 */
public class ItemInfo {
    private int itemId;
    private InvType invType;
    private boolean cash;
    private int price;
    private int slotMax;
    private boolean tradeBlock;
    private boolean notSale;
    private String path = "";
    private boolean noCursed;
    private Map<ScrollStat, Integer> scrollStats = new HashMap<>();
    private Map<SpecStat, Integer> specStats = new HashMap<>();
    private int bagType;
    private int charmEXP;
    private boolean quest;
    private int reqQuestOnProgress;
    private int senseEXP;
    private Set<Integer> questIDs = new HashSet<>();
    private int mobID;
    private int npcID;
    private int linkedID;
    private boolean monsterBook;
    private boolean notConsume;
    private String script = "";

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setInvType(InvType invType) {
        this.invType = invType;
    }

    public InvType getInvType() {
        return invType;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isCash() {
        return cash;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setSlotMax(int slotMax) {
        this.slotMax = slotMax;
    }

    public int getSlotMax() {
        return slotMax;
    }

    public void setTradeBlock(boolean tradeBlock) {
        this.tradeBlock = tradeBlock;
    }

    public boolean isTradeBlock() {
        return tradeBlock;
    }

    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }

    public boolean isNotSale() {
        return notSale;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setNoCursed(boolean noCursed) {
        this.noCursed = noCursed;
    }

    public boolean isNoCursed() {
        return noCursed;
    }

    public Map<ScrollStat, Integer> getScrollStats() {
        return scrollStats;
    }

    public void setScrollStats(Map<ScrollStat, Integer> scrollStats) {
        this.scrollStats = scrollStats;
    }

    public void putScrollStat(ScrollStat scrollStat, int val) {
        getScrollStats().put(scrollStat, val);
    }

    public void setBagType(int bagType) {
        this.bagType = bagType;
    }

    public int getBagType() {
        return bagType;
    }

    public void setCharmEXP(int charmEXP) {
        this.charmEXP = charmEXP;
    }

    public int getCharmEXP() {
        return charmEXP;
    }

    public void setQuest(boolean quest) {
        this.quest = quest;
    }

    public boolean isQuest() {
        return quest;
    }

    public void setReqQuestOnProgress(int reqQuestOnProgress) {
        this.reqQuestOnProgress = reqQuestOnProgress;
    }

    public int getReqQuestOnProgress() {
        return reqQuestOnProgress;
    }

    public void setSenseEXP(int senseEXP) {
        this.senseEXP = senseEXP;
    }

    public int getSenseEXP() {
        return senseEXP;
    }

    public void addQuest(int questID) {
        getQuestIDs().add(questID);
    }

    public Set<Integer> getQuestIDs() {
        return questIDs;
    }

    public void setMobID(int mobID) {
        this.mobID = mobID;
    }

    public int getMobID() {
        return mobID;
    }

    public void setNpcID(int npcID) {
        this.npcID = npcID;
    }

    public int getNpcID() {
        return npcID;
    }

    public void setLinkedID(int linkedID) {
        this.linkedID = linkedID;
    }

    public int getLinkedID() {
        return linkedID;
    }

    public void setMonsterBook(boolean monsterBook) {
        this.monsterBook = monsterBook;
    }

    public boolean isMonsterBook() {
        return monsterBook;
    }

    public void setNotConsume(boolean notConsume) {
        this.notConsume = notConsume;
    }

    public boolean isNotConsume() {
        return notConsume;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    public void putSpecStat(SpecStat ss, int i) {
        getSpecStats().put(ss, i);
    }

    public Map<SpecStat, Integer> getSpecStats() {
        return specStats;
    }

    public void setSpecStats(Map<SpecStat, Integer> specStats) {
        this.specStats = specStats;
    }
}
