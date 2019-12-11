package km.kmwarehouse.services.services;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.repositories.CompanyRepository;
import km.kmwarehouse.web.view.models.CompanyModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    @Override
    public void register(CompanyModel companyModel) {
        Company company = mapper.map(companyModel, Company.class);
        company.setActive(true);
        companyRepository.save(company);
    }

    @Override
    public void edit(CompanyModel companyModel) {
        Company company = companyRepository.findFirstByCompanyCode(companyModel.getCompanyCode());
        company.setCompanyCode(companyModel.getCompanyCode());
        company.setName(companyModel.getName());
        company.setAddress(companyModel.getAddress());
        company.setEmail(companyModel.getEmail());
        company.setRegisterNumber(companyModel.getRegisterNumber());
        company.setVatNumber(companyModel.getVatNumber());
        company.setActive(companyModel.isActive());
        companyRepository.save(company);
    }

    @Override
    public CompanyModel findByCompanyId(String companyCode) {
        return mapper.map(companyRepository.findFirstByCompanyCode(companyCode), CompanyModel.class);
    }

    @Override
    public List<CompanyModel> findAll() {
        return companyRepository.findAll().stream().map(c -> mapper.map(c, CompanyModel.class)).collect(Collectors.toList());
    }

    @Override
    public void activate(String entityId) {
        Company company = companyRepository.findFirstByCompanyCode(entityId);
        company.setActive(true);
    }

    @Override
    public void deactivate(String entityId) {
        Company company = companyRepository.findFirstByCompanyCode(entityId);
        company.setActive(false);
    }
}
