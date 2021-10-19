package com.ning.netty.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;


/**
 * 处理器
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1。0-SNAPSHOT
 */
public class NettyHttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        /*
         * 或者使用HttpRequestDecoder & HttpResponseEncoder
         */
        p.addLast(new HttpServerCodec());
        /*
         * 在处理POST消息体时需要加上
         */
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new NettyHttpServerHandler());
    }
}
