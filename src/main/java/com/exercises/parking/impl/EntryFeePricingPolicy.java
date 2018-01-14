package com.exercises.parking.impl;

import com.exercises.parking.api.*;

import java.util.Date;

/**
 *  The class enhance the PricingPolicyDecorator by adding a fixed entry free to the hourly parking fee.
 *  It uses the Decorator design pattern.
 *
 *  @author mghita
 */
public class EntryFeePricingPolicy extends PricingPolicyDecorator {

  /**
   *  The entry fee.
   */
  private double entryFee;

  /**
   * Constructs a new object from the specified pricing policy by adding the entry fee.
   * @param pricingPolicy - the concrete pricing policy to enhance.
   * @param entryFee - the entry fee.
   */
  public EntryFeePricingPolicy(IPricingPolicy pricingPolicy, double entryFee) {


    super(pricingPolicy);
    this.entryFee = entryFee;
  }

  /**
   * Adds the entry fee to the amount computed by the concrete pricing policy to enhance.
   *
   * @param car the billed car
   * @param parkingSlot the billed parking slot
   * @param arrivalTime the arrival time.
   * @param departureTime the departure time.
   * @return the bill object
   */
  @Override
  public IBill bill(ICar car, IParkingSlot parkingSlot, Date arrivalTime, Date departureTime) {
    double decoratorFee = super.bill(car, parkingSlot, arrivalTime, departureTime).getParkingFee();
    double fee = entryFee + decoratorFee;
    return new Bill(car, parkingSlot, arrivalTime, departureTime, fee);
  }
}
