package ru.cs.vsu.selyutinrv.controller.command;

import ru.cs.vsu.selyutinrv.service.CustomerService;
import ru.cs.vsu.selyutinrv.service.SaleService;
import ru.cs.vsu.selyutinrv.service.VehicleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<Integer, Command> commands = new LinkedHashMap<>();

    public CommandFactory(VehicleService vehicleService,
                          CustomerService customerService,
                          SaleService saleService,
                          ConsoleView view) {
        commands.put(1, new AddVehicleCommand(vehicleService, view));
        commands.put(2, new SellVehicleCommand(vehicleService, customerService, saleService, view));
        commands.put(3, new EditVehicleCommand(vehicleService, view));
        commands.put(4, new EditCustomerCommand(customerService, view));
        commands.put(5, new ShowVehiclesCommand(vehicleService, view));
        commands.put(6, new ShowSalesCommand(saleService, view));
        commands.put(7, new ShowCustomersCommand(customerService, view));
        commands.put(0, new ExitCommand(view));
    }

    public Command getCommand(int choice) {
        return commands.get(choice);
    }

    public Map<Integer, Command> getAllCommands() {
        return commands;
    }
}