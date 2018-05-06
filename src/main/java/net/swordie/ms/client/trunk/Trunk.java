package net.swordie.ms.client.trunk;

import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.DBChar;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.loaders.ItemData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 4/7/2018.
 */
@Entity
@Table(name = "trunks")
public class Trunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trunkID")
    private List<Item> items = new ArrayList<>();
    private long money;
    private byte slotCount;

    public Trunk(){}

    public Trunk(byte slotCount) {
        this.slotCount = slotCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public byte getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(byte slotCount) {
        this.slotCount = slotCount;
    }

    public void encodeItems(OutPacket outPacket) {
        outPacket.encodeByte(getSlotCount());
        long mask = DBChar.All.get();
        outPacket.encodeLong(mask);
        if((mask & 2) != 0) {
            outPacket.encodeLong(getMoney());
        }
        for(int i = 1; i <= 5; i++) {
            InvType curInvType = InvType.getInvTypeByVal(i);
            List<Item> items = getItems().stream().filter(it -> it.getInvType() == curInvType).collect(Collectors.toList());
            outPacket.encodeByte(items.size());
            for(Item item : items) {
                item.encode(outPacket);
            }
        }

    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean canAddMoney(long amount) {
        return getMoney() + amount <= GameConstants.MAX_MONEY;
    }

    public void addMoney(long reqMoney) {
        if(canAddMoney(reqMoney)) {
            setMoney(getMoney() + reqMoney);
        }
    }

    public void addItem(Item item, short quantity) {
        Item curItem = getItemByItemID(item.getItemId());
        if(curItem == null || curItem.getInvType() == InvType.EQUIP) {
            Item newItem = ItemConstants.isEquip(item.getItemId())
                    ? ((Equip) item).deepCopy()
                    : ItemData.getItemDeepCopy(item.getItemId());
            newItem.setQuantity(quantity);
            getItems().add(newItem);
        } else {
            curItem.setQuantity(curItem.getQuantity() + quantity); // check needed for quantity overflow?
        }
    }

    public Item getItemByItemID(int itemID) {
        return getItems().stream().filter(i -> i.getItemId() == itemID).findAny().orElse(null);
    }

    public void removeItem(Item getItem) {
        getItems().remove(getItem);
    }
}
