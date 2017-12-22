package server;

import constants.ServerConstants;

/**
 * Created on 11/2/2017.
 */
public class Channel {
    //CHANNELITEM struct
    private int port;
    private String name;
    private int gaugePx, worldId, channelId;
    private boolean adultChannel;

    private Channel(String name, int gaugePx, World world, int channelId, boolean adultChannel) {
        this.name = name;
        this.gaugePx = gaugePx;
        this.worldId = world.getWorldId();
        this.channelId = channelId;
        this.adultChannel = adultChannel;
        this.port = ServerConstants.LOGIN_PORT + 100 + channelId;
    }

    public Channel(World world, int channelId) {
        this(world.getName() + "-" + channelId, 0, world, channelId, false);
    }

    public Channel(String worldName, int worldId, int channelId) {
        this.name = worldName + "-" + channelId;
        this.gaugePx = 0;
        this.worldId = worldId;
        this.channelId = channelId;
        this.adultChannel = false;
        this.port = ServerConstants.LOGIN_PORT + 100 + channelId;
    }

    public String getName() {
        return name;
    }

    public int getGaugePx() {
        return gaugePx;
    }

    public void setGaugePx(int gaugePx) {
        this.gaugePx = gaugePx;
    }

    public int getWorldId() {
        return worldId;
    }

    public int getChannelId() {
        return channelId;
    }

    public boolean isAdultChannel() {
        return adultChannel;
    }

    public void setAdultChannel(boolean adultChannel) {
        this.adultChannel = adultChannel;
    }

    public int getPort() {
        return port;
    }
}
