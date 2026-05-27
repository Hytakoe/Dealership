package selyutinrv.controller.command;

import selyutinrv.model.Car;
import selyutinrv.model.builder.CarBuilder;
import selyutinrv.service.VehicleService;
import selyutinrv.view.ConsoleView;

public class AddCarCommand extends AddVehicleCommand {

    public AddCarCommand(VehicleService vehicleService, ConsoleView view) {
        super(vehicleService, view);
    }

    @Override
    protected void createAndSaveVehicle() {
        Car car = new CarBuilder()
                .brand(view.getStringInput("Марка: "))
                .model(view.getStringInput("Модель: "))
                .year(view.getIntInput("Год выпуска: "))
                .price(view.getDoubleInput("Цена (руб): "))
                .color(view.getStringInput("Цвет: "))
                .bodyType(view.selectBodyType())
                .doorCount(view.getIntInput("Количество дверей: "))
                .passengerCapacity(view.getIntInput("Количество мест: "))
                .transmission(view.getStringInput("Коробка передач (механика/автомат/робот): "))
                .engineVolume(view.getDoubleInput("Объем двигателя (л): "))
                .fuelType(view.getStringInput("Тип топлива: "))
                .build();

        vehicleService.addVehicle(car);
    }

    @Override
    public String getDescription() {
        return "Добавить легковой автомобиль";
    }
}