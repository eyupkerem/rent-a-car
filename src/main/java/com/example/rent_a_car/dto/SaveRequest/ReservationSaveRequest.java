package com.example.rent_a_car.dto.SaveRequest;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSaveRequest {
    private LocalDate startDate;

    private LocalDate endDate;
}
