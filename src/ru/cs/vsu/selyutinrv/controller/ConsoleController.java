package ru.cs.vsu.selyutinrv.controller;

import ru.cs.vsu.selyutinrv.controller.command.Command;
import ru.cs.vsu.selyutinrv.controller.command.CommandFactory;
import ru.cs.vsu.selyutinrv.model.*;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

public class ConsoleController {
    private final CommandFactory commandFactory;
    private final ConsoleView view;

    public ConsoleController(CommandFactory commandFactory, ConsoleView view) {
        this.commandFactory = commandFactory;
        this.view = view;
    }

    public void start() {
        int choice;

        do {
            try {
                view.showMainMenu(commandFactory.getAllCommands());

                choice = view.getUserChoice();
                Command command = commandFactory.getCommand(choice);

                if (command != null) {
                    command.execute();
                } else {
                    view.showError("Неверный выбор. Попробуйте снова.");
                }

                if (choice != 0) {
                    view.waitForEnter();
                }

            } catch (Exception e) {
                view.showError("Системная ошибка: " + e.getMessage());
                view.waitForEnter();
                choice = -1;
            }
        } while (choice != 0);
    }
}