package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.LoginType;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.Channel;
import net.swordie.ms.Server;
import net.swordie.ms.world.World;
import net.swordie.ms.util.FileTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Tim on 2/28/2017.
 */
public class Login {

    public static OutPacket sendConnect(byte[] siv, byte[] riv) {
        OutPacket oPacket = new OutPacket();

        // version (short) + MapleString (short + char array size) + local IV (int) + remote IV (int) + locale (byte)
        // 0xE
        oPacket.encodeShort((short) 15);
        oPacket.encodeShort(ServerConstants.VERSION);
        oPacket.encodeString(ServerConstants.MINOR_VERSION);
        oPacket.encodeArr(siv);
        oPacket.encodeArr(riv);
        oPacket.encodeByte(ServerConstants.LOCALE);
        oPacket.encodeByte(false);
        return oPacket;
    }

    public static OutPacket sendAliveReq() {
        return new OutPacket(OutHeader.ALIVE_REQ.getValue());
    }

    public static OutPacket sendAuthServer(boolean useAuthServer) {
        OutPacket outPacket = new OutPacket(OutHeader.AUTH_SERVER.getValue());
        outPacket.encodeByte(useAuthServer);
        return outPacket;
    }

    public static OutPacket sendStart() {
        OutPacket outPacket = new OutPacket(OutHeader.CLIENT_START.getValue());

        outPacket.encodeByte(true);

        return outPacket;
    }

    public static OutPacket checkPasswordResult(boolean success, LoginType msg, Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_PASSWORD_RESULT.getValue());

        if(success) {
            outPacket.encodeByte(LoginType.Success.getValue());
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeString(account.getName());
            outPacket.encodeInt(account.getId());
            outPacket.encodeByte(account.getGender());
            outPacket.encodeByte(account.getMsg2());
            outPacket.encodeInt(account.getAccountType().getVal());
            outPacket.encodeInt(account.getAge());
            outPacket.encodeByte(!account.hasCensoredNxLoginID());
            if(account.hasCensoredNxLoginID()) {
                outPacket.encodeString(account.getCensoredNxLoginID());
            }
            outPacket.encodeString(account.getName());
            outPacket.encodeByte(account.getpBlockReason());
            outPacket.encodeByte(0); // idk
            outPacket.encodeLong(account.getChatUnblockDate());
            outPacket.encodeLong(account.getChatUnblockDate());
            outPacket.encodeInt(account.getCharacterSlots() + 3);
            JobConstants.encode(outPacket);
            outPacket.encodeByte(account.getGradeCode());
            outPacket.encodeInt(-1);
            outPacket.encodeByte(0); // idk
            outPacket.encodeByte(0); // ^
            outPacket.encodeFT(account.getCreationDate());
        } else if (msg == LoginType.Blocked) {
            outPacket.encodeByte(msg.getValue());
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeByte(0); // nReason
            outPacket.encodeFT(account.getBanExpireDate());
        } else{
            outPacket.encodeByte(msg.getValue());
            outPacket.encodeByte(0); // these two aren't in ida, wtf
            outPacket.encodeInt(0);
        }

        return outPacket;
    }

    public static OutPacket checkPasswordResultForBan(Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_PASSWORD_RESULT);

        outPacket.encodeByte(LoginType.BlockedNexonID.getValue());
        outPacket.encodeByte(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeFT(account.getBanExpireDate());

        return outPacket;
    }

    public static OutPacket sendWorldInformation(Set<Tuple<Position, String>> stringInfos) {
        // CLogin::OnWorldInformation
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION.getValue());

        World world = Server.getInstance().getWorlds().get(0);
        outPacket.encodeByte(world.getWorldId());
        outPacket.encodeString(world.getName());
        outPacket.encodeByte(world.getWorldState());
        outPacket.encodeString(world.getWorldEventDescription());
        outPacket.encodeShort(world.getWorldEventEXP_WSE());
        outPacket.encodeShort(world.getWorldEventDrop_WSE());
        outPacket.encodeByte(world.isCharCreateBlock());
        outPacket.encodeByte(world.getChannels().size());
        for(Channel c : world.getChannels()) {
            outPacket.encodeString(c.getName());
            outPacket.encodeInt(c.getGaugePx());
            outPacket.encodeByte(c.getWorldId());
            outPacket.encodeByte(c.getChannelId());
            outPacket.encodeByte(c.isAdultChannel());
        }
        if (stringInfos == null) {
            outPacket.encodeShort(0);
        } else {
            outPacket.encodeShort(stringInfos.size());
            for (Tuple<Position, String> stringInfo : stringInfos) {
                outPacket.encodePosition(stringInfo.getLeft());
                outPacket.encodeString(stringInfo.getRight());
            }
        }
        outPacket.encodeInt(0); // some offset
        outPacket.encodeByte(false); // connect with star planet stuff, not interested
        return outPacket;
    }

    public static OutPacket sendWorldInformationEnd() {
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION);

        outPacket.encodeInt(255);

        return outPacket;
    }

    public static OutPacket sendAccountInfo(Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.ACCOUNT_INFO_RESULT);

        outPacket.encodeByte(0); // succeed
        outPacket.encodeInt(account.getId());
        outPacket.encodeByte(account.getGender());
        outPacket.encodeByte(account.getGradeCode());
        outPacket.encodeInt(account.getAccountType().getVal());
        outPacket.encodeInt(account.getVipGrade());
