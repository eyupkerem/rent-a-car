package com.example.rent_a_car.dto.Response;

import com.example.rent_a_car.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Set<Role> roles;

}
