package com.ning.netty.server.ddz.command;

public class CommandResponse {
    private Command command;
    private String response;
    private boolean close;

    public CommandResponse(String response, Command command) {
        this.response = response;
    }

    public CommandResponse(String response, boolean close, Command command) {
        this.response = response;
        this.close = close;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}
