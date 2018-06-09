package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Familiar;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.util.Position;

import java.util.List;

/**
 * @author Sjonnie
 * Created on 6/9/2018.
 */
public class CFamiliar {
    // Thanks to nox, my ida wasn't able to give me the correct opcodes :(

    public static OutPacket familiarEnterField(int charID, boolean transfer, Familiar familiar, boolean on, boolean animation) {
        OutPacket outPacket = new OutPacket(transfer ? OutHeader.FAMILIAR_TRANSFER_FIELD : OutHeader.FAMILIAR_ENTER_FIELD);
        outPacket.encodeInt(charID);
        outPacket.encodeByte(on); // on/off
        outPacket.encodeByte(!animation); // animation
        outPacket.encodeByte(0); // idk
        if (on) {
            outPacket.encodeInt(familiar.getFamiliarID());
            outPacket.encodeInt(familiar.getFatigue()); // fatigue
            outPacket.encodeInt(familiar.getVitality() * GameConstants.FAMILIAR_ORB_VITALITY); // total vitality
            outPacket.encodeString(familiar.getName());
            outPacket.encodePosition(familiar.getPosition());
            outPacket.encodeByte(familiar.getMoveAction());
            outPacket.encodeShort(familiar.getFh());
        }
        return outPacket;
    }

    public static OutPacket familiarMove(int charID, int encodedGatherDuration, Position oldPos, Position oldVPos,
                                         List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.FAMILIAR_MOVE);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(0); // ?

        // Common move path yadayada
        outPacket.encodeInt(encodedGatherDuration);
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeByte(movements.size());
        for (Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }
}
