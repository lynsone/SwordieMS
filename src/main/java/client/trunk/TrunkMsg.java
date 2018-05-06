package client.trunk;

import connection.OutPacket;
import enums.TrunkType;

/**
 * Created on 4/7/2018.
 */
public class TrunkMsg implements TrunkDlg {

    private TrunkType type;

    public TrunkMsg(TrunkType type) {
        this.type = type;
    }

    @Override
    public TrunkType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {

    }
}
