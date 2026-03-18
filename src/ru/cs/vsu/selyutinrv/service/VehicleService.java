package ru.cs.vsu.selyutinrv.service;

import ru.cs.vsu.selyutinrv.model.Vehicle;
import ru.cs.vsu.selyutinrv.repository.VehicleRepository;

import java.util.List;

public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        validateVehicle(vehicle);
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAllAvailable();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Автомобиль с id " + id + " не найден"));
    }

    public void updateVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null || !vehicleRepository.existsById(vehicle.getId())) {
            throw new IllegalArgumentException("Автомобиль не найден");
        }
        validateVehicle(vehicle);
        vehicleRepository.update(vehicle);
    }

    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new IllegalArgumentException("Автомобиль с id " + id + " не найден");
        }
        vehicleRepository.deleteById(id);
    }

    private void validateVehicle(Vehicle vehicle) {
        if (vehicle.getBrand() == null || vehicle.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Марка автомобиля не может быть пустой");
        }
        if (vehicle.getModel() == null || vehicle.getModel().trim().isEmpty()) {
            throw new IllegalArgumentException("Модель автомобиля не может быть пустой");
        }
        if (vehicle.getYear() < 1900 || vehicle.getYear() > 2026) {
            throw new IllegalArgumentException("Некорректный год выпуска");
        }
        if (vehicle.getPrice() <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
    }
}