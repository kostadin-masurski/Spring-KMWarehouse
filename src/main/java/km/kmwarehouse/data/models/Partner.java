package km.kmwarehouse.data.models;

import km.kmwarehouse.data.models.base.LegalEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner extends LegalEntity {

    @Column(nullable = false, unique = true)
    private String partnerId;

    @Column(nullable = false, unique = true)
    private String email;
}
