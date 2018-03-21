package client.guild;

import client.character.Char;
import connection.OutPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 3/18/2018.
 */
public class Guild {
    private int id;
    private String name;
    private GuildMember guildLeader;
    private List<Char> requestors = new ArrayList<>();
    private List<String> gradeNames = new ArrayList<>();
    private List<GuildMember> guildMembers = new ArrayList<>();
    private short guildMarkBg;
    private int guildMarkBgColor;
    private short guildMark;
    private int guildMarkColor;
    private int maxMembers;
    private String notice;
    private int points;
    private int seasonPoints;
    private int allianceID;
    private int level;
    private int rank;
    private int ggp;
    // Start GUILDSETTING struct
    private byte setState;
    private int joinSetting;
    private int reqLevel;
    // End GUILDSETTING struct


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

    public List<GuildMember> getGuildMembers() {
        return guildMembers;
    }

    public void addGuildMember(GuildMember guildMember) {
        getGuildMembers().add(guildMember);
    }

    public void addGuildMember(Char chr) {
        addGuildMember(new GuildMember(chr));
    }

    public void removeGuildMember(GuildMember guildMember) {
        getGuildMembers().remove(guildMember);
    }

    public void removeGuildMember(Char chr) {
        getGuildMembers().remove(getGuildMemberByChar(chr));
    }

    public GuildMember getGuildMemberByChar(Char chr) {
        return getGuildMembers().stream().filter(gm -> gm.getChr().equals(chr)).findAny().orElse(null);
    }

    public GuildMember getGuildLeader() {
        return guildLeader;
    }

    public void setGuildLeader(GuildMember guildLeader) {
        this.guildLeader = guildLeader;
    }

    public boolean isGuildMember(GuildMember gm) {
        return getGuildLeader().equals(gm);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGuildMemberIDs() {
        List<Integer> res = new ArrayList<>();
        getGuildMembers().forEach(gm -> res.add(gm.getChr().getId()));
        return res;
    }
}
