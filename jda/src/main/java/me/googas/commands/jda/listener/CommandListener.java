package me.googas.commands.jda.listener;

import java.util.function.Consumer;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.googas.commands.jda.CommandManager;
import me.googas.commands.jda.EasyJdaCommand;
import me.googas.commands.jda.ListenerOptions;
import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.jda.context.GuildCommandContext;
import me.googas.commands.jda.messages.MessagesProvider;
import me.googas.commands.jda.result.Result;
import me.googas.commands.jda.result.ResultType;
import me.googas.commands.jda.utils.message.FakeMessage;
import me.googas.utility.Series;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

/** The main listener of command execution */
public class CommandListener implements EventListener {

  @NonNull @Getter private final CommandManager manager;
  @NonNull @Getter private final ListenerOptions listenerOptions;
  @NonNull @Getter private final MessagesProvider messagesProvider;
  @NonNull @Getter @Setter private String prefix;

  /**
   * Create an instance
   *
   * @param prefix the prefix to listen
   * @param manager the command manager
   * @param listenerOptions the options of the manager
   * @param messagesProvider the provider of messages
   */
  public CommandListener(
      @NonNull String prefix,
      @NonNull CommandManager manager,
      @NonNull ListenerOptions listenerOptions,
      @NonNull MessagesProvider messagesProvider) {
    this.prefix = prefix;
    this.manager = manager;
    this.listenerOptions = listenerOptions;
    this.messagesProvider = messagesProvider;
  }

  /**
   * On the event of a message received
   *
   * @param event the event of a message received
   */
  @SubscribeEvent
  public void onMessageReceivedEvent(@NonNull MessageReceivedEvent event) {
    String[] strings = event.getMessage().getContentRaw().split(" +");
    String commandName = strings[0];
    if (!commandName.startsWith(this.prefix)) {
      return;
    }
    listenerOptions.preCommand(event, commandName, strings);
    commandName = commandName.substring(prefix.length());
    EasyJdaCommand command = manager.getCommand(commandName);
    CommandContext context =
        getCommandContext(event, strings, command == null ? null : command.getName());
    Result result = getResult(command, commandName, context);
    Message response = getMessage(result, context);
    Consumer<Message> consumer = getConsumer(result, context);
    if (response != null) {
      if (consumer != null && !(event.getMessage() instanceof FakeMessage)) {
        event.getChannel().sendMessage(response).queue(consumer);
      } else {
        event.getChannel().sendMessage(response).queue();
      }
    }
  }

  /**
   * Get the action to do with the message sent from a result
   *
   * @param result the result to get the action from
   * @param context the context of the command execution
   * @return the action from the result or null if it doesn't have any
   */
  public Consumer<Message> getConsumer(Result result, @NonNull CommandContext context) {
    return listenerOptions.processConsumer(result, context);
  }

  /**
   * Get the message that will be send from a result
   *
   * @param result the result to get the message from
   * @param context the context of the command execution
   * @return the message
   */
  private Message getMessage(Result result, CommandContext context) {
    return this.listenerOptions.processResult(result, context);
  }

  /**
   * Get the result of a command execution
   *
   * @param command the command
   * @param commandName the name of the command
   * @param context the context of the command
   * @return the result of the command execution
   */
  private Result getResult(
      EasyJdaCommand command, @NonNull String commandName, CommandContext context) {
    if (command != null) {
      return command.execute(context);
    } else {
      return new Result(ResultType.ERROR, messagesProvider.commandNotFound(commandName, context));
    }
  }

  /**
   * Get the context where the command was executed
   *
   * @param event the event where the command was executed from
   * @param strings the strings representing the arguments of the command
   * @param commandName the name of the command
   * @return the context of the command
   */
  @NonNull
  private CommandContext getCommandContext(
      @NonNull MessageReceivedEvent event, @NonNull String[] strings, String commandName) {
    strings = Series.arrayFrom(1, strings);
    if (event.getMember() != null) {
      return new GuildCommandContext(
          event.getMessage(),
          event.getAuthor(),
          strings,
          event.getChannel(),
          event,
          messagesProvider,
          manager.getProvidersRegistry(),
          commandName);
    } else {
      return new CommandContext(
          event.getMessage(),
          event.getAuthor(),
          strings,
          event.getChannel(),
          messagesProvider,
          manager.getProvidersRegistry(),
          commandName);
    }
  }

  @Override
  public void onEvent(@NonNull @NotNull final GenericEvent genericEvent) {
    if (genericEvent instanceof MessageReceivedEvent) {
      this.onMessageReceivedEvent((MessageReceivedEvent) genericEvent);
    }
  }
}
