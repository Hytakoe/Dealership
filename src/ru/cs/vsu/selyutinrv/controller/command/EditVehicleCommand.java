package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.model.Car;
import ru.cs.vsu.selyutinrv.model.Truck;
import ru.cs.vsu.selyutinrv.model.Vehicle;
import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

import java.util.List;

public class EditVehicleCommand implements Command {
    private final VehicleService vehicleService;
    private final ConsoleView view;

    public EditVehicleCommand(VehicleService vehicleService, ConsoleView view) {
        this.vehicleService = vehicleService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n --- РЕДАКТИРОВАНИЕ АВТОМОБИЛЯ ---");

        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        if (allVehicles.isEmpty()) {
            view.showMessage("В салоне нет автомобилей");
            return;
        }

        view.showAllVehicles(allVehicles);
        Long vehicleId = view.getLongInput("Введите ID автомобиля для редактирования: ");

        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            view.showMessage("\n Редактирование: " + vehicle.getBrand() + " " + vehicle.getModel());
            view.showMessage("(Оставьте поле пустым, чтобы не изменять значение)");

            if (vehicle instanceof Car) {
                editCar((Car) vehicle);
            } else if (vehicle instanceof Truck) {
                editTruck((Truck) vehicle);
            }

            vehicleService.updateVehicle(vehicle);
            view.showMessage("Информация об автомобиле обновлена");

        } catch (IllegalArgumentException e) {
            view.showError("Ошибка: " + e.getMessage());
        }
    }

    private void editCar(Car car) {
        String brand = view.getStringInput("Марка (" + car.getBrand() + "): ");
        if (!brand.isEmpty()) car.setBrand(brand);

        String model = view.getStringInput("Модель (" + car.getModel() + "): ");
        if (!model.isEmpty()) car.setModel(model);

        String year = view.getStringInput("Год (" + car.getYear() + "): ");
        if (!year.isEmpty()) car.setYear(Integer.parseInt(year));

        String price = view.getStringInput("Цена (" + car.getPrice() + "): ");
        if (!price.isEmpty()) car.setPrice(Double.parseDouble(price));

        String color = view.getStringInput("Цвет (" + car.getColor() + "): ");
        if (!color.isEmpty()) car.setColor(color);

        view.showMessage("Текущий тип кузова: " + car.getBodyType().getDisplayName());
        String changeBody = view.getStringInput("Изменить тип кузова? (да/нет): ");
        if (changeBody.equalsIgnoreCase("да")) {
            car.setBodyType(view.selectBodyType());
        }

        String doors = view.getStringInput("Дверей (" + car.getDoorCount() + "): ");
        if (!doors.isEmpty()) car.setDoorCount(Integer.parseInt(doors));

        String seats = view.getStringInput("Мест (" + car.getPassengerCapacity() + "): ");
        if (!seats.isEmpty()) car.setPassengerCapacity(Integer.parseInt(seats));

        String transmission = view.getStringInput("Коробка (" + car.getTransmission() + "): ");
        if (!transmission.isEmpty()) car.setTransmission(transmission);

        String engine = view.getStringInput("Объем двигателя (" + car.getEngineVolume() + "): ");
        if (!engine.isEmpty()) car.setEngineVolume(Double.parseDouble(engine));

        String fuel = view.getStringInput("Топливо (" + car.getFuelType() + "): ");
        if (!fuel.isEmpty()) car.setFuelType(fuel);
    }

    private void editTruck(Truck truck) {
        String brand = view.getStringInput("Марка (" + truck.getBrand() + "): ");
        if (!brand.isEmpty()) truck.setBrand(brand);

        String model = view.getStringInput("Модель (" + truck.getModel() + "): ");
        if (!model.isEmpty()) truck.setModel(model);

        String year = view.getStringInput("Год (" + truck.getYear() + "): ");
        if (!year.isEmpty()) truck.setYear(Integer.parseInt(year));

        String price = view.getStringInput("Цена (" + truck.getPrice() + "): ");
        if (!price.isEmpty()) truck.setPrice(Double.parseDouble(price));

        String color = view.getStringInput("Цвет (" + truck.getColor() + "): ");
        if (!color.isEmpty()) truck.setColor(color);

        view.showMessage("Текущий тип: " + truck.getTruckType().getDisplayName());
        String changeType = view.getStringInput("Изменить тип грузовика? (да/нет): ");
        if (changeType.equalsIgnoreCase("да")) {
            truck.setTruckType(view.selectTruckType());
        }

        String capacity = view.getStringInput("Грузоподъемность (" + truck.getLoadCapacity() + "): ");
        if (!capacity.isEmpty()) truck.setLoadCapacity(Double.parseDouble(capacity));

        String axles = view.getStringInput("Оси (" + truck.getAxleCount() + "): ");
        if (!axles.isEmpty()) truck.setAxleCount(Integer.parseInt(axles));

        String volume = view.getStringInput("Объем кузова (" + truck.getCargoVolume() + "): ");
        if (!volume.isEmpty()) truck.setCargoVolume(Double.parseDouble(volume));

        String hitch = view.getStringInput("Фаркоп (" + (truck.isHasTrailerHitch() ? "есть" : "нет") + "): ");
        if (!hitch.isEmpty()) {
            truck.setHasTrailerHitch(hitch.equalsIgnoreCase("да") || hitch.equalsIgnoreCase("есть"));
        }

        String material = view.getStringInput("Материал кузова (" + truck.getBodyMaterial() + "): ");
        if (!material.isEmpty()) truck.setBodyMaterial(material);
    }

    @Override
    public String getDescription() {
        return "Редактировать автомобиль";
    }
}