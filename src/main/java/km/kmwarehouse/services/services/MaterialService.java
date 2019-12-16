package km.kmwarehouse.services.services;

import km.kmwarehouse.services.models.MaterialServiceModel;

import java.util.List;

public interface MaterialService {
    void register(MaterialServiceModel model);
    void edit(MaterialServiceModel model);
    MaterialServiceModel findByMaterial(String material);
    List<MaterialServiceModel> findAll();
    List<MaterialServiceModel> findAllByMaterialOrDescription(String material, String description);
}
