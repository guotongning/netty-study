package com.ning.netty.server.ddz.enums;

public enum SupportedCommand {
    LOGIN("login"),
    LOGOUT("logout"),
    BYE("bye"),
    ROOM_LIST("room list"),
    CREATE_ROOM("create room"),
    DELETE_ROOM("delete room"),
    JOIN_ROOM("join room"),
    UNSUPPORTED_COMMAND("unsupported command"),
    ;
    private final String response;

    SupportedCommand(String command) {
        this.response = command;
    }

    public String getResponse() {
        return response;
    }

    public static SupportedCommand code2Enum(String command) {
        for (SupportedCommand value : values()) {
            if (command.startsWith(value.getResponse())) {
                return value;
            }
        }
        return UNSUPPORTED_COMMAND;
    }
}
