package com.ning.netty.server.ddz;

public class Command {
    private String clientId;
    private String clientIP;
    private Long timeStamp;
    private SupportedCommand command;

    public Command(String command) {
        String[] commandParams = command.split(":");
        if (commandParams.length == 0) {
            throw new RuntimeException("异常指令！");
        }
        this.clientId = commandParams[0];
        this.command = SupportedCommand.code2Enum(commandParams[1]);
        this.timeStamp = System.currentTimeMillis();
        this.clientIP = "localhost";
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public SupportedCommand getCommand() {
        return command;
    }

    public void setCommand(SupportedCommand command) {
        this.command = command;
    }
}
