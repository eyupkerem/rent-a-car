package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.GearResponse;
import com.example.rent_a_car.dto.SaveRequest.GearSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.GearUpdateRequest;
import com.example.rent_a_car.entity.Gear;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.FieldsEmptyException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.GearMapper;
import com.example.rent_a_car.repository.GearRepository;
import com.example.rent_a_car.service.GearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GearServiceImpl implements GearService {
    private final GearRepository gearRepository;
    private final GearMapper gearMapper;

    public List<GearResponse> getAll() {
        List<Gear> gearList = gearRepository.findAll();
                return gearList.stream()
                        .map(gearMapper :: toGearResponse)
                        .collect(Collectors.toList());

    }

    public GearResponse findById(Long id) {
        Gear gear = gearRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(GEAR_NOT_FOUND));
        return gearMapper.toGearResponse(gear);
    }

    public GearResponse add(GearSaveRequest request) {
        boolean gearExist = gearRepository.findByName(request.getName()).isPresent();
        if (gearExist) {
            throw new AlreadyExistException(GEAR_ALREADY_EXIST);
        }
        Gear gear = gearMapper.toGear(request);
        gearRepository.save(gear);
        return gearMapper.toGearResponse(gear);
    }


    public GearResponse update(Long id , GearUpdateRequest request) {
        boolean gearExist = gearRepository.findByName(request.getName()).isPresent();
        if (gearExist){
            throw new AlreadyExistException(GEAR_ALREADY_EXIST);
        }

        Gear updateGear = gearRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(GEAR_NOT_FOUND)
        );

        if (request.getName().isBlank()){
        throw new FieldsEmptyException(FIELDS_NOT_EMPTY);
        }

        updateGear.setName(request.getName());
        gearRepository.save(updateGear);
        return gearMapper.toGearResponse(updateGear);

    }

    public GearResponse deleteById(Long id) {
        Gear gear = gearRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(GEAR_NOT_FOUND));
        gearRepository.deleteById(id);
        return gearMapper.toGearResponse(gear);
    }


}
