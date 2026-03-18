package ru.cs.vsu.selyutinrv.repository;

import ru.cs.vsu.selyutinrv.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findAll();

    List<Vehicle> findAllAvailable();

    void deleteById(Long id);

    boolean existsById(Long id);

    void update(Vehicle vehicle);
}
