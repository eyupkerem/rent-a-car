package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.dto.Response.CategoryResponse;
import com.example.rent_a_car.dto.SaveRequest.CategorySaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.CategoryUpdateRequest;
import com.example.rent_a_car.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);

    Category toCategory(CategorySaveRequest request);

    Category toCategory(CategoryUpdateRequest request);
}
