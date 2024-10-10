package com.example.rent_a_car.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerException(Exception e){
        return ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(value = {AlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleAlreadyExistException(AlreadyExistException e) {
        return ErrorDto.builder()
                .code(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {FieldsEmptyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleFieldNotEmptyException(FieldsEmptyException e) {
        return ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(value = {EmailNotSendException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleFieldNotEmptyException(EmailNotSendException e) {
        return ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @Builder
    @Data
    public static class ErrorDto {
        private String code;
        private String message;
    }
}