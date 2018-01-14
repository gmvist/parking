package com.exercises.parking.impl;

import com.exercises.parking.api.IBill;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IParkingSlot;

import java.util.Date;

/**
 * Billing implementation
 */
public class Bill implements IBill {

  private final double parkingFee;

  private final Date arrivalTime;

  private final Date departureTime;

  private final ICar car;

  private final IParkingSlot parkingSlot;

  /**
   * Construct the bill
   * @param car
   * @param parkingSlot
   * @param arrivalTime
   * @param departureTime
   * @param parkingFee
   */
  public Bill(ICar car, IParkingSlot parkingSlot, Date arrivalTime, Date departureTime, double parkingFee) {

    this.car = car;
    this.parkingSlot = parkingSlot;
    this.arrivalTime = arrivalTime;
    this.departureTime = departureTime;
    this.parkingFee = parkingFee;
  }

  /**
   *
   * @return the parking fee
   */
  @Override
  public double getParkingFee() {
    return this.parkingFee;
  }

  /**
   *
   * @return the arrival time
   */
  @Override
  public Date getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   *
   * @return the departure time
   */
  @Override
  public Date getDepartureTime() {
    return this.departureTime;
  }

  /**
   *
   * @return the billed car
   */
  @Override
  public ICar getCar() {
    return this.car;
  }

  /**
   *
   * @return the parking slot
   */
  @Override
  public IParkingSlot getParkingSlot() {
    return parkingSlot;
  }
}
