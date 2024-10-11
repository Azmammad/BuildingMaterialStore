package com.example.buildingmaterialstore.model.exception.handle;

public class InactiveUserException extends RuntimeException{
    public InactiveUserException(Long id){
        super("User with id " + id + " is inactive");
    }
}
