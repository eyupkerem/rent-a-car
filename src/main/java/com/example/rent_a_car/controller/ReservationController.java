package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.ReservationResponse;
import com.example.rent_a_car.dto.SaveRequest.ReservationSaveRequest;
import com.example.rent_a_car.service.ReservationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class    ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> findAll(){
        List<ReservationResponse> reservations = reservationService.getAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id){
        ReservationResponse reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/user/{userId}/car/{carId}")
    public ResponseEntity<ReservationResponse> add(@PathVariable Long userId,
                                                    @PathVariable Long carId,
                                                    @RequestBody ReservationSaveRequest request
    )throws MessagingException{
        ReservationResponse reservation = reservationService.add(userId,carId,request);
        return ResponseEntity.ok(reservation);
    }
}
