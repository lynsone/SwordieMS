package client.character.items;

import connection.OutPacket;
import enums.InvType;
import enums.ScrollStat;
import jdk.nashorn.internal.runtime.ScriptObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    private int price;
    private int slotMax;
    private boolean tradeBlock;
    private boolean notSale;
    private String path;
    private boolean noCursed;
    private int successRate;
    private boolean quest;
    private int bagType;
    private Map<ScrollStat, Integer> scrollStats = new HashMap<>();
    private int reqQuestOnProgress;
    private int charmEXP;

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

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setQuest(boolean quest) {
        this.quest = quest;
    }

    public boolean isQuest() {
        return quest;
    }

    public void setBagType(int bagType) {
        this.bagType = bagType;
    }

    public int getBagType() {
        return bagType;
    }

    public void putScrollStat(ScrollStat scrollStat, int value) {
        getScrollStats().put(scrollStat, value);
    }

    public Map<ScrollStat, Integer> getScrollStats() {
        return scrollStats;
    }

    public void setScrollStats(Map<ScrollStat, Integer> scrollStats) {
        this.scrollStats = scrollStats;
    }

    public void setReqQuestOnProgress(int reqQuestOnProgress) {
        this.reqQuestOnProgress = reqQuestOnProgress;
    }

    public int getReqQuestOnProgress() {
        return reqQuestOnProgress;
    }

    public void setCharmEXP(int charmEXP) {
        this.charmEXP = charmEXP;
    }

    public int getCharmEXP() {
        return charmEXP;
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
}
