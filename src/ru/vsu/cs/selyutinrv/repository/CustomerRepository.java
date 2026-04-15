package ru.vsu.cs.selyutinrv.repository;

import ru.vsu.cs.selyutinrv.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    Optional<Customer> findByFullName(String fullName);
    List<Customer> findAll();
    void update(Customer customer);  // Сохраняем с существующим ID
    boolean existsById(Long id);
}