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
            System.err.print("Can not manage a empty parking boy.\n");
            return;
        }
        if (parkingBoys.contains(parkingBoy)) {
            System.err.print("This manager has already managed the parking boy.\n");
            return;
        }
        parkingBoys.add(parkingBoy);
    }

    public Ticket park(Car car) {
        if (isParkable(car)) {
            ParkingBoy targetParkingBoy = parkingBoys.stream()
                    .filter(parkingBoy -> !parkingBoy.isParkingLotsFull()).collect(Collectors.toList())
                    .get(0);
            Ticket ticket = targetParkingBoy.park(car);
            ticketParkingBoyMap.put(ticket, targetParkingBoy);
            return ticket;
        }
        return null;
    }

    private boolean isParkable(Car car) {
        if (car == null) {
            return false;
        }
        if (parkingBoys.size() == 0 || parkingBoys.stream().allMatch(ParkingBoy::isParkingLotsFull)) {
            System.err.print("Not enough position.\n");
            return false;
        }
        return true;
    }

    public Car fetch(Ticket ticket) {
        if (checkTicket(ticket)) {
            return ticketParkingBoyMap.get(ticket).fetch(ticket);
        }
        return null;
    }

    private boolean checkTicket(Ticket ticket) {
        if (ticket == null) {
            System.err.print("Please provide your parking ticket.\n");
            return false;
        }
        if (!ticketParkingBoyMap.containsKey(ticket)) {
            System.err.print("Unrecognized parking ticket.\n");
            return false;
        }
        return true;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.selfParkingBoy.addParkingLot(parkingLot);
    }
}
