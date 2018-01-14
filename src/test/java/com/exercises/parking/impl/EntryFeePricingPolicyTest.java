package com.exercises.parking.impl;

import com.exercises.parking.api.IPricingPolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class EntryFeePricingPolicyTest {

  IPricingPolicy entryFeePricingPolicyTest;
  IPricingPolicy basePricingPolicy;
  Date initialTime = new Date();

  @Before
  public void setUp() throws Exception {
    basePricingPolicy = new BasicPricingPolicy(1.0);
    entryFeePricingPolicyTest = new EntryFeePricingPolicy(basePricingPolicy, 3.0);
  }

  @Test
  public void bill() {

    // bill 2 hours => expected 3.0 + 2*1.0 = 5.0
    double bill = entryFeePricingPolicyTest.bill(initialTime, new Date(initialTime.getTime()+1000*3600*2));
    assertEquals(5.0, bill);
  }
}