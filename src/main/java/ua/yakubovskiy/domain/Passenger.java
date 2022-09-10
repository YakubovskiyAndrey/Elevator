package ua.yakubovskiy.domain;

import java.util.Objects;
import java.util.Random;

public class Passenger {

    private int id;
    private int currentFloor;
    private int targetFloor;
    private Direction direction;

    private static int counter;

    public Passenger(int currentFloor) {
        id = ++counter;
        this.currentFloor = currentFloor;
        generateTargetFloor();
    }

    public void generateTargetFloor() {
        Random random = new Random();
        int floorNum;
        do {
            floorNum = 1 + random.nextInt(BuildingSize.FLOORS.getAmountFloors());
        } while (floorNum == currentFloor);
        this.targetFloor = floorNum;
        this.direction = currentFloor > targetFloor ? Direction.DOWN : Direction.UP;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    public int getTargetFloor() {
        return targetFloor;
    }
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id
                && currentFloor == passenger.currentFloor
                && targetFloor == passenger.targetFloor
                && direction == passenger.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentFloor, targetFloor, direction);
    }

    @Override
    public String toString() {
        return "id=p" + id + " tf=" + targetFloor + "(" + direction.toString() + ")";
    }
}
