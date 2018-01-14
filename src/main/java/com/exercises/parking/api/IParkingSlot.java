package com.exercises.parking.api;

import java.util.Date;

/**
 * The parking slot can be used by a car matching to the parking slot defined constraints.
 *
 * The ICar object acts as a visitor on the parking slots.
 *
 * @author mghita
 */
public interface IParkingSlot {

  /**
   *  This method is part of the Visitor design pattern.
   *  The matching condition between the parking slot and the visiting car are checked here.
   *
   * @param car the visitor object.
   * @param arrivalTime the time when the car has parked on the parking slot.
   * @return true if the car is compatible with the parking slot.
   */
  boolean acceptCar(ICar car, Date arrivalTime);

  /**
   *  This method is part of the Visitor design pattern.
   *  The visitor car object will check if the parking slot type matches before parking on it.
   *
   * @param car
   * @param departureTime
   * @return
   */

  public boolean releaseCar(ICar car, Date departureTime);

  /**
   * The type of the parking slot.
   * @return the type of the parking slot.
   */
  CarType getType();

  /**
   * The arrival time of the car on the parking slot.
   * @return the arrival time.
   */
  Date getArrivalTime();

}
