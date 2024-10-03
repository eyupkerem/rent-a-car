package com.example.rent_a_car.security;

import com.example.rent_a_car.entity.Users;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

import static com.example.rent_a_car.utils.Validations.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CostumUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Stream.of(
                userRepository.findByEmail(email).map(this::userToCostumUserDetails))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + " with email : " + email));
    }

    private CostumUserDetails userToCostumUserDetails(Users user){
        return CostumUserDetails.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getEmail())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
