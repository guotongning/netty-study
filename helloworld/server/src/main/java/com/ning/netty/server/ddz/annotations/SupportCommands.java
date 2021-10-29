package com.ning.netty.server.ddz.annotations;

import com.ning.netty.server.ddz.enums.SupportedCommand;

import java.lang.annotation.*;

import static com.ning.netty.server.ddz.enums.SupportedCommand.UNSUPPORTED_COMMAND;


@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SupportCommands {
    SupportedCommand[] values() default {UNSUPPORTED_COMMAND};
}
