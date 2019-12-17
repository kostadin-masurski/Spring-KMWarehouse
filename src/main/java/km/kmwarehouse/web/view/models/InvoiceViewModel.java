package km.kmwarehouse.web.view.models;

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
public class InvoiceViewModel {
    private Long id;
    private String documentType;
    private Company company;
    private Partner partner;
    private Warehouse warehouse;
    private Material material;
    private BigDecimal quantity;
    /*private LocalDateTime dateTime;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private Long companyRegisterNumber;
    private Long companyVatNumber;
    private String partnerName;
    private String partnerAddress;
    private String partnerEmail;
    private Long partnerRegisterNumber;
    private Long partnerVatNumber;
    private String materialDescription;
    private String unit;
    private BigDecimal price;*/
}
