package com.ning.netty.server;

import com.ning.netty.server.ddz.command.Command;
import com.ning.netty.server.ddz.command.CommandExecutor;
import com.ning.netty.server.ddz.command.CommandResponse;
import com.ning.netty.server.ddz.enums.SupportedCommand;
import io.netty.channel.*;

import java.util.Date;

/**
 * 服务端处理类
 * 服务相关的设置的代码写完之后，我们再来编写主要的业务代码。
 * 使用Netty编写业务层的代码，我们需要继承ChannelInboundHandlerAdapter 或SimpleChannelInboundHandler类，在这里顺便说下它们两的区别吧。
 * 继承SimpleChannelInboundHandler类之后，会在接收到数据后会自动release掉数据占用的Bytebuffer资源。
 * 并且继承该类需要指定数据格式。
 * 而继承ChannelInboundHandlerAdapter则不会自动释放，需要手动调用ReferenceCountUtil.release()等方法进行释放。
 * 继承该类不需要指定数据格式。 所以在这里，个人推荐服务端继承ChannelInboundHandlerAdapter，手动进行释放，防止数据未处理完就自动释放了。
 * 而且服务端可能有多个客户端进行连接，并且每一个客户端请求的数据格式都不一致，这时便可以进行相应的处理。
 * 客户端根据情况可以继承SimpleChannelInboundHandler类。好处是直接指定好传输的数据格式，就不需要再进行格式的转换了。
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0-SNAPSHOT
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    public static final String END = "\r\n";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("Welcome to nicholas's JJ World!" + END);
        ctx.write("It is " + new Date() + " now." + END);
        ctx.flush();
    }

    /**
     * 业务逻辑处理
     *
     * @param ctx 上下文
     * @param msg 收到的消息
     * @throws Exception 异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        CommandResponse response = handleMessage(msg);
        ChannelFuture f = ctx.write(response.getResponse() + END);
        if (response.isClose()) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private CommandResponse handleMessage(String msg) {
        String[] params = msg.split(":");
        if (params.length == 0) {
            return new CommandResponse(SupportedCommand.UNSUPPORTED_COMMAND.getResponse(), null);
        }
        return CommandExecutor.execute(new Command(params[0], SupportedCommand.code2Enum(params[1])));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
        t.printStackTrace();
        ctx.close();
    }
}
