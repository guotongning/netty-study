package com.ning.netty.server.ddz;

public enum SupportedCommand {
    LOGIN("login"),
    LOGOUT("logout"),
    ALL_BRAND("all"),
    SEND_BRAND("hands"),
    PLAY_BRAND("send"),
    UNSUPPORTED_COMMAND("unsupported command"),
    ;
    private final String command;

    SupportedCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static SupportedCommand code2Enum(String command) {
        for (SupportedCommand value : values()) {
            if (command.equals(value.getCommand())) {
                return value;
            }
        }
        return UNSUPPORTED_COMMAND;
    }
}
