package com.example.ipLab.StoreDataBase.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String login){
        super(String.format("User with login: %s hasn't been found", login));
    }
}
