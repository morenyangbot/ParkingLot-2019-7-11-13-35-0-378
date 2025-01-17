package com.thoughtworks.tdd;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy() {
        super();
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }


    @Override
    public Ticket park(Car car) {
        if (!isParkable(car)) {
            return null;
        }

        ParkingLot targetParkingLot = parkingLots.stream().sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o1.getRemainder() - o2.getRemainder();
            }
        }).collect(Collectors.toList()).get(0);

        return targetParkingLot.park(car);

    }
}
