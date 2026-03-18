package ru.cs.vsu.selyutinrv.model.builder;

import ru.cs.vsu.selyutinrv.model.BodyType;
import ru.cs.vsu.selyutinrv.model.Car;

public class CarBuilder {
    private String brand;
    private String model;
    private int year;
    private double price;
    private String color;
    private BodyType bodyType;
    private int doorCount;
    private int passengerCapacity;
    private String transmission;
    private double engineVolume;
    private String fuelType;

    public CarBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder year(int year) {
        this.year = year;
        return this;
    }

    public CarBuilder price(double price) {
        this.price = price;
        return this;
    }

    public CarBuilder color(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder bodyType(BodyType bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public CarBuilder doorCount(int doorCount) {
        this.doorCount = doorCount;
        return this;
    }

    public CarBuilder passengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
        return this;
    }

    public CarBuilder transmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public CarBuilder engineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
        return this;
    }

    public CarBuilder fuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public Car build() {
        // Валидация обязательных полей
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не может быть пустой");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        if (year < 1900 || year > 2026) {
            throw new IllegalArgumentException("Некорректный год");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }

        return new Car(brand, model, year, price, color, bodyType,
                doorCount, passengerCapacity, transmission, engineVolume, fuelType);
    }
}