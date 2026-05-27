package selyutinrv;

import selyutinrv.controller.ConsoleController;
import selyutinrv.controller.command.CommandFactory;
import selyutinrv.repository.CustomerRepository;
import selyutinrv.repository.SaleRepository;
import selyutinrv.repository.VehicleRepository;
import selyutinrv.repository.impl.jdbc.DatabaseConnection;
import selyutinrv.repository.impl.jdbc.JdbcCustomerRepository;
import selyutinrv.repository.impl.jdbc.JdbcSaleRepository;
import selyutinrv.repository.impl.jdbc.JdbcVehicleRepository;
import selyutinrv.service.CustomerService;
import selyutinrv.service.SaleService;
import selyutinrv.service.VehicleService;
import selyutinrv.view.ConsoleView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Path configPath = Paths.get(System.getProperty("user.dir"), "resources", "application.properties");
        VehicleRepository vehicleRepository = new JdbcVehicleRepository();
        CustomerRepository customerRepository = new JdbcCustomerRepository();
        SaleRepository saleRepository = new JdbcSaleRepository(vehicleRepository, customerRepository);

        VehicleService vehicleService = new VehicleService(vehicleRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        SaleService saleService = new SaleService(vehicleRepository, customerRepository, saleRepository);

        ConsoleView consoleView = new ConsoleView();
        CommandFactory commandFactory = new CommandFactory(
                vehicleService, customerService, saleService, consoleView
        );

        ConsoleController controller = new ConsoleController(commandFactory, consoleView);

        controller.start();

        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnection::closeConnection));
    }
}