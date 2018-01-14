package com.exercises.parking.impl;

import com.exercises.parking.api.IBill;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IParkingSlot;

import java.util.Date;

public class Bill implements IBill {

  private final double parkingFee;

  private final Date arrivalTime;

  private final Date departureTime;

  private final ICar car;

  private final IParkingSlot parkingSlot;

  public Bill(ICar car, IParkingSlot parkingSlot, Date arrivalTime, Date departureTime, double parkingFee) {

    this.car = car;
    this.parkingSlot = parkingSlot;
    this.arrivalTime = arrivalTime;
    this.departureTime = departureTime;
    this.parkingFee = parkingFee;
  }

  @Override
  public double getParkingFee() {
    return this.parkingFee;
  }

  @Override
  public Date getArrivalTime() {
    return this.arrivalTime;
  }

  @Override
  public Date getDepartureTime() {
    return this.departureTime;
  }

  @Override
  public ICar getCar() {
    return this.car;
  }

  @Override
  public IParkingSlot getParkingSlot() {
    return parkingSlot;
  }
}
