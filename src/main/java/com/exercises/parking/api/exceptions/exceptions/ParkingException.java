package com.exercises.parking.api.exceptions.exceptions;

public class ParkingException extends Exception {

  public static final String NO_SLOT_AVAILABLE = "No parking place available for %s cars";
  public static final String CAR_NOT_FOUND = "Car not found in the parking";
  public static final String CAR_ALREADY_PARKED = "Car already parked in the parking";

  public ParkingException(String message) {
    super(message);
  }
}
