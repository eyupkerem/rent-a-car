package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.BrandResponse;
import com.example.rent_a_car.dto.SaveRequest.BrandSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.BrandUpdateRequest;
import com.example.rent_a_car.entity.Brand;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.FieldsEmptyException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.BrandMapper;
import com.example.rent_a_car.repository.BrandRepository;
import com.example.rent_a_car.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.*;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brandMapper::toBrandResponse)
                .collect(Collectors.toList());
    }

    public BrandResponse findById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toBrandResponse)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }

    public BrandResponse add(BrandSaveRequest request) {
        boolean brandExists = brandRepository.findByName(request.getName()).isPresent();
        if (brandExists){
            throw new RuntimeException(BRAND_ALREADY_EXIST);
        }

        Brand updateBrand = brandMapper.toBrand(request);
        brandRepository.save(updateBrand);
        return brandMapper.toBrandResponse(updateBrand) ;
    }

    public BrandResponse deleteById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        brandRepository.deleteById(id);
        return brandMapper.toBrandResponse(brand);
    }

    public BrandResponse update(Long id , BrandUpdateRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(BRAND_NOT_FOUND));

        if (request.getName().isBlank()){
            throw new FieldsEmptyException(FIELDS_NOT_EMPTY);
        }

        boolean brandExist = brandRepository.findByName(request.getName()).isPresent();
        if (brandExist){
            throw new AlreadyExistException(BRAND_ALREADY_EXIST);
        }

        brand.setName(request.getName());
        brandRepository.save(brand);
        return brandMapper.toBrandResponse(brand);

    }

}
