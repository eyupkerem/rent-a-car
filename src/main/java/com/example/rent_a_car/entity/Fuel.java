package com.example.rent_a_car.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "fuel_type", unique = true, nullable = false)
//    private EFuel fuel;

    @Column(unique = true , nullable = false)
    private String name;

}
