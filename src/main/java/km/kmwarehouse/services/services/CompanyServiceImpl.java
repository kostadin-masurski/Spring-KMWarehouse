package km.kmwarehouse.services.services;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.repositories.CompanyRepository;
import km.kmwarehouse.services.models.CompanyServiceModel;
import km.kmwarehouse.web.view.models.CompanyModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    @Override
    public void create(CompanyModel companyModel) {
        companyRepository.save(mapper.map(companyModel, Company.class));
    }

    @Override
    public void edit(CompanyModel companyModel) {
        Company company = companyRepository.findFirstByCompanyId(companyModel.getCompanyId());
        company.setCompanyId(companyModel.getCompanyId());
        company.setName(companyModel.getName());
        company.setAddress(companyModel.getAddress());
        company.setEmail(companyModel.getEmail());
        company.setRegisterNumber(companyModel.getRegisterNumber());
        company.setVatNumber(companyModel.getVatNumber());
        companyRepository.save(mapper.map(companyModel, Company.class));
    }
}
