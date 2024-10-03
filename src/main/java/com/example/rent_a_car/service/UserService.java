package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.AuthRequest;
import com.example.rent_a_car.dto.Response.UserResponse;
import com.example.rent_a_car.dto.SaveRequest.UserSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.UserUpdateRequest;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse findById(Long id);

    UserResponse add(UserSaveRequest request);

    UserResponse deleteById(Long id);

    UserResponse update(Long id, UserUpdateRequest request);

    String generateToken(AuthRequest request);
}
