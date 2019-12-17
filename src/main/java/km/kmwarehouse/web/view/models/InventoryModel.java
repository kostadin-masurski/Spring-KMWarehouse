package km.kmwarehouse.web.view.models;

import km.kmwarehouse.data.models.Material;
import km.kmwarehouse.data.models.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InventoryModel {
    private Warehouse warehouse;
    private Material material;
    private BigDecimal quantity;
}
