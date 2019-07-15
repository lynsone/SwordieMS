package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.commands.AdminCommand;
import net.swordie.ms.client.character.commands.AdminCommands;
import net.swordie.ms.client.character.commands.Command;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.ChatUserType;
import net.swordie.ms.enums.GroupMessageType;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Util;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.swordie.ms.enums.ChatType.*;
import static net.swordie.ms.enums.InvType.EQUIP;
import static net.swordie.ms.enums.InvType.EQUIPPED;

public class ChatHandler {

    private static final Logger log = Logger.getLogger(ChatHandler.class);

    @Handler(op = InHeader.USER_CHAT)
    public static void handleUserChat(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt();
        String msg = inPacket.decodeString();
        if (msg.length() > 0 && msg.charAt(0) == '@') {
            if (msg.equalsIgnoreCase("@check")) {
                chr.dispose();
                Map<BaseStat, Integer> basicStats = chr.getTotalBasicStats();
                StringBuilder sb = new StringBuilder();
                List<BaseStat> sortedList = Arrays.stream(BaseStat.values()).sorted(Comparator.comparing(Enum::toString)).collect(Collectors.toList());
                for (BaseStat bs : sortedList) {
                    sb.append(String.format("%s = %d, ", bs, basicStats.getOrDefault(bs, 0)));
                }
                chr.chatMessage(Mob, String.format("X=%d, Y=%d, Stats: %s", chr.getPosition().getX(), chr.getPosition().getY(), sb));
                ScriptManagerImpl smi = chr.getScriptManager();
                // all but field
                smi.stop(ScriptType.Portal);
                smi.stop(ScriptType.Npc);
                smi.stop(ScriptType.Reactor);
                smi.stop(ScriptType.Quest);
                smi.stop(ScriptType.Item);

            }
        } else if (msg.charAt(0) == AdminCommand.getPrefix()) {
            boolean executed = false;
            String command = msg.split(" ")[0].replace("!", "");
            for (Class clazz : AdminCommands.class.getClasses()) {
                Command cmd = (Command) clazz.getAnnotation(Command.class);
                boolean matchingCommand = false;
                for (String name : cmd.names()) {
                    if (name.equalsIgnoreCase(command)
                            && chr.getUser().getAccountType().ordinal() >= cmd.requiredType().ordinal()) {
                        matchingCommand = true;
                        break;
                    }
                }
                if (matchingCommand) {
                    executed = true;
                    String[] split = null;
                    try {
                        AdminCommand adminCommand = (AdminCommand) clazz.getConstructor().newInstance();
                        Method method = clazz.getDeclaredMethod("execute", Char.class, String[].class);
                        split = msg.split(" ");
                        method.invoke(adminCommand, c.getChr(), split);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        chr.chatMessage("Exception: " + e.getCause().toString());
                        e.printStackTrace();
                    }
                }
            }
            if (!executed) {
                chr.chatMessage(Expedition, "Unknown command \"" + command + "\"");
            }
        } else {
            chr.getField().broadcastPacket(UserPacket.chat(chr.getId(), ChatUserType.User, msg,
                    false, 0, c.getWorldId()));
        }
    }

    @Handler(op = InHeader.WHISPER)
    public static void handleWhisper(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte type = inPacket.decodeByte();
        inPacket.decodeInt(); // tick
        String destName = inPacket.decodeString();
        Char dest = c.getWorld().getCharByName(destName);
        if (dest == null) {
            chr.chatMessage("Character not found.");
            return;
        }
        switch (type) {
            case 5: // /find command
                int fieldId = dest.getField().getId();
                int channel = dest.getClient().getChannel();
                if (channel != chr.getClient().getChannel()) {
                    chr.chatMessage("%s is in channel %s-%d.", dest.getName(), dest.getWorld().getName(), channel);
                } else {
                    String fieldString = StringData.getMapStringById(fieldId);
                    if (fieldString == null) {
                        fieldString = "Unknown field.";
                    }
                    chr.chatMessage("%s is at %s.", dest.getName(), fieldString);
                }
                break;
            case 68:
                break;
            case 6: // whisper
                String msg = inPacket.decodeString();
                dest.write(FieldPacket.whisper(chr.getName(), (byte) (c.getChannel() - 1), false, msg, false));
                chr.chatMessage(Whisper, String.format("%s<< %s", dest.getName(), msg));
                break;
        }

    }

    @Handler(op = InHeader.GROUP_MESSAGE)
    public static void handleGroupMessage(Char chr, InPacket inPacket) {
        byte type = inPacket.decodeByte(); // party = 1, alliance = 3
        byte idk2 = inPacket.decodeByte();
        int idk3 = inPacket.decodeInt(); // party id?
        String msg = inPacket.decodeString();
        if (msg.length() > 1000 || !Util.isValidString(msg)) {
            return;
        }
        switch (type) {
            case 0: // buddy
                // TODO
                break;
            case 1: // party
                if (chr.getParty() != null) {
                    chr.getParty().broadcast(FieldPacket.groupMessage(GroupMessageType.Party, chr.getName(), msg), chr);
                }
                break;
            case 2: // guild
                if (chr.getGuild() != null) {
                    chr.getGuild().broadcast(FieldPacket.groupMessage(GroupMessageType.Guild, chr.getName(), msg), chr);
                }
                break;
            case 3: // alliance
                if (chr.getGuild() != null && chr.getGuild().getAlliance() != null) {
                    chr.getGuild().getAlliance().broadcast(FieldPacket.groupMessage(GroupMessageType.Alliance, chr.getName(), msg), chr);
                }
                break;
            default:
                log.error("Unhandled group message type " + type);
        }
    }

}
