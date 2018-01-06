package client.character.skills;

import connection.OutPacket;

/**
 * Created on 1/3/2018.
 */
public class LarknessInfo {
    private int rLarkness;
    private int tLarkness;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getrLarkness());
        outPacket.encodeInt(gettLarkness());
    }

    public int getrLarkness() {
        return rLarkness;
    }

    public void setrLarkness(int rLarkness) {
        this.rLarkness = rLarkness;
    }

    public int gettLarkness() {
        return tLarkness;
    }

    public void settLarkness(int tLarkness) {
        this.tLarkness = tLarkness;
    }
}
