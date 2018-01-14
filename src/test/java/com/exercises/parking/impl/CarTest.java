package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.ICar;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CarTest {

  ParkingSlot parkingSlot;
  Car car;
  Date initialTime;
  Date departureTime;

  @Before
  public void setUp() throws Exception {

    parkingSlot = new ParkingSlot(CarType.SEDAN);
    car = new Car(CarType.SEDAN);
    initialTime = new Date();
    departureTime = new Date(initialTime.getTime()+1000*3600*1);

  }

  @Test
  public void testGettersAndSetters(){

    assertEquals(CarType.SEDAN, car.getType());

    car.setType(CarType.ELECTRIC_50KW);
    assertEquals(CarType.ELECTRIC_50KW, car.getType());

  }

  @Test
  public void park() {

    car.park(parkingSlot, initialTime);
    // the parking slot has the right type
    assertEquals(car.getType(), parkingSlot.getUsedBy().getType());
    // the parking slot has the right arrival time
    assertEquals(initialTime, parkingSlot.getArrivalTime());
  }

  @Test
  public void unpark() {

    car.park(parkingSlot, initialTime);
    car.unpark(parkingSlot, departureTime);
    // the parking slot is not used
    assertNull(parkingSlot.getUsedBy());
    // there is no arrival time set
    assertNull(parkingSlot.getArrivalTime());
  }

}