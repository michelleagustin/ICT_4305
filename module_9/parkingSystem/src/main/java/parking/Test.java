package parking;

interface Drivable {
    void drive();
 }
 
 abstract class Vehicle {
    String model;
    abstract void refuel();
 }