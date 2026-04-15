package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.model.*;
import ru.cs.vsu.selyutinrv.model.builder.CarBuilder;
import ru.cs.vsu.selyutinrv.model.builder.TruckBuilder;
import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

public class AddVehicleCommand implements Command {
    private final VehicleService vehicleService;
    private final ConsoleView view;

    public AddVehicleCommand(VehicleService vehicleService, ConsoleView view) {
        this.vehicleService = vehicleService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n --- ДОБАВЛЕНИЕ НОВОГО АВТОМОБИЛЯ ---");
        view.showVehicleTypeMenu();

        int type = view.getUserChoice();
        Vehicle vehicle;

        if (type == 1) {
            vehicle = createCar();
        } else if (type == 2) {
            vehicle = createTruck();
        } else {
            view.showError("Неверный тип автомобиля");
            return;
        }

        try {
            vehicleService.addVehicle(vehicle);
            view.showMessage("Автомобиль успешно добавлен! ID: " + vehicle.getId());
        } catch (IllegalArgumentException e) {
            view.showError("Ошибка при добавлении: " + e.getMessage());
        }
    }

    private Car createCar() {

        view.showMessage("\n --- Данные легкового автомобиля ---");
        return new CarBuilder()
                .brand(view.getStringInput("Марка: "))
                .model(view.getStringInput("Модель: "))
                .year(view.getIntInput("Год выпуска: "))
                .price(view.getDoubleInput("Цена (руб): "))
                .color(view.getStringInput("Цвет: "))
                .bodyType(view.selectBodyType())
                .doorCount(view.getIntInput("Количество дверей: "))
                .passengerCapacity(view.getIntInput("Количество мест: "))
                .transmission(view.getStringInput("Коробка передач: "))
                .engineVolume(view.getDoubleInput("Объем двигателя (л): "))
                .fuelType(view.getStringInput("Тип топлива: "))
                .build();
    }

    private Truck createTruck() {

        view.showMessage("\n --- Данные грузового автомобиля ---");
        return new TruckBuilder()
                .brand(view.getStringInput("Марка: "))
                .model(view.getStringInput("Модель: "))
                .year(view.getIntInput("Год выпуска: "))
                .price(view.getDoubleInput("Цена (руб): "))
                .color(view.getStringInput("Цвет: "))
                .truckType(view.selectTruckType())
                .loadCapacity(view.getDoubleInput("Грузоподъемность (т): "))
                .axleCount(view.getIntInput("Количество осей: "))
                .cargoVolume(view.getDoubleInput("Объем кузова (м³): "))
                .hasTrailerHitch(view.getBooleanInput("Наличие фаркопа"))
                .bodyMaterial(view.getStringInput("Материал кузова (сталь/алюминий/тент/изотерм): "))
                .build();
    }

    @Override
    public String getDescription() {
        return "Добавить автомобиль";
    }
}