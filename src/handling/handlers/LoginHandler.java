package handling.handlers;

import client.Account;
import client.Client;
import client.character.AvatarLook;
import client.character.Char;
import client.character.CharacterStat;
import client.character.FuncKeyMap;
import client.character.items.Equip;
import client.character.skills.CharacterTemporaryStat;
import connection.InPacket;
import constants.ItemConstants;
import constants.JobConstants;
import constants.ServerConstants;
import enums.CharNameResult;
import enums.LoginType;
import handling.OutHeader;
import loaders.ItemData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import packet.Login;
import server.Channel;
import server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static enums.InvType.EQUIPPED;

/**
 * Created on 4/28/2017.
 */
public class LoginHandler {


    private static int id;

    public static void handleConnect(Client client, InPacket inPacket) {
        byte locale = inPacket.decodeByte();
        short version = inPacket.decodeShort();
        String minorVersion = inPacket.decodeString(1);
        if (locale != ServerConstants.LOCALE || version != ServerConstants.VERSION) {
            System.err.println("Client " + client.getIP() + " has an incorrect version.");
            client.close();
        }
    }

    public static void handleAuthServer(Client client, InPacket inPacket) {
        client.write(Login.sendAuthServer(false));
    }

    public static void handleClientStart(Client client, InPacket inPacket) {
        client.write(Login.sendStart());
    }

    public static void handlePong(Client c, InPacket inPacket) {
        return;
    }

