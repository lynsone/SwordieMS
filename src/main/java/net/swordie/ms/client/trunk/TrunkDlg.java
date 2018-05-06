package net.swordie.ms.client.trunk;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.TrunkType;

/**
 * Created on 4/7/2018.
 */
public interface TrunkDlg {

    TrunkType getType();

    void encode(OutPacket outPacket);
}
