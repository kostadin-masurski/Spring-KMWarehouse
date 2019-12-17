package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.MaterialServiceModel;
import km.kmwarehouse.services.services.MaterialService;
import km.kmwarehouse.web.view.models.MaterialModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/material")
public class MaterialController {
    private final MaterialService materialService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("material/all");
        mav.addObject("materials", materialService.findAll());
        return mav;
    }

    @GetMapping("/register")
    public String getIndex(){
        return "material/create.html";
    }

    @PostMapping("/register")
    public ModelAndView registerMaterial(@ModelAttribute MaterialModel material, ModelAndView mav){
        MaterialServiceModel materialServiceModel = mapper.map(material, MaterialServiceModel.class);
        try {
            mav.setViewName("material/all");
            materialService.register(materialServiceModel);
            mav.addObject("materials", materialService.findAll()
                    .stream().map(m -> mapper.map(m, MaterialModel.class)).collect(Collectors.toList()));
            return mav;
        } catch (Exception e) {
            mav.setViewName("material/register");
            String MATERIAL_REG_ERR = "Material register error. Please fill all fields with the unique material information.";
            mav.addObject("error", MATERIAL_REG_ERR);
            return mav;
        }
    }

    @GetMapping("/{material}")
    public ModelAndView getDetails(@PathVariable String material, ModelAndView mav){
        mav.setViewName("material/details");
        mav.addObject("material", mapper.map(materialService.findByMaterial(material), MaterialModel.class));
        return mav;
    }

    @PostMapping("/{material}")
    public ModelAndView editMaterial(@ModelAttribute MaterialModel material, ModelAndView mav){
        MaterialServiceModel materialServiceModel = mapper.map(material, MaterialServiceModel.class);
        try {
            mav.setViewName("material/all");
            materialService.edit(materialServiceModel);
            mav.addObject("materials", materialService.findAll()
                    .stream().map(m -> mapper.map(m, MaterialModel.class)).collect(Collectors.toList()));
            return mav;
        } catch (Exception e) {
            mav.setViewName("material/details");
            mav.addObject("material", mapper.map(materialService.findByMaterial(material.getMaterial()), MaterialModel.class));
            String MATERIAL_EDIT_ERR = "Material edit error. Please fill all fields with the unique material information.";
            mav.addObject("error", MATERIAL_EDIT_ERR);
            return mav;
        }
    }
}
