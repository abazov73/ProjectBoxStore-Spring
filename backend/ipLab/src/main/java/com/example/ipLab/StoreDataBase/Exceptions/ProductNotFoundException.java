package com.example.ipLab.StoreDataBase.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super(String.format("Product with id: %s hasn't been found", id));
    }
}
