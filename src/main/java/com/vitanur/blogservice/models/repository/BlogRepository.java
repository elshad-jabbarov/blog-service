package com.vitanur.blogservice.models.repository;

import com.vitanur.blogservice.models.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByNameContainingAndTitleContainingAndPublishedAndPublishedDate(String name, String title, Boolean published, LocalDate publishedDate);
}