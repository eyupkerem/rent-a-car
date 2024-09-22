package com.example.rent_a_car.repository;

import com.example.rent_a_car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation , Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN false ELSE true END " +
            "FROM Reservation r " +
            "WHERE r.car.id = :carId " +
            "AND (r.startDate <= :endDate AND r.endDate >= :startDate)")
    boolean isCarAvailable(@Param("carId") Long carId,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);






}
