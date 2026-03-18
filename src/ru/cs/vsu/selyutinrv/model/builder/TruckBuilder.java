package ru.cs.vsu.selyutinrv.model.builder;

import ru.cs.vsu.selyutinrv.model.Truck;
import ru.cs.vsu.selyutinrv.model.TruckType;

public class TruckBuilder {
    private String brand;
    private String model;
    private int year;
    private double price;
    private String color;
    private TruckType truckType;
    private double loadCapacity;
    private int axleCount;
    private double cargoVolume;
    private boolean hasTrailerHitch;
    private String bodyMaterial;

    public TruckBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public TruckBuilder model(String model) {
        this.model = model;
        return this;
    }

    public TruckBuilder year(int year) {
        this.year = year;
        return this;
    }

    public TruckBuilder price(double price) {
        this.price = price;
        return this;
    }

    public TruckBuilder color(String color) {
        this.color = color;
        return this;
    }

    public TruckBuilder truckType(TruckType truckType) {
        this.truckType = truckType;
        return this;
    }

    public TruckBuilder loadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
        return this;
    }

    public TruckBuilder axleCount(int axleCount) {
        this.axleCount = axleCount;
        return this;
    }

    public TruckBuilder cargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
        return this;
    }

    public TruckBuilder hasTrailerHitch(boolean hasTrailerHitch) {
        this.hasTrailerHitch = hasTrailerHitch;
        return this;
    }

    public TruckBuilder bodyMaterial(String bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
        return this;
    }

    public Truck build() {
        // Валидация
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не может быть пустой");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        if (loadCapacity <= 0) {
            throw new IllegalArgumentException("Грузоподъемность должна быть положительной");
        }

        return new Truck(brand, model, year, price, color, truckType,
                loadCapacity, axleCount, cargoVolume, hasTrailerHitch, bodyMaterial);
    }
}