package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> ticketCarMap;
    private int capacity = 10;

    public ParkingLot() {
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this();
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCarMap.remove(ticket);
    }
}
