package net.swordie.ms.connection.netty;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.Packet;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.ChatHandler;
import net.swordie.ms.handlers.LoginHandler;
import net.swordie.ms.world.WorldHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.LogManager;

import java.io.IOException;

import static net.swordie.ms.connection.netty.NettyClient.CLIENT_KEY;


/**
 * Created by Tim on 2/28/2017.
 */
public class ChannelHandler extends ChannelInboundHandlerAdapter {

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        NettyClient o = ctx.channel().attr(CLIENT_KEY).get();
//        if(!LoginAcceptor.channelPool.containsKey(o.getIP())) {
//            System.out.println("[Dropping currently unknown client]");
//            o.close();
//        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("[ChannelHandler] | Channel inactive.");
        Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
        if(c != null && c.getChr() != null) {
            c.getChr().logout();
        } else {
            log.warn("[ChannelHandler] | Was not able to save character, data inconsistency may have occurred.");
        }
        NettyClient o = ctx.channel().attr(CLIENT_KEY).get();
        if (o != null) {
            o.close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
            Char chr = c.getChr();
            InPacket inPacket = (InPacket) msg;
            short op = ((InPacket) msg).decodeShort();
            InHeader opHeader = InHeader.getInHeaderByOp(op);
            if(opHeader == null) {
                handleUnknown(inPacket, op);
                return;
            }
            if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(op))) {
                log.debug(String.format("[In]\t| %s, %d/0x%s\t| %s", InHeader.getInHeaderByOp(op), op, Integer.toHexString(op).toUpperCase(), inPacket));
            }
            switch(opHeader) {
                case CONNECT_CHAT:
                    ChatHandler.handleConnect(c, inPacket);
                    break;
                case FRIEND_CHAT:
                    ChatHandler.handleFriendChat(c, inPacket);
                    break;
                case CONNECT:
                    LoginHandler.handleConnect(c, inPacket);
                    break;
                case USE_AUTH_SERVER:
                    LoginHandler.handleAuthServer(c, inPacket);
                    break;
                case CLIENT_START:
                    LoginHandler.handleClientStart(c, inPacket);
                    break;
                case WVS_CRASH_CALLBACK:
                    if(c != null && c.getAccount() != null) {
                        c.getChr().logout();
                    }
                    break;
                case USER_ACTIVATE_NICK_ITEM:
                    WorldHandler.handleUserActiveNickItem(c, inPacket);
                    break;
                case USER_REQUEST_CHANGE_MOB_ZONE_STATE:
                    WorldHandler.handleUserRequestChangeMobZoneState(c, inPacket);
                    break;
                case USER_TRUNK_REQUEST:
                    WorldHandler.handleUserTrunkRequest(c, inPacket);
                    break;
                case USER_PET_FOOD_ITEM_USE_REQUEST:
                    WorldHandler.handleUserPetFoodItemUseRequest(c, inPacket);
                    break;
                case USER_ITEM_SKILL_SOCKET_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserItemSkillSocketUpdateItemUseRequest(c, inPacket);
                    break;
                case USER_ITEM_SKILL_OPTION_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserItemSkillOptionUpdateItemUseRequest(c, inPacket);
                    break;
                case FRIEND_REQUEST:
                    WorldHandler.handleFriendRequest(c, inPacket);
                    break;
                case USER_GATHER_ITEM_REQUEST:
                    WorldHandler.handleUserGatherItemRequest(c, inPacket);
                    break;
                case USER_SORT_ITEM_REQUEST:
                    WorldHandler.handleUserSortItemRequest(c, inPacket);
                    break;
                case USER_SCRIPT_ITEM_USE_REQUEST:
                    WorldHandler.handleUserScriptItemUseRequest(c, inPacket);
                    break;
                case USER_STAT_CHANGE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserStatChangeItemUseRequest(c, inPacket);
                    break;
                case USER_STAT_CHANGE_ITEM_CANCEL_REQUEST:
                    WorldHandler.handleUserStatChangeItemCancelRequest(chr ,inPacket);
                    break;
                case USER_MACRO_SYS_DATA_MODIFIED:
                    WorldHandler.handleUserMacroSysDataModified(c, inPacket);
                    break;
                case USER_ACTIVATE_DAMAGE_SKIN:
                    WorldHandler.handleUserActivateDamageSkin(c, inPacket);
                    break;
                case USER_ACTIVATE_PET_REQUEST:
                    WorldHandler.handleUserActivatePetRequest(c, inPacket);
                    break;
                case USER_ACTIVATE_EFFECT_ITEM:
                    WorldHandler.handleUserActivateEffectItem(c, inPacket);
                    break;
                case EVENT_UI_REQ:
                    WorldHandler.handleEventUiReq(c, inPacket);
                    break;
                case USER_ACTIVATE_DAMAGE_SKIN__PREMIUM:
                    WorldHandler.handleUserActivateDamageSkinPremium(c, inPacket);
                    break;
                case FISHING_INFO:
                    break;
                case PARTY_INVITABLE_SET:
                    WorldHandler.handlePartyInvitableSet(c, inPacket);
                    break;
                case USER_SKILL_USE_REQUEST:
                    WorldHandler.handleUserSkillUseRequest(c, inPacket);
                    break;
                case USER_SKILL_CANCEL_REQUEST:
                    WorldHandler.handleTemporaryStatResetRequest(c, inPacket);
                    break;
                case PONG:
                    LoginHandler.handlePong(c, inPacket);
                    break;
                case LOGIN_PASSWORD:
                    LoginHandler.handleLoginPassword(c, inPacket);
                    break;
                case SERVERSTATUS_REQUEST:
                    LoginHandler.handleServerStatusRequest(c, inPacket);
                    break;
                case WORLD_STATUS_REQUEST:
                    LoginHandler.handleWorldChannelsRequest(c, inPacket);
                    break;
                case WORLD_LIST_REQUEST:
                case WORLD_LIST_RE_REQUEST:
                case WORLD_LIST_REQ:
                    LoginHandler.handleWorldRequest(c, inPacket);
                    break;
                case CHARLIST_REQUEST:
                    LoginHandler.handleCharListRequest(c, inPacket);
                    break;
                case AUTH_SECOND_PASSWORD:
                    LoginHandler.handleAuthSecondPassword(c, inPacket);
                    break;
                case CHECK_DUPLICATE_ID:
                    LoginHandler.handleCheckCharName(c, inPacket);
                    break;
                case CREATE_CHAR:
                    LoginHandler.handleCreateChar(c, inPacket);
                    break;
                case DELETE_CHAR:
                    LoginHandler.handleDeleteChar(c, inPacket);
                    break;
                case HEARTBEAT_REQUEST:
                    LoginHandler.handleHeartbeatRequest(c, inPacket);
                    break;
                case CLIENT_ERROR:
                    LoginHandler.handleClientError(c, inPacket);
                    break;
                case CHAR_SELECT_NO_PIC:
                    LoginHandler.handleCharSelectNoPic(c, inPacket);
                    break;
                case CHAR_SELECT:
                    LoginHandler.handleCharSelect(c, inPacket);
                    break;
                case UPDATE_CLIENT_ENVIRONMENT:
                case WVS_SET_UP_STEP:
                case LOCALE:
                case USER_CALC_DAMAGE_STAT_SET_REQUEST:
                    break;
                case CHAR_LOGIN:
                    WorldHandler.handleCharLogin(c, inPacket);
                    break;
                case USER_HIT:
                    c.getChr().getJobHandler().handleHit(c, inPacket);
                    break;
                case USER_EMOTION:
                    WorldHandler.handleUserEmotion(c, inPacket);
                    break;
                case USER_SELECT_NPC:
                    WorldHandler.handleUserSelectNpc(c, inPacket);
                    break;
                case USER_SCRIPT_MESSAGE_ANSWER:
                    WorldHandler.handleUserScriptMessageAnswer(c, inPacket);
                    break;
                case WHISPER:
                    WorldHandler.handleWhisper(c, inPacket);
                    break;
                case USER_SOUL_EFFECT_REQUEST:
                    WorldHandler.handleUserSoulEffectRequest(c, inPacket);
                    break;
                case USER_WEAPON_TEMP_ITEM_OPTION_REQUEST:
                    WorldHandler.handleUserWeaponTempItemOptionRequest(chr, inPacket);
                    break;
                case USER_FINAL_ATTACK_REQUEST:
                    WorldHandler.handleUserFinalAttackRequest(c, inPacket);
                    break;
                case USER_CHAT:
                    WorldHandler.handleUserChat(c, inPacket);
                    break;
                case USER_SHOP_REQUEST:
                    WorldHandler.handleUserShopRequest(c, inPacket);
                    break;
                case USER_CHANGE_SLOT_POSITION_REQUEST:
                    WorldHandler.handleUserChangeSlotPositionRequest(c, inPacket);
                    break;
                case MONSTER_BOOK_MOB_INFO:
                    WorldHandler.handleMonsterBookMobInfo(chr, inPacket);
                    break;
                case USER_CONSUME_CASH_ITEM_USE_REQUEST:
                    WorldHandler.handleUserConsumeCashItemUseRequest(c, inPacket);
                    break;
                case USER_ITEM_OPTION_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserItemOptionUpgradeItemUseRequest(c, inPacket);
                    break;
                case USER_ADDITIONAL_OPT_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserAdditionalOptUpgradeItemUseRequest(c, inPacket);
                    break;
                case USER_ITEM_RELEASE_REQUEST:
                    WorldHandler.handleUserItemReleaseRequest(c, inPacket);
                    break;
                case USER_QUEST_REQUEST:
                    WorldHandler.handleUserQuestRequest(c, inPacket);
                    break;
                case ZERO_TAG:
                    WorldHandler.handleZeroTag(c, inPacket);
                    break;
                case PARTY_REQUEST:
                    WorldHandler.handlePartyRequest(c, inPacket);
                    break;
                case PARTY_RESULT:
                    WorldHandler.handlePartyResult(c, inPacket);
                    break;
                case GUILD_REQUEST:
                    WorldHandler.handleGuildRequest(c, inPacket);
                    break;
                case REQUEST_SET_BLESS_OF_DARKNESS:
                    WorldHandler.handleRequestSetBlessOfDarkness(c, inPacket);
                    break;
                case LOAD_ACCOUNT_ID_OF_CHARACTER_FRIEND_REQUEST:
                    WorldHandler.handleLoadAccountIDOfCharacterFriendRequest(c, inPacket);
                    break;
                case FUNC_KEY_MAPPED_MODIFIED:
                    WorldHandler.handleKeymapUpdateRequest(c, inPacket);
                    break;
                case SUMMONED_MOVE:
                    WorldHandler.handleSummonedMove(chr, inPacket);
                    break;
                case SUMMONED_REMOVE:
                    WorldHandler.handleSummonedRemove(c, inPacket);
                    break;
                case SUMMONED_ATTACK:
                    WorldHandler.handleSummonedAttack(c, inPacket);
                    break;
                case SUMMONED_HIT:
                    WorldHandler.handleSummonedHit(c, inPacket);
                    break;
                case USER_ABILITY_UP_REQUEST:
                    WorldHandler.handleUserAbilityUpRequest(c, inPacket);
                    break;
                case USER_ABILITY_MASS_UP_REQUEST:
                    WorldHandler.handleUserAbilityMassUpRequest(c, inPacket);
                    break;
                case USER_CHANGE_STAT_REQUEST:
                    WorldHandler.handleUserChangeStatRequest(c, inPacket);
                    break;
                case USER_SKILL_UP_REQUEST:
                    WorldHandler.handleUserSkillUpRequest(c, inPacket);
                    break;
                case USER_MELEE_ATTACK:
                    WorldHandler.handleMeleeAttack(c, inPacket);
                    break;
                case USER_SHOOT_ATTACK:
                    WorldHandler.handleShootAttack(c, inPacket);
                    break;
                case USER_MAGIC_ATTACK:
                    WorldHandler.handleMagicAttack(c, inPacket);
                    break;
                case USER_NON_TARGET_FORCE_ATOM_ATTACK:
                    WorldHandler.handleNonTargetForceAtomAttack(c, inPacket);
                    break;
                case USER_BODY_ATTACK:
                    WorldHandler.handleBodyAttack(c, inPacket);
                    break;
                case USER_CHARACTER_INFO_REQUEST:
                    WorldHandler.handleUserCharacterInfoRequest(c, inPacket);
                    break;
                case USER_PORTAL_SCROLL_USE_REQUEST:
                    WorldHandler.handleUserPortalScrollUseRequest(c, inPacket);
                    break;
                case USER_TRANSFER_FIELD_REQUEST:
                    WorldHandler.handleChangeFieldRequest(c, inPacket);
                    break;
                case USER_PROTECT_BUFF_DIE_ITEM_REQUEST:
                    WorldHandler.handleUserProtectBuffDieItemRequest(chr, inPacket);
                    break;
                case USER_PORTAL_SCRIPT_REQUEST:
                    WorldHandler.handleUserPortalScriptRequest(c, inPacket);
                    break;
                case USER_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserUpgradeItemUseRequest(c, inPacket);
                    break;
                case USER_UPGRADE_ASSIST_ITEM_USE_REQUEST:
                    WorldHandler.handleUserUpgradeAssistItemUseRequest(c, inPacket);
                    break;
                case USER_DROP_MONEY_REQUEST:
                    WorldHandler.handleUserDropMoneyRequest(c, inPacket);
                    break;
                case USER_TRANSFER_CHANNEL_REQUEST:
                    WorldHandler.handleChangeChannelRequest(c, inPacket);
                    break;
                case USER_MOVE:
                    WorldHandler.handleMove(c, inPacket);
                    break;
                case USER_FORCE_ATOM_COLLISION:
                    WorldHandler.handleForceAtomCollision(c, inPacket);
                    break;
                case LIKE_POINT:
                    WorldHandler.handleLikePoint(c, inPacket);
                    break;
                case REQUEST_ARROW_PLATER_OBJ:
                    WorldHandler.handleRequestArrowPlatterObj(c, inPacket);
                    break;
                case USER_FLAME_ORB_REQUEST:
                    WorldHandler.handleUserFlameOrbRequest(c, inPacket);
                    break;
                case CREATE_PSYCHIC_LOCK:
                    WorldHandler.handleCreatePsychicLock(c, inPacket);
                    break;
                case RELEASE_PSYCHIC_LOCK:
                    WorldHandler.handleReleasePsychicLock(c, inPacket);
                    break;
                case CREATE_KINESIS_PSYCHIC_AREA:
                    WorldHandler.handleCreateKinesisPsychicArea(c, inPacket);
                    break;
                case RELEASE_PSYCHIC_AREA:
                    WorldHandler.handleReleasePsychicArea(c, inPacket);
                    break;
                case MOB_MOVE:
                    WorldHandler.handleMoveMob(c, inPacket);
                    break;
                case MOB_APPLY_CTRL:
//                    WorldHandler.handleMobApplyCtrl(c, inPacket);
                    break;
                case USER_GROWTH_HELPER_REQUEST:
                    WorldHandler.handleUserGrowthRequestHelper(c, inPacket);
                    break;
                case SOCKET_CREATE_REQUEST:
                    WorldHandler.handleSocketCreateRequest(c, inPacket);
                    break;
                case NEBULITE_INSERT_REQUEST:
                    WorldHandler.handleNebuliteInsertRequest(c, inPacket);
                    break;
                case DROP_PICK_UP_REQUEST:
                    WorldHandler.handleDropPickUpRequest(c, inPacket);
                    break;
                case PARTY_MEMBER_CANDIDATE_REQUEST:
                    WorldHandler.handlePartyMemberCandidateRequest(c, inPacket);
                    break;
                case PARTY_CANDIDATE_REQUEST:
                    WorldHandler.handlePartyCandidateRequest(c, inPacket);
                    break;
                case BATTLE_RECORD_ON_OFF_REQUEST:
                    WorldHandler.handleBattleRecordOnOffRequest(c, inPacket);
                    break;
                case USER_SIT_REQUEST:
                    WorldHandler.handleCancelChair(c, inPacket);
                    break;
                case USER_PORTABLE_CHAIR_SIT_REQUEST:
                    WorldHandler.handleShowChair(c, inPacket);
                    break;
                case RW_MULTI_CHARGE_CANCEL_REQUEST:
                    WorldHandler.handleRWMultiChargeCancelRequest(c, inPacket);
                    break;
                case FOX_MAN_ACTION_SET_USE_REQUEST:
                    WorldHandler.handleFoxManActionSetUseRequest(c, inPacket);
                    break;
                case USER_CREATE_AURA_BY_GRENADE:
                    WorldHandler.handleUserCreateAuraByGrenade(c, inPacket);
                    break;
                case USER_CREATE_HOLIDOM_REQUEST:
                    WorldHandler.handleUserCreateHolidomRequest(c, inPacket);
                    break;
                case SUMMONED_SKILL:
                    WorldHandler.handleSummonedSkill(c, inPacket);
                    break;
                case REACTOR_CLICK:
                    WorldHandler.handleReactorClick(c, inPacket);
                    break;
                case REACTOR_RECT_IN_MOB:
                    WorldHandler.handleReactorRectInMob(c, inPacket);
                    break;
                case MOB_EXPLOSION_START:
                    WorldHandler.handleMobExplosionStart(c, inPacket);
                    break;
                case NPC_MOVE:
                    WorldHandler.handleNpcMove(chr, inPacket);
                    break;
                case REQUEST_DEC_COMBO:
                    WorldHandler.handleRequestDecCombo(c, inPacket);
                    break;
                case USER_EQUIPMENT_ENCHANT_WITH_SINGLE_UI_REQUEST:
                    WorldHandler.handleUserEquipmentEnchantWithSingleUIRequest(c, inPacket);
                    break;
                case USER_SKILL_LEARN_ITEM_USE_REQUEST:
                    WorldHandler.handleUserLearnItemUseRequest(c, inPacket);
                    break;
                case USER_REQUEST_FLYING_SWORD_START:
                    WorldHandler.handleUserRequestFlyingSwordStart(c, inPacket);
                    break;
                case USER_REQUEST_STEAL_SKILL_LIST:
                    WorldHandler.handleUserRequestStealSkillList(c, inPacket);
                    break;
                case USER_REQUEST_STEAL_SKILL_MEMORY:
                    WorldHandler.handleUserRequestStealSkillMemory(c, inPacket);
                    break;
                case USER_REQUEST_SET_STEAL_SKILL_SLOT:
                    WorldHandler.handleUserRequestSetStealSkillSlot(c, inPacket);
                    break;
                case USER_EX_ITEM_UPGRADE_ITEM_USE_REQUEST:
                    WorldHandler.handleUserExItemUpgradeItemUseRequest(c, inPacket);
                    break;
                case USER_REQUEST_CHARACTER_POTENTIAL_SKILL_RAND_SET_UI:
                    WorldHandler.handleUserRequestCharacterPotentialSkillRandSetUi(chr, inPacket);
                    break;
                case USER_CASH_PET_PICK_UP_ON_OFF_REQUEST:
                    WorldHandler.handleUserCashPetPickUpOnOffRequest(chr, inPacket);
                    break;
                case RUNE_STONE_USE_REQ:
                    WorldHandler.handleRuneStoneUseRequest(c, inPacket);
                    break;
                case RUNE_STONE_SKILL_REQ:
                    WorldHandler.handleRuneStoneSkillRequest(c, inPacket);
                    break;
                case SET_SON_OF_LINKED_SKILL_REQUEST:
                    WorldHandler.handleSetSonOfLinkedSkillRequest(chr, inPacket);
                    break;
                case USER_MEMORIAL_CUBE_OPTION_REQUEST:
                    WorldHandler.handleUserMemorialCubeOptionRequest(chr, inPacket);
                    break;
                case FAMILIAR_ADD_REQUEST:
                    WorldHandler.handleFamiliarAddRequest(chr, inPacket);
                    break;
                default:
                    handleUnknown(inPacket, op);
                    break;
            }
        }finally {
            ReferenceCountUtil.release(msg);
            if (msg instanceof Packet) {
                ((Packet) msg).release();
            }
        }
    }

    private void handleUnknown(InPacket inPacket, short opCode) {
        if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(opCode))) {
            log.warn(String.format("Unhandled opcode %s/0x%s, packet %s", opCode, Integer.toHexString(opCode).toUpperCase(), inPacket));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof IOException) {
            log.info("Client forcibly closed the game.");
        } else {
            cause.printStackTrace();
        }
    }
}
