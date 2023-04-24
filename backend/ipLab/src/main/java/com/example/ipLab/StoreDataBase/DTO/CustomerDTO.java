package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Customer;

import java.util.List;

public class CustomerDTO {
    public final Long id;
    public final String lastname;
    public final String firstname;
    public final String middleName;
    public final List<OrderedDTO> orders;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.lastname = customer.getLastName();
        this.firstname = customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.orders = customer.getOrders().stream().map(OrderedDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public List<OrderedDTO> getOrders() {
        return orders;
    }
}
