package me.googas.commands.jda.providers;

import lombok.NonNull;
import me.googas.commands.EasyCommandManager;
import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.jda.providers.type.JdaExtraArgumentProvider;

/** Provides the {@link EasyCommandManager} with a {@link CommandContext} */
public class CommandContextProvider implements JdaExtraArgumentProvider<CommandContext> {

  @NonNull
  @Override
  public CommandContext getObject(@NonNull CommandContext context) {
    return context;
  }

  @Override
  public @NonNull Class<CommandContext> getClazz() {
    return CommandContext.class;
  }
}
