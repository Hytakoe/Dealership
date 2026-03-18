package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.model.Customer;
import ru.cs.vsu.selyutinrv.model.builder.CustomerBuilder;
import ru.cs.vsu.selyutinrv.service.CustomerService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

import java.util.List;

public class EditCustomerCommand implements Command {
    private final CustomerService customerService;
    private final ConsoleView view;

    public EditCustomerCommand(CustomerService customerService, ConsoleView view) {
        this.customerService = customerService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("----------РЕДАКТИРОВАНИЕ ПОКУПАТЕЛЯ----------║");

        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            view.showMessage("В системе нет зарегистрированных покупателей");
            return;
        }

        view.showAllCustomers(customers);

        Long customerId = view.getLongInput("\n Введите ID покупателя для редактирования: ");

        try {
            Customer existingCustomer = customerService.getCustomerById(customerId);

            view.showMessage("-----------------------------------------");
            view.showMessage("Редактирование: " + existingCustomer.getFullName());
            view.showMessage("(Оставьте поле пустым, чтобы не изменять значение)");
            view.showMessage("-----------------------------------------");

            // Создаем билдер с текущими значениями
            CustomerBuilder builder = new CustomerBuilder()
                    .fullName(existingCustomer.getFullName())
                    .age(existingCustomer.getAge())
                    .gender(existingCustomer.getGender())
                    .phone(existingCustomer.getPhone())
                    .email(existingCustomer.getEmail())
                    .address(existingCustomer.getAddress());

            // Запрашиваем новые значения
            String fullName = view.getStringInput("ФИО (" + existingCustomer.getFullName() + "): ");
            if (!fullName.trim().isEmpty()) {
                builder.fullName(fullName);
            }

            String ageInput = view.getStringInput("Возраст (" + existingCustomer.getAge() + "): ");
            if (!ageInput.trim().isEmpty()) {
                try {
                    int age = Integer.parseInt(ageInput);
                    builder.age(age);
                } catch (NumberFormatException e) {
                    view.showError("Некорректный возраст. Будет сохранено предыдущее значение.");
                }
            }

            String gender = view.getStringInput("Пол (" + existingCustomer.getGender() + "): ");
            if (!gender.trim().isEmpty()) {
                builder.gender(gender);
            }

            String phone = view.getStringInput("Телефон (" + existingCustomer.getPhone() + "): ");
            if (!phone.trim().isEmpty()) {
                builder.phone(phone);
            }

            String email = view.getStringInput("Email (" + existingCustomer.getEmail() + "): ");
            if (!email.trim().isEmpty()) {
                builder.email(email);
            } else {
                builder.email(existingCustomer.getEmail()); // сохраняем текущий email
            }

            String address = view.getStringInput("Адрес (" + existingCustomer.getAddress() + "): ");
            if (!address.trim().isEmpty()) {
                builder.address(address);
            } else {
                builder.address(existingCustomer.getAddress()); // сохраняем текущий адрес
            }

            Customer updatedCustomer = builder.build();

            if (customerService.hasCustomerChanged(existingCustomer, updatedCustomer)) {
                Customer customerWithId = new Customer(
                        updatedCustomer.getFullName(),
                        updatedCustomer.getAge(),
                        updatedCustomer.getGender(),
                        updatedCustomer.getPhone(),
                        updatedCustomer.getEmail(),
                        updatedCustomer.getAddress()
                );

                // Обновляем в сервисе
                customerService.updateCustomer(customerWithId);

                view.showMessage("\n ИНФОРМАЦИЯ О ПОКУПАТЕЛЕ УСПЕШНО ОБНОВЛЕНА!");
                view.showMessage("----------------------------------------");
                view.showMessage("ID: " + customerWithId.getId());
                view.showMessage("ФИО: " + customerWithId.getFullName());
                view.showMessage("Возраст: " + customerWithId.getAge());
                view.showMessage("Пол: " + customerWithId.getGender());
                view.showMessage("Телефон: " + customerWithId.getPhone());
                view.showMessage("Email: " + customerWithId.getEmail());
                view.showMessage("Адрес: " + customerWithId.getAddress());
                view.showMessage("----------------------------------------");
            } else {
                view.showMessage("\n Данные не были изменены");
            }

        } catch (IllegalArgumentException e) {
            view.showError("Ошибка при редактировании: " + e.getMessage());
        } catch (Exception e) {
            view.showError("Неожиданная ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Редактировать покупателя";
    }
}