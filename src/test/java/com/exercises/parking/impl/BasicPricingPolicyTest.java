package com.exercises.parking.impl;

import com.exercises.parking.api.IPricingPolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class BasicPricingPolicyTest {

  IPricingPolicy pricingPolicy;
  Date initialTime = new Date();

  @Before
  public void setUp() throws Exception {
    pricingPolicy = new BasicPricingPolicy(2.5);
  }

  @Test
  public void bill() {

   // bill 3 hours
   double bill = pricingPolicy.bill(initialTime, new Date(initialTime.getTime()+1000*3600*3));
   assertEquals(7.5, bill);
  }
}