package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Inventory;
import km.kmwarehouse.data.models.Material;
import km.kmwarehouse.data.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    /*List<Material> findAllByWarehouse(Warehouse warehouse);
    BigDecimal findFirstByWarehouseAndMaterial(Warehouse warehouse, Material material);*/
}
