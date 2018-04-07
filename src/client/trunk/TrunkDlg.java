package client.trunk;

import connection.OutPacket;
import enums.TrunkType;

/**
 * Created on 4/7/2018.
 */
public interface TrunkDlg {

    TrunkType getType();

    void encode(OutPacket outPacket);
}
