package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.util.Position;

import java.util.List;

/**
 * Created on 5/21/2018.
 */
public class Summoned {

    public static OutPacket summonedAssistAttackRequest(int charID, int summonID) {
        OutPacket outpacket = new OutPacket(OutHeader.SUMMONED_ASSIST_ATTACK_REQUEST);

        outpacket.encodeInt(charID);
        outpacket.encodeInt(summonID);

        return outpacket;
    }

    public static OutPacket summonedSummonAttackActive(int charID, Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SUMMON_ATTACK_ACTIVE);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(summon.isAttackActive());

        return outPacket;
    }

    public static OutPacket summonedSkill(int charID, Summon summon, int summonSkillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SKILL);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(summonSkillID);

        return outPacket;
    }

    public static OutPacket summonBeholderRevengeAttack(int charID, Summon summon, int mob) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_BEHOLDER_REVENGE_ATTACK);

        outPacket.encodeInt(charID);//char ID
        outPacket.encodeInt(summon.getObjectId());//summon
        outPacket.encodeInt(mob);//mob

        return outPacket;
    }

    public static OutPacket summonedCreated(int charID, Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_CREATED);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(summon.getSkillID());
        outPacket.encodeByte(summon.getCharLevel());
        outPacket.encodeByte(summon.getSlv());
        // CSummoned::Init
        outPacket.encodePosition(summon.getPosition());
        outPacket.encodeByte(summon.getMoveAction());
        outPacket.encodeShort(summon.getCurFoothold());
        outPacket.encodeByte(summon.getMoveAbility());
        outPacket.encodeByte(summon.getAssistType());
        outPacket.encodeByte(summon.getEnterType());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(summon.isFlyMob());
        outPacket.encodeByte(summon.isBeforeFirstAttack());
        outPacket.encodeInt(summon.getTemplateId());
        outPacket.encodeInt(summon.getBulletID());
        AvatarLook al = summon.getAvatarLook();
        outPacket.encodeByte(al != null);
        if(al != null) {
            al.encode(outPacket);
        }
        if(summon.getSkillID() == 35111002) { // Tesla Coil
            outPacket.encodeByte(summon.getTeslaCoilState());
            for(Position pos : summon.getTeslaCoilPositions()) {
                outPacket.encodePosition(pos);
            }
        }
        if(summon.getSkillID() == 42111003) { // Kishin Shoukan
            for(Position pos : summon.getKishinPositions()) {
                outPacket.encodePosition(pos);
            }
        }
        outPacket.encodeByte(summon.isJaguarActive());
        outPacket.encodeInt(summon.getSummonTerm());
        outPacket.encodeByte(summon.isAttackActive());

        return outPacket;
    }

    public static OutPacket summonedRemoved(Summon summon, LeaveType leaveType) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_REMOVED);

        outPacket.encodeInt(summon.getCharID());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(leaveType.getVal());

        return outPacket;
    }

    public static OutPacket summonedAttack(AttackInfo ai) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_ATTACK);

        outPacket.encodeInt(ai.summon.getObjectId());

        outPacket.encodeByte(ai.summon.getCharLevel());
        byte left = (byte) (ai.left ? 1 : 0);
        outPacket.encodeByte(ai.attackActionType | (left << 7));
        outPacket.encodeByte((ai.mobCount << 4) | (ai.attackCount & 0xF));
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            outPacket.encodeInt(mai.mobId);
            outPacket.encodeByte(mai.byteIdk1);
            for (int dmg : mai.damages) {
                outPacket.encodeInt(dmg);
            }
        }
        outPacket.encodeByte(0); // bCounterAttack
        outPacket.encodeByte(ai.attackAction == 0);
        outPacket.encodeShort(ai.attackAction); // ?
        outPacket.encodeShort(ai.attackAction); // ? TODO, one of these is probably attackAction

        return outPacket;
    }

    public static OutPacket summonedMove(int summonID, int encodedGatherDuration, Position oldPos,
                                         Position oldVPos, List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_MOVE);

        outPacket.encodeInt(summonID);
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeInt(encodedGatherDuration);
        outPacket.encodeByte(movements.size());
        for(Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }
}
