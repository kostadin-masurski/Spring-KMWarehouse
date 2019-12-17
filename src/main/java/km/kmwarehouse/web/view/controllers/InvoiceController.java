package km.kmwarehouse.web.view.controllers;

import km.kmwarehouse.services.models.InvoiceCreateServiceModel;
import km.kmwarehouse.services.services.InvService;
import km.kmwarehouse.web.view.models.InvoiceCreateModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvService invService;
    private final ModelMapper mapper;

    @GetMapping("/all")
    public ModelAndView listAll(ModelAndView mav){
        mav.setViewName("invoice/all");
        mav.addObject("invoices", invService.findAllInvoices());
        return mav;
    }

    @GetMapping("/create")
    public String getIndex(){
        return "invoice/create.html";
    }

    @PostMapping("/create")
    public ModelAndView createInvoice(@ModelAttribute InvoiceCreateModel invoice, ModelAndView mav){
        InvoiceCreateServiceModel invoiceCreateServiceModel = mapper.map(invoice, InvoiceCreateServiceModel.class);
        try {
            mav.setViewName("invoice/all");
            invService.createInvoice(invoiceCreateServiceModel);
            mav.addObject("invoices", invService.findAllInvoices());
            return mav;
        } catch (Exception e) {
            mav.setViewName("invoice/create");
            String INVOICE_CREATE_ERR = "Invoice create error. Please check your input and inventory.";
            mav.addObject("error", INVOICE_CREATE_ERR);
            return mav;
        }
    }

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable Long id, ModelAndView mav){
        mav.setViewName("invoice/details");
        mav.addObject("invoice", invService.findById(id));
        return mav;
    }
}
