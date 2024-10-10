package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.ReservationResponse;
import com.example.rent_a_car.dto.SaveRequest.ReservationSaveRequest;
import com.example.rent_a_car.entity.Car;
import com.example.rent_a_car.entity.Reservation;
import com.example.rent_a_car.entity.Users;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.EmailNotSendException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.ReservationMapper;
import com.example.rent_a_car.repository.CarRepository;
import com.example.rent_a_car.repository.ReservationRepository;
import com.example.rent_a_car.repository.UserRepository;
import com.example.rent_a_car.service.ReservationService;
import com.example.rent_a_car.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.*;

@Service
@RequiredArgsConstructor

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final SendEmailService sendEmailService;
    private final ReservationMapper reservationMapper;

    public List<ReservationResponse> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
          return reservations.stream()
                  .map(reservationMapper :: toReservationResponse)
                  .collect(Collectors.toList());
    }

    public ReservationResponse findById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper :: toReservationResponse)
                .orElseThrow(()-> new ResourceNotFoundException(RESERVATION_NOT_FOUND));

    }

    public ReservationResponse add(Long userId, Long carId, ReservationSaveRequest request) throws MessagingException {
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND)
        );
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new ResourceNotFoundException(CAR_NOT_FOUND)
        );

        LocalDate startDate = request.getStartDate();

        if (startDate.isBefore(LocalDate.now())){
            throw new RuntimeException("Please enter a valid dates");
        }

        LocalDate endDate = request.getEndDate();

        boolean isCarActive = carRepository.isCarActive(carId);
        if (!isCarActive){
            throw new RuntimeException("CAR is not Active");
        }

        boolean isAvailable = checkCarReservation(carId, startDate, endDate);
        BigDecimal price = reservationPrice(startDate , endDate, car.getPricePerDay());

        if (isAvailable){
            Reservation newReservation = reservationMapper.toReservation(request);
            newReservation.setCar(car);
            newReservation.setUser(user);
            newReservation.setPrice(price);
            reservationRepository.save(newReservation);

            try{
                sendEmailService.reservationMail(
                        user.getEmail(),
                        user.getFirstName(),
                        car.getName() ,
                        car.getBrand().getName() ,
                        car.getGear().getName() ,
                        startDate ,
                        endDate ,
                        price
                );
            }
            catch (MessagingException e){
                throw  new EmailNotSendException(EMAIL_COULD_NOT_SEND);
            }

            return reservationMapper.toReservationResponse(newReservation);
        }
        else {
            throw new AlreadyExistException(CAR_NOT_AVAILABLE);
        }
    }

    public boolean checkCarReservation(Long carId, LocalDate startDate, LocalDate endDate){
        return reservationRepository.isCarAvailable(carId , startDate , endDate);
    }

    private BigDecimal reservationPrice(LocalDate startDate, LocalDate endDate , Long price){
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return BigDecimal.valueOf(daysBetween).multiply(BigDecimal.valueOf(price));
    }

}
