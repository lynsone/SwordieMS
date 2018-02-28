package net.netty;

import client.Client;
import connection.InPacket;
import enums.ChatMsgColour;
import handling.InHeader;
import handling.handlers.LoginHandler;
import handling.handlers.WorldHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import packet.WvsContext;

import java.io.IOException;

import static net.netty.NettyClient.CLIENT_KEY;


/**
 * Created by Tim on 2/28/2017.
 */
public class ChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyClient o = ctx.channel().attr(CLIENT_KEY).get();
//        if(!LoginAcceptor.channelPool.containsKey(o.getIP())) {
//            System.out.println("[Dropping currently unknown client]");
//            o.close();
//        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[ChannelHandler] | Channel inactive.");
        Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
        if(c != null && c.getChr() != null) {
            c.getChr().updateDB();
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
            InPacket inPacket = (InPacket) msg;
            short op = ((InPacket) msg).decodeShort();
            InHeader opHeader = InHeader.getInHeaderByOp(op);
            if(opHeader == null) {
                handleUnknown(inPacket, op);
                return;
            }
            if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(op))) {
                System.out.println("[In]\t| " + InHeader.getInHeaderByOp(op) + ", " + +op + "/" + Integer.toHexString(op) + "\t| " + inPacket);
            }
            switch(opHeader) {
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
                        c.getAccount().updateDB();
                    }
                    break;
                case USER_ACTIVATE_NICK_ITEM:
                    WorldHandler.handleUserActiveNickItem(c, inPacket);
                    break;
                case USER_ACTIVATE_DAMAGE_SKIN:
                    WorldHandler.handleUserActivateDanageSkin(c, inPacket);
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
                    inPacket.decodeInt(); // crc
                    int skillID = inPacket.decodeInt();
                    byte slv = inPacket.decodeByte();
                    System.out.println("SkillID: " + skillID);
                    c.getChr().chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
                    c.getChr().getJobHandler().handleSkill(c, skillID, slv, inPacket);
                    WvsContext.dispose(c.getChr());
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
                case USER_EMOTION:
                case USER_CALC_DAMAGE_STAT_SET_REQUEST:
                    break;
                // sorted from here ------------------
                case CHAR_LOGIN:
                    WorldHandler.handleCharLogin(c, inPacket);
                    break;
                case USER_HIT:
                    c.getChr().getJobHandler().handleHit(c, inPacket);
                    break;
                case USER_SELECT_NPC:
                    WorldHandler.handleUserSelectNpc(c, inPacket);
                    break;
                case USER_SCRIPT_MESSAGE_ANSWER:
                    WorldHandler.handleUserScriptMessageAnswer(c, inPacket);
                    break;
                case USER_FINAL_ATTACK_REQUEST:
                    WorldHandler.handleUserFinalAttackRequest(c, inPacket);
                    break;
                case USER_CHAT:
                    WorldHandler.handleUserChat(c, inPacket);
                    break;
                case USER_CHANGE_SLOT_POSITION_REQUEST:
                    WorldHandler.handleInventoryOperation(c, inPacket);
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
                case ZERO_TAG:
                    WorldHandler.handleZeroTag(c, inPacket);
                    break;
                case REQUEST_SET_BLESS_OF_DARKNESS:
                    WorldHandler.handleRequestSetBlessOfDarkness(c, inPacket);
                    break;
                case FUNC_KEY_MAPPED_MODIFIED:
                    WorldHandler.handleKeymapUpdateRequest(c, inPacket);
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
                case DROP_PICK_UP_REQUEST:
                    WorldHandler.handleDropPickUpRequest(c, inPacket);
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
                default:
                    handleUnknown(inPacket, op);
                    break;
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handleUnknown(InPacket inPacket, short opCode) {
        if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(opCode))) {
            System.out.println("Unhandled opcode " + opCode + "/0x" + Integer.toHexString(opCode) + ", packet " + inPacket);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof IOException) {
            System.err.println("Client forcibly closed the game.");
        } else {
            cause.printStackTrace();
        }
    }
}
