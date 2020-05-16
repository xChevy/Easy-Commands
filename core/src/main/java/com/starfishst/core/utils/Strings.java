package com.starfishst.core.utils;

import com.starfishst.core.fallback.Fallback;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Utils for strings */
public class Strings {

  /** The main builder used in the utilities */
  @NotNull private static final StringBuilder builder = new StringBuilder();

  /**
   * Get a clear instance of {@link StringBuilder}
   *
   * @return the clear instance (length 0)
   */
  public static StringBuilder getBuilder() {
    Strings.builder.setLength(0);
    return Strings.builder;
  }

  /**
   * To save resources and not waste on a loop
   *
   * @param message the message to build
   * @return "Null" if the message is null else the message
   */
  @NotNull
  public static String buildMessage(@Nullable String message) {
    return message == null ? "Null" : message;
  }

  /**
   * To save resources and not waste on a loop
   *
   * @param message the message to build
   * @return the message if it's not null
   */
  @Nullable
  public static String buildMessageOrNull(@Nullable String message) {
    return message;
  }

  /**
   * Build a message which has place holders
   *
   * @param message the message
   * @param strings the place holders
   * @return the built message
   */
  @NotNull
  public static String buildMessage(@Nullable String message, Object... strings) {
    if (message != null) {
      for (int i = 0; i < strings.length; i++) {
        message =
            message.replace("{" + i + "}", strings[i] == null ? "Null" : strings[i].toString());
      }
    } else {
      message = "Null";
    }
    return message;
  }

  /**
   * Build a message which has place holders but if the message is null it also returns null
   *
   * @param message the message
   * @param strings the place holders
   * @return the built message
   */
  @Nullable
  public static String buildMessageOrNull(@Nullable String message, Object... strings) {
    if (message != null) {
      return buildMessage(message, strings);
    } else {
      return null;
    }
  }

  /**
   * Builds a String from an array.
   *
   * @param strings the string array
   * @return a brand new string
   */
  @NotNull
  public static String fromArray(@NotNull String[] strings) {
    StringBuilder builder = Strings.getBuilder();

    for (String string : strings) {
      builder.append(string).append(" ");
    }
    if (builder.length() >= 1) {
      builder.deleteCharAt(builder.length() - 1);
    }
    return builder.toString();
  }

  /**
   * Build a message using more readable place holders like %name%
   *
   * @param message the message to build
   * @param placeHolders the placeholders and its values
   * @return the built message
   */
  @NotNull
  public static String buildMessage(
      @NotNull String message, @NotNull HashMap<String, String> placeHolders) {
    Atomic<String> atomicMessage = new Atomic<>(message);
    placeHolders.forEach(
        (placeHolder, value) -> {
          if (value != null) {
            atomicMessage.set(atomicMessage.get().replace("%" + placeHolder + "%", value));
          } else {
            Fallback.addError(
                "Value for placeholder " + placeHolder + " is null and was not changed");
          }
        });
    return atomicMessage.get();
  }

  /**
   * Missing method in {@link String} looks if a string contains a certain one ignoring casing
   *
   * @param string the string to look if is inside
   * @param search this one
   * @return true if its contained
   */
  public static boolean containsIgnoreCase(@NotNull String string, @NotNull String search) {
    final int length = search.length();
    if (length == 0) {
      return true;
    } else {
      for (int i = string.length() - length; i >= 0; i--) {
        if (string.regionMatches(true, i, search, 0, length)) return true;
      }
      return false;
    }
  }
}