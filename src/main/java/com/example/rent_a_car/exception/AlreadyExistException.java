package com.example.rent_a_car.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
