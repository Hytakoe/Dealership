package ru.vsu.cs.selyutinrv.repository.impl.inmemory;

import ru.vsu.cs.selyutinrv.model.Sale;
import ru.vsu.cs.selyutinrv.repository.SaleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySaleRepository implements SaleRepository {
    private final Map<Long, Sale> storage = new HashMap<>();
    private long currentId = 1;

    @Override
    public Sale save(Sale sale) {
        Sale saleToSave;

        if (sale.getId() == null) {
            saleToSave = new Sale(
                    currentId++,
                    sale.getVehicle(),
                    sale.getCustomer(),
                    sale.getSaleDate(),
                    sale.getSalePrice()
            );
        } else {
            saleToSave = sale;
        }

        storage.put(saleToSave.getId(), saleToSave);
        return saleToSave;
    }

    @Override
    public List<Sale> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Sale> findSalesByCustomerId(Long customerId) {
        return storage.values().stream()
                .filter(sale -> sale.getCustomer().getId().equals(customerId))
                .toList();
    }

    @Override
    public List<Sale> findSalesByVehicleId(Long vehicleId) {
        return storage.values().stream()
                .filter(sale -> sale.getVehicle().getId().equals(vehicleId))
                .toList();
    }
}