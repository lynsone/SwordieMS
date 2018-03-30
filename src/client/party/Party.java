package client.party;

import client.character.Char;
import client.field.Field;
import connection.OutPacket;
import enums.FieldInstanceType;
import loaders.FieldData;
import packet.WvsContext;
import server.World;

import java.util.*;
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
    private Map<Integer, Field> fields = new HashMap<>();

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
        outPacket.encodeArr(new byte[50]);
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

    public static Party createNewParty(boolean appliable, String name, World world) {
        Party party = new Party();
        party.setAppliable(appliable);
        party.setName(name);
        party.setWorld(world);
        world.addParty(party);
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

    public Map<Integer, Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        getFields().put(field.getId(), field);
    }

    /**
     * Clears the current Fields. Will return any character that is currently on any of the Fields to the Field's return field.
     */
    public void clearFieldInstances() {
        Set<Char> chrs = new HashSet<>();
        for(Field f : getFields().values()) {
            chrs.addAll(f.getChars());
        }
        for(Char chr : chrs) {
            chr.setFieldInstanceType(FieldInstanceType.CHANNEL);
            int returnMap = chr.getField().getReturnMap();
            if(returnMap != 999999999 && returnMap != chr.getField().getReturnMap()) {
                Field field = chr.getClient().getChannelInstance().getField(returnMap);
                chr.warp(field);
            }
        }
        getFields().clear();
    }

    /**
     * Returns the Field corresponding to the provided fieldID. If there is none, creates one.
     * @param fieldID The Field's id.
     * @return The Field corresponding to the given id.
     */
    public Field getOrCreateFieldById(int fieldID) {
        if (getFields().containsKey(fieldID)) {
            return getFields().get(fieldID);
        } else {
            Field field = FieldData.getFieldCopyById(fieldID);
            addField(field);
            return field;
        }
    }

    public boolean isPartyMember(Char chr) {
        return getPartyMemberByID(chr.getId()) != null;
    }

    public void updatePartyMemberInfoByChr(Char chr) {
        if(!isPartyMember(chr)) {
            return;
        }
        updateFull();
    }
}
