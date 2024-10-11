package com.example.buildingmaterialstore.model.enums;


import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE(1),
    INACTIVE(0),
    DELETED(-1);

    private final int value;

    UserStatus(int value){
        this.value = value;
    }

    public static UserStatus formValue(int value){
        for (UserStatus status: UserStatus.values()){
            if (status.getValue() == value){
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + value);
    }
}
