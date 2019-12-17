package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.InventoryServiceModel;
import km.kmwarehouse.services.models.InvoiceCreateServiceModel;
import km.kmwarehouse.services.models.InvoiceViewServiceModel;

import java.util.List;

public interface InvService {
    void createInvoice(InvoiceCreateServiceModel model);
    InvoiceViewServiceModel findById(Long id);
    List<InvoiceViewServiceModel> findAllInvoices();
    List<InventoryServiceModel> findAllInventory();
}
