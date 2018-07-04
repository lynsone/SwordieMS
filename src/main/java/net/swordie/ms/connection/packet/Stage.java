package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.damage.DamageCalc;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.world.field.FieldCustom;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.DBChar;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.world.shop.cashshop.CashShop;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created on 12/14/2017.
 */
public class Stage {

    public static OutPacket setField(Char chr, Field field, int channelId, boolean dev, int oldDriverID,
                                     boolean characterData, boolean usingBuffProtector, byte portal,
                                     boolean setWhiteFadeInOut, int mobStatAdjustRate, FieldCustom fieldCustom,
                                     boolean canNotifyAnnouncedQuest, int stackEventGauge) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_FIELD);

        short shortSize = 0;
        outPacket.encodeShort(shortSize);
        for (int i = 0; i < shortSize; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        outPacket.encodeInt(channelId - 1); // Damn nexon, randomly switching between starting at 1 and 0...
        outPacket.encodeByte(dev);
        outPacket.encodeInt(oldDriverID);
        outPacket.encodeByte(characterData ? 1 : 2);
        outPacket.encodeInt(0); // unused
        outPacket.encodeInt(field.getWidth());
        outPacket.encodeInt(field.getHeight());
        outPacket.encodeByte(characterData);
        short notifierCheck = 0;
        outPacket.encodeShort(notifierCheck);
        if(notifierCheck > 0) {
            outPacket.encodeString(""); // pBlockReasonIter
            for(int i = 0; i < notifierCheck; i++) {
                outPacket.encodeString(""); // sMsg2
            }
        }

        if(characterData) {
            Random random = new SecureRandom();
            int s1 = random.nextInt();
            int s2 = random.nextInt();
            int s3 = random.nextInt();
            outPacket.encodeInt(s1);
            outPacket.encodeInt(s2);
            outPacket.encodeInt(s3);

            chr.setDamageCalc(new DamageCalc(chr, s1, s2, s3));
            chr.encode(outPacket, DBChar.All); // <<<<------------------------------------
            // unk sub (not in kmst)
            // logout event (mushy)
            encodeLogoutEvent(outPacket);
        } else {
            outPacket.encodeByte(usingBuffProtector);
            outPacket.encodeInt(field.getId());
            outPacket.encodeByte(portal);
            outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getHp());
            boolean bool = false;
            outPacket.encodeByte(bool);
            if(bool) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }

        // 41 bytes below
        outPacket.encodeByte(setWhiteFadeInOut);
        outPacket.encodeByte(0); // unsure
        outPacket.encodeFT(FileTime.currentTime());
        outPacket.encodeInt(mobStatAdjustRate);
        boolean hasFieldCustom = fieldCustom != null;
        outPacket.encodeByte(hasFieldCustom);
        if(hasFieldCustom) {
            fieldCustom.encode(outPacket);
        }
        outPacket.encodeByte(false); // is pvp map, deprecated
        outPacket.encodeByte(canNotifyAnnouncedQuest);
        outPacket.encodeByte(stackEventGauge >= 0);
        if(stackEventGauge >= 0) {
            outPacket.encodeInt(stackEventGauge);
        }
        // sub_16A52D0
        outPacket.encodeByte(0); // Star planet, not interesting
        outPacket.encodeByte(0); // more star planet
        // CUser::DecodeTextEquipInfo
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeString("");
        }
        // FreezeAndHotEventInfo::Decode
        outPacket.encodeByte(0); // nAccountType
        outPacket.encodeInt(chr.getAccId());
        // CUser::DecodeEventBestFriendInfo
        outPacket.encodeInt(0); // dwEventBestFriendAID
        // sub_16A4D10
        outPacket.encodeInt(0); // ?
        // sub_16D99C0
        size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // ?
        }
        return outPacket;
    }

    private static void encodeLogoutEvent(OutPacket outPacket) {
        int idOrSomething = 0;
        outPacket.encodeInt(idOrSomething);
        if(idOrSomething > 0) {
            for (int i = 0; i < 3; i++) {
                // sub_9896B0
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("");
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
                outPacket.encodeLong(0);
                outPacket.encodeLong(0);
                outPacket.encodeLong(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("");
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeByte(0);
                // if(a3 & 1 != 0) -> encode int + str + buf of size 0x18 (24). a3 is 0 when called from setField
                int size = 0;
                outPacket.encodeInt(size);
                for (int j = 0; j < size; j++) {
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                }
            }
        }
    }

    public static OutPacket setCashShop(Char chr, CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_CASH_SHOP);

        chr.encode(outPacket, DBChar.All);
        cashShop.encode(outPacket);

        return outPacket;
    }
}
