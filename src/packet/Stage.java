package packet;

import client.field.Field;
import client.character.Char;
import client.field.FieldCustom;
import connection.OutPacket;
import enums.DBChar;
import handling.OutHeader;
import util.FileTime;
import util.Util;

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

        // not sure about this

        short shortSize = 0;
        outPacket.encodeShort(shortSize);
        for (int i = 0; i < shortSize; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        outPacket.encodeInt(channelId);
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
        //outPacket.encodeBytes(Util.getByteArrayByString("B1 FD 58 CF B1 FD 58 CF B1 FD 58 CF FF FF FF FF FF FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 01 00 00 00 00 00 00 00 61 64 61 64 00 00 00 00 00 00 00 00 00 00 01 B1 4F 00 00 31 75 00 00 FF 00 00 01 B8 0B 0C 00 05 00 04 00 04 00 32 00 00 00 32 00 00 00 32 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C0 EE 7D 37 00 00 00 00 00 00 00 00 4A D7 3A 78 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 0A 00 00 00 00 05 06 00 00 00 00 00 FF FF FF FF FE FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 84 7A D3 01 20 16 2B 70 00 14 01 04 00 61 64 61 64 01 04 00 61 64 61 64 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 20 20 20 3C 00 40 E0 FD 3B 37 4F 01 00 05 00 01 45 06 10 00 00 00 80 05 BB 46 E6 17 02 FF FF FF FF 00 00 00 00 1C 00 00 00 FF 82 06 10 7B 60 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF FF FF FF FF 00 40 E0 FD 3B 37 4F 01 FF FF FF FF 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 07 00 01 A6 5B 10 00 00 00 80 05 BB 46 E6 17 02 FF FF FF FF 00 00 00 00 1C 00 00 00 FF 82 06 10 7B 60 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF FF FF FF FF 00 40 E0 FD 3B 37 4F 01 FF FF FF FF 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 01 F0 DD 13 00 00 00 80 05 BB 46 E6 17 02 FF FF FF FF 00 00 00 00 1C 00 00 00 FF 82 06 10 7B 60 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF FF FF FF FF 00 40 E0 FD 3B 37 4F 01 FF FF FF FF 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 37 00 01 20 E2 11 00 00 00 80 05 BB 46 E6 17 02 FF FF FF FF 3C 00 00 00 01 00 01 00 01 00 01 00 1C 00 00 00 FF 83 06 10 7B 60 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF FF FF FF FF 00 40 E0 FD 3B 37 4F 01 FF FF FF FF 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 01 00 00 00 00 01 09 00 5F 38 00 00 B2 7A D3 01 2C 3B 00 00 B2 7A D3 01 FA 49 00 00 B2 7A D3 01 A5 81 00 00 B2 7A D3 01 A6 81 00 00 B2 7A D3 01 DB 81 00 00 B2 7A D3 01 F1 81 00 00 B2 7A D3 01 F3 81 00 00 B2 7A D3 01 B2 99 00 00 B2 7A D3 01 00 00 00 00 00 00 00 00 FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B FF C9 9A 3B 00 00 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 43 72 65 61 74 69 6E 67 2E 2E 2E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 01 00 00 00 00 00 00 00 64 00 00 00 00 80 05 BB 46 E6 17 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 01 00 00 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 40 64 2B 70 84 7A D3 01 64 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));/*
        if(characterData) {
            Random random = new SecureRandom();
            int s1 = random.nextInt();
            int s2 = random.nextInt();
            int s3 = random.nextInt();
            outPacket.encodeInt(s1);
            outPacket.encodeInt(s2);
            outPacket.encodeInt(s3);

            chr.encode(outPacket, DBChar.All);
            // unk sub (not in kmst)
            // logout event (mushy)
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
        //outPacket.encodeBytes(Util.getByteArrayByString("00 00 40 64 2B 70 84 7A D3 01 64 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));/*

        // 41 bytes below
        outPacket.encodeByte(setWhiteFadeInOut);
        outPacket.encodeByte(0); // unsure
        outPacket.encodeFT(new FileTime()); // ftServer
        outPacket.encodeInt(mobStatAdjustRate);
        boolean hasFieldCustom = fieldCustom != null;
        outPacket.encodeByte(hasFieldCustom);
        if(hasFieldCustom) {
            fieldCustom.encode(outPacket);
        }
        //outPacket.encodeBytes(Util.getByteArrayByString("00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));/*
        outPacket.encodeByte(false); // is pvp map, deprecated
        outPacket.encodeByte(canNotifyAnnouncedQuest);
        outPacket.encodeByte(stackEventGauge >= 0);
        if(stackEventGauge >= 0) {
            outPacket.encodeInt(stackEventGauge);
        }
        // sub_16A52D0
        outPacket.encodeByte(0); // Star planet, not interesting
        outPacket.encodeByte(0); // more star planet
//        outPacket.encodeBytes(Util.getByteArrayByString("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));/*
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
}
