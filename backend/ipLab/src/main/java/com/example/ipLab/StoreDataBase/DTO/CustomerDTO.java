package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Customer;

import java.util.List;

public class CustomerDTO {
    public final Long id;
    public final String lastName;
    public final String firstName;
    public final String middleName;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.lastName = customer.getLastName();
        this.firstName = customer.getFirstName();
        this.middleName = customer.getMiddleName();
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastName;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
