package packet;

import client.character.Char;
import connection.OutPacket;
import enums.ChatType;
import handling.OutHeader;

/**
 * Created on 2/3/2018.
 */
public class User {

    public static OutPacket chat(int charID, ChatType type, String msg, boolean onlyBalloon, int idk, int worldID) {
        OutPacket outPacket = new OutPacket(OutHeader.CHAT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(type.getVal());
        outPacket.encodeString(msg);
        outPacket.encodeByte(onlyBalloon);
        outPacket.encodeByte(idk);
        outPacket.encodeByte(worldID);

        return outPacket;
    }

    public static OutPacket setDamageSkin(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_DAMAGE_SKIN);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getDamageSkin().getDamageSkinID());

        return outPacket;
    }

    public static OutPacket setPremiumDamageSkin(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_PREMIUM_DAMAGE_SKIN);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getPremiumDamageSkin().getDamageSkinID());

        return outPacket;
    }

    public static OutPacket emotion(int charID, int emotion, int duration, boolean byItemOption) {
        OutPacket outPacket = new OutPacket(OutHeader.EMOTION);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(emotion);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(byItemOption);

        return outPacket;
    }

    public static OutPacket showItemSkillSocketUpgradeEffect(int charID, boolean success) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_SKILL_SOCKET_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);

        return outPacket;
    }

    public static OutPacket showItemSkillOptionUpgradeEffect(int charID, boolean success, boolean boom) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_SKILL_OPTION_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);
        outPacket.encodeByte(boom);

        return outPacket;
    }

    public static OutPacket SetSoulEffect(int charID, boolean set) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_SOUL_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(set);

        return outPacket;
    }
}
