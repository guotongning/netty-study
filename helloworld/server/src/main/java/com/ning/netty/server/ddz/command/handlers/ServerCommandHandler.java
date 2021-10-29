package com.ning.netty.server.ddz.command.handlers;

import com.ning.netty.server.ddz.annotations.SupportCommands;
import com.ning.netty.server.ddz.command.Command;
import com.ning.netty.server.ddz.command.CommandHandler;
import com.ning.netty.server.ddz.command.CommandResponse;
import com.ning.netty.server.ddz.enums.SupportedCommand;

import static com.ning.netty.server.ddz.enums.SupportedCommand.*;

@SupportCommands(values = {BYE, UNSUPPORTED_COMMAND})
public class ServerCommandHandler implements CommandHandler {
    @Override
    public CommandResponse handle(Command command) {
        SupportedCommand supportedCommand = command.getCommand();
        if (BYE.equals(supportedCommand)) {
            return new CommandResponse(BYE.getResponse(), command);
        }
        return new CommandResponse(UNSUPPORTED_COMMAND.getResponse(), command);
    }
}
