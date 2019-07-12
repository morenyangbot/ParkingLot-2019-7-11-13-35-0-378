package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {
    @Test
    void should_get_the_car_in_fetchCar_when_call_the_ticket_and_return_the_car_parked() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
    }
}
