package com.example.rent_a_car.entity;

import com.example.rent_a_car.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", unique = true, nullable = false)
    private ERole role;
}
