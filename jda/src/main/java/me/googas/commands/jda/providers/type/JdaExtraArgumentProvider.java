package me.googas.commands.jda.providers.type;

import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.providers.type.EasyExtraArgumentProvider;

/**
 * An extension for an extra argument provider using JDA
 *
 * @param <T> the type of object to provide
 */
public interface JdaExtraArgumentProvider<T> extends EasyExtraArgumentProvider<T, CommandContext> {}
