package com.ning.netty.server.ddz;

/**
 * TODO
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public interface CommandHandler {
    String handle(Command command);
}
