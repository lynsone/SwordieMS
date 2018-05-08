package net.swordie.ms.client.shop;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public class SellShopResult implements ShopResult {

    private int itemID;

    public SellShopResult(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public ShopResultType getType() {
        return ShopResultType.NotEnoughInStockMsg;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(itemID);
    }
}
