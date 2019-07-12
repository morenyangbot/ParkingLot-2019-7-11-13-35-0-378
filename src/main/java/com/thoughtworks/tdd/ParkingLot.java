package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> ticketCarMap;

    public ParkingLot() {
        this.ticketCarMap = new HashMap<>();
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCarMap.remove(ticket);
    }
}
