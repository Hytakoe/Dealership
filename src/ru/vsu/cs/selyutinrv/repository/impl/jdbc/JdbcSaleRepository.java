package ru.vsu.cs.selyutinrv.repository.impl.jdbc;

import ru.vsu.cs.selyutinrv.model.Customer;
import ru.vsu.cs.selyutinrv.model.Sale;
import ru.vsu.cs.selyutinrv.model.Vehicle;
import ru.vsu.cs.selyutinrv.repository.CustomerRepository;
import ru.vsu.cs.selyutinrv.repository.SaleRepository;
import ru.vsu.cs.selyutinrv.repository.VehicleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleRepository implements SaleRepository {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public JdbcSaleRepository(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Sale save(Sale sale) {
        String sql = "INSERT INTO sales (vehicle_id, customer_id, sale_date, sale_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, sale.getVehicle().getId());
            stmt.setLong(2, sale.getCustomer().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(sale.getSaleDate()));
            stmt.setDouble(4, sale.getSalePrice());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new Sale(
                        rs.getLong(1),
                        sale.getVehicle(),
                        sale.getCustomer(),
                        sale.getSaleDate(),
                        sale.getSalePrice()
                );
            }
            return sale;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving sale", e);
        }
    }

    @Override
    public List<Sale> findAll() {
        String sql = "SELECT * FROM sales ORDER BY sale_date DESC";
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
            return sales;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all sales", e);
        }
    }

    @Override
    public List<Sale> findSalesByCustomerId(Long customerId) {
        String sql = "SELECT * FROM sales WHERE customer_id = ? ORDER BY sale_date DESC";
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
            return sales;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding sales by customer", e);
        }
    }

    @Override
    public List<Sale> findSalesByVehicleId(Long vehicleId) {
        String sql = "SELECT * FROM sales WHERE vehicle_id = ? ORDER BY sale_date DESC";
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
            return sales;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding sales by vehicle", e);
        }
    }

    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        Long vehicleId = rs.getLong("vehicle_id");
        Long customerId = rs.getLong("customer_id");

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found: " + vehicleId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        return new Sale(
                rs.getLong("id"),
                vehicle,
                customer,
                rs.getTimestamp("sale_date").toLocalDateTime(),
                rs.getDouble("sale_price")
        );
    }
}