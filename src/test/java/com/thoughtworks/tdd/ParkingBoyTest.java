package com.thoughtworks.tdd;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParkingBoyTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }


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
        Assertions.assertEquals("Unrecognized parking ticket.\n", errContent.toString());
    }

    @Test
    void should_not_fetch_car_when_give_no_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(null);

        Assertions.assertNull(fetchedCar);
        Assertions.assertEquals("Please provide your parking ticket.\n", errContent.toString());
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
        Assertions.assertEquals("Unrecognized parking ticket.\n", errContent.toString());
    }

    @Test
    void should_not_park_car_when_parking_lot_full() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        Ticket overflowCarTicket = parkingBoy.park(new Car());
        Assertions.assertNull(overflowCarTicket);
        Assertions.assertEquals("Not enough position.\n", errContent.toString());
    }

    @Test
    void should_not_allow_park_a_parked_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);
        Ticket ticket = parkingBoy.park(car);

        Assertions.assertNull(ticket);
    }

    @Test
    void should_not_allow_park_null() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(null);

        Assertions.assertNull(ticket);
    }
}
