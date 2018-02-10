package packet;

import connection.OutPacket;
import handling.OutHeader;

/**
 * Created on 2/10/2018.
 */
public class BattleRecordMan {
    public static OutPacket serverOnCalcRequestResult(boolean on) {
        OutPacket outPacket = new OutPacket(OutHeader.SERVER_ON_CALC_REQUEST_RESULT);

        outPacket.encodeByte(true);

        return outPacket;
    }
}
