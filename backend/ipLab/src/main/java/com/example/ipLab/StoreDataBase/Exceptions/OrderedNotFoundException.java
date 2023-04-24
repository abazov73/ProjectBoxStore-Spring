package com.example.ipLab.StoreDataBase.Exceptions;

public class OrderedNotFoundException extends RuntimeException{
    public OrderedNotFoundException(Long id){
        super(String.format("Order with id: %s hasn't been found", id));
    }
}
