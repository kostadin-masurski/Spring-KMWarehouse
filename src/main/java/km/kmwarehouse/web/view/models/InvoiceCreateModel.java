package km.kmwarehouse.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceCreateModel {
    private String documentType;
    private String partner;
    private String warehouse;
    private String material;
    private BigDecimal quantity;
}
