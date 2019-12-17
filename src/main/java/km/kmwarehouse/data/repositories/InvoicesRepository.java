package km.kmwarehouse.data.repositories;

import km.kmwarehouse.data.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
}
