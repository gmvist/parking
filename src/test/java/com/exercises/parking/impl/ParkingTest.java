package com.exercises.parking.impl;

import com.exercises.parking.api.*;
import com.exercises.parking.api.exceptions.exceptions.ParkingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Unit test for Parking API
 *
 * @author mghita
 */
public class ParkingTest {

  private IParking parking;
  private Set<IParkingSlot> freeParkingSlots;

  @Before
  public void setUp() {

    //create some parking slots
    freeParkingSlots = new HashSet<IParkingSlot>();

    freeParkingSlots.addAll(Arrays.asList(
            new ParkingSlot(CarType.SEDAN),
            new ParkingSlot(CarType.SEDAN),
            new ParkingSlot(CarType.ELECTRIC_20KW),
            new ParkingSlot(CarType.ELECTRIC_50KW)));

    parking = new Parking(freeParkingSlots, new EntryFeePricingPolicy( new BasicPricingPolicy(2.0), 1.0));

  }

  @Test
  public void testFreeParkingSlots() {

    // test the parking initially has 4 parking slots available.
    assertEquals(4, freeParkingSlots.size());
  }

  @Test
  public void testSedan() {

    try {

      // SEDAN car enters and leaves after 1 hour  => expected bill = 3.0
      Date initialTime = new Date();
      ICar car = new Car(CarType.SEDAN);
      carCheckIn(car, initialTime, 3, 1);
      carCheckOut(car, addHours(initialTime,1.0), 4, 2, 3.0);

      // two SEDAN cars enter the parking
      carCheckIn(new Car(CarType.SEDAN), new Date(), 3, 1);
      carCheckIn(new Car(CarType.SEDAN), new Date(), 2, 0);
    } catch (ParkingException e) {
      Assert.fail(e.getMessage());
    }
    try {
      //third SEDAN car tries to checkIn => rejected as no available place
      carCheckIn(new Car(CarType.SEDAN), new Date(), 2, 0);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.NO_SLOT_AVAILABLE, CarType.SEDAN), e.getMessage());
    }
  }

  @Test
  public void testElectric20KW() {

    try {
      // ELECTRIC_20KW car enters and leaves after 2 hours => expected bill = 5.0
      Date initialTime = new Date();
      ICar car = new Car(CarType.ELECTRIC_20KW);
      carCheckIn(car, initialTime, 3, 0);
      carCheckOut(car, addHours(initialTime,2.0), 4, 1, 5.0);

      // ELECTRIC_20KW cars enter the parking
      carCheckIn(new Car(CarType.ELECTRIC_20KW), new Date(), 3, 0);
    } catch (ParkingException e) {
      Assert.fail(e.getMessage());
    }
    try {
      //no available place for ELECTRIC_20KW cars
      carCheckIn(new Car(CarType.ELECTRIC_20KW), new Date(), 2, 0);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.NO_SLOT_AVAILABLE, CarType.ELECTRIC_20KW), e.getMessage());
    }
  }

  @Test
  public void testElectric50KW() {

    try {
      // ELECTRIC_50KW car enters and leaves after 3 hours => expected bill = 7.0
      Date initialTime = new Date();
      ICar car = new Car(CarType.ELECTRIC_50KW);
      carCheckIn(car, initialTime, 3, 0);
      carCheckOut(car, addHours(initialTime,3.0), 4, 1, 7.0);

      // ELECTRIC_50KW cars enter the parking
      carCheckIn(new Car(CarType.ELECTRIC_50KW), new Date(), 3, 0);
    } catch (ParkingException e) {
      Assert.fail(e.getMessage());
    }
    try {
      //no available place for ELECTRIC_50KW cars
      carCheckIn(new Car(CarType.ELECTRIC_50KW), new Date(), 2, 0);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.NO_SLOT_AVAILABLE, CarType.ELECTRIC_50KW), e.getMessage());
    }
  }

  @Test
  public void testVariousCars() {

    Date initialTime = new Date();
    ICar carSedan1 = new Car(CarType.SEDAN);
    ICar car20KW1 = new Car(CarType.ELECTRIC_20KW);
    ICar car50KW1 = new Car(CarType.ELECTRIC_50KW);
    ICar car50KW2 = new Car(CarType.ELECTRIC_50KW);

    try {
      carCheckIn(carSedan1, initialTime, 3, 1);
      carCheckIn(car50KW1, addHours(initialTime,1.5), 2, 0);
      carCheckIn(car20KW1, addHours(initialTime,1.75), 1, 0);
      carCheckOut(car20KW1, addHours(initialTime,2.0), 2, 1, 1.5);
      carCheckOut(carSedan1, addHours(initialTime,2.45), 3, 2, 5.9);

    } catch (ParkingException e) {
      Assert.fail(e.getMessage());
    }

    try {
      //no available place for ELECTRIC_50KW cars
      carCheckIn(car50KW2, new Date(), 4, 0);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.NO_SLOT_AVAILABLE, car50KW2.getType()), e.getMessage());
    }

  }

    @Test
  public void testUnknownCarCheckOut() {

    Car car = new Car(CarType.ELECTRIC_50KW);
    try {
      //unknown car leaves the parking
      carCheckOut(car, new Date(), 3, 0, 1.5);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.CAR_NOT_FOUND, car.getType()), e.getMessage());
    }

  }

  @Test
  public void testExistingCarCheckIn() {

    Car car = new Car(CarType.SEDAN);
    try {
      carCheckIn(car, new Date(), 3, 1);
      //existing car enters again in the parking
      carCheckIn(car, new Date(), 2, 0);
    } catch (ParkingException e) {
      Assert.assertEquals(String.format(ParkingException.CAR_ALREADY_PARKED, car.getType()), e.getMessage());
    }

  }

  private IParkingSlot carCheckIn(ICar car, Date arrivalTime, long freeSlots, long typeFreeSlots) throws ParkingException {

    IParkingSlot slot = parking.checkIn(car, arrivalTime);

    //check the parking has expected slots available
    assertEquals(freeSlots, freeParkingSlots.size());

    //check the parking has expected type slots available
    assertEquals(typeFreeSlots, freeParkingSlots.stream()
            .filter(p -> p.getType() == car.getType())
            .collect(Collectors.counting()).longValue());

    //check the car was parked on the proper parking slot
    assertTrue(slot.getType() == car.getType());

    return slot;
  }

  private void carCheckOut(ICar car, Date departureTime, long freeSlots, long typeFreeSlots, double expectedBill) throws ParkingException {

    IBill bill = parking.checkOut(car, departureTime);

    //check the parking has expectedTotalFreeSlotsLeft parking slots left
    assertEquals(freeSlots, freeParkingSlots.size());

    //check the parking has expectedCarTypeFreeSlotsLeft sedan parking slot free
    assertEquals(typeFreeSlots, freeParkingSlots.stream()
            .filter(p -> p.getType() == car.getType())
            .collect(Collectors.counting()).longValue());

    //check the bill
    assertEquals(expectedBill, bill.getParkingFee());

  }

  private Date addHours(Date initialDate, double hoursToAdd){

    long timeTOAdd = (long) (1000*3600*hoursToAdd);
    return new Date(initialDate.getTime() + timeTOAdd);
  }

}
