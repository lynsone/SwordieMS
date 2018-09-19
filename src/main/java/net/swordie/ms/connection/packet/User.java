package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ChatUserType;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created on 2/3/2018.
 */
public class User {

    public static OutPacket chat(int charID, ChatUserType type, String msg, boolean onlyBalloon, int idk, int worldID) {
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

    public static OutPacket setStigmaEffect(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.STIGMA_EFFECT);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(true);

        return outPacket;
    }

    public static OutPacket setGachaponEffect(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.GACHAPON_EFFECT);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }
    
    public static OutPacket scriptProgressMessage(String string) {
        OutPacket outPacket = new OutPacket(OutHeader.SCRIPT_PROGRESS_MESSAGE);

        outPacket.encodeString(string);

        return outPacket;
    }

    public static OutPacket progressMessageFont(int fontNameType, int fontSize, int fontColorType, int fadeOutDelay, String message) {
        OutPacket outPacket = new OutPacket(OutHeader.PROGRESS_MESSAGE_FONT);
        
        outPacket.encodeInt(fontNameType);
        outPacket.encodeInt(fontSize);
        outPacket.encodeInt(fontColorType);
        outPacket.encodeInt(fadeOutDelay);
        outPacket.encodeString(message);
 
        return outPacket;
    }
    
    public static OutPacket effect(Effect effect) {
        OutPacket outPacket = new OutPacket(OutHeader.EFFECT);

        effect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket showItemMemorialEffect(int charID, boolean success, int itemID) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_MEMORIAL_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);
        outPacket.encodeInt(itemID);

        return outPacket;
    }
}
