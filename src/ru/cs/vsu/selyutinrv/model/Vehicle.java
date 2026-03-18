package ru.cs.vsu.selyutinrv.model;

public abstract class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String color;
    private boolean sold;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int year, double price, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.sold = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public abstract String getVehicleType();

    public abstract String getSpecificInfo();

    @Override
    public String toString() {
        return String.format("%s %s %d, %s, %.2f руб.",
                brand, model, year, color, price);
    }
}