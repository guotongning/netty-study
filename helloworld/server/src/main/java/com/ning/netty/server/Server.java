package com.ning.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端启动类
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0-SNAPSHOT
 */
public class Server {
    public static void main(String[] args) {
        // 配置服务端
        // boss线程组，用于接受客户端连接。
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // worker线程组，用于进行 SocketChannel的数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group(bossGroup, workerGroup)//设置线程组
                    .channel(NioServerSocketChannel.class)//要被实例化的Channel为：NioServerSocketChannel
                    .handler(new LoggingHandler(LogLevel.INFO))//设置NioServerSocketChannel的处理器。
                    .childHandler(new ServerInitializer())//设置连入服务器的client的socket channel的处理器
                    .bind(8888)//绑定端口
                    .channel()//返回Channel
                    .closeFuture()//Channel关闭
                    .sync();//阻塞等待。
            //从 关闭channel到阻塞等待就是：启动服务端
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();//关闭
            workerGroup.shutdownGracefully();//关闭
        }
    }
}
