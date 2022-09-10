package ua.yakubovskiy.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor {
    private static final int MAX_NUMBER_PASSENGERS = 10;
    private int number;
    private List<Passenger> queue = new ArrayList<>();

    public Floor(int number) {
        Random random = new Random();
        this.number = number;
        int amountPassengers = random.nextInt(MAX_NUMBER_PASSENGERS + 1);
        for (int i = 1; i <= amountPassengers; i++) {
            queue.add(new Passenger(number));
        }
    }
    public int getNumber() {
        return number;
    }

    public void removePassengerFromQueue(List<Passenger> passengersToRemove){
        queue.removeIf(passengersToRemove::contains);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Passenger passenger : queue) {
            sb.append(passenger);
            sb.append("; ");
        }
        return sb.toString().trim();
    }
    public List<Passenger> getQueue() {
        return queue;
    }
}
