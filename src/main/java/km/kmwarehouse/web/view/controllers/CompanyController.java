package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.CompanyServiceModel;
import km.kmwarehouse.services.services.CompanyService;
import km.kmwarehouse.web.view.models.CompanyModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("company/all");
        mav.addObject("companies", companyService.findAll());
        return mav;
    }

    @GetMapping("/register")
    public String getIndex(){
        return "company/register.html";
    }

    @PostMapping("/register")
    public ModelAndView registerCompany(@ModelAttribute CompanyModel company, ModelAndView mav){
        CompanyServiceModel companyServiceModel = mapper.map(company, CompanyServiceModel.class);
        try {
            mav.setViewName("company/all");
            companyService.register(companyServiceModel);
            mav.addObject("companies", companyService.findAll());
            return mav;
        } catch (Exception e) {
            mav.setViewName("company/register");
            String COMPANY_REG_ERR = "Company register error. Please fill all fields with the unique company information.";
            mav.addObject("error", COMPANY_REG_ERR);
            return mav;
        }
    }

    @GetMapping("/{companyCode}")
    public ModelAndView getDetails(@PathVariable String companyCode, ModelAndView mav){
        mav.setViewName("company/details");
        mav.addObject("company", companyService.findByCompanyCode(companyCode));
        return mav;
    }

    @PostMapping("/{companyCode}")
    public ModelAndView editCompany(@ModelAttribute CompanyModel company, ModelAndView mav){
        CompanyServiceModel companyServiceModel = mapper.map(company, CompanyServiceModel.class);
        try {
            mav.setViewName("company/all");
            companyService.edit(companyServiceModel);
            mav.addObject("companies", companyService.findAll());
            return mav;
        } catch (Exception e) {
            mav.setViewName("company/details");
            mav.addObject("company", companyService.findByCompanyCode(company.getCompanyCode()));
            String COMPANY_EDIT_ERR = "Company edit error. Please fill all fields with the unique company information.";
            mav.addObject("error", COMPANY_EDIT_ERR);
            return mav;
        }
    }
}
