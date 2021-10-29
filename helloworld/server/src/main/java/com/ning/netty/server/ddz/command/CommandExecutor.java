package com.ning.netty.server.ddz.command;

public class CommandExecutor {

    public static CommandResponse execute(Command command) {
        return CommandHandlerManager.get(command.getCommand()).handle(command);
    }
}
