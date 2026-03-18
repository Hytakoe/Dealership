package ru.cs.vsu.selyutinrv.view;

import ru.cs.vsu.selyutinrv.controller.command.Command;
import ru.cs.vsu.selyutinrv.model.BodyType;
import ru.cs.vsu.selyutinrv.model.Customer;
import ru.cs.vsu.selyutinrv.model.TruckType;
import ru.cs.vsu.selyutinrv.model.Vehicle;
import ru.cs.vsu.selyutinrv.model.Sale;
import ru.cs.vsu.selyutinrv.model.builder.CustomerBuilder;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void showVehicleTypeMenu() {
        System.out.println("\nТип автомобиля:");
        System.out.println("1. Легковой");
        System.out.println("2. Грузовой");
        System.out.print("Выберите тип: ");
    }

    public int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (да/нет): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("да") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("нет") || input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Ошибка: введите да или нет");
        }
    }

    public BodyType selectBodyType() {
        System.out.println("\nВыберите тип кузова:");
        BodyType[] types = BodyType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i].getDisplayName());
        }

        while (true) {
            try {
                System.out.print("Ваш выбор: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= types.length) {
                    return types[choice - 1];
                }
                System.out.println("Ошибка: введите число от 1 до " + types.length);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public TruckType selectTruckType() {
        System.out.println("\nВыберите тип грузовика:");
        TruckType[] types = TruckType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s (макс. %.1f т)%n", i + 1,
                    types[i].getDisplayName(), types[i].getMaxLoadCapacity());
        }

        while (true) {
            try {
                System.out.print("Ваш выбор: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= types.length) {
                    return types[choice - 1];
                }
                System.out.println("Ошибка: введите число от 1 до " + types.length);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public Customer inputCustomerData() {
        System.out.println("\n--- Регистрация нового покупателя ---");

        return new CustomerBuilder()
                .fullName(getStringInput("ФИО покупателя: "))
                .age(getIntInput("Возраст: "))
                .gender(getStringInput("Пол (М/Ж): "))
                .phone(getStringInput("Телефон: "))
                .email(getStringInput("Email (необязательно): "))
                .address(getStringInput("Адрес (необязательно): "))
                .build();
    }

    public Long selectVehicleId(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("Нет доступных автомобилей");
            return null;
        }

        System.out.println("\nДоступные автомобили:");
        for (Vehicle v : vehicles) {
            System.out.printf("%d. %s %s (%d) - %.2f руб.%n",
                    v.getId(), v.getBrand(), v.getModel(), v.getYear(), v.getPrice());
        }

        while (true) {
            try {
                System.out.print("Введите ID автомобиля: ");
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public Long selectCustomerId(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Нет зарегистрированных покупателей");
            return null;
        }

        System.out.println("\nПокупатели:");
        for (Customer c : customers) {
            System.out.printf("%d. %s, %d лет, %s%n",
                    c.getId(), c.getFullName(), c.getAge(), c.getPhone());
        }

        while (true) {
            try {
                System.out.print("Введите ID покупателя: ");
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    public void showAllVehicles(List<Vehicle> vehicles) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("АВТОМОБИЛИ В САЛОНЕ");
        System.out.println("=".repeat(100));

        if (vehicles.isEmpty()) {
            System.out.println("В салоне нет автомобилей");
            return;
        }

        String format = "| %-3s | %-10s | %-10s | %-4s | %-10s | %-20s |%n";
        System.out.printf(format, "ID", "Марка", "Модель", "Год", "Цена", "Характеристики");
        System.out.println("-".repeat(100));

        for (Vehicle v : vehicles) {
            if (!v.isSold()) {
                System.out.printf(format,
                        v.getId(),
                        v.getBrand(),
                        v.getModel(),
                        v.getYear(),
                        v.getPrice() + " руб.",
                        v.getSpecificInfo());
            }
        }
    }

    public void showSalesTable(List<Sale> sales) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("ТАБЛИЦА ПРОДАЖ");
        System.out.println("=".repeat(100));

        if (sales.isEmpty()) {
            System.out.println("Продаж не было");
            return;
        }

        String format = "| %-3s | %-20s | %-15s | %-12s | %-10s |%n";
        System.out.printf(format, "ID", "Покупатель", "Автомобиль", "Дата", "Сумма");
        System.out.println("-".repeat(100));

        for (Sale s : sales) {
            System.out.printf(format,
                    s.getId(),
                    s.getCustomer().getFullName(),
                    s.getVehicle().getBrand() + " " + s.getVehicle().getModel(),
                    s.getFormattedSaleDate(),
                    s.getSalePrice() + " руб.");
        }
    }

    public void showAllCustomers(List<Customer> customers) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПОКУПАТЕЛИ");
        System.out.println("=".repeat(80));

        if (customers.isEmpty()) {
            System.out.println("Нет зарегистрированных покупателей");
            return;
        }

        String format = "| %-3s | %-20s | %-4s | %-3s | %-15s |%n";
        System.out.printf(format, "ID", "ФИО", "Возраст", "Пол", "Телефон");
        System.out.println("-".repeat(80));

        for (Customer c : customers) {
            System.out.printf(format,
                    c.getId(),
                    c.getFullName(),
                    c.getAge(),
                    c.getGender(),
                    c.getPhone());
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println("ОШИБКА: " + error);
    }

    public void waitForEnter() {
        System.out.print("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }

    public void showMainMenu(Map<Integer, Command> commands) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("АВТОСАЛОН");
        System.out.println("=".repeat(60));

        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
        }
        System.out.print("Выберите действие: ");
    }

    // Добавить метод для Long ввода:
    public Long getLongInput(String prompt) {
        boolean isValid = false;
        long result = 0L;

        while (!isValid) {
            try {
                System.out.print(prompt);
                result = Long.parseLong(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
        return result;
    }
}