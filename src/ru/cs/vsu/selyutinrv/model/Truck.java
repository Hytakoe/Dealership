package ru.cs.vsu.selyutinrv.model;

public class Truck extends Vehicle {
    private TruckType truckType;
    private double loadCapacity;
    private int axleCount;
    private double cargoVolume;
    private boolean hasTrailerHitch;
    private String bodyMaterial;

    public Truck() {
    }

    public Truck(String brand, String model, int year, double price, String color,
                 TruckType truckType, double loadCapacity, int axleCount,
                 double cargoVolume, boolean hasTrailerHitch, String bodyMaterial) {
        super(brand, model, year, price, color);
        this.truckType = truckType;
        this.loadCapacity = loadCapacity;
        this.axleCount = axleCount;
        this.cargoVolume = cargoVolume;
        this.hasTrailerHitch = hasTrailerHitch;
        this.bodyMaterial = bodyMaterial;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(int axleCount) {
        this.axleCount = axleCount;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public boolean isHasTrailerHitch() {
        return hasTrailerHitch;
    }

    public void setHasTrailerHitch(boolean hasTrailerHitch) {
        this.hasTrailerHitch = hasTrailerHitch;
    }

    public String getBodyMaterial() {
        return bodyMaterial;
    }

    public void setBodyMaterial(String bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    @Override
    public String getVehicleType() {
        return "Грузовой";
    }

    @Override
    public String getSpecificInfo() {
        return String.format("Тип: %s, Грузоподъемность: %.1fт, Оси: %d, Объем кузова: %.1fм³, Фаркоп: %s, Материал: %s",
                truckType.getDisplayName(), loadCapacity, axleCount, cargoVolume,
                hasTrailerHitch ? "есть" : "нет", bodyMaterial);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s",
                super.toString(), getVehicleType(), truckType.getDisplayName(), getSpecificInfo());
    }
}