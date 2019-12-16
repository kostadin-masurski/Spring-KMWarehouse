package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.WarehouseServiceModel;
import km.kmwarehouse.services.services.WarehouseService;
import km.kmwarehouse.web.view.models.WarehouseModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("warehouse/all");
        mav.addObject("warehouses", warehouseService.findAll());
        return mav;
    }

    @GetMapping("/register")
    public String getIndex(){
        return "warehouse/register.html";
    }

    @PostMapping("/register")
    public ModelAndView registerWarehouse(@ModelAttribute WarehouseModel warehouse, ModelAndView mav){
        WarehouseServiceModel warehouseServiceModel = mapper.map(warehouse, WarehouseServiceModel.class);
        try {
            mav.setViewName("warehouse/all");
            warehouseService.register(warehouseServiceModel);
            mav.addObject("warehouses", warehouseService.findAll()
                    .stream().map(m -> mapper.map(m, WarehouseModel.class)).collect(Collectors.toList()));
            return mav;
        } catch (Exception e) {
            mav.setViewName("warehouse/register");
            String WAREHOUSE_REG_ERR = "Warehouse register error. Please fill all fields with the unique warehouse information.";
            mav.addObject("error", WAREHOUSE_REG_ERR);
            return mav;
        }
    }

    @GetMapping("/{warehouse}")
    public ModelAndView getDetails(@PathVariable String warehouse, ModelAndView mav){
        mav.setViewName("warehouse/details");
        mav.addObject("warehouse", mapper.map(warehouseService.findByWarehouse(warehouse), WarehouseModel.class));
        return mav;
    }

    @PostMapping("/{warehouse}")
    public ModelAndView editWarehouse(@ModelAttribute WarehouseModel warehouse, ModelAndView mav){
        WarehouseServiceModel warehouseServiceModel = mapper.map(warehouse, WarehouseServiceModel.class);
        try {
            mav.setViewName("warehouse/all");
            warehouseService.edit(warehouseServiceModel);
            mav.addObject("warehouses", warehouseService.findAll()
                    .stream().map(m -> mapper.map(m, WarehouseModel.class)).collect(Collectors.toList()));
            return mav;
        } catch (Exception e) {
            mav.setViewName("warehouse/details");
            mav.addObject("warehouse", mapper.map(warehouseService.findByWarehouse(warehouse.getWarehouse()), WarehouseModel.class));
            String WAREHOUSE_EDIT_ERR = "Warehouse edit error. Please fill all fields with the unique warehouse information.";
            mav.addObject("error", WAREHOUSE_EDIT_ERR);
            return mav;
        }
    }
}
