package com.vitanur.blogservice.models.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vitanur.blogservice.models.entity.User}
 */
@Value
public class UserRequestDto implements Serializable {
    String username;
    String password;
    String name;
    String surname;
    String phoneNumber;
    String email;
    Long userTypeId;
}