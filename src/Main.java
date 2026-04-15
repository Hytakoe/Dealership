import ru.cs.vsu.selyutinrv.controller.ConsoleController;
import ru.cs.vsu.selyutinrv.controller.command.CommandFactory;
import ru.cs.vsu.selyutinrv.model.Car;
import ru.cs.vsu.selyutinrv.model.Truck;
import ru.cs.vsu.selyutinrv.repository.CustomerRepository;
import ru.cs.vsu.selyutinrv.repository.SaleRepository;
import ru.cs.vsu.selyutinrv.repository.VehicleRepository;
import ru.cs.vsu.selyutinrv.repository.impl.InMemoryCustomerRepository;
import ru.cs.vsu.selyutinrv.repository.impl.InMemorySaleRepository;
import ru.cs.vsu.selyutinrv.repository.impl.InMemoryVehicleRepository;
import ru.cs.vsu.selyutinrv.service.CustomerService;
import ru.cs.vsu.selyutinrv.service.SaleService;
import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;


public class Main {
    public static void main(String[] args) {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        SaleRepository saleRepository = new InMemorySaleRepository();

        VehicleService vehicleService = new VehicleService(vehicleRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        SaleService saleService = new SaleService(vehicleRepository, customerRepository, saleRepository);

        ConsoleView consoleView = new ConsoleView();

        CommandFactory commandFactory = new CommandFactory(
                vehicleService,
                customerService,
                saleService,
                consoleView
        );

        ConsoleController controller = new ConsoleController(commandFactory, consoleView);

        addTestData(vehicleService);

        controller.start();
    }

    private static void addTestData(VehicleService vehicleService) {
        Car car1 = new Car();
        car1.setBrand("Toyota");
        car1.setModel("Camry");
        car1.setYear(2022);
        car1.setPrice(2500000);
        car1.setColor("Черный");
        car1.setBodyType(ru.cs.vsu.selyutinrv.model.BodyType.SEDAN);
        car1.setDoorCount(4);
        car1.setPassengerCapacity(5);
        car1.setTransmission("автомат");
        car1.setEngineVolume(2.5);
        car1.setFuelType("бензин");
        vehicleService.addVehicle(car1);

        Car car2 = new Car();
        car2.setBrand("BMW");
        car2.setModel("X5");
        car2.setYear(2023);
        car2.setPrice(5500000);
        car2.setColor("Белый");
        car2.setBodyType(ru.cs.vsu.selyutinrv.model.BodyType.SUV);
        car2.setDoorCount(5);
        car2.setPassengerCapacity(7);
        car2.setTransmission("автомат");
        car2.setEngineVolume(3.0);
        car2.setFuelType("дизель");
        vehicleService.addVehicle(car2);

        Truck truck1 = new Truck();
        truck1.setBrand("Volvo");
        truck1.setModel("FH");
        truck1.setYear(2021);
        truck1.setPrice(7500000);
        truck1.setColor("Синий");
        truck1.setTruckType(ru.cs.vsu.selyutinrv.model.TruckType.HEAVY);
        truck1.setLoadCapacity(20.0);
        truck1.setAxleCount(3);
        truck1.setCargoVolume(40.0);
        truck1.setHasTrailerHitch(true);
        truck1.setBodyMaterial("алюминий");
        vehicleService.addVehicle(truck1);

        Truck truck2 = new Truck();
        truck2.setBrand("GAZ");
        truck2.setModel("Gazelle");
        truck2.setYear(2022);
        truck2.setPrice(1500000);
        truck2.setColor("Белый");
        truck2.setTruckType(ru.cs.vsu.selyutinrv.model.TruckType.LIGHT);
        truck2.setLoadCapacity(1.5);
        truck2.setAxleCount(2);
        truck2.setCargoVolume(10.0);
        truck2.setHasTrailerHitch(false);
        truck2.setBodyMaterial("сталь");
        vehicleService.addVehicle(truck2);
    }
}