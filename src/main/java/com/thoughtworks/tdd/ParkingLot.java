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
            System.err.println("Not enough position.");
            return null;
        }
        if (ticketCarMap.containsValue(car)
                || car == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.err.println("Please provide your parking ticket.");
            return null;
        }
        Car car = ticketCarMap.remove(ticket);
        if (car == null) {
            System.err.println("Unrecognized parking ticket.");
        }
        return car;
    }
}
