package selyutinrv.model;

import java.util.Objects;

public class Customer {
    private Long id;
    private final String fullName;
    private final int age;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;

    public Customer(String fullName, int age, String gender, String phone, String email, String address) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public Customer(Long id, String fullName, int age, String gender, String phone, String email, String address) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return age == customer.age &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(gender, customer.gender) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(address, customer.address);
    }
    @Override
    public String toString() {
        return String.format("%s, %d лет, %s, тел: %s", fullName, age, gender, phone);
    }
}