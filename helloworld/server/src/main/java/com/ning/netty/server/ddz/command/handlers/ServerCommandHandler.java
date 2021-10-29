package com.ning.netty.server.ddz.command.handlers;

import com.ning.netty.server.ddz.annotations.SupportCommands;
import com.ning.netty.server.ddz.command.Command;
import com.ning.netty.server.ddz.command.CommandHandler;
import com.ning.netty.server.ddz.command.CommandResponse;
import com.ning.netty.server.ddz.enums.SupportedCommand;

@SupportCommands(values = {SupportedCommand.BYE})
public class ServerCommandHandler implements CommandHandler {
    @Override
    public CommandResponse handle(Command command) {

        return null;
    }
}
