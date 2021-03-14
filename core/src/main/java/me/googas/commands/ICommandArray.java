package me.googas.commands;

import lombok.NonNull;
import me.googas.commands.context.ICommandContext;

/**
 * Represents a command. This one contains the names/aliases in an {@link java.lang.reflect.Array}
 * because there could be some incompatibilities between frameworks
 *
 * @param <C> the type of command context
 */
public interface ICommandArray<C extends ICommandContext> extends ISimpleCommand<C> {

  /**
   * Get the aliases of the command. The main name of the command will be in the index 0 Check the
   * respective @Command annotation to know more on how the alias will be gotten
   *
   * @return the array of aliases
   */
  @NonNull
  String[] getAliases();
}
