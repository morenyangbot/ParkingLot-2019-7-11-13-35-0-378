package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {
    @Test
    void should_get_the_car_in_fetch_when_call_the_ticket_and_return_the_car_parked() {
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

    @Test
    void should_not_fetch_car_when_give_a_wrong_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);

        Ticket wrongTicket = new Ticket();
        Car fetchedCar = parkingBoy.fetch(wrongTicket);

        Assertions.assertNull(fetchedCar);
    }

    @Test
    void should_not_fetch_car_when_give_no_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(null);

        Assertions.assertNull(fetchedCar);
    }

    @Test
    void should_not_fetch_car_when_given_an_used_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(car);

        parkingBoy.fetch(ticket);
        Car fetchedCarSecondTime = parkingBoy.fetch(ticket);
        Assertions.assertNull(fetchedCarSecondTime);
    }
}
