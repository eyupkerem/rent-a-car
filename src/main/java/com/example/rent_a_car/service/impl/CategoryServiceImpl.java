package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.CategoryResponse;
import com.example.rent_a_car.dto.SaveRequest.CategorySaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.CategoryUpdateRequest;
import com.example.rent_a_car.entity.Category;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.CategoryMapper;
import com.example.rent_a_car.repository.CategoryRepository;
import com.example.rent_a_car.service.CarService;
import com.example.rent_a_car.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.CATEGORY_ALREADY_EXIST;
import static com.example.rent_a_car.utils.Validations.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((categoryMapper :: toCategoryResponse))
                .collect(Collectors.toList());
    }

    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper :: toCategoryResponse)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));
    }


    public CategoryResponse add(CategorySaveRequest request) {
        boolean categoryExist = categoryRepository.findByName(request.getName()).isPresent();

        if (categoryExist){
            throw new AlreadyExistException(CATEGORY_ALREADY_EXIST);
        }

        Category newCategory = categoryMapper.toCategory(request);
        categoryRepository.save(newCategory);
        return categoryMapper.toCategoryResponse(newCategory);
    }

    public CategoryResponse deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(CATEGORY_NOT_FOUND));
        categoryRepository.deleteById(id);
        return categoryMapper.toCategoryResponse(category);
    }


    public CategoryResponse update(Long id, CategoryUpdateRequest request) {
        boolean categoryExist = categoryRepository.findByName(request.getName()).isPresent();
        if (categoryExist){
            throw new AlreadyExistException(CATEGORY_ALREADY_EXIST);
        }

        Category updateCategory = categoryMapper.toCategory(request);
        updateCategory.setName(request.getName());
        categoryRepository.save(updateCategory);
        return categoryMapper.toCategoryResponse(updateCategory);
    }
}
