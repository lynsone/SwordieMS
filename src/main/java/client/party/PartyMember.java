package client.party;

import client.character.Char;

/**
 * Created on 3/19/2018.
 */
public class PartyMember {
    private Char chr;
    private int partyBossCharacterID;
    private TownPortal townPortal;

    // 4 + 13 + 4 + 4 + 4 + 4 + 4 + 4

    public PartyMember(Char chr) {
        this.chr = chr;
    }

    public int getCharID() {
        return chr.getId();
    }

    public String getCharName() {
        return chr.getName();
    }

    public short getJob() {
        return chr.getJob();
    }

    public short getSubSob() {
        return (short) chr.getAvatarData().getCharacterStat().getSubJob();
    }

    public int getLevel() {
        return chr.getLevel();
    }

    public boolean isOnline() {
        return chr.isOnline();
    }

    public Char getChr() {
        return chr;
    }

    public int getPartyBossCharacterID() {
        return partyBossCharacterID;
    }

    public void setPartyBossCharacterID(int partyBossCharacterID) {
        this.partyBossCharacterID = partyBossCharacterID;
    }

    public int getChannel() {
        return chr.getClient().getChannel();
    }

    public int getFieldID() {
        return chr.getFieldID();
    }

    public TownPortal getTownPortal() {
        return townPortal;
    }

    public void setTownPortal(TownPortal townPortal) {
        this.townPortal = townPortal;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartyMember && ((PartyMember) obj).getChr().equals(getChr());
    }
}
