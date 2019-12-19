package km.kmwarehouse.services.services.implementations;

import km.kmwarehouse.data.models.Company;
import km.kmwarehouse.data.models.Inventory;
import km.kmwarehouse.data.models.Invoice;
import km.kmwarehouse.data.repositories.*;
import km.kmwarehouse.services.models.InventoryServiceModel;
import km.kmwarehouse.services.models.InvoiceCreateServiceModel;
import km.kmwarehouse.services.models.InvoiceViewServiceModel;
import km.kmwarehouse.services.services.InvService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvServiceImpl implements InvService {
    private final InvoicesRepository invoicesRepository;
    private final InventoryRepository inventoryRepository;
    private final PartnerRepository partnerRepository;
    private final WarehouseRepository warehouseRepository;
    private final MaterialRepository materialRepository;
    private final ModelMapper mapper;
    private final Company company;

    public InvServiceImpl(InvoicesRepository invoicesRepository, 
                          InventoryRepository inventoryRepository, 
                          CompanyRepository companyRepository, 
                          PartnerRepository partnerRepository, 
                          WarehouseRepository warehouseRepository, 
                          MaterialRepository materialRepository, 
                          ModelMapper mapper) {
        this.invoicesRepository = invoicesRepository;
        this.inventoryRepository = inventoryRepository;
        this.partnerRepository = partnerRepository;
        this.warehouseRepository = warehouseRepository;
        this.materialRepository = materialRepository;
        this.mapper = mapper;
        this.company = companyRepository.findFirstByCompanyCode("0530");
    }

    @Override
    public void createInvoice(InvoiceCreateServiceModel model) {
        if (model.getQuantity().compareTo( new BigDecimal("0")) <= 0){
            throw new IllegalArgumentException("Zero or negative quantity");
        }
        Invoice invoice = mapper.map(model, Invoice.class);
        invoice.setCompany(company);
        invoice.setWarehouse(warehouseRepository.findFirstByWarehouseAndCompany(model.getWarehouse(), company));
        invoice.setMaterial(materialRepository.findFirstByMaterial(model.getMaterial()));
        invoice.setDateTime(LocalDateTime.now());
        Inventory inventory = inventoryRepository.findFirstByWarehouseAndMaterial(invoice.getWarehouse(), invoice.getMaterial());
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setWarehouse(invoice.getWarehouse());
            inventory.setMaterial(invoice.getMaterial());
            inventory.setQuantity(new BigDecimal("0"));
        }
        if (invoice.getDocumentType().toString().equals("OUTBOUND")) {
            if (inventory.getQuantity().compareTo(invoice.getQuantity()) < 0) {
                throw new IllegalArgumentException("Quantity not available");
            }
            inventory.setQuantity(inventory.getQuantity().subtract(invoice.getQuantity()));
            invoice.setPartner(partnerRepository.findFirstByPartner(model.getPartner()));
        } else {
            inventory.setQuantity(inventory.getQuantity().add(invoice.getQuantity()));
            invoice.setPartner(invoice.getMaterial().getVendor());
        }
        inventoryRepository.save(inventory);
        invoicesRepository.save(invoice);
    }

    @Override
    public InvoiceViewServiceModel findById(Long id) {
        return mapper.map(inventoryRepository.findById(id), InvoiceViewServiceModel.class);
    }

    @Override
    public List<InvoiceViewServiceModel> findAllInvoices() {
        return invoicesRepository.findAll().stream()
                .map(i -> mapper.map(i, InvoiceViewServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<InventoryServiceModel> findAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(i -> mapper.map(i, InventoryServiceModel.class)).collect(Collectors.toList());
    }
}
