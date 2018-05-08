package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created on 4/23/2018.
 */
public class CCashShop {
    public static OutPacket queryCashResult(Char chr) {
        Account account = chr.getAccount();

        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_QUERY_CASH_RESULT);

//        outPacket.encodeInt(account.getNXCredit());
//        outPacket.encodeInt(account.getMaplePoints());
//        outPacket.encodeInt(chr.getRewardPoints());
//        outPacket.encodeInt(account.getNXPrepaid());

        return outPacket;
    }
}
