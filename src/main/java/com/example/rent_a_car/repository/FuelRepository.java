package com.example.rent_a_car.repository;

import com.example.rent_a_car.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {

    Optional<Fuel> findByName(String name);
}
