package ru.cs.vsu.selyutinrv.repository;

import ru.cs.vsu.selyutinrv.model.Sale;

import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    List<Sale> findAll();
    List<Sale> findSalesByCustomerId(Long customerId);
    List<Sale> findSalesByVehicleId(Long vehicleId);
}
