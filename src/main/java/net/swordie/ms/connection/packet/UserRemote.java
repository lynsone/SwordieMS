package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AvatarModifiedMask;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;

import java.util.List;

/**
 * Created on 2/3/2018.
 */
public class UserRemote {
    public static OutPacket setActiveNickItem(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_NICK_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getNickItem());

        return outPacket;
    }

    public static OutPacket move(Char chr, int encodedGatherDuration, Position oldPos, Position oldVPos,
                                 List<Movement> movements) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_MOVE);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(encodedGatherDuration);
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeByte(movements.size());
        for(Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket emotion(int id, int emotion, int duration, boolean byItemOption) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EMOTION);

        outPacket.encodeInt(id);
        outPacket.encodeInt(emotion);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(byItemOption);

        return outPacket;
    }

    public static OutPacket attack(Char chr, AttackInfo ai) {
        OutHeader attackType = ai.attackHeader;
        // 535: melee
        // 536: shoot
        // 537: magic
        // 538: body
        OutPacket outPacket = new OutPacket(attackType);
        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(ai.fieldKey);
        outPacket.encodeByte(ai.mobCount << 4 | ai.hits);
        outPacket.encodeByte(chr.getLevel());
        outPacket.encodeByte(ai.slv);
        if(ai.slv > 0) {
            outPacket.encodeInt(ai.skillId);
        }
        if(SkillConstants.isZeroSkill(ai.skillId)) {
            outPacket.encodeByte(ai.zero);
            if(ai.zero != 0) {
                outPacket.encodePosition(chr.getPosition());
            }
        }
        if(attackType == OutHeader.REMOTE_SHOOT_ATTACK &&
                (SkillConstants.getAdvancedCountHyperSkill(ai.skillId) != 0  ||
                SkillConstants.getAdvancedAttackCountHyperSkill(ai.skillId) != 0 ||
                SkillConstants.isShikigamiHauntingSkill(ai.skillId))){
            outPacket.encodeByte(ai.passiveSLV);
            if(ai.passiveSLV > 0) {
                outPacket.encodeInt(ai.passiveSkillID);
            }
        }
        outPacket.encodeByte(ai.someMask);
        outPacket.encodeByte(ai.buckShot);
        outPacket.encodeInt(ai.option3);
        outPacket.encodeInt(ai.bySummonedID);
        if((ai.buckShot & 2) != 0) {
            outPacket.encodeInt(ai.buckShotSkillID);
            outPacket.encodeInt(ai.buckShotSlv);
        }
        if((ai.buckShot & 8) != 0) {
            outPacket.encodeByte(ai.psdTargetPlus);
        }
        byte left = (byte) (ai.left ? 1 : 0);
        outPacket.encodeShort((left << 15) | ai.attackAction);
        if(ai.attackAction <= 1516) {
            outPacket.encodeByte(ai.attackActionType);
            outPacket.encodeShort(ai.x);
            outPacket.encodeShort(ai.y);
            outPacket.encodeByte(ai.showFixedDamage);
            outPacket.encodeByte(!ai.isDragonAttack);
            outPacket.encodeByte(ai.actionSpeed);
            outPacket.encodeByte(ai.mastery);
            outPacket.encodeInt(ai.bulletID);
            for(MobAttackInfo mai : ai.mobAttackInfo) {
                outPacket.encodeInt(mai.mobId);
                if(mai.mobId > 0) {
                    outPacket.encodeByte(mai.byteIdk1);
                    outPacket.encodeByte(mai.byteIdk2);
                    outPacket.encodeByte(mai.byteIdk3);
                    outPacket.encodeShort(mai.byteIdk4);
                    if(ai.skillId == 80001835 || ai.skillId == 42111002 || ai.skillId == 80011050) {
                        // Soul Shear
                        outPacket.encodeByte(ai.hits);
                        outPacket.encodeInt(0); // not exactly sure
                    }
                    for(int dmg : mai.damages) {
                        outPacket.encodeByte(0); // isCrit
                        outPacket.encodeInt(dmg);
                    }
                    if(SkillConstants.isKinesisPsychicLockSkill(ai.skillId)) {
                        outPacket.encodeInt(mai.psychicLockInfo);
                    }
                    if(ai.skillId == 37111005) {
                        outPacket.encodeByte(mai.rocketRushInfo);
                    }
                }
            }
            if(ai.skillId == 2321001 || ai.skillId == 2221052 || ai.skillId == 11121052 || ai.skillId == 12121054) {
                outPacket.encodeInt(ai.keyDown);
            } else if(SkillConstants.isSuperNovaSkill(ai.skillId) || SkillConstants.isScreenCenterAttackSkill(ai.skillId)
                    || ai.skillId == 101000202 || ai.skillId == 101000102 || ai.skillId == 80001762) {
                outPacket.encodePosition(ai.ptAttackRefPoint);
            }
            if(SkillConstants.isKeydownSkillRectMoveXY(ai.skillId)) {
                outPacket.encodePosition(ai.keyDownRectMoveXY);
            }
            if(ai.skillId == 51121009) {
                outPacket.encodeByte(ai.showFixedDamage);
            }
            if(ai.skillId == 112110003) { // formation attack
                outPacket.encodeInt(0);
            }
            if(ai.skillId == 42100007) { // Soul Bomb
                outPacket.encodeShort(0);
                byte size = 0;
                outPacket.encodeByte(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodePosition(new Position());
                }
            }
            if(ai.skillId == 21120019 || ai.skillId == 37121052) {
                outPacket.encodePositionInt(ai.teleportPt);
            }
//            if(ai.attackHeader == )
            outPacket.encodeArr(new byte[50]);
        }

        return outPacket;
    }

    public static OutPacket avatarModified(Char chr, byte mask, byte carryItemEffect) {
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_AVATAR_MODIFIED);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(mask);
        if((mask & AvatarModifiedMask.AvatarLook.getVal()) != 0) {
            al.encode(outPacket);
        }
        if((mask & AvatarModifiedMask.SubAvatarLook.getVal()) != 0) {
            al.encode(outPacket); // subAvatarLook
        }
        if((mask & AvatarModifiedMask.Speed.getVal()) != 0) {
            outPacket.encodeByte(tsm.getOption(CharacterTemporaryStat.Speed).nOption);
        }
        if((mask & AvatarModifiedMask.CarryItemEffect.getVal()) != 0) {
            outPacket.encodeByte(carryItemEffect);
        }
        boolean hasCouple = chr.getCouple() != null;
        outPacket.encodeByte(hasCouple);
        if(hasCouple) {
            chr.getCouple().encodeForRemote(outPacket);
        }
        boolean hasFriendShip = chr.getFriendshipRingRecord() != null;
        outPacket.encodeByte(hasFriendShip);
        if(hasFriendShip) {
            chr.getFriendshipRingRecord().encode(outPacket);
        }
        boolean hasWedding = chr.getMarriageRecord() != null;
        outPacket.encodeByte(hasWedding);
        if(hasWedding) {
            chr.getMarriageRecord().encode(outPacket);
        }
        outPacket.encodeInt(chr.getCompletedSetItemID());
        outPacket.encodeInt(chr.getTotalChuc());

        return outPacket;
    }

    public static OutPacket throwGrenade(int charID, int grenadeID, Position pos, int keyDown, int skillID, int bySummonedID,
                                         int slv, boolean left, int attackSpeed) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_THROW_GRENADE);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(grenadeID);
        outPacket.encodePositionInt(pos);
        outPacket.encodeInt(keyDown);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(bySummonedID);
        outPacket.encodeInt(slv);
        outPacket.encodeByte(left);
        outPacket.encodeInt(attackSpeed);

        return outPacket;
    }

    public static OutPacket destroyGrenade(int charID, int grenadeID) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_DESTROY_GRENADE);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(grenadeID);

        return outPacket;
    }

    public static OutPacket receiveHP(Char chr) {
        return receiveHP(chr.getId(), chr.getHP(), chr.getTotalStat(BaseStat.mhp));
    }

    public static OutPacket receiveHP(int charID, int curHP, int maxHP) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_RECEIVE_HP);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(curHP);
        outPacket.encodeInt(maxHP);

        return outPacket;
    }

    public static OutPacket hit(Char chr, HitInfo hitInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_HIT);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(hitInfo.type);
        outPacket.encodeInt(hitInfo.hpDamage);
        outPacket.encodeByte(hitInfo.isCrit);
        outPacket.encodeByte(hitInfo.hpDamage == 0);
        if (hitInfo.type == -8) {
            outPacket.encodeInt(hitInfo.skillID);
            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.otherUserID);
        } else {
            outPacket.encodeInt(hitInfo.templateID);
            outPacket.encodeByte(hitInfo.action);
            outPacket.encodeInt(hitInfo.mobID);

            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.reflectDamage);
            outPacket.encodeByte(hitInfo.hpDamage == 0); // bGuard
            if (hitInfo.reflectDamage > 0) {
                outPacket.encodeByte(hitInfo.isGuard);
                outPacket.encodeInt(hitInfo.mobID);

                outPacket.encodeByte(hitInfo.hitAction);
                outPacket.encodePosition(chr.getPosition());
            }
            outPacket.encodeByte(hitInfo.specialEffectSkill);
            if ((hitInfo.specialEffectSkill & 1) != 0) {
                outPacket.encodeInt(hitInfo.curStanceSkill);
            }
        }
        outPacket.encodeInt(hitInfo.hpDamage);
        if (hitInfo.hpDamage == -1) {
           outPacket.encodeInt(hitInfo.userSkillID);
        }

        return outPacket;
    }

    public static OutPacket effect(int id, Effect effect) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EFFECT);

        outPacket.encodeInt(id);
        effect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket setDefaultWingItem(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_DEFAULT_WING_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getWingItem());

        return outPacket;
    }
}
