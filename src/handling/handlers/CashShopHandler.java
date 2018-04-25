package handling.handlers;

import client.Client;
import client.cashshop.CashItemInfo;
import client.cashshop.CashShopItem;
import client.character.Char;
import client.character.items.Equip;
import connection.InPacket;
import connection.OutPacket;
import handling.OutHeader;
import loaders.ItemData;
import packet.CCashShop;

/**
 * Created on 4/23/2018.
 */
public class CashShopHandler {

    public static void handleCashShopQueryCashRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        c.write(CCashShop.queryCashResult(c.getChr()));
        CashItemInfo cii = new CashItemInfo();
        cii.setItemID(5160013);
        cii.setAccountID(chr.getAccId());
        cii.setCharacterID(chr.getId());
        cii.setCashItemSN(1);
        cii.setUnsure(1);
        Equip equip = ItemData.getEquipDeepCopyFromID(1000000);

        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);
//        outPacket.encodeByte(2); // type
//
//        outPacket.encodeByte(false);
//        outPacket.encodeShort(1); // count
//
//        cii.encode(outPacket);
//        outPacket.encodeInt(0);
//
//        outPacket.encodeShort(3);
//        outPacket.encodeShort(10);
//        outPacket.encodeShort(4);
//        outPacket.encodeShort(1);
//
//        c.write(outPacket);
// ----------------------------------------------------
//        outPacket.encodeByte(3);
//
//        outPacket.encodeShort(0);
//        cii.encode(outPacket);
//        outPacket.encodeShort(1);
//        equip.encode(outPacket);
//        -------------------------------------
//        outPacket.encodeByte(4);
//        outPacket.encodeByte(1);
//        --------------------------------

        c.write(outPacket);
    }
}
