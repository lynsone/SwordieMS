package packet;

import client.shop.NpcShopDlg;
import client.shop.ShopResult;
import connection.OutPacket;
import handling.OutHeader;

/**
 * Created on 3/28/2018.
 */
public class ShopDlg {

    public static OutPacket openShop(int petTemplateID, NpcShopDlg nsd) {

        OutPacket outPacket = new OutPacket(OutHeader.SHOP_OPEN);
        outPacket.encodeByte(petTemplateID != 0);
        if(petTemplateID != 0) {
            outPacket.encodeInt(petTemplateID);
        }
        nsd.encode(outPacket);

        return outPacket;
    }

    public static OutPacket shopResult(ShopResult shopResult) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOP_RESULT);

        outPacket.encodeByte(shopResult.getType().getVal());
        shopResult.encode(outPacket);

        return outPacket;
    }
}
