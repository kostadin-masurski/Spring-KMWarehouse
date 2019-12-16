package km.kmwarehouse.services.services;

import km.kmwarehouse.data.models.Partner;
import km.kmwarehouse.data.repositories.PartnerRepository;
import km.kmwarehouse.services.models.PartnerServiceModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final ModelMapper mapper;
    private final ValidationService validator;

    @Override
    public void register(PartnerServiceModel partnerServiceModel) {
        if (!validator.isValid(partnerServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol or email");
        }
        Partner partner = mapper.map(partnerServiceModel, Partner.class);
        partner.setActive(true);
        partnerRepository.save(partner);
    }

    @Override
    public void edit(PartnerServiceModel partnerServiceModel) {
        if (!validator.isValid(partnerServiceModel)){
            throw new IllegalArgumentException("Input is too short or contains invalid symbol or email");
        }
        Partner partner = partnerRepository.findFirstByPartner(partnerServiceModel.getPartner());
        partner.setPartner(partnerServiceModel.getPartner());
        partner.setName(partnerServiceModel.getName());
        partner.setAddress(partnerServiceModel.getAddress());
        partner.setEmail(partnerServiceModel.getEmail());
        partner.setRegisterNumber(partnerServiceModel.getRegisterNumber());
        partner.setVatNumber(partnerServiceModel.getVatNumber());
        partner.setActive(partnerServiceModel.isActive());
        partnerRepository.save(partner);
    }

    @Override
    public PartnerServiceModel findByPartner(String partner) {
        return mapper.map(partnerRepository.findFirstByPartner(partner), PartnerServiceModel.class);
    }

    @Override
    public List<PartnerServiceModel> findAll() {
        return partnerRepository.findAll().stream().map(p -> mapper.map(p, PartnerServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PartnerServiceModel> findAllByPartnerOrName(String partner, String name) {
        return partnerRepository.findAllByPartnerOrNameContaining(partner, name).stream().map(p -> mapper.map(p, PartnerServiceModel.class)).collect(Collectors.toList());
    }
}
