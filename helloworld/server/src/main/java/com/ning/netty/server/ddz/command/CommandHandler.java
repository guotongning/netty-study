package com.ning.netty.server.ddz.command;

@FunctionalInterface
public interface CommandHandler {
    CommandResponse handle(Command command);
}
