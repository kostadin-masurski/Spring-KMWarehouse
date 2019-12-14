package km.kmwarehouse.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerServiceModel {
    private String partner;
    private String name;
    private String address;
    private Long registerNumber;
    private String email;
    private Long vatNumber;
    private boolean active;
}
