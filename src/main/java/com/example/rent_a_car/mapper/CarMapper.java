package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.dto.SaveRequest.CarSaveRequest;
import com.example.rent_a_car.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface CarMapper {
    CarResponse toCarResponse(Car car);

    Car toCar(CarSaveRequest carSaveRequest);

}
