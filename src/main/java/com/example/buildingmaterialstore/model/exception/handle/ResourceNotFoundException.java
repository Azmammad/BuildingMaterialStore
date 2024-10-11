package com.example.buildingmaterialstore.model.exception.handle;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
