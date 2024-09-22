package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.UserResponse;
import com.example.rent_a_car.dto.SaveRequest.UserSaveRequest;
import com.example.rent_a_car.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(Users users);

    Users toUser(UserSaveRequest userSaveRequest);
}
