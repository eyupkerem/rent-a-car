package com.example.rent_a_car.repository;

import com.example.rent_a_car.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Optional<Category> findByName(String name);
}
