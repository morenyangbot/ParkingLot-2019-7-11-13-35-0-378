package com.thoughtworks.tdd;

import java.util.ArrayList;
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
        if (isParkable(car)) {
            return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).collect(Collectors.toList()).get(0)
                    .park(car);
        }
        return null;
    }

    protected boolean isParkable(Car car) {
        if (car == null || containsCar(car)) {
            return false;
        }
        if (isParkingLotsFull()) {
            System.err.print("Not enough position.\n");
            return false;
        }
        return true;
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
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    protected boolean containsCar(Car car) {
        List<ParkingLot> targetList = parkingLots.stream().filter(parkingLot -> parkingLot.containsCar(car)).collect(Collectors.toList());
        return targetList.size() > 0;
    }
}
