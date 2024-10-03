package com.example.rent_a_car.controller;

import com.example.rent_a_car.dto.Response.AuthRequest;
import com.example.rent_a_car.dto.Response.UserResponse;
import com.example.rent_a_car.dto.SaveRequest.UserSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.UserUpdateRequest;
import com.example.rent_a_car.service.UserService;
import com.example.rent_a_car.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> users=userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserResponse user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponse> add(@RequestBody UserSaveRequest request){
        UserResponse user = userService.add(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id , @RequestBody UserUpdateRequest request){
        UserResponse user = userService.update(id , request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteById(@PathVariable Long id){
        UserResponse user = userService.deleteById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest request){
        String token = userService.generateToken(request);
        return ResponseEntity.ok(token);
    }



}
