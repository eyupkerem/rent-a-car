package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.FuelResponse;
import com.example.rent_a_car.dto.SaveRequest.FuelSaveRequest;
import com.example.rent_a_car.entity.Fuel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface FuelMapper {
    FuelResponse toFuelResponse(Fuel fuel);
    Fuel toFuel(FuelSaveRequest fuelSaveRequest);
}
