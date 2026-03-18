package ru.cs.vsu.selyutinrv.model.builder;

import ru.cs.vsu.selyutinrv.model.Customer;

public class CustomerBuilder {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    private String fullName;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;

    public CustomerBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CustomerBuilder age(int age) {
        this.age = age;
        return this;
    }

    public CustomerBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder address(String address) {
        this.address = address;
        return this;
    }

    public Customer build() {
        validate();
        return new Customer(fullName, age, gender, phone, email, address);
    }

    private void validate() {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException("Возраст должен быть от " + MIN_AGE + " до " + MAX_AGE + " лет");
        }
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Пол не может быть пустым");
        }
        if (!gender.equalsIgnoreCase("М") && !gender.equalsIgnoreCase("Ж") &&
                !gender.equalsIgnoreCase("Мужской") && !gender.equalsIgnoreCase("Женский")) {
            throw new IllegalArgumentException("Пол должен быть указан как М/Ж или Мужской/Женский");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон не может быть пустым");
        }
        // Email может быть необязательным
        if (email != null && !email.trim().isEmpty() && !email.contains("@")) {
            throw new IllegalArgumentException("Некорректный формат email");
        }
    }
}