package selyutinrv.controller.command;

import selyutinrv.view.ConsoleView;

public class ExitCommand implements Command {
    private final ConsoleView view;

    public ExitCommand(ConsoleView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n Спасибо за использование автосалона.");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Выход";
    }
}