package client.character;

import connection.OutPacket; /**
 * Created on 3/18/2018.
 */
public class Guild {
    private String name;
    private short guildMarkBg;
    private int guildMarkBgColor;
    private short guildMark;
    private int guildMarkColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void encodeForRemote(OutPacket outPacket) {
        outPacket.encodeString(getName());
        outPacket.encodeShort(getGuildMarkBg());
        outPacket.encodeByte(getGuildMarkBgColor());
        outPacket.encodeShort(getGuildMark());
        outPacket.encodeByte(getGuildMarkColor());
    }

    public short getGuildMarkBg() {
        return guildMarkBg;
    }

    public void setGuildMarkBg(short guildMarkBg) {
        this.guildMarkBg = guildMarkBg;
    }

    public int getGuildMarkBgColor() {
        return guildMarkBgColor;
    }

    public void setGuildMarkBgColor(int guildMarkBgColor) {
        this.guildMarkBgColor = guildMarkBgColor;
    }

    public short getGuildMark() {
        return guildMark;
    }

    public void setGuildMark(short guildMark) {
        this.guildMark = guildMark;
    }

    public int getGuildMarkColor() {
        return guildMarkColor;
    }

    public void setGuildMarkColor(int guildMarkColor) {
        this.guildMarkColor = guildMarkColor;
    }
}
