package net.swordie.ms.life.drop;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.DropType;
import net.swordie.ms.connection.packet.DropPool;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.life.Life;

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
    private long mobExp;

    public Drop(int templateId) {
        super(templateId);
    }

    public DropType getDropType() {
        return dropType;
    }

    public void setDropType(DropType dropType) {
        this.dropType = dropType;
    }

    public Drop(int templateId, Item item) {
        super(templateId);
        this.item = item;
        dropType = DropType.ITEM;
        expireTime = FileTime.fromType(FileTime.Type.ZERO_TIME);
    }

    public Drop(int templateId, int money) {
        super(templateId);
        this.money = money;
        dropType = DropType.MONEY;
        expireTime = FileTime.fromType(FileTime.Type.ZERO_TIME);
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

    public void setMobExp(long mobExp) {
        this.mobExp = mobExp;
    }

    public long getMobExp() {
        return mobExp;
    }
}
