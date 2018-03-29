package client.shop;

import connection.OutPacket;
import enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public class BuyShopResult implements ShopResult {

    private int repurchaseItem;
    private int someUpdateItem;
    private int starCoinUpdate;

    public BuyShopResult(int repurchaseItem, int someUpdateItem, int starCoinUpdate) {
        this.repurchaseItem = repurchaseItem;
        this.someUpdateItem = someUpdateItem;
        this.starCoinUpdate = starCoinUpdate;
    }

    @Override
    public ShopResultType getType() {
        return ShopResultType.Buy;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(repurchaseItem != 0);
        if(repurchaseItem != 0) {
            outPacket.encodeInt(repurchaseItem);
        } else {
            outPacket.encodeByte(someUpdateItem != 0);
            if(someUpdateItem != 0) {
                outPacket.encodeInt(someUpdateItem);
            }
            outPacket.encodeInt(starCoinUpdate);
        }
    }
}
