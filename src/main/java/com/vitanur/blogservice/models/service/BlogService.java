package com.vitanur.blogservice.models.service;

import com.vitanur.blogservice.models.dtos.BlogDetails;
import com.vitanur.blogservice.models.dtos.BlogDto;
import com.vitanur.blogservice.models.dtos.CommentDto;
import com.vitanur.blogservice.models.entity.Blog;
import com.vitanur.blogservice.models.entity.Comment;
import com.vitanur.blogservice.models.repository.BlogRepository;
import com.vitanur.blogservice.models.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public Blog createBlog(BlogDto dto) {
        Blog blog = new Blog();
        blog.setName(dto.getName());
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setPublished(dto.getPublished());
        blog.setPublishedDate(dto.getPublishedDate());
        return blogRepository.save(blog);
    }

    @Transactional
    public void addComment(Long blogId, CommentDto commentDto) {
        var blog = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog not found!"));
        var comment = new Comment();
        comment.setBlog(blog);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
    }

    public List<BlogDetails> filterBlogs(String name, String title, Boolean published, LocalDate publishedDate) {
        List<Blog> blogs =
                blogRepository.findByNameContainingAndTitleContainingAndPublishedAndPublishedDate(name, title,
                        published, publishedDate);


        return blogs.stream().map(this::convertToDetailsDTO).toList();
    }

    public BlogDetails getBlogDetails(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found!"));
        return convertToDetailsDTO(blog);


    }

    private BlogDetails convertToDetailsDTO(Blog blog) {
        Set<BlogDetails.BlogImageDto> imageDtos = blog.getImages().stream()
                .map(image -> new BlogDetails.BlogImageDto(image.getFilePath(), image.getAltText()))
                .collect(Collectors.toSet());

        List<BlogDetails.CommentDto> commentDtos = blog.getComments().stream()
                .map(comment -> new BlogDetails.CommentDto(comment.getText(), comment.getCreatedAt()))
                .collect(Collectors.toList());

        return new BlogDetails(blog.getName(), blog.getTitle(), blog.getContent(), blog.getCover(),
                blog.getViewedCount(), blog.getPublished(), blog.getPublishedDate(), imageDtos,
                blog.getUser().getUsername(),
                blog.getUser().getName(), blog.getUser().getSurname(), blog.getUser().getEmail(), commentDtos);
    }
}

