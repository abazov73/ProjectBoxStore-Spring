package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id){
        return new CustomerDTO(customerService.getCustomer(id));
    }

    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return  customerService.getAllCustomers().stream()
                .map(CustomerDTO::new)
                .toList();
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestParam("customerLastName") String customerLastName,
                                      @RequestParam("customerFirstName") String customerFirstName,
                                      @RequestParam("customerMiddleName") String customerMiddleName){
        final Customer customer = customerService.addCustomer(customerLastName, customerFirstName, customerMiddleName);
        return new CustomerDTO(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@RequestParam("customerLastName") String customerLastName,
                                      @RequestParam("customerFirstName") String customerFirstName,
                                      @RequestParam("customerMiddleName") String customerMiddleName,
                                      @PathVariable Long id){
        return new CustomerDTO(customerService.updateCustomer(id, customerLastName, customerFirstName, customerMiddleName));
    }

    @DeleteMapping("/{id}")
    public CustomerDTO deleteCustomer(@PathVariable Long id){
        return new CustomerDTO(customerService.deleteCustomer(id));
    }

    @DeleteMapping()
    public void deleteAllCustomers(){
        customerService.deleteAllCustomers();
    }
}
