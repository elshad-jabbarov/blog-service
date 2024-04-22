package com.vitanur.blogservice.models.dtos;

import com.vitanur.blogservice.models.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link UserType}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeDto implements Serializable {
    String name;
}