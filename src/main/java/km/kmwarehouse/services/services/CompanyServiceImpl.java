package km.kmwarehouse.services.services;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.repositories.CompanyRepository;
import km.kmwarehouse.services.models.CompanyServiceModel;
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
    private final ValidationService validator;

    @Override
    public void register(CompanyServiceModel companyModel) {
        if (!validator.isValid(companyModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol or email");
        }
        Company company = mapper.map(companyModel, Company.class);
        company.setActive(true);
        companyRepository.save(company);
    }

    @Override
    public void edit(CompanyServiceModel companyModel) {
        if (!validator.isValid(companyModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol or email");
        }
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
    public CompanyServiceModel findByCompanyCode(String companyCode) {
        return mapper.map(companyRepository.findFirstByCompanyCode(companyCode), CompanyServiceModel.class);
    }

    @Override
    public List<CompanyServiceModel> findAll() {
        return companyRepository.findAll().stream().map(c -> mapper.map(c, CompanyServiceModel.class)).collect(Collectors.toList());
    }
}
