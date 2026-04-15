package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

public class ShowVehiclesCommand implements Command {
    private final VehicleService vehicleService;
    private final ConsoleView view;

    public ShowVehiclesCommand(VehicleService vehicleService, ConsoleView view) {
        this.vehicleService = vehicleService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n --- АВТОМОБИЛИ В НАЛИЧИИ ---");
        var vehicles = vehicleService.getAvailableVehicles();
        view.showAllVehicles(vehicles);

        if (!vehicles.isEmpty()) {
            view.showMessage("Всего автомобилей: " + vehicles.size());
        }
    }

    @Override
    public String getDescription() {
        return "Показать все автомобили в салоне";
    }
}