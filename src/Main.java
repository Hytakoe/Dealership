import ru.vsu.cs.selyutinrv.controller.ConsoleController;
import ru.vsu.cs.selyutinrv.controller.command.CommandFactory;
import ru.vsu.cs.selyutinrv.repository.CustomerRepository;
import ru.vsu.cs.selyutinrv.repository.SaleRepository;
import ru.vsu.cs.selyutinrv.repository.VehicleRepository;
import ru.vsu.cs.selyutinrv.repository.impl.jdbc.DatabaseConnection;
import ru.vsu.cs.selyutinrv.repository.impl.jdbc.JdbcCustomerRepository;
import ru.vsu.cs.selyutinrv.repository.impl.jdbc.JdbcSaleRepository;
import ru.vsu.cs.selyutinrv.repository.impl.jdbc.JdbcVehicleRepository;
import ru.vsu.cs.selyutinrv.service.CustomerService;
import ru.vsu.cs.selyutinrv.service.SaleService;
import ru.vsu.cs.selyutinrv.service.VehicleService;
import ru.vsu.cs.selyutinrv.view.ConsoleView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Path configPath = Paths.get(System.getProperty("user.dir"), "resources", "application.properties");
        System.out.println("📄 Полный путь = " + configPath.toAbsolutePath());
        System.out.println("✅ Существует? " + Files.exists(configPath));
        System.out.println("📖 Читаем? " + Files.isReadable(configPath));
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