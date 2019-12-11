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
@Table(name = "companies")
public class Company extends LegalEntity {

    @Column(name = "company_code", nullable = false, unique = true)
    private String companyCode;

    @Column(nullable = false, unique = true)
    private String email;
}
