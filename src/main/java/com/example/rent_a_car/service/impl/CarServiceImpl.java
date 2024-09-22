package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.CarResponse;
import com.example.rent_a_car.dto.SaveRequest.CarSaveRequest;
import com.example.rent_a_car.entity.*;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.CarMapper;
import com.example.rent_a_car.repository.*;
import com.example.rent_a_car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final GearRepository gearRepository;
    private final FuelRepository fuelRepository;

    public List<CarResponse> getAll() {
        List<Car> carList = carRepository.findAll();
        return carList.stream()
                .map(carMapper :: toCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse findById(Long id) {
        return carRepository.findById(id)
                .map(carMapper :: toCarResponse)
                .orElseThrow(() -> new ResourceNotFoundException(BRAND_NOT_FOUND));
    }

    public CarResponse add(CarSaveRequest request) {
          boolean nameExist = carRepository.findByName(request.getName()).isPresent();
          if (nameExist){
              throw new RuntimeException(CAR_NAME_ALREADY_EXIST);
          }
          Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException(BRAND_NOT_FOUND));

          Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));

          Gear gear = gearRepository.findById(request.getGearId())
                .orElseThrow(() -> new ResourceNotFoundException(GEAR_NOT_FOUND));

          Fuel fuel = fuelRepository.findById(request.getFuelId())
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));

          Car newCar = carMapper.toCar(request);
          newCar.setBrand(brand);
          newCar.setCategory(category);
          newCar.setGear(gear);
          newCar.setFuel(fuel);

          carRepository.save(newCar);
          return carMapper.toCarResponse(newCar);
    }

    public List<CarResponse> findByCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));

        List<Car> carList = carRepository.findByCategory(category)
                .orElseThrow( () -> new ResourceNotFoundException(CAR_NOT_FOUND));

        return carList.stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }
    public List<CarResponse> findByBrand(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BRAND_NOT_FOUND));

        List<Car> carList = carRepository.findByBrand(brand)
                .orElseThrow( () -> new ResourceNotFoundException(CAR_NOT_FOUND));

        return carList.stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }
    public List<CarResponse> findByFuel(Long id) {

        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));

        List<Car> carList = carRepository.findByFuel(fuel)
                .orElseThrow( () -> new ResourceNotFoundException(CAR_NOT_FOUND));

        return carList.stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    public List<CarResponse> findByBrandAndCategory(Long brandId, Long categoryId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException(BRAND_NOT_FOUND));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));

        List<Car> carList = carRepository.findByBrandAndCategory(brand , category)
                .orElseThrow( () -> new ResourceNotFoundException(CAR_NOT_FOUND));

        return carList.stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    public List<CarResponse> findByBrandAndFuel(Long brandId, Long fuelId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException(BRAND_NOT_FOUND));
        Fuel fuel = fuelRepository.findById(fuelId)
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));

        List<Car> carList = carRepository.findByBrandAndFuel(brand , fuel)
                .orElseThrow( () -> new ResourceNotFoundException(CAR_NOT_FOUND));

        return carList.stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CAR_NOT_FOUND));

        car.setActive(!car.isActive());
        carRepository.save(car);
        return carMapper.toCarResponse(car);
    }

}
