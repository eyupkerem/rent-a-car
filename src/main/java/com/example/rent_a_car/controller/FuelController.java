package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.FuelResponse;
import com.example.rent_a_car.dto.SaveRequest.FuelSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.FuelUpdateRequest;
import com.example.rent_a_car.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fuel")
public class FuelController {
    private final FuelService fuelService;

    @GetMapping
    public ResponseEntity<List<FuelResponse>> getAll(){
        List<FuelResponse> fuels = fuelService.getAll();
        return ResponseEntity.ok(fuels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelResponse> findById(@PathVariable Long id){
        FuelResponse fuel = fuelService.findById(id);
        return ResponseEntity.ok(fuel);
    }

    @PostMapping
    public ResponseEntity<FuelResponse> add(@RequestBody FuelSaveRequest request){
        FuelResponse fuelResponse = fuelService.add(request);
        return ResponseEntity.ok(fuelResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelResponse> update(@PathVariable Long id, @RequestBody FuelUpdateRequest request){
        FuelResponse fuel = fuelService.update(id , request);
        return ResponseEntity.ok(fuel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuelResponse> deleteById(@PathVariable Long id){
        FuelResponse fuel = fuelService.deleteById(id);
        return ResponseEntity.ok(fuel);
    }

}
