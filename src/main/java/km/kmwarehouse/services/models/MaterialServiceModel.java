package km.kmwarehouse.services.models;

import km.kmwarehouse.data.models.Partner;
import km.kmwarehouse.data.models.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MaterialServiceModel {
    private String materialId;
    private String description;
    private BigDecimal price;
    private UnitOfMeasure unitOfMeasure;
    private Partner vendor;
}
