package com.exercises.parking.api;

import java.util.Date;

/**
 * The bill object
 *
 * @author mghita
 */
public interface IBill {

  /**
   *
   * @return the parking fee
   */
  double getParkingFee();

  /**
   *
   * @return the arrival time
   */
  Date getArrivalTime();

  /**
   *
   * @return the departure date
   */
  Date getDepartureTime();

  /**
   *
   * @returnc the billed car
   */
  ICar getCar();

  /**
   *
   * @return the billed parking slot
   */
  IParkingSlot getParkingSlot();
}
