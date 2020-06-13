package com.starfishst.commands.messages;

import com.starfishst.commands.CommandManager;
import com.starfishst.commands.ManagerOptions;
import com.starfishst.commands.context.CommandContext;
import com.starfishst.commands.result.ResultType;
import com.starfishst.core.messages.IMessagesProvider;
import com.starfishst.core.utils.time.Time;
import org.jetbrains.annotations.NotNull;

/** Provides messages to results */
public interface MessagesProvider extends IMessagesProvider<CommandContext> {

  /**
   * @param command is the input string that's not found as a command
   * @param context the context of the command
   * @return The message when a command is not found in {@link CommandManager}
   */
  @NotNull
  String commandNotFound(@NotNull String command, @NotNull CommandContext context);

  /**
   * @param context the context of the command
   * @return The footer in case {@link ManagerOptions#isEmbedMessages()} is true
   */
  @NotNull
  String footer(@NotNull CommandContext context);

  /**
   * @param type the type of result
   * @param context the context of the command
   * @return the title to use for a result
   */
  @NotNull
  String getTitle(@NotNull ResultType type, @NotNull CommandContext context);

  /**
   * @param title the title of the response
   * @param message the message of the response
   * @param context the context of the command
   * @return the message when the result has a message
   */
  @NotNull
  String response(@NotNull String title, @NotNull String message, @NotNull CommandContext context);

  /**
   * @param context the context of the command
   * @return the message when the sender does not have a permission
   */
  @NotNull
  String notAllowed(@NotNull CommandContext context);

  /**
   * @param context the context of the command
   * @return the message when the command has to be executed in a {@link
   *     net.dv8tion.jda.api.entities.Guild}
   */
  @NotNull
  String guildOnly(@NotNull CommandContext context);

  /**
   * Get the url to use as thumbnail
   *
   * @param context the context of the command
   * @return the url to use as thumbnail
   */
  @NotNull
  String thumbnailUrl(@NotNull CommandContext context);

  /**
   * Get the message sent when the user is still on cooldown
   *
   * @param timeLeft the time left for the user
   * @param context the context of the command
   * @return the built string
   */
  @NotNull
  String cooldown(@NotNull Time timeLeft, @NotNull CommandContext context);

  /**
   * The message sent when a string is not a valid user
   *
   * @param string the string that is not valid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidUser(@NotNull String string, @NotNull CommandContext context);

  /**
   * The message sent when a string is not a valid user
   *
   * @param string the string that is not valid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidMember(@NotNull String string, @NotNull CommandContext context);

  /**
   * The message sent when a string is not a valid role
   *
   * @param string the string that is invalid
   * @param context the context of the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidRole(@NotNull String string, @NotNull CommandContext context);

  /**
   * The message sent when a string is not a valid role
   *
   * @param string the string that is invalid
   * @param context the context ofo the command
   * @return the message to tell that the input is wrong
   */
  @NotNull
  String invalidTextChannel(String string, CommandContext context);
}
