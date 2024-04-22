package com.vitanur.blogservice.models.repository;

import com.vitanur.blogservice.models.entity.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogImageRepository extends JpaRepository<BlogImage, Long> {
}