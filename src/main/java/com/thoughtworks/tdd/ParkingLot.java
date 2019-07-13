package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> ticketCarMap;
    private int capacity = 10;

    protected int getCapacity() {
        return capacity;
    }

    public ParkingLot() {
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this();
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (isFull() || containsCar(car) || car == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCarMap.remove(ticket);
    }

    public boolean isFull() {
        return ticketCarMap.size() >= capacity;
    }

    public boolean isEmpty() {
        return ticketCarMap.size() == 0;
    }

    public boolean containsTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    public boolean containsCar(Car car) {
        return ticketCarMap.containsValue(car);
    }

    public int getRemainder() {
        return ticketCarMap.size() - capacity;
    }
}
