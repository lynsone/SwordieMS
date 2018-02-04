package client.jobs;

import client.character.Char;
import connection.OutPacket;

public class ZeroInfo {
    //TODO make actual used variables from these
    boolean isZeroBetaState = false;
    int dbcharZeroLinkCashPart = 0;
    int nMixAddHairColor = 0;
    int nMixHairBaseProb = 0;
    public void encode(OutPacket outPacket, Char chr)
    {
        short mask = 0;
        if((mask & 1) != 0){
            outPacket.encodeByte(isZeroBetaState);
        }
        if ((mask & 2) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getHp()); //hp
        }
        if ((mask & 4) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getMp()); //mp
        }
        if ((mask & 8) != 0) {
            outPacket.encodeByte(chr.getAvatarData().getAvatarLook().getSkin());
        }
        if ((mask & 0x10) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getAvatarLook().getHair());
        }
        if ((mask & 0x20) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getAvatarLook().getFace());
        }
        if ((mask & 0x40) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getMaxHp()); //maxhp
        }
        if ((mask & 0x80) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getMaxMp()); //maxmp
        }
        if ((mask & 0x100) != 0) {
            outPacket.encodeInt(dbcharZeroLinkCashPart);
        }
        if ((mask & 0x200) != 0) {
            outPacket.encodeInt(chr.getAvatarData().getAvatarLook().getMixedHairColor()); //nMixBaseHairColor
            outPacket.encodeInt(nMixAddHairColor); //nMixAddHairColor
            outPacket.encodeInt(nMixHairBaseProb); //nMixHairBaseProb
        }
    }
}
