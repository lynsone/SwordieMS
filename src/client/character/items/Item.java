package client.character.items;

import connection.OutPacket;
import constants.ItemConstants;
import enums.EquipBaseStat;
import enums.InvType;
import enums.ScrollStat;
import jdk.nashorn.internal.runtime.ScriptObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Server;
import util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    @Column(name = "inventoryId")
    protected int inventoryId;
    @Column(name = "itemId")
    protected int itemId;
    @Column(name = "bagIndex")
    protected int bagIndex;
    @Column(name = "cashItemSerialNumber")
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
    @Column(name = "isCash")
    protected boolean isCash;
    @Column(name = "quantity")
    protected int quantity;
    @Column(name = "owner")
    private String owner = "";

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

    public void updateDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        updateDB(session, tx);
        tx.commit();
        session.close();
    }

    public void updateDB(Session session, Transaction tx) {
        getDateExpire().updateDB(session, tx);
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        getDateExpire().createInDB(session, tx);
        session.save(this);
    }

    public void deleteInDB(Session session, Transaction tx) {
        getDateExpire().deleteFromDB(session, tx);
        session.delete(this);
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
        boolean hasSN = false; //getId() > 0;
        outPacket.encodeByte(hasSN);
        if (hasSN) {
            outPacket.encodeLong(getId());
        }
        getDateExpire().encode(outPacket);
        outPacket.encodeInt(-1); // bagindex?
        if (getType() != Type.EQUIP) {
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

}
