package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.PartnerServiceModel;

import java.util.List;

public interface PartnerService {
    void register(PartnerServiceModel model);
    void edit(PartnerServiceModel model);
    PartnerServiceModel findByPartner(String partner);
    List<PartnerServiceModel> findAll();
}
