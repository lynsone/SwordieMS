package client.character.items;

import connection.OutPacket; /**
 * Created on 12/20/2017.
 */
public class MemorialCubeInfo {
    private Equip equip;
    private int cubeItemID;

    public void encode(OutPacket outPacket) {
        Equip equip = getEquip();
        outPacket.encodeByte(equip != null);
        if(equip != null) {
            equip.encode(outPacket);
            outPacket.encodeInt(getCubeItemID());
            outPacket.encodeInt(equip.getBagIndex());
        }
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public int getCubeItemID() {
        return cubeItemID;
    }

    public void setCubeItemID(int cubeItemID) {
        this.cubeItemID = cubeItemID;
    }
}
