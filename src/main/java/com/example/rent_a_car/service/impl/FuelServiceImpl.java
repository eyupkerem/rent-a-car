package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.FuelResponse;
import com.example.rent_a_car.dto.SaveRequest.FuelSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.FuelUpdateRequest;
import com.example.rent_a_car.entity.Fuel;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.FuelMapper;
import com.example.rent_a_car.repository.FuelRepository;
import com.example.rent_a_car.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.FUEL_ALREADY_EXIST;
import static com.example.rent_a_car.utils.Validations.FUEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl  implements FuelService {
    private final FuelRepository fuelRepository;
    private final FuelMapper fuelMapper;

    public List<FuelResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        return fuels.stream()
                .map((fuelMapper :: toFuelResponse))
                .collect(Collectors.toList());
    }

    public FuelResponse findById(Long id) {
        return fuelRepository.findById(id)
                .map(fuelMapper :: toFuelResponse)
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));
    }


    public FuelResponse add(FuelSaveRequest request) {
        boolean fuelExist = fuelRepository.findByName(request.getName()).isPresent();

        if (fuelExist){
            throw new AlreadyExistException(FUEL_ALREADY_EXIST);
        }
        Fuel newFuel = fuelMapper.toFuel(request);
        fuelRepository.save(newFuel);
        return fuelMapper.toFuelResponse(newFuel);
    }


    public FuelResponse update(Long id, FuelUpdateRequest request) {
        boolean fuelExist = fuelRepository.findByName(request.getName()).isPresent();
        if (fuelExist){
            throw new AlreadyExistException(FUEL_ALREADY_EXIST);
        }

        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));

        fuel.setName(request.getName());
        fuelRepository.save(fuel);
        return fuelMapper.toFuelResponse(fuel);
    }

    public FuelResponse deleteById(Long id) {
        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FUEL_NOT_FOUND));
        fuelRepository.delete(fuel);
        return fuelMapper.toFuelResponse(fuel);
    }



}
