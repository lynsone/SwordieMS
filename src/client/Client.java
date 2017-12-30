package client;

import client.character.Char;
import connection.OutPacket;
import io.netty.channel.Channel;
import net.netty.NettyClient;
import packet.Login;
import server.EventManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tim on 2/18/2017.
 */
public class Client extends NettyClient {
    private Char chr;
    private Lock lock;
    private Account account;
    private byte channel;
    private byte worldId;
    private boolean authorized;

    public Client(Channel channel, byte[] sendSeq, byte[] recvSeq) {
        super(channel, sendSeq, recvSeq);
        lock = new ReentrantLock(true);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Lock getLock() {
        return lock;
    }

    public void write(byte[] data) {
        write(new OutPacket(data));
    }

    public void sendPing() {
        write(Login.sendPing());
        EventManager.addEvent(this, "sendPing", 60000);
    }

    public Account getAccount() {
        return account;
    }

    public void setChannel(byte channel) {
        this.channel = channel;
    }

    public byte getChannel() {
        return channel;
    }

    public byte getWorldId() {
        return worldId;
    }

    public void setWorldId(byte worldId) {
        this.worldId = worldId;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }
}
