package selyutinrv.repository;

import selyutinrv.model.Sale;

import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    List<Sale> findAll();
    List<Sale> findSalesByCustomerId(Long customerId);
    List<Sale> findSalesByVehicleId(Long vehicleId);
}
