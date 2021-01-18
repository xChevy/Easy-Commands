package com.starfishst.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/** Provides an static instance of Gson and static utilities for adapters and factories. */
public class GsonProvider {

  /** The map of adapters working in the gson instance */
  @NotNull private static final HashMap<Type, Object> adapters = new HashMap<>();
  /** The type adapter factories to register in gson */
  @NotNull private static final Set<TypeAdapterFactory> factories = new HashSet<>();
  /** The gson instance */
  @NotNull public static Gson GSON = build();

  /** Builds and sets the gson instance */
  public static void refresh() {
    GSON = build();
  }

  /**
   * Builds a gson instance using the map of adapters, factories and pretty printing.
   *
   * @return a gson instance
   */
  @NotNull
  public static Gson build() {
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    adapters.forEach((builder::registerTypeAdapter));
    factories.forEach(builder::registerTypeAdapterFactory);
    return builder.create();
  }

  /**
   * Adds and adapter to the map. This action does not update the gson instance after adding the
   * adapter use {@link #refresh()}
   *
   * @param type the type that the adapter serializes and deserializes
   * @param adapter the adapter
   */
  public static void addAdapter(@NotNull Type type, @NotNull Object adapter) {
    adapters.put(type, adapter);
  }

  /**
   * Add a factory to the set. This action does not update the gson instance after adding the
   * adapter use {@link #refresh()}
   *
   * @param factory the factory to add
   * @return true if changes where made
   */
  public static boolean addFactory(@NotNull TypeAdapterFactory factory) {
    return factories.add(factory);
  }

  /**
   * Saves an object to a file
   *
   * @param file the file to write
   * @param toWrite the object to write
   * @throws IOException in case file handling goes wrong: it could be that the file does not exist
   *     or th file writer just fails. It will be closed at the end
   */
  public static void save(@NotNull File file, @NotNull Object toWrite) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write(GSON.toJson(toWrite));
    fileWriter.close();
  }

  /**
   * Get the adapters inside the provider
   *
   * @return the adapters in a map
   */
  @NotNull
  public static HashMap<Type, Object> getAdapters() {
    return adapters;
  }
}