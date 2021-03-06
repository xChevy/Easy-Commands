package me.googas.commands.jda.providers;

import lombok.NonNull;
import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.jda.providers.type.JdaExtraArgumentProvider;
import net.dv8tion.jda.api.entities.User;

/** Return the sender in the command arguments */
public class UserSenderProvider implements JdaExtraArgumentProvider<User> {
  @NonNull
  @Override
  public User getObject(@NonNull CommandContext context) {
    return context.getSender();
  }

  @Override
  public @NonNull Class<User> getClazz() {
    return User.class;
  }
}
