package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.CategoryResponse;
import com.example.rent_a_car.dto.SaveRequest.CategorySaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.CategoryUpdateRequest;
import com.example.rent_a_car.service.CategoryService;
import com.example.rent_a_car.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(){
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        CategoryResponse category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> add(@RequestBody CategorySaveRequest request){
        CategoryResponse category = categoryService.add(request);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryUpdateRequest request){
        CategoryResponse category = categoryService.update(id , request);
        return ResponseEntity.ok(category);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteById(@PathVariable Long id){
        CategoryResponse category = categoryService.deleteById(id);
        return ResponseEntity.ok(category);
    }

}
