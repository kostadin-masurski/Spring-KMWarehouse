package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Inventory;
import km.kmwarehouse.data.models.Material;
import km.kmwarehouse.data.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findFirstByWarehouseAndMaterial(Warehouse warehouse, Material material);
}
