package me.googas.commands.jda.utils.embeds;

import java.awt.*;
import java.util.LinkedHashMap;
import lombok.NonNull;
import me.googas.commands.jda.ManagerOptions;
import me.googas.commands.jda.context.CommandContext;
import me.googas.commands.jda.listener.CommandListener;
import me.googas.commands.jda.messages.MessagesProvider;
import me.googas.commands.jda.result.Result;
import me.googas.commands.jda.result.ResultType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

/** Easily create embeds */
public class EmbedFactory {

  /** The embed builder */
  private static final EmbedBuilder embedBuilder = new EmbedBuilder();

  /**
   * Create an embed using a result and some message options
   *
   * @param result the result to make as embed
   * @param listener the command listener to get the {@link ManagerOptions}
   * @param context the context of the command
   * @return an embed query built from the result
   */
  public static EmbedQuery fromResult(
      @NonNull Result result, @NonNull CommandListener listener, CommandContext context) {
    MessagesProvider messagesProvider = listener.getMessagesProvider();
    ManagerOptions managerOptions = listener.getManagerOptions();
    ResultType type = result.getType();
    final String thumbnail = messagesProvider.thumbnailUrl(context);
    return newEmbed(
        type.getTitle(messagesProvider, context),
        result.getMessage(),
        thumbnail,
        null,
        messagesProvider.footer(context),
        type.getColor(managerOptions),
        null,
        false);
  }

  /**
   * Create an embed
   *
   * @param title the title of the embed
   * @param description the description of the embed
   * @param thumbnail the thumbnail of the embed
   * @param image the image of the embed
   * @param footer the footer of the embed
   * @param color the color of the embed
   * @param fields the fields of the embed (Leave value in blank for blank field)
   * @param inline should the fields be inline
   * @return a built query
   */
  public static EmbedQuery newEmbed(
      String title,
      String description,
      String thumbnail,
      String image,
      String footer,
      Color color,
      LinkedHashMap<String, String> fields,
      boolean inline) {
    EmbedBuilder builder = getEmbedBuilder();
    if (title != null) {
      assertFalse(
          title.length() >= MessageEmbed.TITLE_MAX_LENGTH,
          "Title cannot be longer than " + MessageEmbed.TITLE_MAX_LENGTH);
      builder.setTitle(title);
    }
    if (description != null) {
      assertFalse(
          description.length() >= MessageEmbed.TEXT_MAX_LENGTH,
          "Description cannot be longer than " + MessageEmbed.TEXT_MAX_LENGTH);
      builder.setDescription(description);
    }
    if (thumbnail != null && !thumbnail.isEmpty()) {
      assertFalse(
          thumbnail.length() >= MessageEmbed.URL_MAX_LENGTH,
          "URL cannot be longer than " + MessageEmbed.URL_MAX_LENGTH);
      builder.setThumbnail(thumbnail);
    }
    if (image != null) {
      builder.setImage(image);
      assertFalse(
          image.length() >= MessageEmbed.URL_MAX_LENGTH,
          "URL cannot be longer than " + MessageEmbed.URL_MAX_LENGTH);
    }
    if (footer != null) {
      assertFalse(
          footer.length() >= MessageEmbed.TEXT_MAX_LENGTH,
          "Footer cannot be longer than " + MessageEmbed.TEXT_MAX_LENGTH);
      builder.setFooter(footer);
    }
    if (color != null) builder.setColor(color);
    if (fields != null) {
      fields.forEach(
          (key, value) -> {
            assertFalse(
                key.length() >= MessageEmbed.TITLE_MAX_LENGTH,
                key + " cannot be longer than " + MessageEmbed.TITLE_MAX_LENGTH);
            assertFalse(
                value.length() >= MessageEmbed.VALUE_MAX_LENGTH,
                value + " cannot be longer than " + MessageEmbed.VALUE_MAX_LENGTH);
            builder.addField(key, value, inline);
          });
    }
    assertFalse(
        builder.length() >= MessageEmbed.EMBED_MAX_LENGTH_BOT,
        "This embed is too big! Max: "
            + MessageEmbed.EMBED_MAX_LENGTH_BOT
            + " characters and it has "
            + embedBuilder.length());
    return new EmbedQuery(embedBuilder);
  }

  private static void assertFalse(boolean toAssert, @NonNull String message) {
    if (!toAssert) throw new IllegalArgumentException(message);
  }

  /**
   * Get the embed builder used in the factory. (It is empty)
   *
   * @return the embed builder used
   */
  public static @NonNull EmbedBuilder getEmbedBuilder() {
    return EmbedFactory.embedBuilder.clear();
  }
}
