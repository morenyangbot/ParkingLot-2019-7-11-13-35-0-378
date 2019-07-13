package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy() {
        parkingLots = new ArrayList<>();
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this();
        this.addParkingLot(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsCar(car)) {
                return null;
            }
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        System.err.println("Not enough position.");
        return null;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.err.println("Please provide your parking ticket.");
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        System.err.println("Unrecognized parking ticket.");
        return null;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
