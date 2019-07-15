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
            System.err.print("Can not add a empty parking lot list.\n");
            return;
        }
        parkingLots.forEach(this::addParkingLot);
    }

    public Ticket park(Car car) {
        if (parkingLots == null) {
            System.err.print("Parking boy has no parking lot.\n");
            return null;
        }
        if (car == null || containsCar(car)) {
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        System.err.print("Not enough position.\n");
        return null;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.err.print("Please provide your parking ticket.\n");
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.containsTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        System.err.print("Unrecognized parking ticket.\n");
        return null;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        if (parkingLot == null) {
            System.err.print("Can not add a empty parking lot.\n");
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

    protected boolean containsCar(Car car) {
        List<ParkingLot> targetList = parkingLots.stream().filter(parkingLot -> parkingLot.containsCar(car)).collect(Collectors.toList());
        return targetList.size() > 0;
    }
}
