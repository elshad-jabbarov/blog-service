package com.vitanur.blogservice.models.dtos;

import com.vitanur.blogservice.models.entity.Blog;
import com.vitanur.blogservice.models.entity.BlogImage;
import com.vitanur.blogservice.models.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Blog}
 */
@Data
@AllArgsConstructor
public class BlogDetails implements Serializable {
    String name;
    String title;
    String content;
    String cover;
    Long viewedCount;
    Boolean published;
    LocalDateTime publishedDate;
    Set<BlogImageDto> images;
    String userUsername;
    String userName;
    String userSurname;
    String userEmail;
    List<CommentDto> comments;

    /**
     * DTO for {@link BlogImage}
     */
    @Data
    @AllArgsConstructor
    public static class BlogImageDto implements Serializable {
        String filePath;
        String altText;
    }

    /**
     * DTO for {@link Comment}
     */
    @Data
    @AllArgsConstructor
    public static class CommentDto implements Serializable {
        String text;
        Instant createdAt;
    }
}