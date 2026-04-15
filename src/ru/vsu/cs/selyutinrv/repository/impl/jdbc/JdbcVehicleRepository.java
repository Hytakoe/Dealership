package ru.vsu.cs.selyutinrv.repository.impl.jdbc;


import ru.vsu.cs.selyutinrv.model.*;
import ru.vsu.cs.selyutinrv.repository.VehicleRepository;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcVehicleRepository implements VehicleRepository {

    @Override
    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            return insert(vehicle);
        } else {
            update(vehicle);
            return vehicle;
        }
    }

    private Vehicle insert(Vehicle vehicle) {
        String sql = """
            INSERT INTO vehicles (brand, model, year, price, color, sold, vehicle_type,
                                  body_type, door_count, passenger_capacity, transmission, 
                                  engine_volume, fuel_type, truck_type, load_capacity, 
                                  axle_count, cargo_volume, has_trailer_hitch, body_material)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            fillVehicleStatement(stmt, vehicle);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                vehicle.setId(rs.getLong(1));
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving vehicle", e);
        }
    }

    public void update(Vehicle vehicle) {
        String sql = """
            UPDATE vehicles SET brand = ?, model = ?, year = ?, price = ?, color = ?, 
                               sold = ?, vehicle_type = ?, body_type = ?, door_count = ?,
                               passenger_capacity = ?, transmission = ?, engine_volume = ?,
                               fuel_type = ?, truck_type = ?, load_capacity = ?, axle_count = ?,
                               cargo_volume = ?, has_trailer_hitch = ?, body_material = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            fillVehicleStatement(stmt, vehicle);
            stmt.setLong(20, vehicle.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating vehicle", e);
        }
    }

    private void fillVehicleStatement(PreparedStatement stmt, Vehicle vehicle) throws SQLException {
        stmt.setString(1, vehicle.getBrand());
        stmt.setString(2, vehicle.getModel());
        stmt.setInt(3, vehicle.getYear());
        stmt.setDouble(4, vehicle.getPrice());
        stmt.setString(5, vehicle.getColor());
        stmt.setBoolean(6, vehicle.isSold());

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            stmt.setString(7, "CAR");
            stmt.setString(8, car.getBodyType().name());
            stmt.setInt(9, car.getDoorCount());
            stmt.setInt(10, car.getPassengerCapacity());
            stmt.setString(11, car.getTransmission());
            stmt.setDouble(12, car.getEngineVolume());
            stmt.setString(13, car.getFuelType());
            stmt.setNull(14, Types.VARCHAR);
            stmt.setNull(15, Types.DECIMAL);
            stmt.setNull(16, Types.INTEGER);
            stmt.setNull(17, Types.DECIMAL);
            stmt.setNull(18, Types.BOOLEAN);
            stmt.setNull(19, Types.VARCHAR);
        } else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            stmt.setString(7, "TRUCK");
            stmt.setNull(8, Types.VARCHAR);
            stmt.setNull(9, Types.INTEGER);
            stmt.setNull(10, Types.INTEGER);
            stmt.setNull(11, Types.VARCHAR);
            stmt.setNull(12, Types.DECIMAL);
            stmt.setNull(13, Types.VARCHAR);
            stmt.setString(14, truck.getTruckType().name());
            stmt.setDouble(15, truck.getLoadCapacity());
            stmt.setInt(16, truck.getAxleCount());
            stmt.setDouble(17, truck.getCargoVolume());
            stmt.setBoolean(18, truck.isHasTrailerHitch());
            stmt.setString(19, truck.getBodyMaterial());
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        String sql = "SELECT * FROM vehicles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToVehicle(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding vehicle by id", e);
        }
    }

    @Override
    public List<Vehicle> findAll() {
        String sql = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all vehicles", e);
        }
    }

    @Override
    public List<Vehicle> findAllAvailable() {
        String sql = "SELECT * FROM vehicles WHERE sold = FALSE";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding available vehicles", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting vehicle", e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM vehicles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking vehicle existence", e);
        }
    }

    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        String vehicleType = rs.getString("vehicle_type");
        Vehicle vehicle;

        if ("CAR".equals(vehicleType)) {
            Car car = new Car();
            car.setBodyType(BodyType.valueOf(rs.getString("body_type")));
            car.setDoorCount(rs.getInt("door_count"));
            car.setPassengerCapacity(rs.getInt("passenger_capacity"));
            car.setTransmission(rs.getString("transmission"));
            car.setEngineVolume(rs.getDouble("engine_volume"));
            car.setFuelType(rs.getString("fuel_type"));
            vehicle = car;
        } else {
            Truck truck = new Truck();
            truck.setTruckType(TruckType.valueOf(rs.getString("truck_type")));
            truck.setLoadCapacity(rs.getDouble("load_capacity"));
            truck.setAxleCount(rs.getInt("axle_count"));
            truck.setCargoVolume(rs.getDouble("cargo_volume"));
            truck.setHasTrailerHitch(rs.getBoolean("has_trailer_hitch"));
            truck.setBodyMaterial(rs.getString("body_material"));
            vehicle = truck;
        }

        vehicle.setId(rs.getLong("id"));
        vehicle.setBrand(rs.getString("brand"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setSold(rs.getBoolean("sold"));

        return vehicle;
    }
}