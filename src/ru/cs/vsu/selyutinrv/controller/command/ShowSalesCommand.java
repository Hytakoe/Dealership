package ru.cs.vsu.selyutinrv.controller.command;


import ru.cs.vsu.selyutinrv.model.Sale;
import ru.cs.vsu.selyutinrv.service.SaleService;
import ru.cs.vsu.selyutinrv.view.ConsoleView;

public class ShowSalesCommand implements Command {
    private final SaleService saleService;
    private final ConsoleView view;

    public ShowSalesCommand(SaleService saleService, ConsoleView view) {
        this.saleService = saleService;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showMessage("\n --- ИСТОРИЯ ПРОДАЖ ---");
        var sales = saleService.getAllSales();
        view.showSalesTable(sales);

        if (!sales.isEmpty()) {
            double totalRevenue = sales.stream()
                    .mapToDouble(Sale::getSalePrice)
                    .sum();
            view.showMessage("Общая выручка: " + totalRevenue + " руб.");
            view.showMessage("Всего продаж: " + sales.size());
        }
    }

    @Override
    public String getDescription() {
        return "Показать таблицу продаж";
    }
}