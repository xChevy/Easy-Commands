package me.googas.commands.bukkit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import me.googas.commands.bukkit.AnnotatedCommand;

/**
 * When you include this annotation into a {@link java.lang.reflect.Method} and invoke {@link
 * me.googas.commands.bukkit.CommandManager#parseCommands(Object)} it tells it that the {@link
 * java.lang.reflect.Method} must create a {@link me.googas.commands.bukkit.AnnotatedCommand}
 *
 * @see me.googas.commands.bukkit.CommandManager#parseCommands(Object)
 * @see me.googas.commands.bukkit.AnnotatedCommand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

  /**
   * The aliases of the command
   *
   * @see AnnotatedCommand#getAliases()
   * @return the aliases of the command
   */
  String[] aliases() default {};

  /**
   * Get a brief description of the command
   *
   * @see AnnotatedCommand
   * @return the description of the command
   */
  String description() default "No description provided";

  /**
   * Get the permission node of the command
   *
   * @return the permission node of the command
   */
  String permission() default "";

  /**
   * Whether the command should be executed async. To know more about Asynchronization check <a
   * href="https://bukkit.fandom.com/wiki/Scheduler_Programming">Bukkit wiki</a>
   *
   * @return true if the command has to run the command asynchronously
   */
  boolean async() default false;
}
