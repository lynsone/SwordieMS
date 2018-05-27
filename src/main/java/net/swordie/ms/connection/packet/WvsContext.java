package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.*;
import net.swordie.ms.client.character.cards.CharacterCard;
import net.swordie.ms.client.character.info.ExpIncreaseInfo;
import net.swordie.ms.client.character.info.ZeroInfo;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.friend.result.FriendResult;
import net.swordie.ms.client.guild.result.GuildResultInfo;
import net.swordie.ms.client.jobs.resistance.WildHunterInfo;
import net.swordie.ms.life.movement.*;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.party.result.PartyResultInfo;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.header.OutHeader;
import org.apache.log4j.LogManager;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;

import java.util.*;

import static net.swordie.ms.enums.MessageType.*;

/**
 * Created on 12/22/2017.
 */
public class WvsContext {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static void dispose(Char chr) {
        chr.dispose();
    }

    public static OutPacket exclRequest() {
        return new OutPacket(OutHeader.EXCL_REQUEST);
    }

    public static OutPacket statChanged(Map<Stat, Object> stats) {
        return statChanged(stats, true, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0);
    }

    public static OutPacket statChanged(Map<Stat, Object> stats, boolean exclRequestSent, byte mixBaseHairColor,
                                        byte mixAddHairColor, byte mixHairBaseProb, byte charmOld, boolean updateCovery,
                                        int hpRecovery, int mpRecovery) {
        OutPacket outPacket = new OutPacket(OutHeader.STAT_CHANGED);

        outPacket.encodeByte(exclRequestSent);
        // GW_CharacterStat::DecodeChangeStat
        int mask = 0;
        for (Stat stat : stats.keySet()) {
            mask |= stat.getVal();
        }
        outPacket.encodeLong(mask);
        Comparator statComper = Comparator.comparingInt(o -> ((Stat) o).getVal());
        TreeMap<Stat, Object> sortedStats = new TreeMap<>(statComper);
        sortedStats.putAll(stats);
        for (Map.Entry<Stat, Object> entry : sortedStats.entrySet()) {
            Stat stat = entry.getKey();
            Object value = entry.getValue();
            switch (stat) {
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
                    if (value instanceof ExtendSP) {
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
        if (charmOld > 0) {
            outPacket.encodeByte(charmOld);
        }
        outPacket.encodeByte(updateCovery);
        if (updateCovery) {
            outPacket.encodeInt(hpRecovery);
            outPacket.encodeInt(mpRecovery);
        }
        return outPacket;
    }

    public static OutPacket inventoryOperation(boolean exclRequestSent, boolean notRemoveAddInfo, InventoryOperation type, short oldPos, short newPos,
                                               int bagPos, Item item) {
        // logic like this in packets :(
        InvType invType = item.getInvType();
        if (oldPos > 0 && newPos < 0 && invType == InvType.EQUIPPED) {
            invType = InvType.EQUIP;
        }

        OutPacket outPacket = new OutPacket(OutHeader.INVENTORY_OPERATION);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(1); // size
        outPacket.encodeByte(notRemoveAddInfo);

        outPacket.encodeByte(type.getVal());
        outPacket.encodeByte(invType.getVal());
        outPacket.encodeShort(oldPos);
        switch (type) {
            case ADD:
                item.encode(outPacket);
                break;
            case UPDATE_QUANTITY:
                outPacket.encodeShort(item.getQuantity());
                break;
            case MOVE:
                outPacket.encodeShort(newPos);
                if (invType == InvType.EQUIP && (oldPos < 0 || newPos < 0)) {
                    outPacket.encodeByte(item.getCashItemSerialNumber() > 0);
                }
                break;
            case REMOVE:
                break;
            case ITEM_EXP:
                outPacket.encodeLong(((Equip) item).getExp());
                break;
            case UPDATE_BAG_POS:
                outPacket.encodeInt(bagPos);
                break;
            case UPDATE_BAG_QUANTITY:
                outPacket.encodeShort(newPos);
                break;
            case UNK_1:
                break;
            case UNK_2:
                outPacket.encodeShort(bagPos); // ?
                break;
            case UPDATE_ITEM_INFO:
                item.encode(outPacket);
                break;
            case UNK_3:
                break;
        }
        return outPacket;
    }

    public static OutPacket updateEventNameTag(int[] tags) {
        OutPacket outPacket = new OutPacket(OutHeader.EVENT_NAME_TAG);

        for (int i = 0; i < 5; i++) {
            outPacket.encodeString("");
            if (i >= tags.length) {
                outPacket.encodeByte(-1);
            } else {
                outPacket.encodeByte(tags[i]);
            }
        }

        return outPacket;
    }

    public static OutPacket changeSkillRecordResult(List<Skill> skills, boolean exclRequestSent, boolean showResult,
                                                    boolean removeLinkSkill, boolean sn) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_SKILL_RECORD_RESULT);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(showResult);
        outPacket.encodeByte(removeLinkSkill);
        outPacket.encodeShort(skills.size());
        for (Skill skill : skills) {
            outPacket.encodeInt(skill.getSkillId());
            outPacket.encodeInt(skill.getCurrentLevel());
            outPacket.encodeInt(skill.getMasterLevel());
            outPacket.encodeFT(new FileTime(0));
        }
        outPacket.encodeByte(sn);

        return outPacket;
    }

    public static List<Movement> parseMovement(InPacket inPacket) {
        // Taken from mushy when my IDA wasn't able to show this properly
        // Made by Maxcloud
        List<Movement> res = new ArrayList<>();
        byte size = inPacket.decodeByte();
        for (int i = 0; i < size; i++) {
            byte type = inPacket.decodeByte();
            switch (type) {
                case 0:
                case 8:
                case 15:
                case 17:
                case 19:
                case 67:
                case 68:
                case 69:
                    res.add(new Movement1(inPacket, type));
                    break;
                case 1:
                case 2:
                case 18:
                case 21:
                case 22:
                case 24:
                case 62:
                case 63:
                case 64:
                case 65:
                    res.add(new Movement3(inPacket, type));
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 9:
                case 10:
                case 11:
                case 13:
                case 26:
                case 27:
                case 52:
                case 53:
                case 54:
                case 61:
                case 76:
                case 77:
                case 78:
                case 80:
                case 82:
                    res.add(new Movement5(inPacket, type));
                    break;
                case 12:
                    res.add(new Movement8(inPacket, type));
                    break;
                case 14:
                case 16:
                    res.add(new Movement6(inPacket, type));
                    break;
                case 23:
                    res.add(new Movement7(inPacket, type));
                    break;
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 57:
                case 58:
                case 59:
                case 60:
                case 70:
                case 71:
                case 72:
                case 74:
                case 79:
                case 81:
                case 83:
                    res.add(new Movement4(inPacket, type));
                    break;
                case 56:
                case 66:
                case 85:
                    res.add(new Movement2(inPacket, type));
                    break;
                default:
                    log.warn(String.format("[WvsContext.parseMovement] The type (%s) is unhandled.", type));
                    break;
            }
        }
        return res;
    }

    public static OutPacket temporaryStatSet(TemporaryStatManager tsm) {
        OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_SET);

        boolean hasMovingAffectingStat = tsm.hasNewMovingEffectingStat(); // encoding flushes new stats
        tsm.encodeForLocal(outPacket);

        outPacket.encodeInt(0); // can't find this is IDA, but crashes without. Not sure what it stands for
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        if (hasMovingAffectingStat) {
            outPacket.encodeByte(0);
        }

        return outPacket;
    }

