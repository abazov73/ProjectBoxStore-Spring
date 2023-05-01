package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerMVCController {
    private final CustomerService customerService;

    public CustomerMVCController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomers(Model model) {
        model.addAttribute("customers",
                customerService.getAllCustomers().stream()
                        .map(CustomerDTO::new)
                        .toList());
        return "customer";
    }

    @GetMapping(value = {"/edit/", "/edit/{id}"})
    public String editCustomer(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("customerDTO", new CustomerDTO());
        } else {
            model.addAttribute("customerId", id);
            model.addAttribute("customerDTO", new CustomerDTO(customerService.getCustomer(id)));
        }
        return "customer-edit";
    }

    @PostMapping(value = {"/", "/{id}"})
    public String saveCustomer(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid CustomerDTO customerDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "customer-edit";
        }
        if (id == null || id <= 0) {
            customerService.addCustomer(customerDto.getlastName(), customerDto.getfirstName(), customerDto.getmiddleName());
        } else {
            customerService.updateCustomer(id, customerDto.getlastName(), customerDto.getfirstName(), customerDto.getmiddleName());
        }
        return "redirect:/customer";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }
}
