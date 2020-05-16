package com.starfishst.core.messages;

import com.starfishst.core.context.ICommandContext;
import org.jetbrains.annotations.NotNull;

/**
 * Provides messages for different instances of the manager
 *
 * @param <O> the sender of the context
 * @param <T> the command context
 */
public interface IMessagesProvider<O, T extends ICommandContext<O>> {

  /**
   * The message sent when a string is not valid as a long
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell the user that the input is wrong
   */
  @NotNull
  String invalidLong(@NotNull String string, @NotNull T context);

  /**
   * The message sent when a string is not valid as a integer
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidInteger(@NotNull String string, @NotNull T context);

  /**
   * The message sent when a string is not valid as a double
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidDouble(@NotNull String string, @NotNull T context);

  /**
   * The message sent when a string is not valid as a boolean
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidBoolean(@NotNull String string, @NotNull T context);

  /**
   * The message sent when a string is not valid as Time
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidTime(@NotNull String string, @NotNull T context);

  /**
   * Get the message to send when there's a missing a argument
   *
   * @param name the name of the argument
   * @param description the description of the argument
   * @param position the position of the argument
   * @return The error when the message is missing arguments
   */
  @NotNull
  String missingArgument(@NotNull String name, @NotNull String description, int position);
}