package client.life;

import util.Util;

/**
 * Created on 2/21/2018.
 */
public class DropInfo {
    private int itemID;
    private int chance; // out of a 1000
    private int questReq;
    private int money;

    public DropInfo() {
    }

    public DropInfo(int itemID, int money, int chance, int questReq) {
        this.itemID = itemID;
        this.money = money;
        this.chance = chance;
        this.questReq = questReq;
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
        return Util.succeedProp(getChance(), 1000);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
