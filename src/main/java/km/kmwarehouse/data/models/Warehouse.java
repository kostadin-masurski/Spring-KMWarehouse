package km.kmwarehouse.data.models;

import km.kmwarehouse.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String whId;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}
