package com.vitanur.blogservice.models.dtos;

import com.vitanur.blogservice.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    String username;
    String name;
    String surname;
    String phoneNumber;
    String email;
    Boolean status;
    List<PermissionDto> permissions;

}