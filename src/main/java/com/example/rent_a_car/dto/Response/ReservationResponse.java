package com.example.rent_a_car.dto.Response;

import com.example.rent_a_car.entity.Car;
import com.example.rent_a_car.entity.Users;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private UserResponse user;
    private CarResponse car;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
