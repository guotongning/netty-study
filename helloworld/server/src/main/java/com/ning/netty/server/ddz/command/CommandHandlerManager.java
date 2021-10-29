package com.ning.netty.server.ddz.command;

import com.ning.netty.server.ddz.annotations.SupportCommands;
import com.ning.netty.server.ddz.enums.SupportedCommand;

import java.util.HashMap;
import java.util.ServiceLoader;

public class CommandHandlerManager {

    private static final HashMap<SupportedCommand, CommandHandler> SUPPORTED_COMMAND_MAP = new HashMap<>();

    private static final CommandHandler DEFAULT_HANDLER = command -> new CommandResponse("Unsupported command!", command);

    public static void init() {
        ServiceLoader<CommandHandler> handlers = ServiceLoader.load(CommandHandler.class);
        for (CommandHandler handler : handlers) {
            SupportCommands annotation = handler.getClass().getAnnotation(SupportCommands.class);
            SupportedCommand[] values = annotation.values();
            for (SupportedCommand value : values) {
                CommandHandler commandHandler = get(value);
                if (commandHandler != null) {
                    throw new RuntimeException(String.format("the command [%s] expected one processor but found two:[%s,%s]", value.getResponse(), commandHandler.getClass().getSimpleName(), handler.getClass().getSimpleName()));
                }
                add(value, handler);
            }
        }
    }

    public static void add(SupportedCommand supportedCommand, CommandHandler commandHandler) {
        if (supportedCommand == null) {
            return;
        }
        if (commandHandler == null) {
            commandHandler = DEFAULT_HANDLER;
        }
        SUPPORTED_COMMAND_MAP.put(supportedCommand, commandHandler);
    }

    public static CommandHandler get(SupportedCommand supportedCommand) {
        return SUPPORTED_COMMAND_MAP.get(supportedCommand);
    }
}
