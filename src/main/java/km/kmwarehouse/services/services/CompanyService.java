package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.CompanyServiceModel;

import java.util.List;

public interface CompanyService {
    void register(CompanyServiceModel model);
    void edit(CompanyServiceModel model);
    CompanyServiceModel findByCompanyCode(String companyCode);
    List<CompanyServiceModel> findAll();
}
