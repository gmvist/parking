package com.exercises.parking.impl;

import com.exercises.parking.api.CarType;
import com.exercises.parking.api.IBill;
import com.exercises.parking.api.ICar;
import com.exercises.parking.api.IPricingPolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class EntryFeePricingPolicyTest {

  IPricingPolicy entryFeePricingPolicyTest;
  IPricingPolicy basePricingPolicy;
  ParkingSlot parkingSlot;
  ICar car;
  Date initialTime = new Date();

  @Before
  public void setUp() throws Exception {
    basePricingPolicy = new BasicPricingPolicy(1.0);
    entryFeePricingPolicyTest = new EntryFeePricingPolicy(basePricingPolicy, 3.0);
    car = new Car(CarType.SEDAN);
    parkingSlot = new ParkingSlot(CarType.SEDAN);
  }

  @Test
  public void bill() {

    // bill 2 hours => expected 3.0 + 2*1.0 = 5.0
    IBill bill = entryFeePricingPolicyTest.bill(car, parkingSlot, initialTime, new Date(initialTime.getTime()+1000*3600*2));
    assertEquals(5.0, bill.getParkingFee());
  }
}