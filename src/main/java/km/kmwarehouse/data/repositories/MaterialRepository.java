package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findFirstByMaterial(String material);
    List<Material> findAllByMaterialOrDescriptionContaining(String material, String description);
}
