package com.example.ipLab.StoreDataBase.Exceptions;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(Long id){
        super(String.format("Store with id: %s hasn't been found", id));
    }
}
