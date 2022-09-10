package ua.yakubovskiy.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    private Elevator elevator;
    private Floor floor;

    @BeforeEach
    void setUp() {
        floor = new Floor(1);
        elevator = new Elevator(Direction.UP, floor, 20);
    }

    @Test
    void passengerEnterElevator(){
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1));
        floor.setQueue(passengers);
        assertTrue(elevator.elevatorIsEmpty());
        elevator.openDoor();
        assertFalse(elevator.elevatorIsEmpty());
    }

    @Test
    void passengerExitElevator(){
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1));
        floor.setQueue(passengers);
        assertTrue(elevator.getPeopleGotOut().isEmpty());
        elevator.openDoor();
        elevator.getPassengers().get(0).setTargetFloor(1);
        elevator.openDoor();
        assertFalse(elevator.getPeopleGotOut().isEmpty());
    }
}