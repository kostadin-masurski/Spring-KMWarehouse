package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.WarehouseServiceModel;

import java.util.List;

public interface WarehouseService {
    void register(WarehouseServiceModel model);
    void edit(WarehouseServiceModel model);
    WarehouseServiceModel findByWarehouse(String warehouse);
    List<WarehouseServiceModel> findAll();
    List<WarehouseServiceModel> findAllByWarehouseOrName(String warehouse, String name);
}
