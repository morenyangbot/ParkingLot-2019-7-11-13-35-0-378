package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingManager {
    private List<ParkingBoy> parkingBoys;
    private Map<Ticket, ParkingBoy> ticketParkingBoyMap;
    private ParkingBoy selfParkingBoy;

    public ParkingManager() {
        parkingBoys = new ArrayList<>();
        this.selfParkingBoy = new ParkingBoy();
        parkingBoys.add(this.selfParkingBoy);
        ticketParkingBoyMap = new HashMap<>();
    }

    public void manage(ParkingBoy parkingBoy) {
        if (parkingBoy == null) {
            System.err.println("Can not manage a empty parking boy.");
            return;
        }
        parkingBoys.add(parkingBoy);
    }

    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        List<ParkingBoy> targetParkingBoyList = parkingBoys.stream().filter(parkingBoy -> !parkingBoy.isParkingLotsFull()).collect(Collectors.toList());
        if (targetParkingBoyList.size() == 0) {
            System.err.println("Not enough position.");
            return null;
        }
        ParkingBoy targetParkingBoy = targetParkingBoyList.get(0);
        Ticket ticket = targetParkingBoy.park(car);
        ticketParkingBoyMap.put(ticket, targetParkingBoy);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.err.println("Please provide your parking ticket.");
            return null;
        }
        ParkingBoy parkingBoy = ticketParkingBoyMap.get(ticket);
        if (parkingBoy == null) {
            System.err.println("Unrecognized parking ticket.");
            return null;
        }
        return parkingBoy.fetch(ticket);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.selfParkingBoy.addParkingLot(parkingLot);
    }
}
