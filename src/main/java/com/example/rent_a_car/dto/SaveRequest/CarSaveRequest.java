package com.example.rent_a_car.dto.SaveRequest;

import com.example.rent_a_car.entity.Brand;
import com.example.rent_a_car.entity.Category;
import com.example.rent_a_car.entity.Fuel;
import com.example.rent_a_car.entity.Gear;
import lombok.*;

import javax.swing.*;
import java.time.Year;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarSaveRequest {
    private String name;
    private Year year;
    private Long km;
    private String color;
    private Long brandId;
    private Long categoryId;
    private Long gearId;
    private Long fuelId;
    private Long pricePerDay;
    private boolean airCondition;
    private int numberOfSeats;
    private boolean rented;
    private boolean active;

}
