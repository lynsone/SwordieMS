package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterCollection;
import net.swordie.ms.client.character.MonsterCollectionExploration;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.potential.CharacterPotentialMan;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.Warrior;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.WildHunter;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.MonsterCollectionGroup;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.MonsterCollectionData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Portal;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.swordie.ms.enums.ChatType.SystemNotice;

public class UserHandler {

    private static final Logger log = Logger.getLogger(UserHandler.class);


    @Handler(op = InHeader.USER_MOVE)
    public static void handleUserMove(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        // CVecCtrlUser::EndUpdateActive
        byte fieldKey = inPacket.decodeByte();
        inPacket.decodeInt(); // ? something with field
        inPacket.decodeInt(); // tick
        inPacket.decodeByte(); // ? doesn't get set at all
        // CMovePathCommon::Encode
        MovementInfo movementInfo = new MovementInfo(inPacket);
        movementInfo.applyTo(chr);
        field.checkCharInAffectedAreas(chr);
        field.broadcastPacket(UserRemote.move(chr, movementInfo), chr);
        if (chr.getPosition().getY() > 5000) {
            // failsafe when the char falls outside of the map
            Portal portal = field.getDefaultPortal();
            Position position = new Position(portal.getX(), portal.getY());
            chr.setPosition(position);
            chr.write(FieldPacket.teleport(position, chr));
        }
        // client has stopped moving. this might not be the best way
        if (chr.getMoveAction() == 4 || chr.getMoveAction() == 5) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            for (int skill : Job.REMOVE_ON_STOP) {
                if (tsm.hasStatBySkillId(skill)) {
                    tsm.removeStatsBySkill(skill);
                }
            }
        }
    }

    @Handler(op = InHeader.USER_HIT)
    public static void handleUserHit(Char chr, InPacket inPacket) {
        chr.getJobHandler().handleHit(inPacket);
    }

    @Handler(op = InHeader.USER_GROWTH_HELPER_REQUEST)
    public static void handleUserGrowthRequestHelper(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
            chr.dispose();
            return;
        }
        short status = inPacket.decodeShort();
        if (status == 0) {
            // TODO: verify that this map is actually valid, otherwise players can warp to pretty much anywhere they want
            int mapleGuideMapId = inPacket.decodeInt();
            Field toField = chr.getClient().getChannelInstance().getField(mapleGuideMapId);
            if (toField == null || (toField.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
                chr.dispose();
                return;
            }
            chr.warp(toField);
        }
        if (status == 2) {
            //TODO wtf happens here
            //int write 0
            //int something?
        }

    }


    @Handler(op = InHeader.FUNC_KEY_MAPPED_MODIFIED)
    public static void handleFuncKeyMappedModified(Client c, InPacket inPacket) {
        int updateType = inPacket.decodeInt();
        switch (updateType) {
            case 0:
                int size = inPacket.decodeInt();
                for (int i = 0; i < size; i++) {
                    int index = inPacket.decodeInt();
                    byte type = inPacket.decodeByte();
                    int value = inPacket.decodeInt();
                    c.getChr().getFuncKeyMap().putKeyBinding(index, type, value);
                }
                break;
            case 1: // HP potion
                break;
        }
    }


    @Handler(op = InHeader.USER_CHARACTER_INFO_REQUEST)
    public static void handleUserCharacterInfoRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        inPacket.decodeInt(); // tick
        int requestID = inPacket.decodeInt();
        Char requestChar = field.getCharByID(requestID);
        if (requestChar == null) {
            chr.chatMessage(SystemNotice, "The character you tried to find could not be found.");
        } else {
            c.write(FieldPacket.characterInfo(requestChar));
        }
    }


    @Handler(op = InHeader.EVENT_UI_REQ)
    public static void handleEventUiReq(Client c, InPacket inPacket) {
        //TODO: get opcodes for CUIContext::OnPacket
    }


    @Handler(op = InHeader.BATTLE_RECORD_ON_OFF_REQUEST)
    public static void handleBattleRecordOnOffRequest(Client c, InPacket inPacket) {
        // CBattleRecordMan::RequestOnCalc
        boolean on = inPacket.decodeByte() != 0;
        boolean isNew = inPacket.decodeByte() != 0;
        boolean clear = inPacket.decodeByte() != 0;
        c.getChr().setBattleRecordOn(on);
        c.write(BattleRecordMan.serverOnCalcRequestResult(on));
    }

    @Handler(op = InHeader.USER_SIT_REQUEST)
    public static void handleUserSitRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int fieldSeatId = inPacket.decodeShort();

        chr.setPortableChairID(0);
        chr.setPortableChairMsg("");
        chr.write(FieldPacket.sitResult(chr.getId(), fieldSeatId));
        field.broadcastPacket(UserRemote.remoteSetActivePortableChair(chr.getId(), 0, false, ""));
        chr.dispose();
    }

    @Handler(op = InHeader.USER_PORTABLE_CHAIR_SIT_REQUEST)
    public static void handleUserPortableChairSitRequest(Char chr, InPacket inpacket) {
        Field field = chr.getField();
        int itemId = inpacket.decodeInt(); // item id
        int pos = inpacket.decodeInt(); // setup position
        byte chairBag = inpacket.decodeByte(); // is Chair in a bag
        boolean textChair = inpacket.decodeInt() != 0; // boolean to show text
        String text = "";
        if (textChair) {
            text = inpacket.decodeString(); // text to display
        }
        if (textChair && (text.length() > 12 || !Util.isValidString(text) || !chr.hasItem(itemId))) {
            chr.chatMessage("Invalid text.");
            chr.dispose();
            return;
        }

        // Tower Chair  check & id
        inpacket.decodeInt(); // encodes 0
        int unknown = inpacket.decodeInt();

        inpacket.decodeInt(); // Time

        field.broadcastPacket(UserRemote.remoteSetActivePortableChair(chr.getId(), itemId, textChair, text));
        chr.setPortableChairID(itemId);
        chr.setPortableChairMsg(text);
        chr.dispose();
    }

    @Handler(op = InHeader.USER_EMOTION)
    public static void handleUserEmotion(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int emotion = inPacket.decodeInt();
        int duration = inPacket.decodeInt();
        boolean byItemOption = inPacket.decodeByte() != 0;
        if (GameConstants.isValidEmotion(emotion)) {
            chr.getField().broadcastPacket(UserRemote.emotion(chr.getId(), emotion, duration, byItemOption), chr);
        }
    }

    @Handler(op = InHeader.USER_ACTIVATE_EFFECT_ITEM)
    public static void handleUserActivateEffectItem(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int itemId = inPacket.decodeInt();
        if (chr.hasItem(itemId)) {
            chr.setActiveEffectItemID(itemId);
        }
    }

    @Handler(op = InHeader.USER_SOUL_EFFECT_REQUEST)
    public static void handleUserSoulEffectRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        boolean set = inPacket.decodeByte() != 0;
        chr.getField().broadcastPacket(UserPacket.SetSoulEffect(chr.getId(), set));
    }

    @Handler(op = InHeader.MONSTER_BOOK_MOB_INFO)
    public static void handleMonsterBookMobInfo(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        int cardID = inPacket.decodeInt();
        ItemInfo ii = ItemData.getItemInfoByID(cardID);
        Mob mob = MobData.getMobById(ii.getMobID());
        if (mob != null) {
            // TODO Figure out which packet to send
        }
    }

    @Handler(op = InHeader.USER_REQUEST_CHARACTER_POTENTIAL_SKILL_RAND_SET_UI)
    public static void handleUserRequestCharacterPotentialSkillRandSetUi(Char chr, InPacket inPacket) {
        // what a name
        int cost = GameConstants.CHAR_POT_RESET_COST;
        int rate = inPacket.decodeInt();
        int size = inPacket.decodeInt();
        Set<Integer> lockedLines = new HashSet<>();
        for (int i = 0; i < size; i++) {
            lockedLines.add(inPacket.decodeInt());
            if (lockedLines.size() == 0) {
                cost += GameConstants.CHAR_POT_LOCK_1_COST;
            } else {
                cost += GameConstants.CHAR_POT_LOCK_2_COST;
            }
        }
        boolean locked = rate > 0;
        if (locked) {
            cost += GameConstants.CHAR_POT_GRADE_LOCK_COST;
        }
        if (cost > chr.getHonorExp()) {
            chr.chatMessage("You do not have enough honor exp for that action.");
            chr.getOffenseManager().addOffense(String.format("Character %d tried to reset honor without having enough exp (required %d, has %d)",
                    chr.getId(), cost, chr.getHonorExp()));
            chr.dispose();
            return;
        }
        chr.addHonorExp(-cost);

        CharacterPotentialMan cpm = chr.getPotentialMan();
        boolean gradeUp = !locked && Util.succeedProp(GameConstants.BASE_CHAR_POT_UP_RATE);
        boolean gradeDown = !locked && Util.succeedProp(GameConstants.BASE_CHAR_POT_DOWN_RATE);
        byte grade = cpm.getGrade();
        // update grades
        if (grade < CharPotGrade.Legendary.ordinal() && gradeUp) {
            grade++;
        } else if (grade > CharPotGrade.Rare.ordinal() && gradeDown) {
            grade--;
        }
        // set new potentials that weren't locked
        for (CharacterPotential cp : chr.getPotentials()) {
            cp.setGrade(grade);
            if (!lockedLines.contains((int) cp.getKey())) {
                cpm.addPotential(cpm.generateRandomPotential(cp.getKey()));
            }
        }
    }

    @Handler(op = InHeader.RUNE_STONE_USE_REQ)
    public static void handleRuneStoneUseRequest(Client c, InPacket inPacket) {
        int unknown = inPacket.decodeInt(); // unknown
        RuneType runeType = RuneType.getByVal((byte) inPacket.decodeInt());

        Char chr = c.getChr();
        int minLevel = chr.getField().getMobs().stream().mapToInt(m -> m.getForcedMobStat().getLevel()).min().orElse(0);

        // User is on RuneStone Cooldown
        if ((c.getChr().getRuneCooldown() + (GameConstants.RUNE_COOLDOWN_TIME * 60000)) < System.currentTimeMillis()) {

            // Rune is too strong for user
            if (minLevel > c.getChr().getStat(Stat.level)) {
                c.write(FieldPacket.runeStoneUseAck(4));
                return;
            }

            // Send Arrow Message
            c.write(FieldPacket.runeStoneUseAck(5));
        } else {
            long minutes = (((c.getChr().getRuneCooldown() + (GameConstants.RUNE_COOLDOWN_TIME * 60000)) - System.currentTimeMillis()) / 60000);
            long seconds = (((c.getChr().getRuneCooldown() + (GameConstants.RUNE_COOLDOWN_TIME * 60000)) - System.currentTimeMillis()) / 1000);
            chr.chatScriptMessage("You cannot use another Rune for " +
                    (minutes > 0 ?
                            minutes + " minute" + (minutes > 1 ? "s" : "") + " and " + (seconds - (minutes * 60)) + " second" + ((seconds - (minutes * 60)) > 1 ? "s" : "") + "" :
                            seconds + " second" + (seconds > 1 ? "s" : "")
                    ));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.RUNE_STONE_SKILL_REQ)
    public static void handleRuneStoneSkillRequest(Client c, InPacket inPacket) {
        boolean success = inPacket.decodeByte() != 0; //Successfully done the Arrow Shit for runes

        if (success) {
            RuneStone runeStone = c.getChr().getField().getRuneStone();

            c.getChr().getField().useRuneStone(c, runeStone);
            //c.write(FieldPacket.runeStoneSkillAck(runeStone.getRuneType()));
            runeStone.activateRuneStoneEffect(c.getChr());
            c.getChr().setRuneCooldown(System.currentTimeMillis());
        }
        c.getChr().dispose();
    }

    @Handler(op = InHeader.MONSTER_COLLECTION_EXPLORE_REQ)
    public static void handleMonsterCollectionExploreReq(Char chr, InPacket inPacket) {
        int region = inPacket.decodeInt();
        int session = inPacket.decodeInt();
        int group = inPacket.decodeInt();
        int key = region * 10000 + session * 100 + group;
        Account account = chr.getAccount();
        MonsterCollection mc = account.getMonsterCollection();
        MonsterCollectionExploration mce = mc.getExploration(region, session, group);
        boolean complete = mc.isComplete(region, session, group);
        if (complete && mce == null) {
            // starting an exploration
            if (mc.getOpenExplorationSlots() <= 0) {
                chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.NotEnoughExplorationSlots, null, 0));
                return;
            }
            mce = mc.createExploration(region, session, group);
            mc.addExploration(mce);
            chr.write(UserLocal.collectionRecordMessage(mce.getPosition(), mce.getValue(true)));
            chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.ExploreBegin, null, 0));
        } else {
            // trying to start an incomplete/already exploring group
            chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.NoMonstersForExploring, null, 0));
        }
        chr.dispose(); // still required even if you send a collection result
    }

    @Handler(op = InHeader.MONSTER_COLLECTION_COMPLETE_REWARD_REQ)
    public static void handleMonsterCollectionCompleteRewardReq(Char chr, InPacket inPacket) {
        int reqType = inPacket.decodeInt(); // 0 = group
        int region = inPacket.decodeInt();
        int session = inPacket.decodeInt();
        int group = inPacket.decodeInt();
        int exploreIndex = inPacket.decodeInt();
        MonsterCollection mc = chr.getAccount().getMonsterCollection();
        switch (reqType) {
            case 0: // group
                MonsterCollectionGroup mcs = mc.getGroup(region, session, group);
                if (mcs != null && !mcs.isRewardClaimed() && mc.isComplete(region, session, group)) {
                    Tuple<Integer, Integer> rewardInfo = MonsterCollectionData.getReward(region, session, group);
                    Item item = ItemData.getItemDeepCopy(rewardInfo.getLeft());
                    item.setQuantity(rewardInfo.getRight());
                    chr.addItemToInventory(item);
                    mcs.setRewardClaimed(true);
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CollectionCompletionRewardSuccess, null, 0));
                } else if (mcs != null && mcs.isRewardClaimed()) {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.AlreadyClaimedReward, null, 0));
                } else {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CompleteCollectionBeforeClaim, null, 0));
                }
                break;
            case 4: // exploration
                MonsterCollectionExploration mce = mc.getExploration(region, session, group);
                if (mce != null && mce.getEndDate().isExpired()) {
                    mc.removeExploration(mce);
                    chr.write(UserLocal.collectionRecordMessage(mce.getPosition(), mce.getValue(false)));
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CollectionCompletionRewardSuccess, null, 0));
                } else {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.TryAgainInAMoment, null, 0));
                }
                break;
            default:
                log.warn("Unhandled MonsterCollectionCompleteRewardReq type " + reqType);
                chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.TryAgainInAMoment, null, 0));

        }
        chr.dispose(); // still required even if you send a collection result
    }

    @Handler(op = InHeader.USER_EFFECT_LOCAL)
    public static void handleUserEffectLocal(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        byte slv = inPacket.decodeByte();
        byte sendLocal = inPacket.decodeByte();

        int chrId = chr.getId();
        Field field = chr.getField();

        if (!chr.hasSkill(skillId)) {
            chr.getOffenseManager().addOffense(String.format("Character {%d} tried to use a skill {%d} they do not have.", chrId, skillId));
        }


        if (skillId == Evan.DRAGON_FURY) {
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showDragonFuryEffect(skillId, slv, 0, true)));

        } else if (skillId == Warrior.FINAL_PACT) {
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showFinalPactEffect(skillId, slv, 0, true)));

        } else if (skillId == WildHunter.CALL_OF_THE_HUNTER) {
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showCallOfTheHunterEffect(skillId, slv, 0, chr.isLeft(), chr.getPosition().getX(), chr.getPosition().getY())));

        } else if (skillId == Kaiser.VERTICAL_GRAPPLE || skillId == AngelicBuster.GRAPPLING_HEART) { // 'Grappling Hook' Skills
            int chrPositionY = inPacket.decodeInt();
            Position ropeConnectDest = inPacket.decodePositionInt();
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showVerticalGrappleEffect(skillId, slv, 0, chrPositionY, ropeConnectDest.getX(), ropeConnectDest.getY())));

        } else if (skillId == 15001021/*TB  Flash*/ || skillId == Shade.FOX_TROT) { // 'Flash' Skills
            Position origin = inPacket.decodePositionInt();
            Position dest = inPacket.decodePositionInt();
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showFlashBlinkEffect(skillId, slv, 0, origin.getX(), origin.getY(), dest.getX(), dest.getY())));

        } else if (SkillConstants.isSuperNovaSkill(skillId)) { // 'SuperNova' Skills
            Position chrPosition = inPacket.decodePositionInt();
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showSuperNovaEffect(skillId, slv, 0, chrPosition.getX(), chrPosition.getY())));

        } else if (SkillConstants.isUnregisteredSkill(skillId)) { // 'Unregistered' Skills
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showUnregisteredSkill(skillId, slv, 0, chr.isLeft())));

        } else if (SkillConstants.isHomeTeleportSkill(skillId)) {
            field.broadcastPacket(UserRemote.effect(chrId, Effect.skillUse(skillId, slv, 0)));

        } else if (skillId == BattleMage.DARK_SHOCK) {
            Position origin = inPacket.decodePositionInt();
            Position dest = inPacket.decodePositionInt();
            field.broadcastPacket(UserRemote.effect(chrId, Effect.showDarkShockSkill(skillId, slv, origin, dest)));
        } else {
            log.error(String.format("Unhandled Remote Effect Skill id %d", skillId));
            chr.chatMessage(String.format("Unhandled Remote Effect Skill:  id = %d", skillId));
        }
    }


    @Handler(op = InHeader.USER_FOLLOW_CHARACTER_REQUEST)
    public static void handleUserFollowCharacterRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int driverChrId = inPacket.decodeInt();
        short unk = inPacket.decodeShort();

        Char driverChr = field.getCharByID(driverChrId);
        if (driverChr == null) {
            return;
        }
        driverChr.write(WvsContext.setPassenserRequest(chr.getId()));
    }

    @Handler(op = InHeader.SET_PASSENGER_RESULT)
    public static void handleSetPassengerResult(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int requestorChrId = inPacket.decodeInt();
        boolean accepted = inPacket.decodeByte() != 0;
        Char requestorChr = field.getCharByID(requestorChrId);

        if (!accepted) {

            int errorType = inPacket.decodeInt();
            switch (errorType) {

            }

        } else {
            requestorChr.write(UserPacket.followCharacter(chr.getId(), false, new Position()));

        }
    }

    @Handler(op = InHeader.QUICKSLOT_KEY_MAPPED_MODIFIED)
    public static void handleQuickslotKeyMappedModified(Char chr, InPacket inPacket) {
        final int length = GameConstants.QUICKSLOT_LENGTH;
        List<Integer> quickslotKeys = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            quickslotKeys.add(inPacket.decodeInt());
        }
        chr.setQuickslotKeys(quickslotKeys);
    }


}
