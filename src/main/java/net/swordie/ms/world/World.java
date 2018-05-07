package net.swordie.ms.world;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.connection.db.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 11/2/2017.
 */
public class World {
    //WORLDITEM struct

    private int worldId, worldState, worldEventEXP_WSE, worldEventDrop_WSE, boomUpEventNotice;
    private boolean starplanet;
    private String name, worldEventDescription;
    private List<Channel> channels;
    private Map<Integer, Party> parties = new HashMap<>();
    private Map<Integer, Guild> guilds = new HashMap<>();
    private int partyIDCounter = 1;

    public World(int worldId, String name, int worldState, String worldEventDescription, int worldEventEXP_WSE,
                 int worldEventDrop_WSE, int boomUpEventNotice, int amountOfChannels, boolean starplanet) {
        this.worldId = worldId;
        this.name = name;
        this.worldState = worldState;
        this.worldEventDescription = worldEventDescription;
        this.worldEventEXP_WSE = worldEventEXP_WSE;
        this.worldEventDrop_WSE = worldEventDrop_WSE;
        this.boomUpEventNotice = boomUpEventNotice;
        List<Channel> channelList = new ArrayList<>();
        for(int i = 1; i <= amountOfChannels; i++){
            channelList.add(new Channel(name, worldId, i));
        }
        this.channels = channelList;
        this.starplanet = starplanet;
    }

    public World(int worldId, String name, int amountOfChannels) {
        this(worldId, name, 0, "", 100, 100,
                0, amountOfChannels, false);
    }

    public int getWorldId() {
        return worldId;
    }

    public String getName() {
        return name;
    }

    public int getWorldState() {
        return worldState;
    }

    public int getWorldEventEXP_WSE() {
        return worldEventEXP_WSE;
    }

    public int getWorldEventDrop_WSE() {
        return worldEventDrop_WSE;
    }

    public int getBoomUpEventNotice() {
        return boomUpEventNotice;
    }

    public boolean isStarplanet() {
        return starplanet;
    }

    public String getWorldEventDescription() {
        return worldEventDescription;
    }

    public void setWorldEventDescription(String worldEventDescription) {
        this.worldEventDescription = worldEventDescription;
    }

    public Channel getChannelById(byte id) {
        return getChannels().stream().filter(c -> c.getChannelId() == id).findFirst().orElse(null);
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public ServerStatus getStatus() {
        return ServerStatus.NORMAL;
    }

    public Char getCharByName(String name) {
        for(Channel c : getChannels()) {
            Char chr = c.getCharByName(name);
            if(chr != null) {
                return chr;
            }
        }
        return null;
    }

    public Char getCharByID(int id) {
        for(Channel c : getChannels()) {
            Char chr = c.getCharById(id);
            if(chr != null) {
                return chr;
            }
        }
        return null;
    }

    public Map<Integer, Party> getParties() {
        return parties;
    }

    public void addParty(Party p) {
        int id = getPartyIdAndIncrement(); // sequential IDs should be fine here
        getParties().put(id, p);
        p.setId(id);
        if(p.getWorld() == null) {
            p.setWorld(this);
        }
    }

    public void removeParty(Party p) {
        getParties().remove(p.getId());
    }

    public Party getPartybyId(int partyID) {
        return getParties().get(partyID);
    }

    public Map<Integer, Guild> getGuilds() {
        return guilds;
    }

    public Guild getGuildByID(int id) {
        Guild g = getGuilds().get(id);
        return g == null ? loadGuildFromDB(id) : g;
    }

    public Guild loadGuildFromDB(int id) {
        return (Guild) DatabaseManager.getObjFromDB(Guild.class, id);
    }

    public int getPartyIdAndIncrement() {
        return partyIDCounter++;
    }

    public int getPartyIDCounter() {
        return partyIDCounter;
    }

    public void setPartyIDCounter(int partyIDCounter) {
        this.partyIDCounter = partyIDCounter;
    }

    public Account getAccountByID(int accID) {
        for(Channel c : getChannels()) {
            Account res = c.getAccountByID(accID);
            if(res != null) {
                return res;
            }
        }
        return null;
    }
}
