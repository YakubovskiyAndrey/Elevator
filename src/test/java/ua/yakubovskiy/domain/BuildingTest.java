package ua.yakubovskiy.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;

    @BeforeEach
    void setUp() {
        building = new Building();
    }

    @Test
    void floorsMustNotBeEmpty(){
        assertFalse(building.getFloors().isEmpty());
    }

    @Test
    void maximumFloorMustBeGreaterThanOrEqual5AndLessThanOrEqual20(){
        int index = building.getFloors().size() - 1;
        int floorNumber = building.getFloors().get(index).getNumber();
        assertTrue(floorNumber >= 5);
        assertTrue(floorNumber <= 20);
    }
}