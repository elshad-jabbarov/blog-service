package com.vitanur.blogservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthRequestDto {

    private String username;
    private String password;
}
