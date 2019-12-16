package km.kmwarehouse.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MaterialModel {
    private String material;
    private String description;
    private BigDecimal price;
    private String unitOfMeasure;
    private String vendor;
    private boolean active;
}
