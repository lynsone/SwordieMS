package client.character;

import connection.OutPacket;
import util.FileTime;

/**
 * Created on 12/20/2017.
 */
public class FarmPotential {
    public void encode(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // dwMonsterID
            outPacket.encodeFT(new FileTime(0)); // potentialExpire
        }
    }
}
