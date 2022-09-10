package ua.yakubovskiy;

import ua.yakubovskiy.domain.Building;


public class Main {
    public static void main(String[] args) {
        Building building = new Building();
        System.out.println("START");
        building.start(10);
    }
}