package com.ning.netty.server.ddz.command.handlers;

import com.ning.netty.server.ddz.annotations.SupportCommands;
import com.ning.netty.server.ddz.command.Command;
import com.ning.netty.server.ddz.command.CommandHandler;
import com.ning.netty.server.ddz.command.CommandResponse;
import com.ning.netty.server.ddz.enums.SupportedCommand;

import static com.ning.netty.server.ddz.enums.SupportedCommand.*;

@SupportCommands(values = {LOGIN, LOGOUT})
public class LoginCommandHandler implements CommandHandler {

    @Override
    public CommandResponse handle(Command command) {
        String clientId = command.getClientId();
        SupportedCommand supportedCommand = command.getCommand();
        String response = null;
        if (LOGIN.equals(supportedCommand)) {
            response = login(clientId);
        } else if (LOGOUT.equals(supportedCommand)) {
            response = logout(clientId);
        }
        return new CommandResponse(response, command);
    }

    private String logout(String clientId) {
        return "logout success clientId=" + clientId;
    }

    private String login(String clientId) {
        return "login success clientId=" + clientId;
    }
}
