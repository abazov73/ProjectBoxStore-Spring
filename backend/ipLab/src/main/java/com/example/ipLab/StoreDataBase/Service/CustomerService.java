package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Exceptions.CustomerNotFoundException;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Repositories.CustomerRepository;
import com.example.ipLab.StoreDataBase.util.validation.ValidatorUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidatorUtil validatorUtil;

    public CustomerService(CustomerRepository customerRepository,
                           ValidatorUtil validatorUtil){
        this.customerRepository = customerRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Customer addCustomer(String customerLastName, String customerFirstName, String customerMiddleName){
        Customer customer = new Customer(customerLastName, customerFirstName, customerMiddleName);
        validatorUtil.validate(customer);
        return customerRepository.save(customer);
    }

    @Transactional()
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomer(Long id, String customerLastName, String customerFirstName, String customerMiddleName){
        Customer customer = getCustomer(id);
        customer.setLastName(customerLastName);
        customer.setFirstName(customerFirstName);
        customer.setMiddleName(customerMiddleName);
        validatorUtil.validate(customer);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer deleteCustomer(Long id){
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
        return customer;
    }
    @Transactional
    public void deleteAllCustomers(){
        //for (var customer:
        //     getAllCustomers()) {
        //    customer.removeOrders();
        //}
        customerRepository.deleteAll();
    }
}
