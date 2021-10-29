package com.ning.netty.server.ddz.enums;

public enum SupportedCommand {
    LOGIN("login"),
    LOGOUT("logout"),
    BYE("bye"),
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
            if (command.equals(value.getResponse())) {
                return value;
            }
        }
        return UNSUPPORTED_COMMAND;
    }
}
