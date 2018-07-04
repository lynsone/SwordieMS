package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.shop.cashshop.CashItemInfo;
import net.swordie.ms.world.shop.cashshop.CashShop;

import java.util.List;

/**
 * Created on 4/23/2018.
 */
public class CCashShop {
    public static OutPacket queryCashResult(Char chr) {
        Account account = chr.getAccount();

        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_QUERY_CASH_RESULT);

        outPacket.encodeInt(account.getNXCredit());
        outPacket.encodeInt(account.getMaplePoints());
        outPacket.encodeInt(chr.getRewardPoints());
        outPacket.encodeInt(account.getNXPrepaid());

        return outPacket;
    }

    public static OutPacket cashItemResBuyDone(CashItemInfo cashItemInfo, FileTime registerDate, CashItemInfo receiveBonus,
                                               int someInt) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_Buy_Done.getVal());
        cashItemInfo.encode(outPacket);
        boolean hasRegisterDate = registerDate != null;
        outPacket.encodeInt(hasRegisterDate ? 1 : 0);
        if (hasRegisterDate) {
            outPacket.encodeFT(registerDate);
        }
        boolean hasReceiveBonus = receiveBonus != null;
        outPacket.encodeByte(hasReceiveBonus);
        if (receiveBonus != null) {
            receiveBonus.encode(outPacket);
        }
        boolean hasSomeInt = someInt != 0;
        outPacket.encodeByte(hasSomeInt);
        if (hasSomeInt) {
            outPacket.encodeInt(someInt);
        }

        return outPacket;
    }

    public static OutPacket WebShopOrderGetList_Done(List<CashItemInfo> firstList, List<Item> secondList) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_WebShopOrderGetList_Done.getVal());
        outPacket.encodeShort(firstList.size());
        for (CashItemInfo cii : firstList) {
            cii.encode(outPacket);
        }
        outPacket.encodeShort(secondList.size());
        for (Item item : secondList) {
            item.encode(outPacket);
        }

        return outPacket;
    }
}
