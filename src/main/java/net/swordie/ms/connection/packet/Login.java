package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.LoginType;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.connection.Packet;
import net.swordie.ms.world.Channel;
import net.swordie.ms.Server;
import net.swordie.ms.world.World;
import net.swordie.ms.util.FileTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public static OutPacket sendPing() {
        return new OutPacket(OutHeader.PING.getValue());
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

    public static OutPacket checkPasswordResult(boolean success, byte error, Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_PASSWORD_RESULT.getValue());

        if(success) {
            outPacket.encodeByte(LoginType.SUCCESS.getValue());
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeString(account.getUsername());
            outPacket.encodeInt(account.getId());
            outPacket.encodeByte(account.getGender());
            outPacket.encodeByte(account.getMsg2());
            outPacket.encodeInt(account.getAccountType());
            outPacket.encodeInt(account.getAge());
            outPacket.encodeByte(!account.hasCensoredNxLoginID());
            if(account.hasCensoredNxLoginID()) {
                outPacket.encodeString(account.getCensoredNxLoginID());
            }
            outPacket.encodeString(account.getUsername());
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
            outPacket.encodeLong(account.getCreationDate()); // account creation date
        } else{
            outPacket.encodeByte(error);
            outPacket.encodeByte(0); // these two aren't in ida, wtf
            outPacket.encodeInt(0);
        }

        return outPacket;
    }

    public static Packet sendWorldInformation() {
        // CLogin::OnWorldInformation
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION.getValue());

        World world = Server.getInstance().getWorlds().get(0);
        outPacket.encodeByte(world.getWorldId());
        outPacket.encodeString(world.getName());
        outPacket.encodeByte(world.getWorldState());
        outPacket.encodeString(world.getWorldEventDescription());
        outPacket.encodeShort(world.getWorldEventEXP_WSE());
        outPacket.encodeShort(world.getWorldEventDrop_WSE());
        outPacket.encodeByte(0); // no clue. Is not in kmst, but in 176 idb.
        outPacket.encodeByte(world.getChannels().size());
        for(Channel c : world.getChannels()) {
            outPacket.encodeString(c.getName());
            outPacket.encodeInt(c.getGaugePx());
            outPacket.encodeByte(c.getWorldId());
            outPacket.encodeByte(c.getChannelId());
            outPacket.encodeByte(c.isAdultChannel());
        }
        outPacket.encodeShort(0); //*(result._m_pStr + 311) = CInPacket::Decode2(iPacket_1);
        // if > 0, write a position (short x/y) and a string. Something with a login balloon with a message, I think?
        outPacket.encodeInt(0); // packet offset
        outPacket.encodeByte(false);
        // write int if true.
        // Int is used for CUILoginStart::SetViewWorldButtonMakeShining(v54); v52 = decode4, then v54 = *(v39 + 293);
        return outPacket;
    }

    public static Packet sendWorldInformationEnd() {
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION);

        outPacket.encodeInt(255);

        return outPacket;
    }

    public static Packet sendAccountInfo(Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.ACCOUNT_INFO_RESULT);

        outPacket.encodeByte(0); // succeed
        outPacket.encodeInt(account.getId());
        outPacket.encodeByte(account.getGender());
        outPacket.encodeByte(account.getGradeCode());
        outPacket.encodeInt(account.getAccountType());
        outPacket.encodeInt(account.getVipGrade());
//        outPacket.encodeInt(account.getAge());
        outPacket.encodeByte(account.getPurchaseExp());
        outPacket.encodeString(account.getUsername());
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
        // TODO handle server load here
        OutPacket outPacket = new OutPacket(OutHeader.SERVER_STATUS.getValue());
        World world = null;
        for(World w : Server.getInstance().getWorlds()) {
            if(w.getWorldId() == worldId) {
                world = w;
            }
        }
        if(world != null) {
            outPacket.encodeByte(world.getStatus().getValue());
        } else {
            outPacket.encodeByte(ServerStatus.BUSY.getValue());
        }
        outPacket.encodeByte(0); // ?

        return outPacket;
    }

    public static OutPacket sendCharacterList(Account account, byte worldId, byte channel, byte code) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_WORLD_RESULT);

        outPacket.encodeByte(code); //nDay
        outPacket.encodeString("normal"); // not sure what this stands for
        outPacket.encodeInt(1); // refCount
        outPacket.encodeByte(0); // bBurningEventBlock
        int reserved = 0;
        outPacket.encodeInt(reserved); //character locations
        FileTime.fromLong(0).encode(outPacket); //Timestamp
        for(int i = 0; i < reserved; i++) {
            FileTime ft = FileTime.fromLong(0);
            outPacket.encodeInt(ft.getLowDateTime());
            ft.encode(outPacket);
        }
        boolean isEdited = false;
        outPacket.encodeByte(isEdited); //edited characters
        List<Char> chars = new ArrayList<>(account.getCharacters());
        chars.sort(Comparator.comparingInt(Char::getId));
        int nSecond = chars.size();
        outPacket.encodeInt(nSecond); // nSecond
        for (Char chr : chars) {
            outPacket.encodeInt(chr.getId());
        }

        outPacket.encodeByte(chars.size());
        for(Char character : chars) {
            character.getAvatarData().encode(outPacket);
            outPacket.encodeByte(false); // family stuff, deprecated (v61 = &v2->m_abOnFamily.a[v59];)
            boolean hasRanking = character.getRanking() != null;
            outPacket.encodeByte(hasRanking);
            if(hasRanking) {
                character.getRanking().encode(outPacket);
            }
        }
        outPacket.encodeByte(account.getPicStatus().getVal()); // bLoginOpt
        outPacket.encodeByte(false); // bQuerySSNOnCreateNewCharacter
        outPacket.encodeInt(account.getCharacterSlots());
        outPacket.encodeInt(0); // buying char slots
        outPacket.encodeInt(-1); // nEventNewCharJob
        outPacket.encodeFT(new FileTime(System.currentTimeMillis()));
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
        if(type == LoginType.SUCCESS) {
            c.getAvatarData().encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket sendAuthResponse(int response) {
        OutPacket outPacket = new OutPacket(OutHeader.HEARTBEAT_RESPONSE);

        outPacket.encodeInt(response);

        return outPacket;
    }

    public static OutPacket selectCharacterResult(LoginType loginType, byte errorCode, int port, int characterId) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_CHARACTER_RESULT);

        outPacket.encodeByte(loginType.getValue());
        outPacket.encodeByte(errorCode);

        if(loginType == LoginType.SUCCESS) {
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
}
