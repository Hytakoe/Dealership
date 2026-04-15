package ru.vsu.cs.selyutinrv.controller.command;

import ru.vsu.cs.selyutinrv.service.CustomerService;
import ru.vsu.cs.selyutinrv.service.SaleService;
import ru.vsu.cs.selyutinrv.service.VehicleService;
import ru.vsu.cs.selyutinrv.view.ConsoleView;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<Integer, Command> commands = new LinkedHashMap<>();

    public CommandFactory(VehicleService vehicleService,
                          CustomerService customerService,
                          SaleService saleService,
                          ConsoleView view) {
        commands.put(1, new AddCarCommand(vehicleService, view));
        commands.put(2, new AddTruckCommand(vehicleService, view));
        commands.put(3, new SellVehicleCommand(vehicleService, customerService, saleService, view));
        commands.put(4, new EditVehicleCommand(vehicleService, view));
        commands.put(5, new EditCustomerCommand(customerService, view));
        commands.put(6, new ShowVehiclesCommand(vehicleService, view));
        commands.put(7, new ShowSalesCommand(saleService, view));
        commands.put(8, new ShowCustomersCommand(customerService, view));
        commands.put(0, new ExitCommand(view));
    }

    public Command getCommand(int choice) {
        return commands.get(choice);
    }

    public Map<Integer, Command> getAllCommands() {
        return commands;
    }
}