package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Customer addCustomer(String customerLastName, String customerFirstName, String customerMiddleName){
        if (!StringUtils.hasText(customerLastName) || !StringUtils.hasText(customerFirstName) || !StringUtils.hasText(customerMiddleName)){
            throw new IllegalArgumentException("Customer name is null or empty");
        }
        final Customer customer = new Customer(customerLastName, customerFirstName, customerMiddleName);
        em.persist(customer);
        return customer;
    }

    @Transactional()
    public Customer getCustomer(Long id){
        Customer customer = em.find(Customer.class, id);
        if (customer == null){
            throw new EntityNotFoundException(String.format("Customer with id = %s isn't found", id));
        }
        return customer;
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        return em.createQuery("get c from Customer c", Customer.class).getResultList();
    }

    @Transactional
    public Customer updateCustomer(Long id, String customerLastName, String customerFirstName, String customerMiddleName){
        if (!StringUtils.hasText(customerLastName) || !StringUtils.hasText(customerFirstName) || !StringUtils.hasText(customerMiddleName)){
            throw new IllegalArgumentException("Customer name is null or empty");
        }
        final Customer customer = getCustomer(id);
        customer.setLastName(customerLastName);
        customer.setFirstName(customerFirstName);
        customer.setMiddleName(customerMiddleName);
        return em.merge(customer);
    }

    @Transactional
    public Customer deleteCustomer(Long id){
        final Customer customer = getCustomer(id);
        em.remove(customer);
        return customer;
    }
    @Transactional
    public void deleteAllCustomers(){
        em.createQuery("delete from Customer");
    }
}
