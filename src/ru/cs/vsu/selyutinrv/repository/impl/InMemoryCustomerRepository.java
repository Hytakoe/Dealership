package ru.cs.vsu.selyutinrv.repository.impl;

import ru.cs.vsu.selyutinrv.model.Customer;
import ru.cs.vsu.selyutinrv.repository.CustomerRepository;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<Long, Customer> storage = new HashMap<>();
    private long currentId = 1;

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(currentId++);
        }
        storage.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Customer> findByFullName(String fullName) {
        return storage.values().stream()
                .filter(customer -> customer.getFullName().equalsIgnoreCase(fullName))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(Customer customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Cannot update customer without ID");
        }
        if (!storage.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer with ID " + customer.getId() + " not found");
        }
        storage.put(customer.getId(), customer);
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}