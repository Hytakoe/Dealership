package selyutinrv.service;

import selyutinrv.model.Customer;
import selyutinrv.model.Sale;
import selyutinrv.model.Vehicle;
import selyutinrv.repository.CustomerRepository;
import selyutinrv.repository.SaleRepository;
import selyutinrv.repository.VehicleRepository;

import java.util.List;

public class SaleService {
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    public SaleService(VehicleRepository vehicleRepository,
                       CustomerRepository customerRepository,
                       SaleRepository saleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    public Sale sellVehicle(Long vehicleId, Customer customer) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Автомобиль не найден"));

        if (vehicle.isSold()) {
            throw new IllegalArgumentException("Автомобиль уже продан");
        }

        Customer savedCustomer = customerRepository.save(customer);

        vehicle.setSold(true);
        vehicleRepository.update(vehicle);

        Sale sale = new Sale(vehicle, savedCustomer, vehicle.getPrice());
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public List<Sale> getSalesByCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Покупатель не найден");
        }
        return saleRepository.findSalesByCustomerId(customerId);
    }

    public List<Sale> getSalesByVehicle(Long vehicleId) {
        return saleRepository.findSalesByVehicleId(vehicleId);
    }
}