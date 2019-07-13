package com.thoughtworks.tdd;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy() {
        super();
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }


    @Override
    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.containsCar(car)) {
                return null;
            }
        }

        ParkingLot targetParkingLot = parkingLots.stream().sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return Double.compare(o1.getRemainder() / o1.getCapacity(), o2.getRemainder() / o2.getCapacity());
            }
        }).collect(Collectors.toList()).get(0);

        if (targetParkingLot.isFull()) {
            System.err.println("Not enough position.");
            return null;
        }

        return targetParkingLot.park(car);

    }
}
