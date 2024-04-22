package com.vitanur.blogservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.vitanur.blogservice.models.entity.Blog}
 */
@Data
@AllArgsConstructor
public class BlogDto implements Serializable {
    String name;
    String title;
    String content;
    String cover;
    Boolean published;
    LocalDateTime publishedDate;
}