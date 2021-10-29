package com.ning.netty.server.ddz.command.handlers;

import com.ning.netty.server.ddz.annotations.SupportCommands;
import com.ning.netty.server.ddz.command.Command;
import com.ning.netty.server.ddz.command.CommandHandler;
import com.ning.netty.server.ddz.command.CommandResponse;
import com.ning.netty.server.ddz.enums.SupportedCommand;

import static com.ning.netty.server.ddz.enums.SupportedCommand.LOGIN;
import static com.ning.netty.server.ddz.enums.SupportedCommand.LOGOUT;

@SupportCommands(values = {LOGIN, LOGOUT})
public class LoginCommandHandler implements CommandHandler {

    @Override
    public CommandResponse handle(Command command) {
        String clientId = command.getClientId();
        SupportedCommand supportedCommand = command.getCommand();
        if (LOGIN.equals(supportedCommand)) {
            login(clientId);
        } else if (LOGOUT.equals(supportedCommand)) {
            logout(clientId);
        }
        return new CommandResponse("", command);
    }

    private void logout(String clientId) {

    }

    private void login(String clientId) {

    }
}
