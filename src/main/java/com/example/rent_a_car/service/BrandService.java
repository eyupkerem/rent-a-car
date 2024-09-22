package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.BrandResponse;
import com.example.rent_a_car.dto.SaveRequest.BrandSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.BrandUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandResponse> getAll();

    BrandResponse findById(Long id);

    BrandResponse add(BrandSaveRequest request);

    BrandResponse deleteById(Long id);
    BrandResponse update(Long id , BrandUpdateRequest request);
}
