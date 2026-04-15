package ru.cs.vsu.selyutinrv.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sale {
    private final Long id;              // final - неизменяемо
    private final Vehicle vehicle;
    private final Customer customer;
    private final LocalDateTime saleDate;
    private final double salePrice;

    public Sale(Vehicle vehicle, Customer customer, double salePrice) {
        this.id = null;
        this.vehicle = vehicle;
        this.customer = customer;
        this.salePrice = salePrice;
        this.saleDate = LocalDateTime.now();
        validate();
    }

    public Sale(Long id, Vehicle vehicle, Customer customer,
                LocalDateTime saleDate, double salePrice) {
        this.id = id;
        this.vehicle = vehicle;
        this.customer = customer;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        validate();
    }

    private void validate() {
        if (vehicle == null) {
            throw new IllegalArgumentException("Автомобиль не может быть null");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Покупатель не может быть null");
        }
        if (salePrice <= 0) {
            throw new IllegalArgumentException("Цена продажи должна быть положительной");
        }
        if (saleDate == null) {
            throw new IllegalArgumentException("Дата продажи не может быть null");
        }
    }

    public Long getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public double getSalePrice() { return salePrice; }

    public String getFormattedSaleDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return saleDate.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("Продажа #%d: %s %s -> %s, дата: %s, сумма: %.2f руб.",
                id, vehicle.getBrand(), vehicle.getModel(), customer.getFullName(),
                getFormattedSaleDate(), salePrice);
    }
}