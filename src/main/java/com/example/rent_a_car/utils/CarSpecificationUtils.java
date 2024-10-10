package com.example.rent_a_car.utils;


import com.example.rent_a_car.entity.Car;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static com.example.rent_a_car.entity.SpecificationConstant.STATUS_FIELD;

@Component
public class CarSpecificationUtils {

    public Specification<Car> filterByStatus(Integer status){
        return ((root, query, criteriaBuilder) -> {
            if (ObjectUtils.isEmpty(status))
                return criteriaBuilder.conjunction();


            Boolean activeStatus;
            if (status.equals(1)) {
                activeStatus = true;
            } else if (status.equals(0)) {
                activeStatus = false;
            } else {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get(STATUS_FIELD), activeStatus);
        });
    }

}
