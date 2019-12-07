package km.kmwarehouse.services.services;


import km.kmwarehouse.services.models.CompanyServiceModel;
import km.kmwarehouse.web.view.models.CompanyModel;

public interface CompanyService {
    void create(CompanyModel companyModel);
    void edit(CompanyModel companyModel);
}
