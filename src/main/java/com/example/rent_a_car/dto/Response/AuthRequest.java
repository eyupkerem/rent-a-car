package com.example.rent_a_car.dto.Response;


public record AuthRequest(
        String email,
        String password
) {
}
