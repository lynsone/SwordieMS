package packet;

import client.character.*;
import client.character.items.BodyPart;
import client.character.items.Equip;
import client.character.skills.ForceAtomInfo;
import client.character.skills.PsychicArea;
import client.life.*;
import connection.OutPacket;
import constants.ItemConstants;
import constants.SkillConstants;
import enums.*;
import handling.OutHeader;
import handling.handlers.PsychicLock;
import util.Position;
import util.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CField {



    public static OutPacket funcKeyMappedManInit(FuncKeyMap funcKeyMap) {
        OutPacket outPacket = new OutPacket(OutHeader.FUNC_KEY_MAPPED_MAN_INIT);

        funcKeyMap.encode(outPacket);

        return outPacket;
    }
    public static  OutPacket beastTamerFuncKeyMappedManInit(){
        OutPacket outPacket = new OutPacket();
        outPacket.encodeShort(0);
        return  outPacket;
    }

    public static OutPacket affectedAreaCreated(AffectedArea aa) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_CREATED);

        outPacket.encodeInt(aa.getObjectId());
        outPacket.encodeByte(aa.getMobOrigin());
        outPacket.encodeInt(aa.getCharID());
        outPacket.encodeInt(aa.getSkillID());
        outPacket.encodeByte(aa.getSlv());
        outPacket.encodeShort(aa.getDelay());
        aa.getRect().encode(outPacket);
        outPacket.encodeInt(aa.getElemAttr());
        outPacket.encodeInt(aa.getElemAttr()); // ?
        outPacket.encodePosition(aa.getPosition());
        outPacket.encodeInt(aa.getForce());
        outPacket.encodeInt(aa.getOption());
        outPacket.encodeByte(aa.getOption() != 0);
        outPacket.encodeInt(aa.getIdk()); // ?
        if(SkillConstants.isFlipAffectAreaSkill(aa.getSkillID())) {
            outPacket.encodeByte(aa.isFlip());
        }
        outPacket.encodeInt(0); // ?

        return outPacket;
    }

    public static OutPacket affectedAreaRemoved(AffectedArea aa, boolean mistEruption) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_REMOVED);

        outPacket.encodeInt(aa.getObjectId());
        if(aa.getSkillID() == 2111003) {
            outPacket.encodeByte(mistEruption);
        }

        return outPacket;
    }

    public static OutPacket summonedAssistAttackRequest(int charID, Summon summon) {
        OutPacket outpacket = new OutPacket(OutHeader.SUMMONED_ASSIST_ATTACK_REQUEST);

        outpacket.encodeInt(charID);
        outpacket.encodeInt(summon.getObjectId());
        outpacket.encodeInt(summon.getAssistType());

        return outpacket;
    }

    public static OutPacket summonedSummonAttackActive(int charID, Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SUMMON_ATTACK_ACTIVE);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(summon.isAttackActive());

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

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int targetID, int forceAtomType, boolean toMob,
                                     int targets, int skillID, ForceAtomInfo fai, Rect rect, int arriveDir, int arriveRange,
                                     Position forcedTargetPos, int bulletID, Position pos) {
        List<Integer> integers = new ArrayList<>();
        integers.add(targets);
        List<ForceAtomInfo> forceAtomInfos = new ArrayList<>();
        forceAtomInfos.add(fai);
        return createForceAtom(byMob, userOwner, targetID, forceAtomType, toMob, integers, skillID, forceAtomInfos,
                rect, arriveDir, arriveRange, forcedTargetPos, bulletID, pos);
    }

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int charID, int forceAtomType, boolean toMob,
                                     List<Integer> targets, int skillID, List<ForceAtomInfo> faiList, Rect rect, int arriveDir, int arriveRange,
                                     Position forcedTargetPos, int bulletID, Position pos) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_FORCE_ATOM);

        outPacket.encodeByte(byMob);
        if(byMob) {
            outPacket.encodeInt(userOwner);
        }
        outPacket.encodeInt(charID);
        outPacket.encodeInt(forceAtomType);
        if(forceAtomType != 0 && forceAtomType != 9 && forceAtomType != 14) {
            outPacket.encodeByte(toMob);
            switch (forceAtomType) {
                case 2:
                case 3:
                case 6:
                case 7:
                case 11:
                case 12:
                case 13:
                case 17:
                case 19:
                case 20:
                case 23:
                case 24:
                case 25:
                    outPacket.encodeInt(targets.size());
                    for (int i : targets) {
                        outPacket.encodeInt(i);
                    }
                    break;
                default:
                    outPacket.encodeInt(targets.get(0));
                    break;
            }
            outPacket.encodeInt(skillID);
        }
        for(ForceAtomInfo fai : faiList) {
            outPacket.encodeByte(1);
            fai.encode(outPacket);
        }
        outPacket.encodeByte(0);
        switch (forceAtomType) {
            case 11:
                outPacket.encodeRectInt(rect);
                outPacket.encodeInt(bulletID);
                break;
            case 9:
            case 15:
                outPacket.encodeRectInt(rect);
                break;
            case 16:
                outPacket.encodePositionInt(pos);
                break;
            case 17:
                outPacket.encodeInt(arriveDir);
                outPacket.encodeInt(arriveRange);
                break;
            case 18:
                outPacket.encodePositionInt(forcedTargetPos);
                break;
        }

        return outPacket;
    }
    public static OutPacket finalAttackRequest(Char chr, int skillID, int finalSkillID, int delay, int mobID,
                                               int userRequestTime) {
        return finalAttackRequest(chr, skillID, finalSkillID, delay, mobID, userRequestTime, false, null);
    }

    public static OutPacket finalAttackRequest(Char chr, int skillID, int finalSkillID, int delay, int mobID,
                                               int userRequestTime, boolean left, Position base) {
        OutPacket outPacket = new OutPacket(OutHeader.FINAL_ATTACK_REQUEST);

        int wt = ItemConstants.getWeaponType(chr.getEquippedItemByBodyPart(BodyPart.WEAPON).getItemId());

        outPacket.encodeInt(skillID);
        outPacket.encodeInt(finalSkillID);
        outPacket.encodeInt(wt);
        outPacket.encodeInt(delay);
        outPacket.encodeInt(mobID);
        outPacket.encodeInt(userRequestTime);
        if(skillID == 101000102) { // Air Riot
            outPacket.encodeByte(left);
            outPacket.encodePosition(base);
        }

        return outPacket;
    }

    public static OutPacket setAmmo(int ammo) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_AMMO);

        outPacket.encodeInt(ammo);

        return outPacket;
    }

    public static OutPacket createPsychicArea(boolean approved, PsychicArea psychicArea) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_AREA);

        outPacket.encodeByte(approved);
        if(approved) {
            outPacket.encodeInt(psychicArea.action);
            outPacket.encodeInt(psychicArea.actionSpeed);
            outPacket.encodeInt(psychicArea.localPsychicAreaKey);
            outPacket.encodeInt(psychicArea.skillID);
            outPacket.encodeShort(psychicArea.slv);
            outPacket.encodeInt(psychicArea.psychicAreaKey);
            outPacket.encodeInt(psychicArea.duration);
            outPacket.encodeByte(psychicArea.isLeft);
            outPacket.encodeShort(psychicArea.skeletonFilePathIdx);
            outPacket.encodeShort(psychicArea.skeletonAniIdx);
            outPacket.encodeShort(psychicArea.skeletonLoop);
            outPacket.encodePositionInt(psychicArea.start);
        } else {
            outPacket.encodeInt(psychicArea.localPsychicAreaKey);
        }
        return outPacket;
    }

    public static OutPacket releasePsychicArea(int localAreaKey) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_AREA);

        outPacket.encodeInt(localAreaKey);

        return outPacket;
    }

    public static OutPacket createPsychicLock(boolean approved, PsychicLock pl) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_LOCK);

        outPacket.encodeByte(approved);
        if(approved) {
            pl.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket releasePsychicLock(int id) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK);

        outPacket.encodeInt(id);

        return outPacket;
    }

    public static OutPacket releasePsychicLockMob(List<Integer> ids) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK_MOB);

        for(int i : ids) {
            outPacket.encodeByte(1);
            outPacket.encodeInt(i);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }

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

    public static OutPacket characterInfo(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_INFO);

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(false); // Star Planet
        outPacket.encodeByte(chr.getStat(Stat.level));
        outPacket.encodeShort(chr.getJob());
        outPacket.encodeShort(chr.getStat(Stat.subJob));
        outPacket.encodeByte(cs.getPvpGrade());
        outPacket.encodeInt(cs.getPop()); //Fame
        MarriageRecord marriage = chr.getMarriageRecord();
        outPacket.encodeByte(marriage != null);
        if(marriage != null) {
            marriage.encode(outPacket);
        }
        outPacket.encodeByte(0); // size(byte) of productSkill(Professions)(short); stuff like mining, herblore, etc...
        outPacket.encodeString("-");
        outPacket.encodeString("");
        outPacket.encodeByte(-1); // Forced pet IDx
        outPacket.encodeByte(0); // User state (?)
        outPacket.encodeByte(false); // pet activated
        outPacket.encodeByte(0); // CUIUserInfo::SetPetInfo
        outPacket.encodeByte(0); // old Wish list
        // MedalAchievementInfo::Decode
        Equip medal = (Equip) chr.getEquippedItemByBodyPart(BodyPart.MEDAL);
        outPacket.encodeInt(medal == null ? 0 : medal.getItemId());
        outPacket.encodeShort(0); // medal size
        // for each medal, encode stuff (check ida)
        // End MedalAchievementInfo::Decode
        // DamageSkinSaveInfo::Decode
        outPacket.encodeByte(1); // size
        // check ida for structure
        outPacket.encodeInt(0);
        outPacket.encodeInt(2431965);
        outPacket.encodeByte(0);
        outPacket.encodeString("This is a basic Damage Skin.\r\n\r\n\r\n\r\n\r\n");
        outPacket.encodeInt(-1);
        outPacket.encodeInt(0);
        outPacket.encodeByte(1);;
        outPacket.encodeString("");
        outPacket.encodeShort(0);
        outPacket.encodeShort(0);
        // End DamageSkinSaveInfo::Decode
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharisma());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getInsight());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getWill());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCraft());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getSense());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharm());
        outPacket.encodeInt(chr.getAccId());
        // FarmUserInfo::Decode
        outPacket.encodeString("Best farm eu");
        outPacket.encodeInt(13); // nFarmPoint
        outPacket.encodeInt(13); // nFarmLevel
        outPacket.encodeInt(13); // nFarmExp
        outPacket.encodeInt(13); // nFarmPoint
        outPacket.encodeInt(13); // nFarmCash
        outPacket.encodeByte(13); // nFarmGender
        outPacket.encodeInt(13); // nFarmTheme
        outPacket.encodeInt(13); // nFarmSlotExtend
        outPacket.encodeInt(13); // nFarmLockerSlotCount
        // End FarmUserInfo::Decode
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        //Chairs
        outPacket.encodeInt(0); //chair amount(size)
        outPacket.encodeInt(0);
        outPacket.encodeInt(30);
        outPacket.encodeInt(0);


        return outPacket;
    }

    public static OutPacket showItemUpgradeEffect(int charID, boolean success, boolean enchantDlg, int uItemID, int eItemID) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(success);
        outPacket.encodeByte(enchantDlg);
        outPacket.encodeInt(uItemID);
        outPacket.encodeInt(eItemID);

        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket showItemReleaseEffect(int charID, short pos, boolean bonus) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_RELEASE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeShort(pos);
        outPacket.encodeByte(bonus);

        return outPacket;
    }

    public static OutPacket hyperUpgradeDisplay(Equip equip, boolean downgradeable, long meso, long beforeMVP, int successChance,
                                                int destroyChance, boolean chanceTime, int flag) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.HyperUpgradeDisplay.getVal());
        outPacket.encodeByte(downgradeable);
        outPacket.encodeLong(meso);
        outPacket.encodeLong(beforeMVP);
        outPacket.encodeInt(successChance);
        outPacket.encodeInt(destroyChance);
        outPacket.encodeByte(chanceTime);
        outPacket.encodeInt(flag);
        TreeMap<EnchantStat, Integer> vals =  equip.getHyperUpgradeStats();
        int mask = 0;
        for(EnchantStat es : vals.keySet()) {
            mask |= es.getVal();
        }
        outPacket.encodeInt(mask);
        vals.forEach((es, val) -> outPacket.encodeInt(val));
        // TODO incomplete

        return outPacket;
    }

    public static OutPacket redCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip) {
        OutPacket outPacket = new OutPacket(OutHeader.RED_CUBE_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket cancelChair(int chrid, int id) {
        OutPacket outPacket = new OutPacket(OutHeader.SIT_RESULT);

        outPacket.encodeInt(chrid);
        if(id == -1) {
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeByte(1);
            outPacket.encodeShort(id);
        }

        return outPacket;
    }

    public static OutPacket showChair(int characterid, int itemid) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_PORTABLE_CHAIR);

        outPacket.encodeInt(characterid);
        outPacket.encodeInt(itemid);
        int message = 0;
        outPacket.encodeInt(message);
        if (message > 0) {
            outPacket.encodeString("");
        }
        int towerChair = 0;
        outPacket.encodeInt(towerChair);
        if (towerChair > 0) {
            outPacket.encodeInt(0);//TowerChairID
        }
        outPacket.encodeInt(0);//mesochaircount
        outPacket.encodeInt(0);//unkGMS
        outPacket.encodeInt(0);//unkGMS

        return outPacket;
    }
}
