package packet;

import client.Client;
import client.character.Char;
import client.character.CharacterCard;
import client.character.ExtendSP;
import client.character.NonCombatStatDayLimit;
import client.character.items.Item;
import connection.OutPacket;
import enums.InvType;
import enums.Stat;
import handling.OutHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 12/22/2017.
 */
public class WvsContext {

    public static void dipose(Client c, Char chr) {
        c.write(statChanged(new HashMap<>(), true, (byte) chr.getAvatarData().getCharacterStat().getMixBaseHairColor(),
                (byte) chr.getAvatarData().getCharacterStat().getMixAddHairColor(), (byte) chr.getAvatarData().getCharacterStat().getMixHairBaseProb(),
                (byte) 0, false, 0 , 0));
    }

    public static OutPacket statChanged(Map<Stat, Object> stats, boolean exclRequestSent, byte mixBaseHairColor,
                                        byte mixAddHairColor, byte mixHairBaseProb, byte charmOld, boolean updateCovery,
                                        int hpRecovery, int mpRecovery) {
        OutPacket outPacket = new OutPacket(OutHeader.STAT_CHANGED);

        outPacket.encodeByte(exclRequestSent);
        // GW_CharacterStat::DecodeChangeStat
        int mask = 0;
        for(Stat stat : stats.keySet()) {
            mask |= stat.getVal();
        }
        outPacket.encodeLong(mask);
        for(Map.Entry<Stat, Object> entry : stats.entrySet()) {
            Stat stat = entry.getKey();
            Object value = entry.getValue();
            switch(stat) {
                case skin:
                case level:
                case fatigue:
                    outPacket.encodeByte((Byte) value);
                    break;
                case face:
                case hair:
                case hp:
                case mhp:
                case mp:
                case mmp:
                case pop:
                case charismaEXP:
                case insightEXP:
                case willEXP:
                case craftEXP:
                case senseEXP:
                case charmEXP:
                case eventPoints:
                    outPacket.encodeInt((Integer) value);
                    break;
                case str:
                case dex:
                case inte:
                case luk:
                case ap:
                    outPacket.encodeShort((Short) value);
                    break;
                case sp:
                    if(value instanceof ExtendSP) {
                        ((ExtendSP) value).encode(outPacket);
                    } else {
                        outPacket.encodeShort((Short) value);
                    }
                    break;
                case exp:
                case money:
                    outPacket.encodeLong((Long) value);
                    break;
                case dayLimit:
                    ((NonCombatStatDayLimit) value).encode(outPacket);
                    break;
                case albaActivity:
                    //TODO
                    break;
                case characterCard:
                    ((CharacterCard) value).encode(outPacket);
                    break;
                case pvp1:
                case pvp2:
                    break;
                case subJob:
                    outPacket.encodeShort((Short) value);
                    outPacket.encodeShort(0);
            }
        }

        outPacket.encodeByte(mixBaseHairColor);
        outPacket.encodeByte(mixAddHairColor);
        outPacket.encodeByte(mixHairBaseProb);
        outPacket.encodeByte(charmOld > 0);
        if(charmOld > 0) {
            outPacket.encodeByte(charmOld);
        }
        outPacket.encodeByte(updateCovery);
        if(updateCovery) {
            outPacket.encodeInt(hpRecovery);
            outPacket.encodeInt(mpRecovery);
        }
        return outPacket;
    }

    public static OutPacket inventoryOperation(Char chr, boolean exclRequestSent, short oldPos, short newPos,
                                               InvType invType, short quantity, boolean notRemoveAddInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.INVENTORY_OPERATION);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(1); // size
        outPacket.encodeByte(notRemoveAddInfo);

        outPacket.encodeByte(2); // move
        outPacket.encodeByte(invType.getVal());
        outPacket.encodeShort(oldPos);
        outPacket.encodeShort(newPos);

        return outPacket;
    }
}
