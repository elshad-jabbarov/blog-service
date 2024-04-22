package com.vitanur.blogservice.models.service;

import com.vitanur.blogservice.models.dtos.PermissionDto;
import com.vitanur.blogservice.models.dtos.UserRequestDto;
import com.vitanur.blogservice.models.dtos.UserResponseDto;
import com.vitanur.blogservice.models.dtos.UserTypeDto;
import com.vitanur.blogservice.models.entity.Permission;
import com.vitanur.blogservice.models.entity.User;
import com.vitanur.blogservice.models.entity.UserType;
import com.vitanur.blogservice.models.repository.PermissionRepository;
import com.vitanur.blogservice.models.repository.UserRepository;
import com.vitanur.blogservice.models.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRequestDto userDto) throws Exception {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new Exception("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setStatus(true); // Active by default
        newUser.setCreatedAt(LocalDateTime.now());

        Optional<UserType> userType = userTypeRepository.findById(userDto.getUserTypeId());
        newUser.setUserType(userType.orElseThrow(() -> new IllegalArgumentException("Invalid User Type ID")));

        List<Permission> permissions;

        permissions = permissionRepository.findAll();


        newUser.setPermissions(permissions);

        return userRepository.save(newUser);
    }


    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return convertToDto(user);
    }


    private UserResponseDto convertToDto(User user) {
        var permissionDtos = user.getPermissions().stream()
                .map(permission -> new PermissionDto(
                        permission.getName(),
                        permission.getUserTypes().stream()
                                .map(userType -> new UserTypeDto(userType.getName()))
                                .collect(Collectors.toSet())))
                .toList();

        return new UserResponseDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getStatus(),
                permissionDtos
                );
    }

}
