package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.PartnerServiceModel;
import km.kmwarehouse.services.services.PartnerService;
import km.kmwarehouse.web.view.models.PartnerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/partner")
public class PartnerController {
    private final PartnerService partnerService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("partner/all");
        mav.addObject("partners", partnerService.findAll());
        return mav;
    }

    @GetMapping("/register")
    public String getIndex(){
        return "partner/register.html";
    }

    @PostMapping("/register")
    public ModelAndView registerPartner(@ModelAttribute PartnerModel partner, ModelAndView mav){
        PartnerServiceModel partnerServiceModel = mapper.map(partner, PartnerServiceModel.class);
        try {
            mav.setViewName("partner/all");
            partnerService.register(partnerServiceModel);
            mav.addObject("partners", partnerService.findAll());
            return mav;
        } catch (Exception e) {
            mav.setViewName("partner/register");
            String COMPANY_REG_ERR = "Partner register error. Please fill all fields with the unique partner information.";
            mav.addObject("error", COMPANY_REG_ERR);
            return mav;
        }
    }

    @GetMapping("/{partnerCode}")
    public ModelAndView getDetails(@PathVariable String partnerCode, ModelAndView mav){
        mav.setViewName("partner/details");
        mav.addObject("partner", partnerService.findByPartner(partnerCode));
        return mav;
    }

    @PostMapping("/{partner}")
    public ModelAndView editPartner(@ModelAttribute PartnerModel partner, ModelAndView mav){
        PartnerServiceModel partnerServiceModel = mapper.map(partner, PartnerServiceModel.class);
        try {
            mav.setViewName("partner/all");
            partnerService.edit(partnerServiceModel);
            mav.addObject("partners", partnerService.findAll());
            return mav;
        } catch (Exception e) {
            mav.setViewName("partner/details");
            mav.addObject("partner", partnerService.findByPartner(partner.getPartner()));
            String COMPANY_EDIT_ERR = "Partner edit error. Please fill all fields with the unique partner information.";
            mav.addObject("error", COMPANY_EDIT_ERR);
            return mav;
        }
    }
}
