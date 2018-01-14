package com.exercises.parking.api;

import java.util.Date;

/**
 * Exposes the API of the pricing policy applied by the parking.
 *
 * @author mghita
 */
public interface IPricingPolicy {

  /**
   * Computes the amount of parking fees.
   *
   * @param arrivalTime the arrival time.
   * @param departureTime the departure time.
   * @return the amount of the parking fee.
   */
  double bill(Date arrivalTime, Date departureTime );

}