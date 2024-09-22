package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.dto.SaveRequest.CarSaveRequest;

import java.util.List;

public interface CarService {
    List<CarResponse> getAll();

    CarResponse findById(Long id);

    CarResponse add(CarSaveRequest request);

    CarResponse delete(Long id);
    List<CarResponse> findByCategory(Long id);

    List<CarResponse> findByBrand(Long id);

    List<CarResponse> findByFuel(Long id);

    List<CarResponse> findByBrandAndCategory(Long brandId, Long categoryId);

    List<CarResponse> findByBrandAndFuel(Long brandId, Long fuelId);
}
