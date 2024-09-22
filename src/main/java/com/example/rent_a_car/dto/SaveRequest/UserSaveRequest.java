package com.example.rent_a_car.dto.SaveRequest;

import com.example.rent_a_car.entity.enums.ERole;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Set<ERole> roles;
}
