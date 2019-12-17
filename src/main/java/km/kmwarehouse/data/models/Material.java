package km.kmwarehouse.data.models;

import km.kmwarehouse.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "materials")
public class Material extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String material;

    @Column(nullable = false, unique = true)
    private String description;

    /*@ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/

    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne(targetEntity = Partner.class)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Partner vendor;
}
