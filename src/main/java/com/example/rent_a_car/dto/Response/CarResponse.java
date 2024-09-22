package com.example.rent_a_car.dto.Response;

import com.example.rent_a_car.entity.Brand;
import com.example.rent_a_car.entity.Category;
import com.example.rent_a_car.entity.Fuel;
import com.example.rent_a_car.entity.Gear;
import lombok.*;

import java.time.Year;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String name;
    private Year year;
    private Long km;
    private String color;
    private BrandResponse brand;
    private CategoryResponse category;
    private GearResponse gear;
    private FuelResponse fuel;
    private Long pricePerDay;
    private boolean airCondition;
    private int numberOfSeats;
    private boolean rented;
//    private boolean active;


}
