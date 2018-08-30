package net.swordie.ms.life.drop;

import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.util.Util;

/**
 * Created on 2/21/2018.
 */
public class DropInfo {
    private int itemID;
    private int chance; // out of a 1000
    private int questReq;
    private int money;
    private int minMoney, maxmoney;
    private int minQuant = 1;
    private int maxQuant = 1;
    private int quantity = 1;

    public DropInfo() {
    }

    public DropInfo(int itemID, int money, int chance, int questReq) {
        this.itemID = itemID;
        this.money = money;
        this.chance = chance;
        this.questReq = questReq;
    }

    public DropInfo(int itemID, int chance, int questReq, int minMoney, int maxmoney) {
        this.itemID = itemID;
        this.chance = chance;
        this.questReq = questReq;
        this.minMoney = minMoney;
        this.maxmoney = maxmoney;
        generateNextDrop();
    }

    public DropInfo(int itemID, int money, int chance, int questReq, int minQuant, int maxQuant) {
        this.itemID = itemID;
        this.money = money;
        this.chance = chance;
        this.questReq = questReq;
        this.minQuant = minQuant;
        this.maxQuant = maxQuant;
        generateNextDrop();
    }

    public void generateNextDrop() {
        if (getMaxmoney() > 0) {
            setMoney(getMinMoney() + Util.getRandom(getMaxmoney() - getMinMoney()));
        } else {
            setQuantity(getMinQuant() + Util.getRandom(getMaxQuant() - getMinQuant()));
        }
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getMaxmoney() {
        return maxmoney;
    }

    public void setMaxmoney(int maxmoney) {
        this.maxmoney = maxmoney;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DropInfo)) {
            return false;
        }
        DropInfo other = (DropInfo) obj;
        return other.getItemID() == getItemID() && other.getMoney() == getMoney();
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getQuestReq() {
        return questReq;
    }

    public void setQuestReq(int questReq) {
        this.questReq = questReq;
    }

    /**
     * Does an RNG roll to check if this should be dropped.
     * @return Whether or not the drop is successful.
     */
    public boolean willDrop() {
        return Util.succeedProp(getChance(), GameConstants.MAX_DROP_CHANCE);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isMoney() {
        return getMoney() > 0 || getMinMoney() > 0;
    }

    public int getMinQuant() {
        return minQuant;
    }

    public void setMinQuant(int minQuant) {
        this.minQuant = minQuant;
    }

    public int getMaxQuant() {
        return maxQuant;
    }

    public void setMaxQuant(int maxQuant) {
        this.maxQuant = maxQuant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        if (getItemID() != 0) {
            return String.format("Item %d, chance %d", getItemID(), getChance());
        } else {
            return String.format("%d mesos.", getMoney());
        }
    }
}
