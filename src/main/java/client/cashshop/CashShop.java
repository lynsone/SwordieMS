package client.cashshop;

import connection.OutPacket;
import util.FileTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 4/21/2018.
 */
public class CashShop {
    private List<CashShopItem> items;
    private List<Integer> saleItems;
    private boolean eventOn;
    private boolean lockerTransfer;
    private boolean refundAvailable;
    private boolean usingOTP;
    private boolean usingNewOTP;
    private boolean betaTest;
    private CashItemInfo cii = new CashItemInfo();

    public CashShop() {
        items = new ArrayList<>();
        saleItems = new ArrayList<>();
        cii.setItemID(5160013);
        cii.setAccountID(0);
        cii.setCharacterID(0);
        cii.setCashItemSN(1);
        cii.setUnsure(1);
    }

    public List<CashShopItem> getItems() {
        return items;
    }

    public List<Integer> getSaleItems() {
        return saleItems;
    }

    public void encodeSaleInfo(OutPacket outPacket) {
        short size = 0;
//        outPacket.encodeShort(getSaleItems().size());
//        for(int i = 0; i < size; i++) {
//            outPacket.encodeInt(cii.getItemID());
//            cii.encode(outPacket);
//            cii.encode(outPacket);
//            // TODO
//        }
//        size = 0;
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(5160013);
//            outPacket.encodeString("ayy");
//        }
        size = 1;
        outPacket.encodeInt(size); // int per size
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
//        size = 1;
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(0);
//            outPacket.encodeString("");
//        }
        size = 1;
        outPacket.encodeInt(size); // randomItemCount
        for (int i = 0; i < size; i++) {
            outPacket.encodeArr(new byte[20]);
//            int itemID = 0;
//            outPacket.encodeInt(itemID); // itemID
//            if(itemID / 1000 == 5533) {
//                outPacket.encodeInt(0);
//            }

        }
//        outPacket.encodeInt(5160013);


    }

    public void encodeMainBest(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeShort(size); // was int in kmst?
        for (int i = 0; i < size; i++) {
            outPacket.encodeByte(1); // nClass
            outPacket.encodeInt(5160013); // nQuestID?
        }
    }

    public void encodeCustomizedPackage(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeByte(2); // nClass
            outPacket.encodeInt(5160013); // nQuestID?
        }
    }

    public void encodeSearchHelper(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
//            outPacket.encodeString("ayy"); // sKeyword
//            outPacket.encodeString("lmao"); // sMsg
            outPacket.encodeArr(new byte[10]);
        }
    }

    public void encode(OutPacket outPacket) {
//        outPacket.encodeArr(new byte[1 + 4 + 4 + 2 + 4 /*nox*/ + 1080 + 1 + 1 + 1 + 4 + 1 + 1 + 1 + 8 + 1 + 1 + 4 + 1 + 1 + /*extra*/2]);
        // CCashShop::LoadData
        outPacket.encodeByte(!isBetaTest());
        encodeSaleInfo(outPacket);
        encodeMainBest(outPacket);
        encodeCustomizedPackage(outPacket);
//        encodeSearchHelper(outPacket); // not in gms?
//        // buffer aBest, 3 inner loops (int (category) + int (gender) + int (sn))
        outPacket.encodeArr(new byte[1080]);
        short size = 0;
//        outPacket.encodeShort(size + 1);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(0); // nSN
//            outPacket.encodeInt(0); // nStockState: CS_StockState IDA
//        }
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            // CS_LIMITGOODS, size 116
//            outPacket.encodeInt(0);
//            for (int j = 0; j < 10; j++) {
//                outPacket.encodeInt(0); // nSN
//            }
//            outPacket.encodeInt(0); // CS_LimitGoodsState
//            outPacket.encodeInt(0); // nOriginCount
//            outPacket.encodeInt(0); // nRemainCount
//            outPacket.encodeInt(0); // dwConditionFlag
//            outPacket.encodeInt(0); // nDateStart
//            outPacket.encodeInt(0); // nDateEnd
//            outPacket.encodeInt(0); // nHourStart
//            outPacket.encodeInt(0); // nHourEnd
//            for (int j = 0; j < 7; j++) {
//                outPacket.encodeByte(0); // abWeek
//            }
//            outPacket.encodeByte(0); // nBackgrndType
//            outPacket.encodeString(""); // sNoticeMsg
//            outPacket.encodeInt(0); // nRepeatMin
//            // TODO Incomplete
//        }
        // END CCashShop::LoadData

        outPacket.encodeShort(0); // not in idb?
        // self
        outPacket.encodeByte(true);
        outPacket.encodeByte(true);
        outPacket.encodeByte(true);

        outPacket.encodeInt(0);

        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        outPacket.encodeLong(0);


        outPacket.encodeByte(0);
        boolean someBool = false;
        outPacket.encodeByte(someBool);
        if(someBool) { // ^
            outPacket.encodeString("ayy");
        }
        outPacket.encodeInt(0);
        someBool = false;
        outPacket.encodeByte(someBool);
        if(someBool) { // ^
            outPacket.encodeLong(0);
        }
        outPacket.encodeByte(0);


        // kmst
//        outPacket.encodeByte(isEventOn());
//        outPacket.encodeByte(isLockerTransfer());
//        outPacket.encodeByte(isRefundAvailable());
//        outPacket.encodeByte(isUsingOTP());
//        outPacket.encodeByte(isUsingNewOTP());
    }

    public boolean isEventOn() {
        return eventOn;
    }

    public void setEventOn(boolean eventOn) {
        this.eventOn = eventOn;
    }

    public boolean isLockerTransfer() {
        return lockerTransfer;
    }

    public void setLockerTransfer(boolean lockerTransfer) {
        this.lockerTransfer = lockerTransfer;
    }

    public boolean isRefundAvailable() {
        return refundAvailable;
    }

    public void setRefundAvailable(boolean refundAvailable) {
        this.refundAvailable = refundAvailable;
    }

    public boolean isUsingOTP() {
        return usingOTP;
    }

    public void setUsingOTP(boolean usingOTP) {
        this.usingOTP = usingOTP;
    }

    public boolean isUsingNewOTP() {
        return usingNewOTP;
    }

    public void setUsingNewOTP(boolean usingNewOTP) {
        this.usingNewOTP = usingNewOTP;
    }

    public boolean isBetaTest() {
        return betaTest;
    }

    public void setBetaTest(boolean betaTest) {
        this.betaTest = betaTest;
    }
}
