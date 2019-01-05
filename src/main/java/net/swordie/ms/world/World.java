package net.swordie.ms.world;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.alliance.AllianceResult;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;

import java.util.*;

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
    private Map<Integer, Alliance> alliances = new HashMap<>();
    private int partyIDCounter = 1;
    private boolean charCreateBlock;
    private boolean reboot;

    public World(int worldId, String name, int worldState, String worldEventDescription, int worldEventEXP_WSE,
                 int worldEventDrop_WSE, int boomUpEventNotice, int amountOfChannels, boolean starplanet, boolean reboot) {
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
        this.reboot = reboot;
    }

    public World(int worldId, String name, int amountOfChannels) {
        this(worldId, name, 0, "", 100, 100,
                0, amountOfChannels, false, false);
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

    public boolean isStarPlanet() {
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

    public List<Guild> getGuildsWithCriteria(int levMin, int levMax, int sizeMin, int sizeMax, int avgLevMin, int avgLevMax) {
        List<Guild> guilds = (List<Guild>) DatabaseManager.getObjListFromDB(Guild.class);
        for (int i = 0; i < guilds.size(); i++) {
            Guild g = guilds.get(i);
            //calculate average level of guild members
            int averageLevel = 0;
            for (int j = 0; j < g.getMembers().size(); j++) {
                averageLevel += g.getMembers().get(i).getLevel();
            }
            if (g.getReqLevel() == 0) {

            }
            if (levMin != 0 && levMin > g.getReqLevel() + 1) { //getReqLevel is automatically set to 0
                guilds.remove(g);
                continue;
            } else if (levMax != 0 && levMax < g.getReqLevel()) {
                guilds.remove(g);
                continue;
            } else if (sizeMin != 0 && sizeMin > g.getMembers().size()) {
                guilds.remove(g);
                continue;
            } else if (sizeMax != 0 && sizeMax < g.getMembers().size()) {
                guilds.remove(g);
                continue;
            } else if (avgLevMin != 0 && avgLevMin > averageLevel) {
                guilds.remove(g);
                continue;
            } else if (avgLevMax != 0 && avgLevMax < averageLevel) {
                guilds.remove(g);
                continue;
            }
        }
        return guilds;
    }

    public List<Guild> getGuildsByString(int searchType, boolean exactWord, String searchTerm) {
        List<Guild> guilds = (List<Guild>) DatabaseManager.getObjListFromDB(Guild.class);
        for (int i = 0; i < guilds.size(); i++) {
            Guild g = guilds.get(i);
            if (searchType == 2) { //guildName
                if (exactWord) {
                    if (!g.getName().equals(searchTerm)) {
                        guilds.remove(g);
                        continue;
                    }
                } else {
                    if (!g.getName().contains(searchTerm)) {
                        guilds.remove(g);
                        continue;
                    }
                }
            }
            if (searchType == 3) { //guildLeaderName
                if (exactWord) {
                    if (Char.getFromDBById(g.getLeaderID()).getName().equals(searchTerm)) {
                        guilds.remove(g);
                        continue;
                    }
                } else {
                    if (!Char.getFromDBById(g.getLeaderID()).getName().contains(searchTerm)) {
                        guilds.remove(g);
                        continue;
                    }
                }
            }
        }
        return guilds;
    }

    public Guild getGuildByID(int id) {
        Guild guild = getGuilds().get(id);
        if (guild == null) {
            guild = loadGuildFromDB(id);
            getGuilds().put(id, guild);
            if (guild.getAllianceID() != 0) {
                guild.setAlliance(getAlliance(guild.getAllianceID()));
            }
        }
        return guild;
    }

    public Guild getGuildByName(String name) {
        Guild guild = getGuilds().values().stream().filter(g -> g.getName().equals(name)).findAny().orElse(null);
        if (guild == null) {
            guild = (Guild) DatabaseManager.getObjFromDB(Guild.class, name);
            if (guild != null) {
                getGuilds().put(guild.getId(), guild);
                if (guild.getAllianceID() != 0) {
                    guild.setAlliance(getAlliance(guild.getAllianceID()));
                }
            }
        }
        return guild;
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

    public void broadcastPacket(OutPacket outPacket) {
        for(Channel channel : getChannels()) {
            channel.broadcastPacket(outPacket);
        }
    }

    public boolean isCharCreateBlock() {
        return charCreateBlock;
    }

    public void setCharCreateBlock(boolean charCreateBlock) {
        this.charCreateBlock = charCreateBlock;
    }

    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    public boolean isFull() {
        boolean full = true;
        for (Channel channel : getChannels()) {
            if (channel.getChars().size() < channel.MAX_SIZE) {
                full = false;
                break;
            }
        }
        return full;
    }

    public Map<Integer, Alliance> getAlliances() {
        return alliances;
    }

    private void addAlliance(Alliance ally) {
        getAlliances().put(ally.getId(), ally);
        // Initialize guilds to be the same instance as the ones we currently have
        Set<Guild> guilds = new HashSet<>();
        for (Guild guild : ally.getGuilds()) {
            Guild ourGuild = getGuildByID(guild.getId());
            ourGuild.setAlliance(ally);
            guilds.add(ourGuild);
        }
        ally.setGuilds(guilds);
    }

    public Alliance getAlliance(int id) {
        Alliance ally = getAlliances().getOrDefault(id, null);
        if (ally == null) {
            ally = (Alliance) DatabaseManager.getObjFromDB(Alliance.class, id);
            if (ally != null) {
                addAlliance(ally);
            }
        }
        return ally;
    }

    public Alliance getAlliance(String name) {
        Alliance ally = getAlliances().values().stream().filter(a -> a.getName().equals(name)).findAny().orElse(null);
        if (ally == null) {
            ally = (Alliance) DatabaseManager.getObjFromDB(Alliance.class, name);
            if (ally != null) {
                addAlliance(ally);
            }
        }
        return ally;
    }

    public void clearCache() {
        for (Channel channel : getChannels()) {
            channel.clearCache();
        }
    }
}
