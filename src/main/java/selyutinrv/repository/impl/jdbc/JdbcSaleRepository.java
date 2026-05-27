package selyutinrv.repository.impl.jdbc;

import selyutinrv.model.Customer;
import selyutinrv.model.Sale;
import selyutinrv.model.Vehicle;
import selyutinrv.repository.CustomerRepository;
import selyutinrv.repository.SaleRepository;
import selyutinrv.repository.VehicleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleRepository implements SaleRepository {

    private static final String SQL_INSERT_SALE =
            "INSERT INTO sales (vehicle_id, customer_id, sale_date, sale_price) VALUES (?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM sales ORDER BY sale_date DESC";

    private static final String SQL_SELECT_BY_CUSTOMER =
            "SELECT * FROM sales WHERE customer_id = ? ORDER BY sale_date DESC";

    private static final String SQL_SELECT_BY_VEHICLE =
            "SELECT * FROM sales WHERE vehicle_id = ? ORDER BY sale_date DESC";

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public JdbcSaleRepository(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Sale save(Sale sale) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_SALE, Statement.RETURN_GENERATED_KEYS)) {

            fillSaleStatement(stmt, sale);
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
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL)) {

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
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_CUSTOMER)) {

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
        List<Sale> sales = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_VEHICLE)) {

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

    private void fillSaleStatement(PreparedStatement stmt, Sale sale) throws SQLException {
        stmt.setLong(1, sale.getVehicle().getId());
        stmt.setLong(2, sale.getCustomer().getId());
        stmt.setTimestamp(3, Timestamp.valueOf(sale.getSaleDate()));
        stmt.setDouble(4, sale.getSalePrice());
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