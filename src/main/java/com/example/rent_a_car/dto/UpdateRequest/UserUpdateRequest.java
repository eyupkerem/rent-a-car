package com.example.rent_a_car.dto.UpdateRequest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
