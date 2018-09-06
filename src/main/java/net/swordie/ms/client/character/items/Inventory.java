package net.swordie.ms.client.character.items;

import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.enums.InvType;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryId")
    private List<Item> items;
    @Column(name = "type")
    private InvType type;
    private byte slots;

    public Inventory() {
        items = new CopyOnWriteArrayList<>();
        type = InvType.EQUIP;
    }

    public Inventory(InvType t, int slots) {
        this.type = t;
        items = new CopyOnWriteArrayList<>();
        this.slots = (byte) slots;
    }

    public Inventory deepCopy() {
        Inventory inventory = new Inventory(getType(), getSlots());
        inventory.setItems(new CopyOnWriteArrayList<>(getItems()));
        return inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getSlots() {
        return slots;
    }

    public void setSlots(byte slots) {
        this.slots = slots;
    }

    public void addItem(Item item) {
        if(getItems().size() < getSlots()) {
            getItems().add(item);
            item.setInvType(getType());
            sortItemsByIndex();
            if (item.getId() == 0) { // ensures that each item has a unique id
                DatabaseManager.saveToDB(this);
            }
        }
    }
    public void removeItem(Item item) {
        getItems().remove(item);
        sortItemsByIndex();
    }

    public int getFirstOpenSlot() {
        for (int i = 1; i <= getSlots(); i++) {
            if(getItemBySlot(i) == null) {
                return i;
            }
        }
        return 0;
    }

    public Item getFirstItemByBodyPart(BodyPart bodyPart) {
        List<Item> items = getItemsByBodyPart(bodyPart);
        return items != null && items.size() > 0 ? items.get(0) : null;
    }

    public List<Item> getItemsByBodyPart(BodyPart bodyPart) {
        return getItems().stream().filter(item -> item.getBagIndex() == bodyPart.getVal()).collect(Collectors.toList());
    }

    public List<Item> getItems() {
        return items;
    }

    public void sortItemsByIndex() {
        getItems().sort(Comparator.comparingInt(Item::getBagIndex));
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public InvType getType() {
        return type;
    }

    public void setType(InvType type) {
        this.type = type;
    }

    public Item getItemBySlot(short bagIndex) {
        return getItemBySlot(bagIndex < 0 ? -bagIndex : bagIndex);
    }

    private Item getItemBySlot(int bagIndex) {
        return getItems().stream().filter(item -> item.getBagIndex() == bagIndex).findAny().orElse(null);
    }

    public Item getItemByItemID(int itemId) {
        return getItems().stream().filter(item -> item.getItemId() == itemId).findFirst().orElse(null);
    }

    public Item getItemBySN(long sn) {
        return getItems().stream().filter(item -> item.getId() == sn).findFirst().orElse(null);
    }

    public boolean containsItem(int itemID) {
        return getItemByItemID(itemID) != null;
    }

    public boolean canPickUp(Item item) {
        return !isFull() || (item.getInvType().isStackable() && getItemByItemID(item.getItemId()) != null);
    }

    private boolean isFull() {
        return getItems().size() >= getSlots();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Items: ");
        for(Item item : getItems()) {
            sb.append(String.format("%d id=%d slot=%d | ", item.getItemId(), item.getId(), item.getBagIndex()));
        }
        return sb.toString();
    }

    public int getEmptySlots() {
        return getSlots() - getItems().size();
    }
}
