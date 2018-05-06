package client.character.quest.questRequirements;

import client.character.Char;
import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 3/2/2018.
 */
public class QuestStartItemRequirement implements QuestStartRequirement {
    private int id;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addReqItem(int reqItem, int count) {
        setId(reqItem);
        setQuantity(count);
    }

    @Override
    public boolean hasRequirements(Char chr) {
        return chr.hasItemCount(getId(), getQuantity());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getId());
        dos.writeInt(getQuantity());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestStartItemRequirement qsir = new QuestStartItemRequirement();
        qsir.setId(dis.readInt());
        qsir.setQuantity(dis.readInt());
        return qsir;
    }
}
