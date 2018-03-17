package net.netty;

import client.Client;
import constants.ServerConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.crypto.MapleCrypto;
import org.apache.log4j.LogManager;
import packet.Login;

import java.util.HashMap;
import java.util.Map;

import static net.netty.NettyClient.CLIENT_KEY;

public class ChannelAcceptor implements Runnable{

    public Map<String, Channel> channelPool = new HashMap<>();
    public server.Channel channel;
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Override
    public void run() {
        // Taken from http://netty.io/wiki/user-guide-for-4.x.html

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline().addLast(new PacketDecoder(), new ChannelHandler(), new PacketEncoder());

                    byte[] siv = new byte[]{70, 114, 30, 82};
                    byte[] riv = new byte[]{82, 48, 25, 115};

                    Client c = new Client(ch, siv, riv);
                    // remove after debug stage
                    log.debug(String.format("Opened session with %s on channel %d", c.getIP(), channel.getChannelId()));
                    c.write(Login.sendConnect(riv, siv));

                    channelPool.put(c.getIP(), ch);

                    ch.attr(CLIENT_KEY).set(c);
                    ch.attr(Client.CRYPTO_KEY).set(new MapleCrypto());

                    c.sendPing();
                }
            });

            b.childOption(ChannelOption.TCP_NODELAY, true);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(channel.getPort()).sync();
            log.info(String.format("Channel %d listening on port %d", channel.getChannelId(), channel.getPort()));
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
