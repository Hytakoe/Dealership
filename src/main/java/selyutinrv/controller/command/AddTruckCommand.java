package selyutinrv.controller.command;

import selyutinrv.model.Truck;
import selyutinrv.model.builder.TruckBuilder;
import selyutinrv.service.VehicleService;
import selyutinrv.view.ConsoleView;

public class AddTruckCommand extends AddVehicleCommand {

    public AddTruckCommand(VehicleService vehicleService, ConsoleView view) {
        super(vehicleService, view);
    }

    @Override
    protected void createAndSaveVehicle() {
        Truck truck = new TruckBuilder()
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
                .bodyMaterial(view.getStringInput("Материал кузова: "))
                .build();

        vehicleService.addVehicle(truck);
    }

    @Override
    public String getDescription() {
        return "Добавить грузовой автомобиль";
    }
}