package ru.vsu.cs.selyutinrv.repository.impl.jdbc;

import ru.vsu.cs.selyutinrv.model.Customer;
import ru.vsu.cs.selyutinrv.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerRepository implements CustomerRepository {

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            return insert(customer);
        } else {
            update(customer);
            return customer;
        }
    }

    private Customer insert(Customer customer) {
        String sql = "INSERT INTO customers (full_name, age, gender, phone, email, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFullName());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getAddress());
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
        String sql = "UPDATE customers SET full_name = ?, age = ?, gender = ?, phone = ?, email = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFullName());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getAddress());
            stmt.setLong(7, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM customers WHERE full_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM customers ORDER BY id";
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
        String sql = "SELECT 1 FROM customers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking customer existence", e);
        }
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