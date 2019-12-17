package km.kmwarehouse.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceCreateServiceModel {
    private String documentType;
    private String partner;
    private String warehouse;
    private String material;
    private BigDecimal quantity;
}
