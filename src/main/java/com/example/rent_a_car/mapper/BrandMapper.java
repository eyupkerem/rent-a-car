package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.BrandResponse;
import com.example.rent_a_car.dto.SaveRequest.BrandSaveRequest;
import com.example.rent_a_car.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toBrandResponse(Brand brand);

    Brand toBrand(BrandSaveRequest brandSaveRequest);
}
