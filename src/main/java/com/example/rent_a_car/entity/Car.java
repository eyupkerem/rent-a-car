package com.example.rent_a_car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , nullable = false , length = 20)
    private String name;

    @Column(name = "year" , nullable = false , length = 20)
    private Year year;

    @Column(name = "km" , nullable = false , length = 20)
    private Long km;

    @Column(name = "color" , nullable = false , length = 20)
    private String color;

    @ManyToOne
    @JoinColumn(name = "brand_id" , nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "gear_id" , nullable = false)
    private Gear gear;

    @ManyToOne
    @JoinColumn(name = "fuel_id" , nullable = false)
    private Fuel fuel;

    @Column(name = "price_per_day" , nullable = false)
    private Long pricePerDay;

    @Column(name = "is_air_condition" , nullable = false)
    private boolean airCondition;

    @Column(name = "number_of_seats" , nullable = false)
    private int numberOfSeats;

    @Column(name = "is_rented" , nullable = false)
    private boolean rented;

    @Column(name = "is_active" , nullable = false)
    private boolean active;

}
