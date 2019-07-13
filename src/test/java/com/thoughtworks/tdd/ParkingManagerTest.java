package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingManagerTest {
    @Test
    void should_manage_multiple_parking_boy_to_park_and_fetch_a_car() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());

        parkingManager.manage(parkingBoy);
        parkingManager.manage(smartParkingBoy);
        parkingManager.manage(superSmartParkingBoy);

        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        Car fetchedCar = parkingManager.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_manage_parking_lot_by_parking_manager() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.addParkingLot(new ParkingLot());

        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        Car fetchedCar = parkingManager.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
    }
}
