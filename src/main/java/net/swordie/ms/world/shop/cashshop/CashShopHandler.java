package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.connection.packet.CCashShop;

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
