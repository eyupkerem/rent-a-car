package com.example.rent_a_car.service;

import com.example.rent_a_car.dto.Response.CategoryResponse;
import com.example.rent_a_car.dto.SaveRequest.CategorySaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

    CategoryResponse findById(Long id);

    CategoryResponse add(CategorySaveRequest request);

    CategoryResponse deleteById(Long id);

    CategoryResponse update(Long id, CategoryUpdateRequest request);
}
