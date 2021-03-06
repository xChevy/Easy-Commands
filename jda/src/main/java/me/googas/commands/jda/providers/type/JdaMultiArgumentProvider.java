package me.googas.commands.jda.providers.type;

import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.providers.type.EasyMultipleArgumentProvider;

/**
 * An extension for a multiple argument provider using JDA
 *
 * @param <T> the type of object to provide
 */
public interface JdaMultiArgumentProvider<T>
    extends EasyMultipleArgumentProvider<T, CommandContext> {}
