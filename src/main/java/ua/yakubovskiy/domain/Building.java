package ua.yakubovskiy.domain;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private final Elevator elevator;
    private final List<Floor> floors = new ArrayList<>();

    public Building() {
        int amountFloors = BuildingSize.FLOORS.getAmountFloors();
        for (int i = 1; i <= amountFloors; i++) {
            floors.add(new Floor(i));
        }
        this.elevator = new Elevator(Direction.UP, floors.get(0), amountFloors);
    }
    public void start(int steps){
        for(int i = 1; i <= steps; i++){
            elevator.openDoor();
            int peopleEntered = elevator.getPeopleEntered();
            List<Passenger> peopleGotOut = elevator.getPeopleGotOut();
            if(peopleGotOut != null && !peopleGotOut.isEmpty()) addPassengerInQueue(peopleGotOut);
            if(peopleGotOut != null) showFrame(i, peopleGotOut.size(), peopleEntered);
            int indexCurrentFloor = floors.indexOf(elevator.getCurrentFloor());
            if(indexCurrentFloor < (floors.size()-1) && elevator.getDirection().equals(Direction.UP)){
                Floor floor = floors.get(indexCurrentFloor + 1);
                elevator.setCurrentFloor(floor);
            }else if(indexCurrentFloor > 0 && elevator.getDirection().equals(Direction.DOWN)){
                Floor floor = floors.get(indexCurrentFloor - 1);
                elevator.setCurrentFloor(floor);
            }
        }
    }

    private void addPassengerInQueue(List<Passenger> peopleGotOut){
        peopleGotOut.stream()
                .forEach(passenger -> {
                    passenger.setCurrentFloor(elevator.getCurrentFloor().getNumber());
                    elevator.getCurrentFloor().getQueue().add(passenger);
                    passenger.generateTargetFloor();
                });
    }

    private void showFrame(int step, int peopleGotOut, int peopleEntered){
        System.out.println("****************************************** Step " + step + " ******************************************");
        StringBuilder stringBuilder = new StringBuilder();
        for (Floor floor: floors){
            if(elevator.getCurrentFloor().getNumber() != floor.getNumber())
                stringBuilder.append(floor.getNumber() + " floor: waiting[" + floor+ "]\n");
            else
                stringBuilder.append(">"+floor.getNumber() + " floor: waiting[" + floor+ "]\ninfo: Elevator:{" +elevator+"} "+
                        peopleGotOut+" people got out of the elevator. "+peopleEntered+" people entered the elevator.\n");
        }
        System.out.print(stringBuilder);
    }
}
