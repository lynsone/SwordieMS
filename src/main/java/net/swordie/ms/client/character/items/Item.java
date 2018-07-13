package net.swordie.ms.client.character.items;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.InventoryOperation.ADD;
import static net.swordie.ms.enums.InventoryOperation.REMOVE;
import static net.swordie.ms.enums.InventoryOperation.UPDATE_ITEM_INFO;

/**
 * GW_ItemSlotBase
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    protected int inventoryId;
    protected int itemId;
    protected int bagIndex;
    protected long cashItemSerialNumber;
    @JoinColumn(name = "dateExpire")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    protected FileTime dateExpire = FileTime.getFileTimeFromType(FileTime.Type.PERMANENT);
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "invType")
    protected InvType invType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    protected Type type;
    protected boolean isCash;
    protected int quantity;
    private String owner = "";


    public enum Type {
        EQUIP(1),
        ITEM(2),
        PET(3);

        private byte val;

        Type(byte val) {
            this.val = val;
        }

        Type(int val) {
            this((byte) val);
        }

        public byte getVal() {
            return val;
        }

        public static Type getTypeById(int id) {
            return Arrays.stream(Type.values()).filter(type -> type.getVal() == id).findFirst().orElse(null);
        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void drop() {
        setBagIndex(0);
    }

    public void addQuantity(int amount) {
        if (amount > 0 && amount + getQuantity() > 0) {
            setQuantity(getQuantity() + amount);
        }
    }

    public void removeQuantity(int amount) {
        if (amount > 0) {
            setQuantity(Math.max(0, getQuantity() - amount));
        }
    }


    public Item() {
    }

    public Item(int itemId, int bagIndex, long cashItemSerialNumber, FileTime dateExpire, InvType invType,
                boolean isCash, Type type) {
        this.itemId = itemId;
        this.bagIndex = bagIndex;
        this.cashItemSerialNumber = cashItemSerialNumber;
        this.dateExpire = dateExpire;
        this.invType = invType;
        this.isCash = isCash;
        this.type = type;
    }

    public Item deepCopy() {
        Item item = new Item();
        item.setItemId(getItemId());
        item.setBagIndex(getBagIndex());
        item.setCashItemSerialNumber(getCashItemSerialNumber());
        item.setDateExpire(getDateExpire().deepCopy());
        item.setInvType(getInvType());
        item.setCash(isCash());
        item.setType(getType());
        item.setOwner(getOwner());
        item.setQuantity(getQuantity());
        item.setInventoryId(getInventoryId());
        return item;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBagIndex() {
        return bagIndex;
    }

    public void setBagIndex(int bagIndex) {
        this.bagIndex = Math.abs(bagIndex);
    }

    public long getCashItemSerialNumber() {
        return getId();
    }

    public FileTime getDateExpire() {
        return dateExpire;
    }

    public InvType getInvType() {
        return invType;
    }

    public Type getType() {
        return type;
    }

    public boolean isCash() {
        return isCash;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getType().getVal());
        // GW_ItemSlotBase
        outPacket.encodeInt(getItemId());
        boolean hasSN = this instanceof PetItem;
        outPacket.encodeByte(hasSN);
        if (hasSN) {
            outPacket.encodeLong(getId());
        }
        getDateExpire().encode(outPacket);
        outPacket.encodeInt(getBagIndex());
        if (getType() == Type.ITEM) {
            outPacket.encodeShort(getQuantity()); // nQuantity
            outPacket.encodeString(getOwner()); // sOwner
            outPacket.encodeShort(0); // flag
            if (ItemConstants.isThrowingStar(getItemId()) || ItemConstants.isBullet(getItemId()) ||
                    ItemConstants.isFamiliar(getItemId())) {
                outPacket.encodeLong(getInventoryId());
            }
        }
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCashItemSerialNumber(long cashItemSerialNumber) {
        this.cashItemSerialNumber = cashItemSerialNumber;
    }

    public void setDateExpire(FileTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public void setInvType(InvType invType) {
        this.invType = invType;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCash(boolean cash) {
        isCash = cash;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", ItemId: " + getItemId() + ", Qty: " + getQuantity() + ", InvType: " + getInvType()
                + ", BagIndex: " + getBagIndex();
    }

    /**
     * Sends a packet to the given Char to show that this Item has updated.
     * @param chr The Char to give the update to
     */
    public void updateToChar(Char chr) {
        short bagIndex = (short) (getInvType() == EQUIPPED ? - getBagIndex() : getBagIndex());
        chr.write(WvsContext.inventoryOperation(true, false, ADD,
                bagIndex, (short) 0, 0, this));
    }

}
