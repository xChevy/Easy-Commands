package me.googas.commands.annotations;

import me.googas.commands.EasyCommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to represent the parent commands that are parsed using reflection
 *
 * Lets suppose to have the prefix '/'. If you have the {@link me.googas.commands.EasyCommand} 'hello' you can register other
 * {@link me.googas.commands.EasyCommand} using {@link me.googas.commands.EasyCommand#addChildren(EasyCommand)} and the command
 * execution with be as follows:
 *
 * /hello <command> <**args>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parent {}
