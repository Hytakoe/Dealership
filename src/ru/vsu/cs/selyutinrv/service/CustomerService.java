package ru.vsu.cs.selyutinrv.service;

import ru.vsu.cs.selyutinrv.model.Customer;
import ru.vsu.cs.selyutinrv.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель с ID " + id + " не найден"));
    }

    public Customer getCustomerByFullName(String fullName) {
        return customerRepository.findByFullName(fullName)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель \"" + fullName + "\" не найден"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer updatedCustomer) {
        getCustomerById(updatedCustomer.getId());
        validateCustomer(updatedCustomer);
        customerRepository.update(updatedCustomer);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getFullName() == null || customer.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }
        if (customer.getAge() < MIN_AGE || customer.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("Возраст должен быть от " + MIN_AGE + " до " + MAX_AGE + " лет");
        }
        if (customer.getGender() == null || customer.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("Пол не может быть пустым");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон не может быть пустым");
        }
    }
}