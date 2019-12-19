package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.RegisterUserServiceModel;
import km.kmwarehouse.services.services.AuthService;
import km.kmwarehouse.web.view.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(
            AuthService authService,
            ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping({"/", "/login"})
    public String getLoginForm(@RequestParam(required = false) String error, Model model) {
        if(error != null) {
            model.addAttribute("error", error);
        }
        return "home/index.html";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home/home.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("model", new RegisterUserModel());
        return "home/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home/register.html";
        }

        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }
}
