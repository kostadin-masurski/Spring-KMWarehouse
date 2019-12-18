package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.services.MaterialService;
import km.kmwarehouse.services.services.PartnerService;
import km.kmwarehouse.services.services.WarehouseService;
import km.kmwarehouse.web.view.models.MaterialModel;
import km.kmwarehouse.web.view.models.PartnerModel;
import km.kmwarehouse.web.view.models.WarehouseModel;
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
    private final WarehouseService warehouseService;
    private final MaterialService materialService;
    private final ModelMapper mapper;

    @GetMapping(value = "/api/partners/{code}-{name}")
    public List<PartnerModel> getPartners(@PathVariable String code, @PathVariable String name){
        return partnerService.findAllByPartnerOrName(code, name).stream()
                .map(p -> mapper.map(p, PartnerModel.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/warehouses/{code}-{name}")
    public List<WarehouseModel> getWarehouses(@PathVariable String code, @PathVariable String name){
        return warehouseService.findAllByWarehouseOrName(code, name).stream()
                .map(p -> mapper.map(p, WarehouseModel.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/materials/{code}-{name}")
    public List<MaterialModel> getMaterial(@PathVariable String code, @PathVariable String name){
        return materialService.findAllByMaterialOrDescription(code, name).stream()
                .map(p -> mapper.map(p, MaterialModel.class)).collect(Collectors.toList());
    }
}
