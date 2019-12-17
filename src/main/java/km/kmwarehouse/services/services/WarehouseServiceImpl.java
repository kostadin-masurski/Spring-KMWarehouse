package km.kmwarehouse.services.services;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.models.Warehouse;
import km.kmwarehouse.data.repositories.WarehouseRepository;
import km.kmwarehouse.data.repositories.CompanyRepository;
import km.kmwarehouse.services.models.WarehouseServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ModelMapper mapper;
    private final ValidationService validator;
    private final Company company;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                CompanyRepository companyRepository,
                                ModelMapper mapper,
                                ValidationService validator) {
        this.warehouseRepository = warehouseRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.company = companyRepository.findFirstByCompanyCode("0530");
    }

    @Override
    public void register(WarehouseServiceModel warehouseServiceModel) {
        if (!validator.isValid(warehouseServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol.");
        }
        Warehouse warehouse = mapper.map(warehouseServiceModel, Warehouse.class);
        warehouse.setCompany(company);
        warehouse.setActive(true);
        warehouseRepository.save(warehouse);
    }

    @Override
    public void edit(WarehouseServiceModel warehouseServiceModel) {
        if (!validator.isValid(warehouseServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol.");
        }
        Warehouse warehouse = warehouseRepository.findFirstByWarehouseAndCompany(warehouseServiceModel.getWarehouse(), company);
        warehouse.setWarehouse(warehouseServiceModel.getWarehouse());
        warehouse.setName(warehouseServiceModel.getName());
        warehouse.setActive(warehouseServiceModel.isActive());
        warehouseRepository.save(warehouse);
    }

    @Override
    public WarehouseServiceModel findByWarehouse(String warehouse) {
        return mapper.map(warehouseRepository.findFirstByWarehouseAndCompany(warehouse, company), WarehouseServiceModel.class);
    }

    @Override
    public List<WarehouseServiceModel> findAll() {
        return warehouseRepository.findAllByCompany(company).stream().map(p -> mapper.map(p, WarehouseServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseServiceModel> findAllByWarehouseOrName(String warehouse, String name) {
        return warehouseRepository.findAllByCompanyAndWarehouseOrNameContaining(company, warehouse, name).stream().map(p -> mapper.map(p, WarehouseServiceModel.class)).collect(Collectors.toList());
    }
}
