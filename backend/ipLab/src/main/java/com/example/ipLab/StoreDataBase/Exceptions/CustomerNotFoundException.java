package com.example.ipLab.StoreDataBase.Exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super(String.format("Customer with id: %s hasn't been found", id));
    }
}
