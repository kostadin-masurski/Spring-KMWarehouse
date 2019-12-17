package km.kmwarehouse.services.models;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.models.Material;
import km.kmwarehouse.data.models.Partner;
import km.kmwarehouse.data.models.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceViewServiceModel {
    private Long id;
    private String documentType;
    private Company company;
    private Partner partner;
    private Warehouse warehouse;
    private Material material;
    private BigDecimal quantity;
}
