package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.service.CustomerService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

public class ShowCustomersCommand implements Command {
    private final CustomerService customerService;
    private final ConsoleView view;

    public ShowCustomersCommand(CustomerService customerService, ConsoleView view) {
        this.customerService = customerService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n --- СПИСОК ПОКУПАТЕЛЕЙ ---");
        var customers = customerService.getAllCustomers();
        view.showAllCustomers(customers);

        if (!customers.isEmpty()) {
            view.showMessage("Всего покупателей: " + customers.size());
        }
    }

    @Override
    public String getDescription() {
        return "Показать всех покупателей";
    }
}