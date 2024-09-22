package com.example.rent_a_car.dto.Response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
    private Long id;
    private String name;
}
