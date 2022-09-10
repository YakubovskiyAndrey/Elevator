package ua.yakubovskiy.domain;

import java.util.Random;

public enum BuildingSize {
    FLOORS(5, 20);
    private final int min;
    private final int max;
    private final int amountFloors;

    BuildingSize(int min, int max) {
        this.min = min;
        this.max = max;
        this.amountFloors = generateAmountFloors();
    }

    private int generateAmountFloors(){
        Random random = new Random();
        return random.nextInt((getMax() - getMin()) + 1) + getMin();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getAmountFloors() {
        return amountFloors;
    }
}
