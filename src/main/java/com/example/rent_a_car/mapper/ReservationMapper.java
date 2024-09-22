package com.example.rent_a_car.mapper;

import com.example.rent_a_car.dto.Response.ReservationResponse;
import com.example.rent_a_car.dto.SaveRequest.ReservationSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.ReservationUpdateRequest;
import com.example.rent_a_car.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponse toReservationResponse(Reservation reservation);

    Reservation toReservation(ReservationSaveRequest request);
    Reservation toReservation(ReservationUpdateRequest request);

}
