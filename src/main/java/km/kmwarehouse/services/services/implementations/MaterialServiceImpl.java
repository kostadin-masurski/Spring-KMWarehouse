package km.kmwarehouse.services.services.implementations;

import km.kmwarehouse.data.models.Material;
import km.kmwarehouse.data.repositories.MaterialRepository;
import km.kmwarehouse.data.repositories.PartnerRepository;
import km.kmwarehouse.services.models.MaterialServiceModel;
import km.kmwarehouse.services.services.MaterialService;
import km.kmwarehouse.services.services.ValidationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final PartnerRepository partnerRepository;
    private final ModelMapper mapper;
    private final ValidationService validator;

    @Override
    public void register(MaterialServiceModel materialServiceModel) {
        if (!validator.isValid(materialServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol.");
        }
        Material material = mapper.map(materialServiceModel, Material.class);
        material.setVendor(partnerRepository.findFirstByPartner(materialServiceModel.getVendor()));
        material.setActive(true);
        materialRepository.save(material);
    }

    @Override
    public void edit(MaterialServiceModel materialServiceModel) {
        if (!validator.isValid(materialServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol.");
        }
        Material material = materialRepository.findFirstByMaterial(materialServiceModel.getMaterial());
        material.setMaterial(materialServiceModel.getMaterial());
        material.setDescription(materialServiceModel.getDescription());
        material.setPrice(materialServiceModel.getPrice());
        material.setUnitOfMeasure(materialServiceModel.getUnitOfMeasure());
        material.setVendor(partnerRepository.findFirstByPartner(materialServiceModel.getVendor()));
        material.setActive(materialServiceModel.isActive());
        materialRepository.save(material);
    }

    @Override
    public MaterialServiceModel findByMaterial(String material) {
        Material materialEntity = materialRepository.findFirstByMaterial(material);
        MaterialServiceModel materialServiceModel = mapper.map(materialEntity, MaterialServiceModel.class);
        materialServiceModel.setVendor(materialEntity.getVendor().getPartner());
        return materialServiceModel;
    }

    @Override
    public List<MaterialServiceModel> findAll() {
        return materialRepository.findAll().stream().map(p -> {
            MaterialServiceModel materialServiceModel = mapper.map(p, MaterialServiceModel.class);
            materialServiceModel.setVendor(p.getVendor().getName());
            return materialServiceModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MaterialServiceModel> findAllByMaterialOrDescription(String material, String description) {
        return materialRepository.findAllByMaterialOrDescriptionContaining(material, description).stream().map(p -> mapper.map(p, MaterialServiceModel.class)).collect(Collectors.toList());
    }
}
