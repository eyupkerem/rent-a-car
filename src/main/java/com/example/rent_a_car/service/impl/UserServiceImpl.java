package com.example.rent_a_car.service.impl;

import com.example.rent_a_car.dto.Response.UserResponse;
import com.example.rent_a_car.dto.SaveRequest.UserSaveRequest;
import com.example.rent_a_car.dto.UpdateRequest.UserUpdateRequest;
import com.example.rent_a_car.entity.Role;
import com.example.rent_a_car.entity.Users;
import com.example.rent_a_car.entity.enums.ERole;
import com.example.rent_a_car.exception.AlreadyExistException;
import com.example.rent_a_car.exception.FieldsEmptyException;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.mapper.UserMapper;
import com.example.rent_a_car.repository.RoleRepository;
import com.example.rent_a_car.repository.UserRepository;
import com.example.rent_a_car.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.rent_a_car.utils.Validations.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAll() {
        List<Users> users= userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

    }


    public UserResponse add(UserSaveRequest request) {
        if (!request.getEmail().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            throw new RuntimeException(GMAIL_MANDATORY);
        }

        boolean emailExist = userRepository.findByEmail(request.getEmail()).isPresent();
        boolean phoneNumberExist = userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent();

        if (emailExist || phoneNumberExist){
            throw new RuntimeException(EMAIL_OR_PHONE_NUMBER_EXIST);
        }

        Users newUser = userMapper.toUser(request);
        Set<Role> roles = new HashSet<>();
        for (ERole eRole : request.getRoles()) {
            Role role = roleRepository.findByRole(eRole)
                    .orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND));

            roles.add(role);

        }
        newUser.setRoles(roles);

        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    public UserResponse update(Long id, UserUpdateRequest request) {
        Users user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(USER_NOT_FOUND));

        if (request.getFirstName().isBlank() &&
                request.getLastName().isBlank() &&
                request.getEmail().isBlank() &&
                request.getPhoneNumber().isBlank() &&
                request.getPassword().isBlank()
        ) {
            throw new FieldsEmptyException(FIELDS_NOT_EMPTY);
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return userMapper.toUserResponse(user);

    }


    public UserResponse deleteById(Long id) {
        Users user= userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        userRepository.deleteById(id);

        return userMapper.toUserResponse(user);
    }
}
