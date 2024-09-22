package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.GearResponse;
import com.example.rent_a_car.dto.SaveRequest.GearSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.GearUpdateRequest;
import com.example.rent_a_car.entity.Gear;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GearMapper {
    GearResponse toGearResponse(Gear gear);

    Gear toGear(GearSaveRequest request);

    Gear toGear(GearUpdateRequest request);

}
