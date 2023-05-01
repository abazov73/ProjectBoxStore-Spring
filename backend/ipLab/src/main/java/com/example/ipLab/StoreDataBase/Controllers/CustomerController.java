package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.WebConfiguration;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/customer")
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
    public CustomerDTO createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        final Customer customer = customerService.addCustomer(customerDTO.getlastName(), customerDTO.getfirstName(), customerDTO.getmiddleName());
        return new CustomerDTO(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@RequestBody @Valid CustomerDTO customerDTO,
                                      @PathVariable Long id){
        return new CustomerDTO(customerService.updateCustomer(id, customerDTO.getlastName(), customerDTO.getfirstName(), customerDTO.getmiddleName()));
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
