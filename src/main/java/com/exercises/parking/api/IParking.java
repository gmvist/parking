package com.exercises.parking.api;

import com.exercises.parking.api.exceptions.exceptions.ParkingException;

import java.util.Date;

/**
 *  The Parking interface is the entry point of the API. It's acting as a Facade providing the necessary methods for:
 *  - sending a car to a free parking slot of the right type if any available
 *  - denying the car access to parking if no vehicle compatible places left
 *  - billing the customer when the car leaves the parking.
 *
 *  @author mghita
 *
 */
public interface IParking {

  /**
   * This method is called when a car enters to the parking.
   * It returns a matching parking slot if any available, otherwise throws a ParkingException.
   *
   * @param car the car object entering to the parking.
   * @param arrivalTime the arrival time.
   * @return an empty compatible parking slot.
   * @throws ParkingException when no parking lots available or the car is already checked-in in the parking.
   */
  IParkingSlot checkIn(ICar car, Date arrivalTime) throws ParkingException;

  /**
   * This method is called when a car leaves the parking.
   * It returns a parking fee. If the car is not registered to the parking it throws a ParkingException.
   *
   * @param car the car object leaving the parking.
   * @param departureTime the departure time.
   * @return the amount of parking fee to pay.
   * @throws ParkingException when the car is not registered in the parking.
   */
  double checkOut(ICar car, Date departureTime) throws ParkingException;

}
