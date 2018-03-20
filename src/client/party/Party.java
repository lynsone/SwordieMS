package client.party;

import client.character.Char;
import connection.OutPacket;
import packet.WvsContext;
import server.World;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created on 3/19/2018.
 */
public class Party {
    private int id;
    private PartyMember[] partyMembers = new PartyMember[6];
    private boolean appliable;
    private String name;
    private int partyLeaderID;
    private World world;
    private Char applyingChar;

    public boolean isAppliable() {
        return appliable;
    }

    public void setAppliable(boolean appliable) {
        this.appliable = appliable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartyMember[] getPartyMembers() {
        return partyMembers;
    }

    public boolean isFull() {
        return Arrays.stream(getPartyMembers()).noneMatch(Objects::isNull);
    }

    public boolean isEmpty() {
        return Arrays.stream(getPartyMembers()).allMatch(Objects::isNull);
    }

    public void encode(OutPacket outPacket) {
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getCharID() : 0);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeString(pm != null ? pm.getCharName() : "", 13);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getJob() : 0);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getSubSob() : 0);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getLevel() : 0);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getChannel() - 1 : 0);
        }
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null && pm.isOnline() ? 1 : 0);
        }
//        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(getPartyLeaderID());
//        }
        // end PARTYMEMBER struct
        for(PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getFieldID() : 0);
        }
        for(PartyMember pm : partyMembers) {
            if(pm != null && pm.getTownPortal() != null) {
                pm.getTownPortal().encode(outPacket);
            } else {
                new TownPortal().encode(outPacket);
            }
        }
        outPacket.encodeByte(isAppliable() && !isFull());
        outPacket.encodeString(getName());
        outPacket.encodeArrByte(new byte[50]);
    }

    public int getPartyLeaderID() {
        return partyLeaderID;
    }

    public void setPartyLeaderID(int partyLeaderID) {
        this.partyLeaderID = partyLeaderID;
    }

    /**
     * Adds a {@link Char} to this Party. Will do nothing if this Party is full.
     * @param chr The Char to add.
     */
    public void addPartyMember(Char chr) {
        if(isFull()) {
            return;
        }
        PartyMember pm = new PartyMember(chr);
        if(isEmpty()) {
            setPartyLeaderID(chr.getId());
            chr.getClient().getWorld().addParty(this);
        }
        PartyMember[] partyMembers = getPartyMembers();
        PartyJoinResult pjr = new PartyJoinResult();
        pjr.party = this;
        pjr.joinerName = chr.getName();
        for(int i = 0; i < partyMembers.length; i++) {
            if(partyMembers[i] == null) {
                partyMembers[i] = pm;
                chr.setParty(this);
                break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TownPortal getTownPortal() {
        PartyMember pm = Arrays.stream(getPartyMembers()).filter(Objects::nonNull)
                .filter(p -> p.getTownPortal() != null)
                .findFirst().orElse(null);
        return pm != null ? pm.getTownPortal() : new TownPortal();
    }

    public PartyMember getPartyLeader() {
        return Arrays.stream(getPartyMembers()).filter(p -> p != null && p.getCharID() == getPartyLeaderID()).findFirst().orElse(null);
    }

    public boolean hasCharAsLeader(Char chr) {
        return getPartyLeader().getChr().equals(chr);
    }

    public void disband() {
        LeavePartyResult lpr = new LeavePartyResult();
        lpr.party = this;
        lpr.partyExists = false;
        lpr.leaver = getPartyLeader();
        for(PartyMember pm : getPartyMembers()) {
            if(pm == null) {
                continue;
            }
            pm.getChr().setParty(null);
            if(pm.isOnline()) {
                pm.getChr().write(WvsContext.partyResult(lpr));
            }
        }
        for (int i = 0; i < getPartyMembers().length; i++) {
            getPartyMembers()[i] = null;
        }
        getWorld().removeParty(this);
        setWorld(null);
    }

    public List<PartyMember> getOnlineMembers() {
        return Arrays.stream(getPartyMembers()).filter(pm -> pm != null && pm.isOnline()).collect(Collectors.toList());
    }

    public List<PartyMember> getMembers() {
        return Arrays.stream(getPartyMembers()).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void updateFull() {
        for(PartyMember pm : getOnlineMembers()) {
            UpdatePartyResult upr = new UpdatePartyResult();
            upr.party = this;
            pm.getChr().write(WvsContext.partyResult(upr));
        }
    }

    public PartyMember getPartyMemberByID(int charID) {
        return Arrays.stream(getPartyMembers()).filter(p -> p != null && p.getCharID() == charID).findFirst().orElse(null);
    }

    public void broadcast(OutPacket outPacket) {
        for(PartyMember pm : getOnlineMembers()) {
            pm.getChr().write(outPacket);
        }
    }

    public void broadcast(OutPacket outPacket, Char exceptChar) {
        for(PartyMember pm : getOnlineMembers()) {
            if(!pm.getChr().equals(exceptChar)) {
                pm.getChr().write(outPacket);
            }
        }
    }

    public void removePartyMember(PartyMember partyMember) {
        for (int i = 0; i < getPartyMembers().length; i++) {
            PartyMember pm = getPartyMembers()[i];
            if(pm != null && pm.equals(partyMember)) {
                pm.getChr().setParty(null);
                getPartyMembers()[i] = null;
                break;
            }
        }
    }

    public void expel(int expelID) {
        LeavePartyResult lpr = new LeavePartyResult();
        lpr.party = this;
        lpr.leaver = getPartyMemberByID(expelID);
        lpr.partyExists = true;
        lpr.wasExpelled = true;
        broadcast(WvsContext.partyResult(lpr));
        removePartyMember(lpr.leaver);
        updateFull();
    }

    public static Party createNewParty(boolean appliable, String name) {
        Party party = new Party();
        party.setId(10);
        party.setAppliable(appliable);
        party.setName(name);
        return party;
    }

    public int getAvgLevel() {
        Collection<PartyMember> partyMembers = getMembers();
        return partyMembers.stream()
                .mapToInt(pm -> pm.getChr().getLevel())
                .sum() / partyMembers.size();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public Char getApplyingChar() {
        return applyingChar;
    }

    public void setApplyingChar(Char applyingChar) {
        this.applyingChar = applyingChar;
    }
}
