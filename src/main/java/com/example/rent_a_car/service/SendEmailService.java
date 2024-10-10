package com.example.rent_a_car.service;

import jakarta.mail.MessagingException;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SendEmailService {
    public void reservationMail(String to ,
                                String costumerName ,
                                String carName ,
                                String brandName ,
                                String gearType,
                                LocalDate startDate ,
                                LocalDate endDate ,
                                BigDecimal price) throws MessagingException;
}
