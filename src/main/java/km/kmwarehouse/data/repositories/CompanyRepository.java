package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findFirstByCompanyCode(String companyCode);
}
