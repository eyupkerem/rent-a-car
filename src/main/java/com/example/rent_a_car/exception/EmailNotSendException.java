package com.example.rent_a_car.exception;

public class EmailNotSendException extends RuntimeException{
    public EmailNotSendException(String message) {
        super(message);
    }

}
