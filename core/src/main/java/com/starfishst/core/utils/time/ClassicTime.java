package com.starfishst.core.utils.time;

import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * Just like {@link Time} but using the java unit {@link TimeUnit}. Only created for getting the
 * unit and the value use {@link Time} for other time queries
 */
public class ClassicTime {

  /** The unit of the time */
  @NotNull private final TimeUnit unit;
  /** The value of the time */
  private long value;

  /**
   * Create an instance
   *
   * @param value the value of time
   * @param unit the unit of time
   */
  public ClassicTime(long value, @NotNull TimeUnit unit) {
    this.value = value;
    this.unit = unit;
  }

  /**
   * Get the value of the time
   *
   * @return the value of the time
   */
  public long getValue() {
    return value;
  }

  /**
   * Get the unit of time
   *
   * @return the unit
   */
  @NotNull
  public TimeUnit getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return this.value + " " + unit.toString().toLowerCase();
  }
}