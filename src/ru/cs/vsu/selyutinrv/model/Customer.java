package ru.cs.vsu.selyutinrv.model;

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
    public String toString() {
        return String.format("%s, %d лет, %s, тел: %s", fullName, age, gender, phone);
    }
}