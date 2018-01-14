package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.IBill;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IPricingPolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class BasicPricingPolicyTest {

  IPricingPolicy pricingPolicy;
  ParkingSlot parkingSlot;
  ICar car;

  Date initialTime = new Date();

  @Before
  public void setUp() throws Exception {
    pricingPolicy = new BasicPricingPolicy(2.5);
    car = new Car(CarType.SEDAN);
    parkingSlot = new ParkingSlot(CarType.SEDAN);
  }

  @Test
  public void bill() {

   // bill 3 hours
   IBill bill = pricingPolicy.bill(car, parkingSlot, initialTime, new Date(initialTime.getTime()+1000*3600*3));
   assertEquals(7.5, bill.getParkingFee());
  }
}