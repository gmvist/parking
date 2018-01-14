package com.exercises.parking.impl;

import com.exercises.parking.api.*;

import java.util.Date;

/**
 *  A basic implementation of the pricing policy API.
 *
 *  @author mghita
 */
public class BasicPricingPolicy implements IPricingPolicy {

  /**
   * Defines the price per hour.
   */
  private double pricePerHour;

  /**
   * The price per hour.
   * @param pricePerHour
   */
  public BasicPricingPolicy(double pricePerHour) {

    this.pricePerHour = pricePerHour;
  }

  /**
   * Computes the parking fee by multiplying the numbers of hours with the price per hour.
   *
   * @param arrivalTime the arrival time.
   * @param departureTime the departure time.
   * @return the parking fee.
   */
  public IBill bill(ICar car, IParkingSlot parkingSlot, Date arrivalTime, Date departureTime) {

    double hours = (double)(departureTime.getTime()-arrivalTime.getTime())/(1000*3600);
    double fee = hours * pricePerHour;
    return new Bill(car, parkingSlot, arrivalTime, departureTime, fee);
  }
}
