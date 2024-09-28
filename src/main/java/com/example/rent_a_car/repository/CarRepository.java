package com.example.rent_a_car.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.entity.Brand;
import com.example.rent_a_car.entity.Car;
import com.example.rent_a_car.entity.Category;
import com.example.rent_a_car.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByName(String name);
    @Query("SELECT c.active FROM Car c WHERE c.id = :carId")
    boolean isCarActive(@Param("carId") Long carId);
    Optional<List<Car>> findByCategory(Category category);
    Optional<List<Car>> findByBrand(Brand brand);
    Optional<List<Car>> findByFuel(Fuel fuel);
    Optional<List<Car>> findByBrandAndCategory(Brand brand, Category category);
    Optional<List<Car>> findByBrandAndFuel(Brand brand, Fuel fuel);
    @Query("SELECT c FROM Car c WHERE c.active = true")
    Optional<List<Car>> getActiveCars();
    @Query("SELECT c FROM Car c WHERE c.active = false ")
    Optional<List<Car>> getNonActiveCars();
}
