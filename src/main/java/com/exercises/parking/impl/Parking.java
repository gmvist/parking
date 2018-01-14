package com.exercises.parking.impl;

import com.exercises.parking.api.*;
import com.exercises.parking.api.exceptions.exceptions.ParkingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  The Parking class is a Container implementing the parking API.
 *  It contains a set of available parking slots and a map representing the association between a car and a parking slot.
 *
 *  @author mghita
 *
 */
public class Parking implements IParking {

  /**
   *  The available parking slots only.
   */
  private final Set<IParkingSlot> freeParkingSlots;

  /**
   * The cars currently parked to a parking slot.
   */
  private final Map<ICar,IParkingSlot> parkedCars = new HashMap<>();

  /**
   * The specific parking pricing policy.
   */
  private IPricingPolicy pricingPolicy;

  /**
   * Constructs a parking object containing a set of available parking slots and a pricing policy.
   *
   * @param freeParkingSlots
   * @param pricingPolicy
   */
  public Parking(Set<IParkingSlot> freeParkingSlots, IPricingPolicy pricingPolicy) {

    this.freeParkingSlots = freeParkingSlots;
    this.pricingPolicy = pricingPolicy;
  }

  /**
   * This method is called when a car enters to the parking.
   * It returns a matching parking slot if any available, otherwise throws a ParkingException.
   *
   * @param car the car object entering to the parking.
   * @param arrivalTime the arrival time.
   * @return an empty compatible parking slot.
   * @throws ParkingException when no parking lots available or the car is already checked-in in the parking.
   */
  @Override
  public IParkingSlot checkIn(ICar car, Date arrivalTime) throws ParkingException {

    // check if the car exists in the parking
    if(parkedCars.get(car) != null){
      throw new ParkingException(ParkingException.CAR_ALREADY_PARKED);
    }

    for(IParkingSlot parkingSlot: freeParkingSlots){
      // Apply the visitor car on each parking slot.
      if(parkingSlot.acceptCar(car, arrivalTime)) {
        //associate the car with the parking slot.
        parkedCars.put(car,parkingSlot);
        // remove the parking slot from the list of the available parking slots.
        freeParkingSlots.remove(parkingSlot);
        return parkingSlot;
      }
    }
    throw new ParkingException(String.format(ParkingException.NO_SLOT_AVAILABLE, car.getType()));
  }

  /**
   * This method is called when a car leaves the parking.
   * It returns a parking fee. If the car is not registered to the parking it throws a ParkingException.
   *
   * @param car the car object leaving the parking.
   * @param departureTime the departure time.
   * @return the amount of parking fee to pay.
   * @throws ParkingException when the car is not registered in the parking.
   */
  @Override
  public IBill checkOut(ICar car, Date departureTime) throws ParkingException{

    // check if the car exists in the parking.
    if(parkedCars.get(car) == null){
      throw new ParkingException(ParkingException.CAR_NOT_FOUND);
    }

    IParkingSlot parkingSlot = parkedCars.get(car);
    Date arrivalTime=parkingSlot.getArrivalTime();

    // apply the visitor car on the parking slot.
    parkingSlot.releaseCar(car, departureTime);

    //remove the association between the car and the parking slot.
    parkedCars.remove(car);
    //add the parking slot to the available parking slots list.
    freeParkingSlots.add(parkingSlot);

    return pricingPolicy.bill(car, parkingSlot, arrivalTime, departureTime);
  }
}
