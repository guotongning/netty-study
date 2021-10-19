package com.ning.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器启动类
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0-SNAPSHOT
 */
public class NettyHttpServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);
    public static final int PORT = 8888;

    public static void main(String[] args) {
        // configure the server
        EventLoopGroup group = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.childOption(ChannelOption.TCP_NODELAY, true);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            b.group(group, worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyHttpServerInitializer());
            Channel channel = b.bind(PORT).sync().channel();
            logger.info("Netty http server listening on port " + PORT);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
