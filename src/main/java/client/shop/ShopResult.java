package client.shop;

import connection.OutPacket;
import enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public interface ShopResult {

    ShopResultType getType();

    void encode(OutPacket outPacket);
}