//        outPacket.encodeInt(account.getAge());
        outPacket.encodeByte(account.getPurchaseExp());
        outPacket.encodeString(account.getName());
        outPacket.encodeByte(account.getnBlockReason());
        outPacket.encodeByte(0); // ?
        outPacket.encodeLong(account.getChatUnblockDate());
        outPacket.encodeString(account.getCensoredNxLoginID());
        outPacket.encodeLong(0);
        outPacket.encodeInt(28);
        outPacket.encodeLong(0);
        outPacket.encodeString(""); //v25 = CInPacket::DecodeStr(iPacket_1, &nAge);
        JobConstants.encode(outPacket);
        outPacket.encodeByte(0);
        outPacket.encodeInt(-1);

        return outPacket;
    }

    public static OutPacket sendServerStatus(byte worldId) {
        OutPacket outPacket = new OutPacket(OutHeader.SERVER_STATUS.getValue());
        World world = null;
        for(World w : Server.getInstance().getWorlds()) {
            if(w.getWorldId() == worldId) {
                world = w;
            }
        }
        if(world != null && !world.isFull()) {
            outPacket.encodeByte(world.getStatus().getValue());
        } else {
            outPacket.encodeByte(ServerStatus.BUSY.getValue());
        }
        outPacket.encodeByte(0); // ?

        return outPacket;
    }

    public static OutPacket selectWorldResult(Account account, byte code, String specialServer,
                                  boolean burningEventBlock) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_WORLD_RESULT);

        outPacket.encodeByte(code);
        outPacket.encodeString(specialServer);
        outPacket.encodeInt(account.getTrunk().getSlotCount());
        outPacket.encodeByte(burningEventBlock); // bBurningEventBlock
        int reserved = 0;
        outPacket.encodeInt(reserved); // Reserved size
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME)); //Reserved timestamp
        for(int i = 0; i < reserved; i++) {
            // not really interested in this
            FileTime ft = FileTime.fromType(FileTime.Type.ZERO_TIME);
            outPacket.encodeInt(ft.getLowDateTime());
            ft.encode(outPacket);
        }
        boolean isEdited = false;
        outPacket.encodeByte(isEdited); // edited characters
        List<Char> chars = new ArrayList<>(account.getCharacters());
        chars.sort(Comparator.comparingInt(Char::getId));
        int orderSize = chars.size();
        outPacket.encodeInt(orderSize);
        for (Char chr : chars) {
            // order of chars
            outPacket.encodeInt(chr.getId());
        }

        outPacket.encodeByte(chars.size());
        for(Char chr : chars) {
            chr.getAvatarData().encode(outPacket);
            outPacket.encodeByte(false); // family stuff, deprecated (v61 = &v2->m_abOnFamily.a[v59];)
            boolean hasRanking = chr.getRanking() != null && !JobConstants.isGmJob(chr.getJob());
            outPacket.encodeByte(hasRanking);
            if (hasRanking) {
                chr.getRanking().encode(outPacket);
            }
        }
        outPacket.encodeByte(account.getPicStatus().getVal()); // bLoginOpt
        outPacket.encodeByte(false); // bQuerySSNOnCreateNewCharacter
        outPacket.encodeInt(account.getCharacterSlots());
        outPacket.encodeInt(0); // buying char slots
        outPacket.encodeInt(-1); // nEventNewCharJob
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME));
        outPacket.encodeByte(0); // nRenameCount
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket checkDuplicatedIDResult(String name, byte code) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_DUPLICATED_ID_RESULT);

        outPacket.encodeString(name);
        outPacket.encodeByte(code);

        return outPacket;
    }

    public static OutPacket createNewCharacterResult(LoginType type, Char c) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_NEW_CHARACTER_RESULT);

        outPacket.encodeByte(type.getValue());
        if(type == LoginType.Success) {
            c.getAvatarData().encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket sendAuthResponse(int response) {
        OutPacket outPacket = new OutPacket(OutHeader.PRIVATE_SERVER_PACKET);

        outPacket.encodeInt(response);

        return outPacket;
    }

    public static OutPacket selectCharacterResult(LoginType loginType, byte errorCode, int port, int characterId) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_CHARACTER_RESULT);

        outPacket.encodeByte(loginType.getValue());
        outPacket.encodeByte(errorCode);

        if(loginType == LoginType.Success) {
            byte[] server = new byte[]{8, 31, 99, ((byte) 141)};
            outPacket.encodeArr(server);
            outPacket.encodeShort(port);

            byte[] chatServer = new byte[]{8, 31, 99, ((byte) 133)};
            // chat stuff
            outPacket.encodeArr(chatServer);
            outPacket.encodeShort(ServerConstants.CHAT_PORT);

            outPacket.encodeInt(characterId);
            outPacket.encodeByte(0);
            outPacket.encodeInt(0); // ulArgument
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeByte(0);
        }

        return outPacket;
    }

    public static OutPacket sendDeleteCharacterResult(int charId, LoginType loginType) {
        OutPacket outPacket = new OutPacket(OutHeader.DELETE_CHARACTER_RESULT);

        outPacket.encodeInt(charId);
        outPacket.encodeByte(loginType.getValue());


        return outPacket;
    }

    public static OutPacket sendRecommendWorldMessage(int nWorldID, String nMsg) {
        OutPacket oPacket = new OutPacket(OutHeader.RECOMMENDED_WORLD_MESSAGE);
        oPacket.encodeByte(1);
        oPacket.encodeInt(nWorldID);
        oPacket.encodeString(nMsg);
        return oPacket;
    }
}
