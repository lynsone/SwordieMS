package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.connection.packet.CCashShop;
import org.apache.log4j.Logger;

/**
 * Created on 4/23/2018.
 */
public class CashShopHandler {
    private static final Logger log = Logger.getLogger(CashShopHandler.class);

    public static void handleCashShopQueryCashRequest(Client c, InPacket inPacket) {
        c.write(CCashShop.queryCashResult(c.getChr()));
    }

    public static void handleCashShopCashItemRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Account account = chr.getAccount();
        byte type = inPacket.decodeByte();
        CashItemType cit = CashItemType.getRequestTypeByVal(type);
        if (cit == null) {
            log.error("Unhandled cash shop cash item request " + type);
            chr.dispose();
            return;
        }
        switch (cit) {
            default:
                log.error("Unhandled cash shop cash item request " + cit);
                chr.dispose();
                break;
        }
    }
}
