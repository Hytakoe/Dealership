package ru.cs.vsu.selyutinrv.model;

public class Car extends Vehicle {
    private BodyType bodyType;
    private int doorCount;
    private int passengerCapacity;
    private String transmission;
    private double engineVolume;
    private String fuelType;

    public Car() {
    }

    public Car(String brand, String model, int year, double price, String color,
               BodyType bodyType, int doorCount, int passengerCapacity,
               String transmission, double engineVolume, String fuelType) {
        super(brand, model, year, price, color);
        this.bodyType = bodyType;
        this.doorCount = doorCount;
        this.passengerCapacity = passengerCapacity;
        this.transmission = transmission;
        this.engineVolume = engineVolume;
        this.fuelType = fuelType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getVehicleType() {
        return "Легковой";
    }

    @Override
    public String getSpecificInfo() {
        return String.format("Тип кузова: %s, Дверей: %d, Мест: %d, Коробка: %s, Объем: %.1fл, Топливо: %s",
                bodyType.getDisplayName(), doorCount, passengerCapacity,
                transmission, engineVolume, fuelType);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s",
                super.toString(), getVehicleType(), bodyType.getDisplayName(), getSpecificInfo());
    }
}