package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.ReservationResponse;
import com.example.rent_a_car.dto.SaveRequest.ReservationSaveRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getAll();
    ReservationResponse findById(Long id);
    ReservationResponse add(Long userId, Long carId, ReservationSaveRequest request) throws MessagingException;
    boolean checkCarReservation(Long carId, LocalDate startDate, LocalDate endDate);
}
