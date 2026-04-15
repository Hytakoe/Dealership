package ru.vsu.cs.selyutinrv.controller.command;

import ru.vsu.cs.selyutinrv.service.VehicleService;
import ru.vsu.cs.selyutinrv.view.ConsoleView;

public abstract class AddVehicleCommand implements Command {
    protected final VehicleService vehicleService;
    protected final ConsoleView view;

    public AddVehicleCommand(VehicleService vehicleService, ConsoleView view) {
        this.vehicleService = vehicleService;
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            view.showMessage("\n--- ДОБАВЛЕНИЕ НОВОГО АВТОМОБИЛЯ ---");
            createAndSaveVehicle();
            view.showMessage("Автомобиль успешно добавлен!");
        } catch (IllegalArgumentException e) {
            view.showError("Ошибка при добавлении: " + e.getMessage());
        }
    }

    protected abstract void createAndSaveVehicle();
}