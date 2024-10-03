package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.dto.SaveRequest.CarSaveRequest;
import com.example.rent_a_car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAll(){
        List<CarResponse> cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/active-cars")
    public ResponseEntity<List<CarResponse>> getActiveCars(){
        List<CarResponse> activeCars = carService.getActiveCars();
        return ResponseEntity.ok(activeCars);
    }
    @GetMapping("/non-active-cars")
    public ResponseEntity<List<CarResponse>> getNonActiveCars(){
        List<CarResponse> activeCars = carService.getNonActiveCars();
        return ResponseEntity.ok(activeCars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findById(@PathVariable Long id){
        CarResponse carResponse = carService.findById(id);
        return ResponseEntity.ok(carResponse);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<CarResponse>> findByCategory(@PathVariable Long id){
        List<CarResponse> cars = carService.findByCategory(id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<List<CarResponse>> findByBrand(@PathVariable Long id){
        List<CarResponse> cars = carService.findByBrand(id);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/fuel/{id}")
    public ResponseEntity<List<CarResponse>> findByFuel(@PathVariable Long id){
        List<CarResponse> cars = carService.findByFuel(id);
        return ResponseEntity.ok(cars);
    }


    @GetMapping("/cars")
    public ResponseEntity<List<CarResponse>> findCars(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long fuelId,
            @RequestParam(required = false) Long gearId) {

        List<CarResponse> cars = carService.findCarsByFilters(brandId, categoryId, fuelId, gearId);
        return ResponseEntity.ok(cars);
    }


    @PostMapping
    public ResponseEntity<CarResponse> add(@RequestBody CarSaveRequest request){
        CarResponse car = carService.add(request);
        return ResponseEntity.ok(car);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponse> delete(@PathVariable Long id){
        CarResponse car = carService.delete(id);
        return ResponseEntity.ok(car);
    }
}
