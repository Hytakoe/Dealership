package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.model.Customer;
import ru.cs.vsu.selyutinrv.model.Sale;
import ru.cs.vsu.selyutinrv.model.Vehicle;
import ru.cs.vsu.selyutinrv.service.CustomerService;
import ru.cs.vsu.selyutinrv.service.SaleService;
import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

import java.util.List;

public class SellVehicleCommand implements Command {
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final ConsoleView view;

    public SellVehicleCommand(VehicleService vehicleService,
                              CustomerService customerService,
                              SaleService saleService,
                              ConsoleView view) {
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n--- ПРОДАЖА АВТОМОБИЛЯ ---");

        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
        if (availableVehicles.isEmpty()) {
            view.showMessage("В салоне нет доступных автомобилей для продажи");
            return;
        }

        view.showAllVehicles(availableVehicles);
        Long vehicleId = view.getLongInput("Введите ID автомобиля для продажи: ");

        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

            view.showMessage("\nВы продаете: " + vehicle.getBrand() + " " + vehicle.getModel());
            view.showMessage("Цена: " + vehicle.getPrice() + " руб.");

            view.showMessage("\n--- Данные покупателя ---");
            view.showMessage("Покупатель уже зарегистрирован?");
            view.showMessage("1. Да, выбрать из списка");
            view.showMessage("2. Нет, зарегистрировать нового");

            int choice = view.getIntInput("Ваш выбор: ");
            Customer customer;

            if (choice == 1) {
                customer = selectExistingCustomer();
            } else if (choice == 2) {
                customer = view.inputCustomerData();
            } else {
                view.showError("Неверный выбор");
                return;
            }

            Sale sale = saleService.sellVehicle(vehicleId, customer);
            view.showMessage("\n АВТОМОБИЛЬ ПРОДАН!");
            view.showMessage("---------------------------------------");
            view.showMessage("Покупатель: " + sale.getCustomer().getFullName());
            view.showMessage("Автомобиль: " + sale.getVehicle().getBrand() + " " + sale.getVehicle().getModel());
            view.showMessage("Дата продажи: " + sale.getFormattedSaleDate());
            view.showMessage("Сумма: " + sale.getSalePrice() + " руб.");
            view.showMessage("---------------------------------------");

        } catch (IllegalArgumentException e) {
            view.showError("Ошибка при продаже: " + e.getMessage());
        }
    }

    private Customer selectExistingCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            view.showMessage("Нет зарегистрированных покупателей");
            return view.inputCustomerData();
        }

        view.showAllCustomers(customers);
        Long customerId = view.getLongInput("Введите ID покупателя: ");
        return customerService.getCustomerById(customerId);
    }

    @Override
    public String getDescription() {
        return "Продать автомобиль";
    }
}