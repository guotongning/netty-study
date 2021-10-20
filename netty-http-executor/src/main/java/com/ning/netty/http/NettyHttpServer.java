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
        // 1. 初始化用于Acceptor的 main线程池 以及用于I/O工作的 slave线程池
        EventLoopGroup group = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //https://www.cnblogs.com/itdriver/p/8149913.html 《Netty4.x 源码实战系列（一）： 深入理解ServerBootstrap 与 Bootstrap》
            // 2. 初始化ServerBootstrap实例，此实例是netty服务端应用开发的入口。
            ServerBootstrap b = new ServerBootstrap();
            b.group(group, worker)// 3. 通过ServerBootstrap的group方法设置初始化的线程池
                    .channel(NioServerSocketChannel.class)// 4. 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel
                    .handler(new LoggingHandler(LogLevel.INFO))// 5. 设置ServerSocketChannel的处理器
                    .childHandler(new NettyHttpServerInitializer())// 6. 设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的"主战场"
                    .option(ChannelOption.SO_BACKLOG, 1024)// 7. 配置ServerSocketChannel的选项
                    .childOption(ChannelOption.TCP_NODELAY, true)// 8. 配置子通道也就是SocketChannel的选项
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .bind(PORT).sync().channel()// 9. 绑定端口
                    .closeFuture().sync(); // 10. 监听端口
            logger.info("Netty http server listening on port " + PORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
