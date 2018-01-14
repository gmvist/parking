package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IParkingSlot;

import java.util.Date;

/**
 * The car implementation.
 *
 * @author mghita
 *
 */
public class Car implements ICar{

  /**
   * The type of the car.
   */
  private CarType type;

  /**
   * Constructs a car object of the specified type.
   * @param type
   */
  public Car(CarType type) {
    this.type = type;
  }

  /**
   * The type of the car.
   * @return the car type
   */
  public CarType getType() {
    return type;
  }

  /**
   * Set the type of the car
   * @param type of the car
   */
  public void setType(CarType type) {
    this.type = type;
  }

  /**
   * The "visit" method, it does the necessary actions to park a car to a parking lot:
   *  - sets the parking lot as used by the car.
   *  - sets the arrival time of the car.
   *
   * @param parkingSlot the visited object.
   * @param arrivalTime the time when the car arrived to the parking lot.
   */
  @Override
  public void park(IParkingSlot parkingSlot, Date arrivalTime) {

    ((ParkingSlot)parkingSlot).setUsedBy(this);
    ((ParkingSlot)parkingSlot).setArrivalTime(arrivalTime);
  }

  /**
   * The "visit" method, it does the necessary actions to unpark a car from a parking lot:
   *  - sets the parking place as unused.
   *
   * @param parkingSlot the parking lot to unpark from.
   * @param departureTime the departure time.
   */
  @Override
  public void unpark(IParkingSlot parkingSlot, Date departureTime) {

    ((ParkingSlot)parkingSlot).setUsedBy(null);
    ((ParkingSlot)parkingSlot).setArrivalTime(null);

  }
}
