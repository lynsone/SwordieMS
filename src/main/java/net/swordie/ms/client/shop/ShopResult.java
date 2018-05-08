package net.swordie.ms.client.shop;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ShopResultType;

/**
 * Created on 3/29/2018.
 */
public interface ShopResult {

    ShopResultType getType();

    void encode(OutPacket outPacket);
}
