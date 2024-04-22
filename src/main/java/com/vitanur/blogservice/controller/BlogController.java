package com.vitanur.blogservice.controller;

import com.vitanur.blogservice.models.dtos.BlogDetails;
import com.vitanur.blogservice.models.dtos.BlogDto;
import com.vitanur.blogservice.models.dtos.CommentDto;
import com.vitanur.blogservice.models.entity.Blog;
import com.vitanur.blogservice.models.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDto blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @PostMapping("/{blogId}/comments")
    public void addCommentToBlog(@PathVariable Long blogId, @RequestBody CommentDto comment) {
        blogService.addComment(blogId, comment);
    }

    @GetMapping
    public ResponseEntity<List<BlogDetails>> filterBlogs(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean published,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedDate) {
        return ResponseEntity.ok(blogService.filterBlogs(name, title, published, publishedDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDetails> getBlogDetails(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogDetails(id));
    }
}

