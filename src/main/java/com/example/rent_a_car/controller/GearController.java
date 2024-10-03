package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.GearResponse;
import com.example.rent_a_car.dto.SaveRequest.GearSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.GearUpdateRequest;
import com.example.rent_a_car.service.GearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gear")
public class GearController {

    private final GearService gearService;

    @GetMapping
    public ResponseEntity<List<GearResponse>> findAll(){
        List<GearResponse> gearList = gearService.getAll();
        return ResponseEntity.ok(gearList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GearResponse> findById(@PathVariable Long id){
        GearResponse gear = gearService.findById(id);
        return ResponseEntity.ok(gear);
    }

    @PostMapping
    public ResponseEntity<GearResponse> add(@RequestBody GearSaveRequest request){
        GearResponse gear = gearService.add(request);
        return ResponseEntity.ok(gear);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GearResponse> update(@PathVariable Long id , @RequestBody GearUpdateRequest request){
        GearResponse gear = gearService.update(id , request);
        return ResponseEntity.ok(gear);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<GearResponse> deleteById(@PathVariable Long id){
        GearResponse gear = gearService.deleteById(id);
        return ResponseEntity.ok(gear);
    }
}
