package selyutinrv.repository.impl.jdbc;

import selyutinrv.model.Customer;
import selyutinrv.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerRepository implements CustomerRepository {

    private static final String SQL_INSERT =
            "INSERT INTO customers (full_name, age, gender, phone, email, address) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE customers SET full_name = ?, age = ?, gender = ?, phone = ?, email = ?, address = ? WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM customers WHERE id = ?";

    private static final String SQL_SELECT_BY_NAME =
            "SELECT * FROM customers WHERE full_name = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM customers ORDER BY id";

    private static final String SQL_EXISTS_BY_ID =
            "SELECT 1 FROM customers WHERE id = ?";

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            return insert(customer);
        }
        update(customer);
        return customer;
    }

    private Customer insert(Customer customer) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            fillCustomerStatement(stmt, customer);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new Customer(
                        rs.getLong(1),
                        customer.getFullName(),
                        customer.getAge(),
                        customer.getGender(),
                        customer.getPhone(),
                        customer.getEmail(),
                        customer.getAddress()
                );
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {

            fillCustomerStatement(stmt, customer);
            stmt.setLong(7, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToCustomer(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by id", e);
        }
    }

    @Override
    public Optional<Customer> findByFullName(String fullName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_NAME)) {

            stmt.setString(1, fullName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToCustomer(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by name", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL)) {

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all customers", e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_EXISTS_BY_ID)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking customer existence", e);
        }
    }

    private void fillCustomerStatement(PreparedStatement stmt, Customer customer) throws SQLException {
        stmt.setString(1, customer.getFullName());
        stmt.setInt(2, customer.getAge());
        stmt.setString(3, customer.getGender());
        stmt.setString(4, customer.getPhone());
        stmt.setString(5, customer.getEmail());
        stmt.setString(6, customer.getAddress());
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address")
        );
    }
}