package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.FuelResponse;
import com.example.rent_a_car.dto.SaveRequest.FuelSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.FuelUpdateRequest;

import java.util.List;

public interface FuelService {
    List<FuelResponse> getAll();

    FuelResponse findById(Long id);

    FuelResponse add(FuelSaveRequest request);

    FuelResponse deleteById(Long id);

    FuelResponse update(Long id, FuelUpdateRequest request);
}
