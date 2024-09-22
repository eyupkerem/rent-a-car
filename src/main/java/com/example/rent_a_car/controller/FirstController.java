package com.example.rent_a_car.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/first")
public class FirstController {

    @GetMapping
    public String first(){
        return "rent-a-car";
    }

}