    public static OutPacket temporaryStatReset(TemporaryStatManager temporaryStatManager, boolean demount) {
        OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_RESET);

        for (int i : temporaryStatManager.getRemovedMask()) {
            outPacket.encodeInt(i);
        }
//        temporaryStatManager.getRemovedStats().forEach((cts, option) -> outPacket.encodeInt(0));
        temporaryStatManager.encodeRemovedIndieTempStat(outPacket);
        if (temporaryStatManager.hasRemovedMovingEffectingStat()) {
            outPacket.encodeByte(0);
        }
        outPacket.encodeByte(0); // ?
        outPacket.encodeByte(demount);

        temporaryStatManager.getRemovedStats().clear();
        return outPacket;
    }

    public static OutPacket skillUseResult(boolean stillGoing) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_USE_RESULT);
        // 2221011 - Frozen Breath
        outPacket.encodeByte(stillGoing);

        return outPacket;
    }

    public static OutPacket explosionAttack(int skillID, Position pos, int mobID, int count) {
        OutPacket outPacket = new OutPacket(OutHeader.EXPLOSION_ATTACK);

        outPacket.encodeInt(skillID);
        outPacket.encodeInt(pos.getX());
        outPacket.encodeInt(pos.getY());
        outPacket.encodeInt(mobID);
        outPacket.encodeInt(count);

        return outPacket;
    }

    public static OutPacket dropPickupMessage(int money, short internetCafeExtra, short smallChangeExtra) {
        return dropPickupMessage(money, (byte) 1, internetCafeExtra, smallChangeExtra, (short) 0);
    }

    public static OutPacket dropPickupMessage(Item item, short quantity) {
        return dropPickupMessage(item.getItemId(), (byte) 0, (short) 0, (short) 0, quantity);
    }

    public static OutPacket dropPickupMessage(int i, byte type, short internetCafeExtra, short smallChangeExtra, short quantity) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(DROP_PICKUP_MESSAGE.getVal());
        outPacket.encodeByte(type);
        // also error (?) codes -2, ,-3, -4, -5, <default>
        switch (type) {
            case 1: // Mesos
                outPacket.encodeByte(false); // boolean: portion was lost after falling to the ground
                outPacket.encodeInt(i); // Mesos
                outPacket.encodeShort(internetCafeExtra); // Internet cafe
                outPacket.encodeShort(smallChangeExtra); // Spotting small change
                break;
            case 0: // item
                outPacket.encodeInt(i);
                outPacket.encodeInt(quantity); // ?
                break;
            case 2: // ?
                outPacket.encodeInt(100);
                break;
        }

        return outPacket;
    }

    public static OutPacket questRecordMessageAddValidCheck(int qrKey, byte state) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(QUEST_RECORD_MESSAGE_ADD_VALID_CHECK.getVal());
        outPacket.encodeInt(qrKey);
        outPacket.encodeByte(true);
        outPacket.encodeByte(state);
        // TODO probably missing something here

        return outPacket;
    }

    public static OutPacket questRecordMessage(Quest quest) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(QUEST_RECORD_MESSAGE.getVal());
        outPacket.encodeInt(quest.getQRKey());
        QuestStatus state = quest.getStatus();
        outPacket.encodeByte(state.getVal());

        switch(state) {
            case NOT_STARTED:
                outPacket.encodeByte(0); // If quest is completed, but should never be true?
                break;
            case STARTED:
                outPacket.encodeString(quest.getQRValue());
                break;
            case COMPLETE:
                outPacket.encodeFT(quest.getCompletedTime());
                break;
        }

        return outPacket;
    }

    public static OutPacket incExpMessage(ExpIncreaseInfo eii) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_EXP_MESSAGE.getVal());
        eii.encode(outPacket);

        return outPacket;
    }

    public static OutPacket incSpMessage(short job, byte amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_SP_MESSAGE.getVal());
        outPacket.encodeShort(job);
        outPacket.encodeByte(amount);

        return outPacket;
    }

    public static OutPacket incMoneyMessage(String clientName, int amount, int charID) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_MONEY_MESSAGE.getVal());
        outPacket.encodeInt(amount);
        outPacket.encodeInt(1);
        outPacket.encodeString(clientName);

        return outPacket;
    }

    /**
     * Returns a net.swordie.ms.connection.packet for messages with the following {@link MessageType}:<br>
     * GENERAL_ITEM_EXPIRE_MESSAGE<br>
     * ITEM_PROTECT_EXPIRE_MESSAGE<br>
     * ITEM_ABILITY_TIME_LIMITED_EXPIRE_MESSAGE<br>
     * SKILL_EXPIRE_MESSAGE
     *
     * @param mt    The message type.
     * @param items The list of ints that should be encoded.
     * @return The message OutPacket.
     */
    public static OutPacket message(MessageType mt, List<Integer> items) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(mt.getVal());
        switch (mt) {
            case GENERAL_ITEM_EXPIRE_MESSAGE:
            case ITEM_PROTECT_EXPIRE_MESSAGE:
            case ITEM_ABILITY_TIME_LIMITED_EXPIRE_MESSAGE:
            case SKILL_EXPIRE_MESSAGE:
                outPacket.encodeByte(items.size());
                items.forEach(outPacket::encodeInt);
                break;
        }
        return outPacket;
    }

    public static OutPacket itemExpireReplaceMessage(List<String> strings) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(ITEM_EXPIRE_REPLACE_MESSAGE.getVal());
        outPacket.encodeByte(strings.size());
        strings.forEach(outPacket::encodeString);

        return outPacket;
    }

    /**
     * Returns a net.swordie.ms.connection.packet for messages with the following {@link MessageType}:<br>
     * int: <br>
     * CASH_ITEM_EXPIRE_MESSAGE<br>
     * INC_POP_MESSAGE<br>
     * INC_GP_MESSAGE<br>
     * GIVE_BUFF_MESSAGE<br><br>
     * int + byte: <br>
     * INC_COMMITMENT_MESSAGE<br><br>
     * String: <br>
     * SYSTEM_MESSAGE<br><br>
     * int + String: <br>
     * QUEST_RECORD_EX_MESSAGE<br>
     * WORLD_SHARE_RECORD_MESSAGE<br>
     *
     * @param mt     The message type.
     * @param i      The integer to encode.
     * @param string The String to encode.
     * @param type   The type (byte) to encode.
     * @return The message OutPacket.
     */
    public static OutPacket message(MessageType mt, int i, String string, byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(mt.getVal());
        switch (mt) {
            case CASH_ITEM_EXPIRE_MESSAGE:
            case INC_POP_MESSAGE:
            case INC_GP_MESSAGE:
            case GIVE_BUFF_MESSAGE:
                outPacket.encodeInt(i);
                break;
            case INC_COMMITMENT_MESSAGE:
                outPacket.encodeInt(i);
                outPacket.encodeByte(type);
                break;
            case SYSTEM_MESSAGE:
                outPacket.encodeString(string);
                break;
            case QUEST_RECORD_EX_MESSAGE:
            case WORLD_SHARE_RECORD_MESSAGE:
                outPacket.encodeInt(i);
                outPacket.encodeString(string);
        }

        return outPacket;
    }

    public static OutPacket flipTheCoinEnabled(byte enabled) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_FLIP_THE_COIN_ENABLED);

        outPacket.encodeByte(enabled);

        return outPacket;
    }

    public static OutPacket modComboResponse(int combo) {
        OutPacket outPacket = new OutPacket(OutHeader.MOD_COMBO_RESPONSE);

        outPacket.encodeInt(combo);

        return outPacket;
    }

    public static OutPacket wildHunterInfo(WildHunterInfo whi) {
        OutPacket outPacket = new OutPacket(OutHeader.WILD_HUNTER_INFO);

        whi.encode(outPacket);

        return outPacket;
    }

    public static OutPacket zeroInfo(ZeroInfo currentInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.ZERO_INFO);

        currentInfo.encode(outPacket);

        return outPacket;
    }

    public static OutPacket gatherItemResult(byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.GATHER_ITEM_RESULT);

        outPacket.encodeByte(0); // doesn't get used
        outPacket.encodeByte(type);

        return outPacket;
    }

    public static OutPacket sortItemResult(byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.GATHER_ITEM_RESULT);

        outPacket.encodeByte(0); // doesn't get used
        outPacket.encodeByte(type);

        return outPacket;
    }

    public static OutPacket clearAnnouncedQuest() {
        return new OutPacket(OutHeader.CLEAR_ANNOUNCED_QUEST);
    }

    public static OutPacket partyResult(PartyResultInfo pri) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_RESULT);

        outPacket.encodeByte(pri.getType().getVal());
        pri.encode(outPacket);

        return outPacket;
    }

    public static OutPacket partyMemberCandidateResult(Set<Char> chars) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_MEMBER_CANDIDATE_RESULT);

        outPacket.encodeByte(chars.size());
        for(Char chr : chars) {
            outPacket.encodeInt(chr.getId());
            outPacket.encodeString(chr.getName());
            outPacket.encodeShort(chr.getJob());
            outPacket.encodeShort(chr.getAvatarData().getCharacterStat().getSubJob());
            outPacket.encodeByte(chr.getLevel());
        }

        return outPacket;
    }

    public static OutPacket partyCandidateResult(Set<Party> parties) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_CANDIDATE_RESULT);

        outPacket.encodeByte(parties.size());
        for(Party party : parties) {
            Char leader = party.getPartyLeader().getChr();
            outPacket.encodeInt(party.getId());
            outPacket.encodeString(leader.getName());
            outPacket.encodeByte(party.getAvgLevel());
            outPacket.encodeByte(party.getMembers().size());
            outPacket.encodeString(party.getName());
            outPacket.encodeByte(party.getMembers().size());
            for(PartyMember pm : party.getMembers()) {
                outPacket.encodeInt(pm.getCharID());
                outPacket.encodeString(pm.getCharName());
                outPacket.encodeShort(pm.getJob());
                outPacket.encodeShort(pm.getSubSob());
                outPacket.encodeByte(pm.getLevel());
                outPacket.encodeByte(pm.equals(party.getPartyLeader()));
            }
        }
        outPacket.encodeArr(new byte[40]);

        return outPacket;
    }

    public static OutPacket guildResult(GuildResultInfo gri) {
        OutPacket outPacket = new OutPacket(OutHeader.GUILD_RESULT);

        outPacket.encodeByte(gri.getType().getVal());
        gri.encode(outPacket);

        return outPacket;
    }

    public static OutPacket flameWizardFlareBlink(Char chr, Position newPosition, boolean used) {
        OutPacket outPacket = new OutPacket(OutHeader.FLAME_WIZARD_FLARE_BLINK);

        Position zero = new Position(0,0);
        outPacket.encodeInt(chr.getId()); //chr?
        outPacket.encodeByte(used); //used?

        if (used) {

        //Blink - Clear + Teleport
            chr.write(CField.teleport(newPosition, chr));

        } else {

        //Blink - Set Position
            outPacket.encodeByte(used);
            outPacket.encodeShort(1);
            outPacket.encodePosition(newPosition); //2x encode Short (x/y)
            outPacket.encodePosition(zero); //2x encode Short (x/y)
        }
        return outPacket;
    }

    public static OutPacket friendResult(FriendResult friendResult) {
        OutPacket outPacket = new OutPacket(OutHeader.FRIEND_RESULT);

        outPacket.encodeByte(friendResult.getType().getVal());
        friendResult.encode(outPacket);

        return outPacket;
    }


    public static OutPacket loadAccountIDOfCharacterFriendResult(Set<Friend> friends) {
        OutPacket outPacket = new OutPacket(OutHeader.LOAD_ACCOUNT_ID_OF_CHARACTER_FRIEND_RESULT);

        outPacket.encodeInt(friends.size());
        for(Friend fr : friends) {
            outPacket.encodeInt(fr.getFriendID());
            outPacket.encodeInt(fr.getFriendAccountID());
        }

        return outPacket;
    }

    public static OutPacket macroSysDataInit(List<Macro> macros) {
        OutPacket outPacket = new OutPacket(OutHeader.MACRO_SYS_DATA_INIT);

        outPacket.encodeByte(macros.size());
        for(Macro macro : macros) {
            macro.encode(outPacket);
        }
        return outPacket;
    }

    public static OutPacket monsterBookSetCard(int id) {
//        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_LIFE_INVITE_ITEM_RESULT);
        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_BOOK_SET_CARD);

        outPacket.encodeByte(id > 0); // false -> already added msg
        if (id > 0) {
            outPacket.encodeInt(id);
            outPacket.encodeInt(1); // card count, but we're just going to stuck with 1.
        }

        return outPacket;
    }

    public static OutPacket characterPotentialReset(PotentialResetType prt, int arg) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_POTENTIAL_RESET);

        outPacket.encodeByte(prt.ordinal());
        switch (prt) {
            case Pos:
                outPacket.encodeShort(arg);
                break;
            case Skill:
                outPacket.encodeInt(arg);
                break;
            case All:
                break;
        }
        return outPacket;
    }

    public static OutPacket characterPotentialSet(CharacterPotential cp) {
        return characterPotentialSet(true, true, cp.getKey(), cp.getSkillID(), cp.getSlv(), cp.getGrade(), true);
    }

    public static OutPacket characterPotentialSet(boolean exclRequest, boolean changed, short pos, int skillID,
                                                  short skillLevel, short grade, boolean updatePassive) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_POTENTIAL_SET);

        outPacket.encodeByte(exclRequest);
        outPacket.encodeByte(changed);
        if (changed) {
            outPacket.encodeShort(pos);
            outPacket.encodeInt(skillID);
            outPacket.encodeShort(skillLevel);
            outPacket.encodeShort(grade);
            outPacket.encodeByte(updatePassive);
        }

        return outPacket;
    }

    public static OutPacket characterHonorExp(int exp) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_HONOR_EXP);

        outPacket.encodeInt(exp);

        return outPacket;
    }
}
