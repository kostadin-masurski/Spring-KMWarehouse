package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.services.PartnerService;
import km.kmwarehouse.web.view.models.PartnerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ApiController {
    private final PartnerService partnerService;
    private final ModelMapper mapper;

    @GetMapping(value = "/api/partners/{vendorCode}-{vendorName}")
    public List<PartnerModel> getPartners(@PathVariable String vendorCode, @PathVariable String vendorName){
        return partnerService.findAllByPartnerOrName(vendorCode, vendorName).stream()
                .map(p -> mapper.map(p, PartnerModel.class)).collect(Collectors.toList());
    }
}
