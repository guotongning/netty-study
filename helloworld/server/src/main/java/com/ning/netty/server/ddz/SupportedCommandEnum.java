package com.ning.netty.server.ddz;

public enum SupportedCommandEnum {
    LOGIN("login"),
    LOGOUT("logout"),
    ALL_BRAND("all"),
    SEND_BRAND("hands"),
    PLAY_BRAND("send"),
    ;
    private final String command;

    SupportedCommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static SupportedCommandEnum code2Enum(String command) {
        for (SupportedCommandEnum value : values()) {
            if (command.equals(value.getCommand())) {
                return value;
            }
        }
        return null;
    }
}
