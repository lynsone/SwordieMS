package client.shop;

import connection.OutPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/27/2018.
 */
public class NpcShopDlg {

    private int shopID;
    private int selectNpcItemID;
    private int npcTemplateID;
    private int starCoin;
    private int shopVerNo;
    private List<NpcShopItem> items = new ArrayList<>();

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getSelectNpcItemID());
        outPacket.encodeInt(getNpcTemplateID());
        outPacket.encodeInt(getStarCoin());
        // start gms only
        boolean hasQuest = false;
        outPacket.encodeByte(hasQuest);
        if(hasQuest) {
            // just a guess that's this is for quests
            outPacket.encodeInt(0); // questID?
            outPacket.encodeString(""); // questKey?
        }
        // end gms only
        outPacket.encodeInt(getShopVerNo());
        outPacket.encodeShort(getItems().size());
        getItems().forEach(item -> item.encode(outPacket));
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public List<NpcShopItem> getItems() {
        return items;
    }

    public int getSelectNpcItemID() {
        return selectNpcItemID;
    }

    public void setSelectNpcItemID(int selectNpcItemID) {
        this.selectNpcItemID = selectNpcItemID;
    }

    public int getNpcTemplateID() {
        return npcTemplateID;
    }

    public void setNpcTemplateID(int npcTemplateID) {
        this.npcTemplateID = npcTemplateID;
    }

    public int getStarCoin() {
        return starCoin;
    }

    public void setStarCoin(int starCoin) {
        this.starCoin = starCoin;
    }

    public int getShopVerNo() {
        return shopVerNo;
    }

    public void setShopVerNo(int shopVerNo) {
        this.shopVerNo = shopVerNo;
    }

    public void addItem(NpcShopItem nsi) {
        getItems().add(nsi);
    }
}
