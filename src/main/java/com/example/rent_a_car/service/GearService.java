package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.GearResponse;
import com.example.rent_a_car.dto.SaveRequest.GearSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.GearUpdateRequest;

import java.util.List;

public interface GearService {
    List<GearResponse> getAll();

    GearResponse findById(Long id);

    GearResponse add(GearSaveRequest request);

    GearResponse deleteById(Long id);

    GearResponse update(Long id , GearUpdateRequest request);
}
