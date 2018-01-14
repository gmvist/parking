package com.exercises.parking.api;

import java.util.Date;

/**
 *  This interface represents a car abstraction.
 *  From the application point of view, a ICar has a specific type and can park and unpark to/from a parking slot.
 *
 *  The ICar acts as a visitor to the IParkingLot objects.
 *
 *  @author mghita
 *
 */
public interface ICar {

  /**
   * Defines the type of the car.
   *
   * @return the type of the car
   */
  CarType getType();
  /**
   * The "visit" method, it does the necessary actions to park a car to a parking lot.
   *
   * @param parkingSlot the visited object.
   * @param arrivalTime the time when the car arrived to the parking lot.
   */
  void park(IParkingSlot parkingSlot, Date arrivalTime);

  /**
   * The "visit" method, it does the necessary actions to unpark a car from a parking lot.
   *
   * @param parkingSlot the parking lot to unpark from.
   */
  void unpark(IParkingSlot parkingSlot, Date departureTime);
}
