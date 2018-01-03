package client.character.items;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import server.Server;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventoryId")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Item> items;
    @Column(name = "type")
    private Type type;
    @Column(name = "slots")
    private byte slots;

    public Inventory() {
        items = new ArrayList<>();
        type = Type.EQUIP;
    }

    public Inventory(Type t, int slots) {
        this.type = t;
        items = new ArrayList<>();
        this.slots = (byte) slots;
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

    public void updateDB(Session session, Transaction tx) {
        for(Item item : getItems()) {
            session.saveOrUpdate(item.getDateExpire());
            item.updateDB(session, tx);
        }
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        for(Item item : getItems()) {
            item.createInDB(session, tx);
        }
        session.save(this);
    }

    public void deleteInDB(Session session, Transaction tx) {
        for(Item item : getItems()) {
            item.deleteInDB(session, tx);
        }
        session.delete(this);
    }

    public void addItem(Item item) {
        if(getItems().size() < getSlots()) {
            getItems().add(item);
        }
    }

    public void updateDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        updateDB(session, tx);
        tx.commit();
        session.close();
    }

    public void removeItem(Item item) {
        if(getItems().contains(item)) {
            getItems().remove(item);
        }
    }

    public int getFirstOpenSlot() {
        for (int i = 1; i <= getItems().size(); i++) {
            if(getItemBySlot(i) == null) {
                return i;
            }
        }
        return 0;
    }

    public enum Type {
        EQUIPPED,
        EQUIP,
        CONSUME,
        ETC,
        INSTALL,
        CASH
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Item getItemBySlot(short bagIndex) {
        return getItemBySlot(bagIndex < 0 ? -bagIndex : bagIndex);
    }

    private Item getItemBySlot(int bagIndex) {
        return getItems().stream().filter(item -> item.getBagIndex() == bagIndex).findAny().orElse(null);
    }
}
