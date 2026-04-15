package ru.vsu.cs.selyutinrv.controller.command;

import ru.vsu.cs.selyutinrv.model.Customer;
import ru.vsu.cs.selyutinrv.model.builder.CustomerBuilder;
import ru.vsu.cs.selyutinrv.service.CustomerService;
import ru.vsu.cs.selyutinrv.view.ConsoleView;

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
        showHeader();

        Long customerId = selectCustomer();
        if (customerId == null) {
            return;
        }

        try {
            Customer existingCustomer = customerService.getCustomerById(customerId);
            showEditingInfo(existingCustomer);

            CustomerBuilder builder = createBuilderWithCurrentValues(existingCustomer);
            collectNewValues(builder, existingCustomer);

            Customer updatedCustomer = builder.build();
            saveIfChanged(existingCustomer, updatedCustomer);

        } catch (IllegalArgumentException e) {
            view.showError("Ошибка: " + e.getMessage());
        }
    }

    private void showHeader() {
        view.showMessage("\n--- РЕДАКТИРОВАНИЕ ПОКУПАТЕЛЯ ---");
    }

    private Long selectCustomer() {
        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            view.showMessage("Нет зарегистрированных покупателей");
            return null;
        }

        view.showAllCustomers(customers);
        return view.getLongInput("Введите ID покупателя для редактирования: ");
    }

    private void showEditingInfo(Customer customer) {
        view.showMessage("\nРедактирование: " + customer.getFullName());
        view.showMessage("(Оставьте поле пустым, чтобы не изменять значение)");
    }

    private CustomerBuilder createBuilderWithCurrentValues(Customer customer) {
        return new CustomerBuilder()
                .fullName(customer.getFullName())
                .age(customer.getAge())
                .gender(customer.getGender())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress());
    }

    private void collectNewValues(CustomerBuilder builder, Customer existing) {
        updateStringField(builder::fullName, "ФИО", existing.getFullName());
        updateIntField(builder::age, "Возраст", existing.getAge());
        updateStringField(builder::gender, "Пол", existing.getGender());
        updateStringField(builder::phone, "Телефон", existing.getPhone());
        updateStringField(builder::email, "Email", existing.getEmail());
        updateStringField(builder::address, "Адрес", existing.getAddress());
    }

    private void updateStringField(java.util.function.Consumer<String> setter,
                                   String fieldName,
                                   String currentValue) {
        String input = view.getStringInput(fieldName + " (" + currentValue + "): ");
        if (!input.trim().isEmpty()) {
            setter.accept(input);
        }
    }

    private void updateIntField(java.util.function.IntConsumer setter,
                                String fieldName,
                                int currentValue) {
        String input = view.getStringInput(fieldName + " (" + currentValue + "): ");
        if (!input.trim().isEmpty()) {
            try {
                setter.accept(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                view.showError("Некорректный возраст. Оставлено: " + currentValue);
            }
        }
    }

    private void saveIfChanged(Customer existing, Customer updated) {
        if (!existing.equals(updated)) {
            Customer customerWithId = new Customer(
                    updated.getFullName(),
                    updated.getAge(),
                    updated.getGender(),
                    updated.getPhone(),
                    updated.getEmail(),
                    updated.getAddress()
            );

            customerWithId.setId(existing.getId());
            customerService.updateCustomer(customerWithId);
            view.showMessage("Информация о покупателе обновлена");
        } else {
            view.showMessage("Данные не были изменены");
        }
    }

    @Override
    public String getDescription() {
        return "Редактировать покупателя";
    }
}