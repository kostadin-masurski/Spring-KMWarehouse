package km.kmwarehouse.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerModel {
    private String partnerId;
    private String name;
    private String address;
    private String email;
    private Long registerNumber;
    private Long vatNumber;
}
