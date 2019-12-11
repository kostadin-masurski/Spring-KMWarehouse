package km.kmwarehouse.services.services;

import km.kmwarehouse.web.view.models.CompanyModel;

import java.util.List;

public interface CompanyService extends BaseService{
    void register(CompanyModel companyModel);
    void edit(CompanyModel companyModel);
    CompanyModel findByCompanyId(String companyCode);
    List<CompanyModel> findAll();
}
