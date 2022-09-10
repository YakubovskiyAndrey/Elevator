package ua.yakubovskiy.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    private Floor floor;

    @BeforeEach
    void setUp() {
        floor = new Floor(1);
    }

    @Test
    void removePassengerFromQueueTest(){
        List<Passenger> queue = new ArrayList<>();
        queue.add(new Passenger(1));
        floor.setQueue(queue);
        assertFalse(floor.getQueue().isEmpty());
        floor.removePassengerFromQueue(queue);
        assertTrue(floor.getQueue().isEmpty());
    }
}