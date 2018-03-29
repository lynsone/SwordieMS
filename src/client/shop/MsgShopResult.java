package client.shop;

import connection.OutPacket;
import enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public class MsgShopResult implements ShopResult {

    private ShopResultType type;

    public MsgShopResult(ShopResultType type) {
        this.type = type;
    }

    @Override
    public ShopResultType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {}
}
