package handling.handlers;

import client.Client;
import client.character.Char;
import client.character.commands.AdminCommand;
import client.character.commands.AdminCommands;
import client.character.commands.Command;
import client.field.Field;
import connection.InPacket;
import enums.InvType;
import packet.Stage;
import packet.WvsContext;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 12/14/2017.
 */
public class WorldHandler {
    public static void handleCharLogin(Client c, InPacket inPacket) {
        int worldId = inPacket.decodeInt();
        int charId = inPacket.decodeInt(); // does not properly get sent to us?
        Char chr = Char.getFromDBById(2);
        chr.setClient(c);
        c.setChr(chr);
        Field field = new Field(100000000);
        c.write(Stage.setField(chr, field, c.getChannel(), false, 0, true, false,
        (byte) 0, false, 100, null, true, -1));


    }

    public static void handleChat(Client c, InPacket inPacket) {
        inPacket.decodeInt();
        String msg = inPacket.decodeString();
        if(msg.length() > 0 && msg.charAt(0) == '@') {
            if(msg.equalsIgnoreCase("@check")) {
                WvsContext.dipose(c, c.getChr());
            }
        } else if(msg.charAt(0) == AdminCommand.getPrefix()) {
            for(Class clazz : AdminCommands.class.getClasses()) {
                try {
                    Command o = (Command) clazz.getConstructor().newInstance();
                    String[] split = msg.split(" ");
                    String[] fin = new String[split.length - 1];
                    System.arraycopy(split, 2, fin, 1, fin.length - 1);
                    o.execute(fin);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handleInventoryOperation(Client c, InPacket inPacket) {
        inPacket.decodeInt();
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        short oldPos = inPacket.decodeShort();
        short newPos = inPacket.decodeShort();
        short quantity = inPacket.decodeShort();

        c.write(WvsContext.inventoryOperation(c.getChr(), true, oldPos, newPos, invType, quantity, false));

    }
}
