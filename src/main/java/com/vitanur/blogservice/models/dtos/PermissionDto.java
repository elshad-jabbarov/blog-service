package com.vitanur.blogservice.models.dtos;

import com.vitanur.blogservice.models.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Permission}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {
    String name;
    Set<UserTypeDto> userTypes;
}