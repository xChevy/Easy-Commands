package me.googas.commands.providers;

import lombok.NonNull;
import me.googas.commands.ICommandManager;
import me.googas.commands.context.ICommandContext;
import me.googas.commands.exceptions.ArgumentProviderException;
import me.googas.commands.messages.IMessagesProvider;
import me.googas.commands.providers.type.IArgumentProvider;

/** Provides the {@link ICommandManager} with a {@link Boolean} */
public class BooleanProvider<T extends ICommandContext> implements IArgumentProvider<Boolean, T> {

  private final IMessagesProvider<T> messagesProvider;

  /**
   * Create an instance
   *
   * @param messagesProvider to send the error message in case that the long could not be parsed
   */
  public BooleanProvider(IMessagesProvider<T> messagesProvider) {
    this.messagesProvider = messagesProvider;
  }

  @Override
  public @NonNull Class<Boolean> getClazz() {
    return boolean.class;
  }

  @NonNull
  @Override
  public Boolean fromString(@NonNull String string, @NonNull T context)
      throws ArgumentProviderException {
    boolean result;
    if (string.equalsIgnoreCase("true")) {
      result = true;
    } else if (string.equalsIgnoreCase("1")) {
      result = true;
    } else if (string.equalsIgnoreCase("false")) {
      result = false;
    } else if (string.equalsIgnoreCase("0")) {
      result = false;
    } else {
      throw new ArgumentProviderException(messagesProvider.invalidBoolean(string, context));
    }
    return result;
  }

  @Override
  public boolean provides(@NonNull Class<?> clazz) {
    return clazz == Boolean.class || clazz == boolean.class;
  }
}