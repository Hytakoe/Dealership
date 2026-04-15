package ru.cs.vsu.selyutinrv.service;

import ru.cs.vsu.selyutinrv.model.Customer;
import ru.cs.vsu.selyutinrv.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;

public class CustomerService {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    // Вспомогательный метод для контроллера - проверяет, изменились ли данные
    public boolean hasCustomerChanged(Customer oldCustomer, Customer newCustomer) {
        if (oldCustomer == null || newCustomer == null) {
            return true;
        }

        return !Objects.equals(oldCustomer.getFullName(), newCustomer.getFullName()) ||
                oldCustomer.getAge() != newCustomer.getAge() ||
                !Objects.equals(oldCustomer.getGender(), newCustomer.getGender()) ||
                !Objects.equals(oldCustomer.getPhone(), newCustomer.getPhone()) ||
                !Objects.equals(oldCustomer.getEmail(), newCustomer.getEmail()) ||
                !Objects.equals(oldCustomer.getAddress(), newCustomer.getAddress());
    }
}