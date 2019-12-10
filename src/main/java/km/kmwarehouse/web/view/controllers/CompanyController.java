package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.services.CompanyService;
import km.kmwarehouse.web.view.models.CompanyModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("company/company-all");
        mav.addObject("companies", companyService.findAll());
        return mav;
    }

    @GetMapping("/register")
    public String getIndex(){
        return "company/company-register.html";
    }

    @PostMapping("/register")
    public ModelAndView registerCompany(@ModelAttribute CompanyModel company, ModelAndView mav){
        try {
            mav.setViewName("home/home");
            companyService.register(company);
            return mav;
        } catch (Exception e) {
            mav.setViewName("company/company-register");
            String COMPANY_REG_ERR = "Company register error. Please fill all fields with the unique company information.";
            mav.addObject("error", COMPANY_REG_ERR);
            return mav;
        }
    }

    @GetMapping("/edit/{companyId}")
    public String getHome(@PathVariable String companyId){
        return "home/home.html";
    }
}
