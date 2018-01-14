package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.IBill;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IParkingSlot;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class BillTest {

  IParkingSlot parkingSlot;
  ICar car;
  Date arrivalTime;
  Date departureTime;

  @Before
  public void setUp() {

    parkingSlot = new ParkingSlot(CarType.SEDAN);
    car = new Car(CarType.SEDAN);
    arrivalTime = new Date();
    departureTime = new Date(arrivalTime.getTime()+1000*3600*3);
  }


  @Test
  public void testGetters() {

    IBill bill = new Bill(car, parkingSlot, arrivalTime, departureTime, 1.0);

    assertEquals(car, bill.getCar());
    assertEquals(parkingSlot, bill.getParkingSlot());
    assertEquals(arrivalTime, bill.getArrivalTime());
    assertEquals(departureTime, bill.getDepartureTime());
    assertEquals(1.0, bill.getParkingFee());
  }

}