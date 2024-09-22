package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.BrandResponse;
import com.example.rent_a_car.dto.SaveRequest.BrandSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.BrandUpdateRequest;
import com.example.rent_a_car.service.BrandService;
import com.example.rent_a_car.service.impl.BrandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll(){
        List<BrandResponse> brands=brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> findById(@PathVariable Long id){
        BrandResponse brand = brandService.findById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public ResponseEntity<BrandResponse> add(@RequestBody BrandSaveRequest request){
        BrandResponse brandResponse = brandService.add(request);
        return ResponseEntity.ok(brandResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable Long id
            , @RequestBody BrandUpdateRequest request){
        BrandResponse brandResponse = brandService.update(id , request);
        return ResponseEntity.ok(brandResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandResponse> deleteById(@PathVariable Long id){
        BrandResponse brand = brandService.deleteById(id);
        return ResponseEntity.ok(brand);
    }

}
