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

    @Builder
    @Data
    public static class ErrorDto {
        private String code;
        private String message;
    }
}