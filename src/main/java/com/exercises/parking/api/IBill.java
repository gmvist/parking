package com.exercises.parking.api;

import java.util.Date;

public interface IBill {

  double getParkingFee();
  Date getArrivalTime();
  Date getDepartureTime();
  ICar getCar();
  IParkingSlot getParkingSlot();
}
