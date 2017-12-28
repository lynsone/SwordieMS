package client.character.items;

import connection.OutPacket;
import enums.InvType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * GW_ItemSlotBase
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "inventoryId")
    protected int inventoryId;
    @Column(name = "itemId")
    protected int itemId;
    @Column(name = "bagIndex")
    protected int bagIndex;
    @Column(name = "cashItemSerialNumber")
    protected long cashItemSerialNumber;
    @JoinColumn(name = "dateExpire")
    @OneToOne
    protected FileTime dateExpire = FileTime.getFileTimeFromType(FileTime.Type.PERMANENT);
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "invType")
    protected InvType invType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    protected Type type;
    @Column(name = "isCash")
    protected boolean isCash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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

    public enum Type {
        EQUIP(1),
        ITEM(2),
        PET(3)
        ;

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

    public Item() {}

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
        return cashItemSerialNumber;
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
        boolean hasSN = getCashItemSerialNumber() > 0;
        outPacket.encodeByte(hasSN);
        if(hasSN) {
            outPacket.encodeLong(getCashItemSerialNumber());
        }
        getDateExpire().encode(outPacket);
        outPacket.encodeInt(-1); // bagindex?
        if(getType() != Type.EQUIP) {
            outPacket.encodeShort(1); // nQuantity
            outPacket.encodeString(""); // sOwner
            outPacket.encodeShort(0); // flag
            // if is throwing star/bullet/ /10000 == 287, write long
        }
    }
}
