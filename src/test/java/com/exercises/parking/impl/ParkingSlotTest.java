package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.ICar;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit test class for the ParkingSlot
 *
 * @author mghita
 */
public class ParkingSlotTest {

  ParkingSlot parkingSlot;
  ICar car;
  Date initialTime;
  Date departureTime;

  @Before
  public void setUp() {

    parkingSlot = new ParkingSlot(CarType.SEDAN);
    car = new Car(CarType.SEDAN);
    initialTime = new Date();
    departureTime = new Date(initialTime.getTime()+1000*3600*3);
  }

  @Test
  public void testGettersAndSetters(){

    assertEquals(CarType.SEDAN, parkingSlot.getType());

    parkingSlot.setType(CarType.ELECTRIC_50KW);
    assertEquals(CarType.ELECTRIC_50KW, parkingSlot.getType());

    parkingSlot.setUsedBy(car);
    assertEquals(car.getType(), parkingSlot.getUsedBy().getType());
  }

  @Test
  public void acceptCar() {

    parkingSlot.acceptCar(car, initialTime);
    // the parking slot has the right type
    assertEquals(car.getType(), parkingSlot.getUsedBy().getType());
    // the parking slot has the right arrival time
    assertEquals(initialTime, parkingSlot.getArrivalTime());
  }

  @Test
  public void releaseCar() {

    parkingSlot.acceptCar(car, initialTime);
    parkingSlot.releaseCar(car, departureTime);

    // the parking slot is not used
    assertNull(parkingSlot.getUsedBy());
    // there is no arrival time set
    assertNull(parkingSlot.getArrivalTime());
    // the car cannot be released from an empty parking slot
    assertFalse(parkingSlot.releaseCar(car, departureTime));

  }
}