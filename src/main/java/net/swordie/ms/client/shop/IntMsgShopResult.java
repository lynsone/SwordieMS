package net.swordie.ms.client.shop;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public class IntMsgShopResult implements ShopResult {

    private int value;
    private ShopResultType type;

    public IntMsgShopResult(int value, ShopResultType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public ShopResultType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(value);
    }
}
