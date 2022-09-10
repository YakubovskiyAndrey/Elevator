package ua.yakubovskiy.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger(1);
    }

    @Test
    void targetFloorMustBeGreaterThanCurrentFloor(){
        passenger.generateTargetFloor();
        assertTrue(passenger.getTargetFloor() > passenger.getCurrentFloor());
    }
}