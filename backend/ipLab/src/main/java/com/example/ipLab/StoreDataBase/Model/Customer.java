package com.example.ipLab.StoreDataBase.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotBlank(message = "Customer's last name can't be empty")
    private String lastName;
    @Column
    @NotBlank(message = "Customer's first name can't be empty")
    private String firstName;
    @Column
    @NotBlank(message = "Customer's middle name can't be empty")
    private String middleName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Ordered> orders;
    public void AddOrdered(Ordered ordered){
        this.orders.add(ordered);
        if (ordered.getCustomer() != this){
            ordered.setCustomer(this);
        }
    }
    @PreRemove
    public void removeOrders(){
        for (var order:
             orders) {
            order.removeCustomer();
        }
        orders = null;
    }
    public Customer(){
        this.orders = new ArrayList<>();
    }

    public Customer(String lastName, String firstName, String middleName){
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.orders = new ArrayList<Ordered>();
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Ordered> getOrders() {
        return orders;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
