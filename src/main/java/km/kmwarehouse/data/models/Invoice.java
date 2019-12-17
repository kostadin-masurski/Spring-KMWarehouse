package km.kmwarehouse.data.models;

import km.kmwarehouse.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne(targetEntity = Partner.class)
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    private Partner partner;

    @ManyToOne(targetEntity = Warehouse.class)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

    @Column(nullable = false)
    private BigDecimal quantity;

    /*@ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/

    @Column
    private LocalDateTime dateTime;
}
