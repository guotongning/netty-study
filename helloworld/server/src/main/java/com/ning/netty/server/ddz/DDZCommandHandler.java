package com.ning.netty.server.ddz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DDZCommandHandler implements CommandHandler {

    private static final Map<String, Room> LOGIN_CLIENTS = new HashMap<>();

    @Override
    public String handle(Command command) {
        String clientId = command.getClientId();
        SupportedCommandEnum commandEnum = command.getCommand();
        switch (commandEnum) {
            case LOGIN:
                Room room = LOGIN_CLIENTS.get(clientId);
                if (room != null) {
                    return "Please do not log in again!";
                }
                LOGIN_CLIENTS.put(clientId, new Room());
                return "login success!";
            case ALL_BRAND:
                return "1235";
            case SEND_BRAND:
                return "1231241231";
            case PLAY_BRAND:
                return "dsfasdf";
            default:
                return "Unsupported command!";
        }
    }
}
