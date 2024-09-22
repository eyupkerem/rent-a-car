package com.example.rent_a_car.repository;

import com.example.rent_a_car.entity.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GearRepository extends JpaRepository<Gear,Long> {
    Optional<Gear> findByName(String name);
}
