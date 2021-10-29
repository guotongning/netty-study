package com.ning.netty.server.ddz.command;

import com.ning.netty.server.ddz.enums.SupportedCommand;

public class CommandResponse {
    private Command command;
    private String response;
    private Long timeStamp;
    private boolean close;

    public CommandResponse(String response, Command command) {
        if (response == null || "".equals(response)) {
            response = SupportedCommand.UNSUPPORTED_COMMAND.getResponse();
        }
        this.response = response;
        this.command = command;
        this.timeStamp = System.currentTimeMillis();
    }

    public CommandResponse(String response, Command command, boolean close) {
        this.response = response;
        this.close = close;
        this.command = command;
        this.timeStamp = System.currentTimeMillis();
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}
