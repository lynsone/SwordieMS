package client.life;

import client.character.Char;
import client.character.items.Equip;
import client.character.items.Item;
import constants.GameConstants;
import enums.DropType;
import packet.DropPool;
import server.EventManager;
import util.FileTime;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2/21/2018.
 */
public class Drop extends Life {

    private Item item;
    private int money;
    private DropType dropType;
    private int ownerID;
    private boolean explosiveDrop;
    private FileTime expireTime;
    private boolean byPet;

    public Drop(int objectId) {
        super(objectId);
    }

    public DropType getDropType() {
        return dropType;
    }

    public void setDropType(DropType dropType) {
        this.dropType = dropType;
    }

    public Drop(int objectID, Item item) {
        super(objectID);
        this.item = item;
        dropType = DropType.ITEM;
        expireTime = FileTime.getFileTimeFromType(FileTime.Type.ZERO_TIME);
    }

    public Drop(int objectId, int money) {
        super(objectId);
        this.money = money;
        dropType = DropType.MONEY;
        expireTime = FileTime.getFileTimeFromType(FileTime.Type.ZERO_TIME);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        setDropType(DropType.ITEM);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        setDropType(DropType.MONEY);
    }

    public boolean isMoney() {
        return getDropType() == DropType.MONEY;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public boolean isExplosiveDrop() {
        return explosiveDrop;
    }

    public void setExplosiveDrop(boolean explosiveDrop) {
        this.explosiveDrop = explosiveDrop;
    }

    public FileTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(FileTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isByPet() {
        return byPet;
    }

    public void setByPet(boolean byPet) {
        this.byPet = byPet;
    }

    public byte getItemGrade() {
        byte res = 0;
        if(getItem() != null && getItem() instanceof Equip) {
            res = (byte) ((Equip) getItem()).getGrade();
        }
        return res;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        onlyChar.write(DropPool.dropEnterField(this, getPosition(), getOwnerID()));
        EventManager.addEvent(() -> setOwnerID(0), GameConstants.DROP_REMOVE_OWNERSHIP_TIME, TimeUnit.SECONDS);
    }
}
