package ru.cs.vsu.selyutinrv.repository.impl;

import ru.cs.vsu.selyutinrv.model.Vehicle;
import ru.cs.vsu.selyutinrv.repository.VehicleRepository;

import java.util.*;

public class InMemoryVehicleRepository implements VehicleRepository {
    private final Map<Long, Vehicle> storage = new HashMap<>();
    private long currentId = 1;

    @Override
    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            vehicle.setId(currentId++);
        }
        storage.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Vehicle> findAllAvailable() {
        return storage.values().stream()
                .filter(vehicle -> !vehicle.isSold())
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

    @Override
    public void update(Vehicle vehicle) {
        if (vehicle.getId() != null && storage.containsKey(vehicle.getId())) {
            storage.put(vehicle.getId(), vehicle);
        }
    }
}