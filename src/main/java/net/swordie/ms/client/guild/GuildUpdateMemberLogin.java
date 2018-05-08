package net.swordie.ms.client.guild;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.GuildResultType;

/**
 * Created on 3/22/2018.
 */
public class GuildUpdateMemberLogin implements GuildResultInfo {

    private int guildID;
    private int charID;
    private boolean online;
    private boolean showBox;

    public GuildUpdateMemberLogin(int guildID, int charID, boolean online) {
        this.guildID = guildID;
        this.charID = charID;
        this.online = online;
        showBox = true;
    }

    public GuildUpdateMemberLogin(int guildID, int charID, boolean online, boolean showBox) {
        this.guildID = guildID;
        this.charID = charID;
        this.online = online;
        this.showBox = showBox;
    }

    @Override
    public GuildResultType getType() {
        return GuildResultType.UpdateMemberLogin;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(guildID);
        outPacket.encodeInt(charID);
        outPacket.encodeByte(online);
        outPacket.encodeByte(showBox);
    }
}
