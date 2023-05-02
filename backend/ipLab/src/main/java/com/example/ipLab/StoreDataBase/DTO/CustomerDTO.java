package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Customer;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
    public Long id;
    @NotBlank(message = "lastName can't be null or empty")
    public String lastName;
    @NotBlank(message = "firstName can't be null or empty")
    public String firstName;
    @NotBlank(message = "middleName can't be null or empty")
    public String middleName;
    public String customerFIO;

    public CustomerDTO(){

    }

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.lastName = customer.getLastName();
        this.firstName = customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.customerFIO = lastName + " " + firstName + " " + middleName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getlastName() {
        return lastName;
    }
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }
    public String getfirstName() {
        return firstName;
    }
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getmiddleName() {
        return middleName;
    }
    public void setmiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getcustomerFIO() {
        return customerFIO;
    }

    public void setcustomerFIO(String customerFIO) {
        this.customerFIO = customerFIO;
    }
}
