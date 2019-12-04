package km.kmwarehouse.data.models.base;

import km.kmwarehouse.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class LegalEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private Long registerNumber;

    @Column(nullable = false, unique = true)
    private Long vatNumber;
}
