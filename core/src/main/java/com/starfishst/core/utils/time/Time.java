package com.starfishst.core.utils.time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Simple class for timing */
public class Time {

  /** The value of time */
  private final long value;
  /** The unit of time */
  @NotNull private final Unit unit;

  /**
   * Create a time instance
   *
   * @param value the value of time
   * @param unit the unit of time
   */
  public Time(final long value, @NotNull final Unit unit) {
    this.value = value;
    this.unit = unit;
  }

  /**
   * Parse a string of time: Should look like '1s' -> one second
   *
   * @param string the string to parse
   * @return a new time instance if correctly formatted
   */
  public static Time fromString(@Nullable final String string) {
    if (string != null && !string.isEmpty()) {
      final long value = Long.parseLong(string.substring(0, string.length() - 1));
      final Unit unit = Unit.fromChar(string.charAt(string.length() - 1));
      return new Time(value, unit);
    } else {
      throw new IllegalArgumentException("Cannot get time from a null string");
    }
  }

  /**
   * Get a time instance using millis
   *
   * @param millis the millis
   * @return the time
   */
  public static Time fromMillis(long millis) {
    return new Time(millis, Unit.MILLISECONDS);
  }

  /**
   * Get this same time but in other unit. It will also change the value depending on the unit
   * provided
   *
   * @param unit the unit to get the time from
   * @return the time with the new unit
   */
  public Time getAs(@NotNull final Unit unit) {
    return new Time(this.millis() / unit.millis(), unit);
  }

  /**
   * For minecraft purposes this will change the time into minecraft ticks
   *
   * @return the time in ticks
   */
  public long ticks() {
    return (millis() * 20) / 1000;
  }

  /**
   * Get the time in millis
   *
   * @return the time in millis
   */
  public long millis() {
    return this.value * this.unit.millis();
  }

  /**
   * Get the next date since today using this time
   *
   * @return the next date
   */
  public LocalDateTime nextDate() {
    return TimeUtils.getLocalDateFromMillis(nextDateMillis());
  }

  /**
   * Get the previous date since today using this time
   *
   * @return the previous date
   */
  public LocalDateTime previousDate() {
    return TimeUtils.getLocalDateFromMillis(previousDateMillis());
  }

  /**
   * Get the next offset date since today using this time
   *
   * @return the next date
   */
  public OffsetDateTime nextDateOffset() {
    return TimeUtils.getOffsetDateFromMillis(nextDateMillis());
  }

  /**
   * s Get the previous offset date since today using this time
   *
   * @return the previous date
   */
  public OffsetDateTime previousDateOffset() {
    return TimeUtils.getOffsetDateFromMillis(previousDateMillis());
  }

  /**
   * Get the next date since today using this time
   *
   * @return the next date in millis
   */
  private long nextDateMillis() {
    return System.currentTimeMillis() + this.millis();
  }

  /**
   * Get the previous date since today using this time
   *
   * @return the previous date in millis
   */
  private long previousDateMillis() {
    return System.currentTimeMillis() - this.millis();
  }

  /**
   * This means that if there is 60 seconds the effective string will be 1 minute
   *
   * @return the effective string
   */
  @NotNull
  public String toEffectiveString() {
    return this.getAs(Unit.fromMillis(this.millis())).toString();
  }

  /**
   * Is a string that can be used in {@link Time#fromString(String)}
   *
   * @return the string
   */
  @NotNull
  public String toDatabaseString() {
    return value + unit.getSimple();
  }

  /**
   * Get the time as {@link ClassicTime}
   *
   * @return the time as classic time
   */
  @NotNull
  public ClassicTime toClassicTime() {
    TimeUnit newUnit = unit.toTimeUnit();
    long newValue = getValue(newUnit);
    return new ClassicTime(newValue, newUnit);
  }

  /**
   * Just like {@link Time#getValue()} but getting as another java unit
   *
   * @param unit the unit to get the value from
   * @return the value as certain unit
   */
  public long getValue(@NotNull TimeUnit unit) {
    return getAs(Unit.fromTimeUnit(unit)).getValue();
  }

  /**
   * Just like {@link Time#getValue()} but getting as another unit
   *
   * @param unit the unit to get the value from
   * @return the value as certain unit
   */
  public long getValue(@NotNull Unit unit) {
    return getAs(unit).getValue();
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
   * @return the unit of time
   */
  @NotNull
  public Unit getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return this.value + " " + unit.toString().toLowerCase();
  }

  /**
   * subtract this time with another
   *
   * @param time the time to subtract this one
   * @return the subtraction but it cannot be negative it would be 0
   */
  @NotNull
  public Time subtract(@NotNull Time time) {
    long millis = this.millis() - time.millis();
    return Time.fromMillis(millis < 0 ? 0 : millis);
  }

  /**
   * Sums this time with another one
   *
   * @param time the another one to sum
   * @return the result of the operation
   */
  @NotNull
  public Time sum(@NotNull Time time) {
    return Time.fromMillis(millis() + time.millis());
  }

  /**
   * Compares if this time is higher than another time
   *
   * @param time the time to check if is lower than this one
   * @return true if the parameter time is lower than this
   */
  public boolean higherThan(@NotNull Time time) {
    return this.millis() > time.millis();
  }

  /**
   * Compares if this time is lower than another time
   *
   * @param time the time to check if is higher than this one
   * @return tru if the parameter time is higher than this
   */
  public boolean lowerThan(@NotNull Time time) {
    return this.millis() < time.millis();
  }
}
