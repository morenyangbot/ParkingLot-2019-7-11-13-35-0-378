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

    @Test
    void should_park_and_get_multiple_cars_and_fetch_correct_cars_by_tickets() {
        Car car = new Car();
        Car anotherCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(car);
        Ticket anotherTicket = parkingBoy.park(anotherCar);

        Car anotherFetchedCar = parkingBoy.fetch(anotherTicket);
        Car fetchedCar = parkingBoy.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
        Assertions.assertEquals(anotherCar, anotherFetchedCar);
    }
}
