package net.netty;

import client.Client;
import client.jobs.JobManager;
import connection.InPacket;
import handling.InHeader;
import handling.handlers.LoginHandler;
import handling.handlers.WorldHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import packet.WvsContext;

import static net.netty.NettyClient.CLIENT_KEY;

import java.io.IOException;


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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
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
                case CRASH:
                    if(c != null && c.getAccount() != null) {
                        c.getAccount().updateDB();
                    }
                    break;
                case SKILL:
                    inPacket.decodeInt(); // crc
                    c.getChr().getJobHandler().handleSkill(c, inPacket);
                    WvsContext.dispose(c, c.getChr());
                    break;
                case TEMPORARY_STAT_RESET_REQUEST:
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
                case USE_BUTTON:
                case LOAD:
                case LOCALE:
                    break;
                case CHAR_LOGIN:
                    WorldHandler.handleCharLogin(c, inPacket);
                    break;
                case CHAT:
                    WorldHandler.handleChat(c, inPacket);
                    break;
                case INVENTORY_OPERATION:
                    WorldHandler.handleInventoryOperation(c, inPacket);
                    break;
                case KEYMAP_UPDATE_REQUEST:
                    WorldHandler.handleKeymapUpdateRequest(c, inPacket);
                    break;
                case ABILITY_POINT_DISTRIBUTE:
                    WorldHandler.handleAbilityPointDistribute(c, inPacket);
                    break;
                case AUTO_ABILITY_POINT_DISTRIBUTE:
                    WorldHandler.handleAutoAbilityPointDistribute(c, inPacket);
                    break;
                case SKILL_RECORD_UPDATE_REQUEST:
                    WorldHandler.handleSkillRecordUpdateRequest(c, inPacket);
                    break;
                case MELEE_ATTACK:
                    WorldHandler.handleMeleeAttack(c, inPacket);
                    break;
                case MAGIC_ATTACK:
                    WorldHandler.handleMagicAttack(c, inPacket);
                    break;
                case BODY_ATTACK:
                    WorldHandler.handleBodyAttack(c, inPacket);
                    break;
                case CHANGE_FIELD_REQUEST:
                    WorldHandler.handleChangeFieldRequest(c, inPacket);
                    break;
                case MOVE:
                    WorldHandler.handleMove(c, inPacket);
                    break;
                case MOVE_LIFE:
                    WorldHandler.handleMoveLife(c, inPacket);
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
