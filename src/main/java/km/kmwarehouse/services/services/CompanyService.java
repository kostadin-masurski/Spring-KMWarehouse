package km.kmwarehouse.services.services;

import km.kmwarehouse.web.view.models.CompanyModel;

import java.util.List;

public interface CompanyService {
    void register(CompanyModel companyModel);
    void edit(CompanyModel companyModel);
    List<CompanyModel> findAll();
}
