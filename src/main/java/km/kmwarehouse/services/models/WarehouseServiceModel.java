package km.kmwarehouse.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WarehouseServiceModel {
    private String warehouse;
    private String name;
    private String address;
    private boolean active;
}
