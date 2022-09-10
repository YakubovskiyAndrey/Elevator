package ua.yakubovskiy.domain;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private static final int CAPACITY = 5;
    private Direction direction;
    private List<Passenger> passengers = new ArrayList<>();
    private Floor currentFloor;
    private int maxFloor;
    private List<Passenger> peopleGotOut = new ArrayList<>();
    private int peopleEntered;

    public Elevator(Direction direction, Floor currentFloor, int maxFloor) {
        this.direction = direction;
        this.currentFloor = currentFloor;
        this.maxFloor = maxFloor;
    }

    public List<Passenger> getPeopleGotOut() {
        return peopleGotOut;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getPeopleEntered() {
        return peopleEntered;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public boolean elevatorIsFull(){
        return passengers.size() == CAPACITY;
    }

    public boolean elevatorIsEmpty(){
        return passengers.isEmpty();
    }

    public Direction getDirection() {
        return direction;
    }

    private Direction getDirectionFromPeople() {
        if(currentFloor.getNumber() == 1) return Direction.UP;
        else if(getCurrentFloor().getNumber() == maxFloor) return Direction.DOWN;
        else {
            int peoplesWantUp = currentFloor.getQueue().stream()
                    .filter(passenger -> passenger.getDirection().equals(Direction.UP)).toArray().length;
            return currentFloor.getQueue().size() - peoplesWantUp < peoplesWantUp ? Direction.UP : Direction.DOWN;
        }
    }

    public void openDoor(){
        if(!elevatorIsEmpty()){
            peopleGotOut = removePassengers();
        }
        if(elevatorIsEmpty()) {
            this.direction = getDirectionFromPeople();
        }
        peopleEntered = addPassengersToElevator();
    }

    private void setDirection(){
        if(currentFloor.getNumber() == 1) direction = Direction.UP;
        else if(currentFloor.getNumber() == maxFloor) direction = Direction.DOWN;
    }

    private int addPassengersToElevator(){
        setDirection();
        List<Passenger> passengersToRemoveFromQueue = new ArrayList<>();
        for (Passenger passenger: currentFloor.getQueue()){
            if(elevatorIsFull()){
                break;
            }
            if(direction.equals(Direction.UP)){
                if(passenger.getTargetFloor() > currentFloor.getNumber()){
                    passengersToRemoveFromQueue.add(passenger);
                    passengers.add(passenger);
                }
            }else if (passenger.getTargetFloor() < currentFloor.getNumber()){
                passengersToRemoveFromQueue.add(passenger);
                passengers.add(passenger);
            }
        }
        currentFloor.removePassengerFromQueue(passengersToRemoveFromQueue);
        return passengersToRemoveFromQueue.size();
    }

    private List<Passenger> removePassengers(){
        List<Passenger> removedPassengers = new ArrayList<>();
             passengers.removeIf(passenger -> {
            if(passenger.getTargetFloor() == currentFloor.getNumber()){
                removedPassengers.add(passenger);
                return true;
            }else {
                return false;
            }
        });
        return removedPassengers;
    }

    @Override
    public String toString() {
        StringBuilder res= new StringBuilder();
        for (Passenger passenger : passengers) {
            res.append(passenger).append("; ");
        }
        if(res.length()>0) res.deleteCharAt(res.length()-1);
        return res.toString();
    }
}
