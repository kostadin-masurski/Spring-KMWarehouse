package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAllByCompany(Company company);

    Warehouse findFirstByWarehouseAndCompany(String warehouse, Company company);

    List<Warehouse> findAllByCompanyAndWarehouseOrNameContaining(Company company, String warehouse, String name);
}
