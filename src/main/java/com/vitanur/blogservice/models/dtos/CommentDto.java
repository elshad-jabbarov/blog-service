package com.vitanur.blogservice.models.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vitanur.blogservice.models.entity.Comment}
 */
@Value
public class CommentDto implements Serializable {
    String text;
}