    public static void handleLoginPassword(Client c, InPacket inPacket) {
        Connection connection = Server.getInstance().getDatabaseConnection();
        byte sid = inPacket.decodeByte();
        String password = inPacket.decodeString();
        String username = inPacket.decodeString();
        long mac = inPacket.decodeLong();
        int gameRoomClient = inPacket.decodeInt();
        byte idk = inPacket.decodeByte();
        int channel = inPacket.decodeInt();
        boolean success = true;
        byte result;
        Account account = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                success = password.equals(rs.getString("password"));
                int id = rs.getInt("id");
                result = success ? LoginType.SUCCESS.getValue() : LoginType.INVALID_PASSWORD.getValue();
                if (success) {
                    account = Account.getFromDBById(id);
                    Server.getInstance().getAccounts().add(account);
                    c.setAccount(account);
                }
            } else {
                result = LoginType.NOT_A_REGISTERED_ID.getValue();
                success = false;
            }
        } catch (SQLException e) {
            result = LoginType.HAVING_TROUBLE.getValue();
            e.printStackTrace();
        }

        c.write(Login.checkPasswordResult(success, result, account));
    }

    public static void handleWorldRequest(Client c, InPacket packet) {
        c.write(Login.sendWorldInformation());
        c.write(Login.sendWorldInformationEnd());
    }

    public static void handleServerStatusRequest(Client c, InPacket inPacket) {
        c.write(Login.sendWorldInformation());
        c.write(Login.sendWorldInformationEnd());
    }

    public static void handleWorldChannelsRequest(Client c, InPacket inPacket) {
        byte worldId = inPacket.decodeByte();
        c.write(Login.sendServerStatus(worldId));
    }

    public static void handleCharListRequest(Client c, InPacket inPacket) {
        byte somethingThatIsTwo = inPacket.decodeByte();
        byte worldId = inPacket.decodeByte();
        byte channel = (byte) (inPacket.decodeByte() + 1);
        byte code = 0; // success code

        c.setWorldId(worldId);
        c.setChannel(channel);
//        c.write(Login.sendAccountInfo(c.getAccount()));
        c.write(Login.sendCharacterList(c.getAccount(), worldId, channel, code));
    }

    public static void handleCheckCharName(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        CharNameResult code = CharNameResult.OK;
        if (name.toLowerCase().contains("virtual") || name.toLowerCase().contains("kernel")) {
            code = CharNameResult.INVALID_NAME;
        } else {
            Connection connection = Server.getInstance().getDatabaseConnection();
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("SELECT * FROM characterStats WHERE name = ?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    code = CharNameResult.ALREADY_IN_USE;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        c.write(Login.checkDuplicatedIDResult(name, code.getVal()));
    }

    public static void handleCreateChar(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        int keySettingType = inPacket.decodeInt();
        int eventNewCharSaleJob = inPacket.decodeInt();
        int curSelectedRace = inPacket.decodeInt();
        JobConstants.JobEnum job = JobConstants.LoginJob.getLoginJobById(curSelectedRace).getBeginJob();
        short curSelectedSubJob = inPacket.decodeShort();
        byte gender = inPacket.decodeByte();
        byte skin = inPacket.decodeByte();

        byte itemLength = inPacket.decodeByte();
        int[] items = new int[itemLength]; //face, hair, markings, skin, overall, top, bottom, cape, boots, weapon
        for (int i = 0; i < itemLength; i++) {
            items[i] = inPacket.decodeInt();
            System.out.println(items[i]);
        }
        System.out.println(curSelectedRace);

        Char chr = new Char(c.getAccount().getId(), name, keySettingType, eventNewCharSaleJob, job.getJobId(),
                curSelectedSubJob, gender, skin, items);
        // Start job specific handling ----------------------------------------------------------------
        if (curSelectedRace == 5) { //Mercedes
            chr.getAvatarData().getAvatarLook().setDrawElfEar(true);
        }
        if (curSelectedRace == 15) { //Zero
            chr.getAvatarData().setZeroAvatarLook(chr.getAvatarData().getAvatarLook().deepCopy());
            chr.getAvatarData().getAvatarLook().getHairEquips().remove(new Integer(1562000));
            chr.getAvatarData().getZeroAvatarLook().getHairEquips().remove(new Integer(1572000));
            chr.getAvatarData().getZeroAvatarLook().setWeaponId(1562000);
            chr.getAvatarData().getZeroAvatarLook().setGender(1);
            chr.getAvatarData().getZeroAvatarLook().setSkin(chr.getAvatarData().getAvatarLook().getSkin());
            chr.getAvatarData().getZeroAvatarLook().setFace(21290);
            chr.getAvatarData().getZeroAvatarLook().setHair(37623);
            chr.getAvatarData().getZeroAvatarLook().setZeroBetaLook(true);
            chr.getAvatarData().getCharacterStat().setLevel(100);
            chr.getAvatarData().getCharacterStat().setStr(300); //TODO give lv 100 zero proper stats
        }

        // End job specific handling ------------------------------------------------------------------

        chr.setFuncKeyMap(FuncKeyMap.getDefaultMapping());
//        chr.createInDB();
        chr.getAvatarData().getAvatarLook().setDemonSlayerDefFaceAcc(1012279);
        c.getAccount().addCharacter(chr);
//        chr.setAccId(c.getAccount().getId());
//        chr.updateDB();
        c.getAccount().updateDB();

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setCharacterId(chr.getId());
        cs.setCharacterIdForLog(chr.getId());
        cs.setPosMap(100000000);
        chr.updateDB();
        for (int i : chr.getAvatarData().getAvatarLook().getHairEquips()) {
            Equip equip = ItemData.getEquipDeepCopyFromId(i);
            if (equip != null && equip.getItemId() >= 1000000) {
                equip.setBagIndex(ItemConstants.getBodyPartFromItem(
                        equip.getItemId(), chr.getAvatarData().getAvatarLook().getGender()));
                chr.addItemToInventory(EQUIPPED, equip, true);
            }
        }
        if(curSelectedRace == 15) { // Zero hack for adding 2nd weapon (removing it in hairequips for zero look)
            Equip equip = ItemData.getEquipDeepCopyFromId(1562000);
            equip.setBagIndex(ItemConstants.getBodyPartFromItem(
                    equip.getItemId(), chr.getAvatarData().getAvatarLook().getGender()));
            chr.addItemToInventory(EQUIPPED, equip, true);
        }
        chr.getInventoryByType(EQUIPPED).updateDB();
        c.write(Login.createNewCharacterResult(LoginType.SUCCESS, chr));
    }

    public static void handleDeleteChar(Client c, InPacket inPacket) {
        if (handleAuthSecondPassword(c, inPacket)) {
            int charId = inPacket.decodeInt();
            Char chr = Char.getFromDBById(charId);
            chr.deleteFromDB();
            Account a = Account.getFromDBById(c.getAccount().getId());
            a.deleteCharacter(chr);
            c.write(Login.sendDeleteCharacterResult(charId, LoginType.SUCCESS));
        }
    }

    public static void handleClientError(Client c, InPacket inPacket) {
        c.close();
        if (inPacket.getData().length < 8) {
            System.err.println("Error: " + inPacket);
            return;
        }
        short type = inPacket.decodeShort();
        String type_str = "Unknown?!";
        if (type == 0x01) {
            type_str = "SendBackupPacket";
        } else if (type == 0x02) {
            type_str = "Crash Report";
        } else if (type == 0x03) {
            type_str = "Exception";
        }
        int errortype = inPacket.decodeInt();
        short data_length = inPacket.decodeShort();

        int idk = inPacket.decodeInt();

        short op = inPacket.decodeShort();

        OutHeader opcode = OutHeader.getOutHeaderByOp(op);
        System.err.printf("[Error %s] (%s / %d) Data: %s%n", errortype, opcode, op, inPacket);
        if(opcode == OutHeader.TEMPORARY_STAT_SET) {
            for (int i = 0; i < CharacterTemporaryStat.length; i++) {
                int mask = inPacket.decodeInt();
                for(CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                    if(cts.getPos() == i && (cts.getVal() & mask) != 0) {
                        System.err.printf("[Error %s] Contained stat %s%n", errortype, cts.toString());
                    }
                }
            }
        }
    }

    public static int getId() {
        return id;
    }

    public static void handleHeartbeatRequest(Client c, InPacket inPacket) {
        c.write(Login.sendAuthResponse(((int) OutHeader.HEARTBEAT_RESPONSE.getValue()) ^ inPacket.decodeInt()));
    }

    public static void handleCharSelectNoPic(Client c, InPacket inPacket) {
        inPacket.decodeBytes(2);
        int characterId = inPacket.decodeInt();
        String mac = inPacket.decodeString();
        String somethingElse = inPacket.decodeString();
        String pic = inPacket.decodeString();
        c.getAccount().setPic(pic);
        // Update in DB
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction t = session.beginTransaction();
        session.update(c.getAccount());
        t.commit();
        session.close();
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        c.write(Login.selectCharacterResult(LoginType.SUCCESS, (byte) 0, channel.getPort(), characterId));
    }

    public static void handleCharSelect(Client c, InPacket inPacket) {
        int characterId = inPacket.decodeInt();
        String name = inPacket.decodeString();
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        if (c.isAuthorized()) {
            Server.getInstance().getWorldById(worldId).getChannelById(worldId).addClientInTransfer(channelId, characterId, c);
            c.write(Login.selectCharacterResult(LoginType.SUCCESS, (byte) 0, channel.getPort(), characterId));
        }
    }

    public static boolean handleAuthSecondPassword(Client c, InPacket inPacket) {
        boolean success = false;
        String pic = inPacket.decodeString();
//        int userId = inPacket.decodeInt();
        // after this: 2 strings indicating pc info. Not interested in that rn
        if (c.getAccount().getPic().equals(pic)) {
            success = true;
        } else {
            c.write(Login.selectCharacterResult(LoginType.INVALID_PASSWORD, (byte) 0, 0, 0));
        }
        c.setAuthorized(success);
        return success;
    }
}
