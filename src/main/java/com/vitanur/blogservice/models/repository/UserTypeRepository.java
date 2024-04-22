package com.vitanur.blogservice.models.repository;

import com.vitanur.blogservice.models.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}