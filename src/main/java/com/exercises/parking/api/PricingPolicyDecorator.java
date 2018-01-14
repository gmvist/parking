package com.exercises.parking.api;

import java.util.Date;

/**
 * This abstract class represents a Decorator for the IPricingPolicy interface.
 * It can be extended by other concrete pricing policy classes to add other pricing policies without changing the API.
 *
 * @author mghita
 */
public abstract class PricingPolicyDecorator implements IPricingPolicy{

  /**
   * The base pricing policy to enhance.
   *
   */
  private IPricingPolicy pricingPolicy;

  /**
   * The constructor takes as a parameter the pricingPolicy to enhance.
   *
   * @param pricingPolicy a concrete pricing policy to enhance.
   */
  public PricingPolicyDecorator(IPricingPolicy pricingPolicy) {

    this.pricingPolicy = pricingPolicy;
  }

  /**
   * The bill method of the concrete pricing policy to enhance.
   *
   * @param arrivalTime the arrival time.
   * @param departureTime the departure time.
   * @return the amount of the parking fee.
   */
  @Override
  public IBill bill(ICar car, IParkingSlot parkingSlot, Date arrivalTime, Date departureTime) {

    return pricingPolicy.bill(car, parkingSlot, arrivalTime, departureTime);
  }
}
