package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IParkingSlot;

import java.util.Date;

/**
 * An implementation of a parking slot.
 *
 *
 * @author mghita
 */
public class ParkingSlot implements IParkingSlot {

  /**
   *  Holds the type of the parking slot, in this case matching to the type of the car.
    */
  private CarType type;

  /**
   * The arrival time.
   */
  private Date arrivalTime;

  /**
   * The parking slot can be used by a ICar object.
   */
  private ICar usedBy;

  /**
   * Constructs a parking slot object of a specific type.
   * @param type the parking slot type.
   */
  public ParkingSlot(CarType type) {

    this.type = type;
  }

  /**
   * Gets the parking slot type
   * @return the type of the parking slot
   */
  public CarType getType() {

    return type;
  }

  /**
   * Sets the parking slot type
   * @param type the type of the parking slot.
   */
  public void setType(CarType type) {

    this.type = type;
  }

  /**
   * The time the car parked on the parking slot.
   * If null, the parking slot is considered as free.
   * @return
   */
  public Date getArrivalTime(){

    return this.arrivalTime;
  }

  /**
   * Sets the arrival time.
   * @param date The arrival time.
   */
  public void setArrivalTime(Date date) {

    this.arrivalTime =date;
  }

  /**
   * Retrieve the car object parked on the parking slot.
   * @return
   */
  public ICar getUsedBy() {

    return usedBy;
  }

  /**
   * Associates the car to the parking slot.
   * @param usedBy
   */
  public void setUsedBy(ICar usedBy) {

    this.usedBy = usedBy;
  }

  /**
   *  This method is part of the Visitor design pattern.
   *  The matching condition beteween the parking slot and the visiting car are checked here.
   *
   * @param car the visitor object.
   * @param arrivalTime the time when the car has parked on the parking slot.
   * @return true if the car is compatible with the parking slot.
   */
  public boolean acceptCar(ICar car, Date arrivalTime){

    if(type == car.getType()){
      car.park(this, arrivalTime);
      return true;
    }
    return false;
  }

  /**
   *  This method is part of the Visitor design pattern.
   *  Checks are done before the parking is released.
   *
   * @param car
   * @param departureTime
   * @return
   */

  public boolean releaseCar(ICar car, Date departureTime){

    if(car.equals(this.getUsedBy())){
      car.unpark(this, departureTime);
      return true;
    }
    return false;
  }

}


