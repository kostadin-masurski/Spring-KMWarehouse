package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Partner findFirstByPartner(String partner);
}
