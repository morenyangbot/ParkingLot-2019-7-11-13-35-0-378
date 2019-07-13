package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (parkingLots == null) {
            System.err.println("Can not add a empty parking lot list.");
            return;
        }
        parkingLots.forEach(this::addParkingLot);
    }

    public Ticket park(Car car) {
        if (parkingLots == null) {
            System.err.println("Parking boy has no parking lot.");
            return null;
        }
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
        if (parkingLot == null) {
            System.err.println("Can not add a empty parking lot.");
            return;
        }
        parkingLots.add(parkingLot);
    }

    public boolean isParkingLotsFull() {
        if (parkingLots.size() == 0) {
            return true;
        }
        return parkingLots.stream().sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.getRemainder() - o2.getRemainder();
            }
        }).collect(Collectors.toList()).get(0).isFull();
    }
}
