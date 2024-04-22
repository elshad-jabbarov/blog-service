package com.vitanur.blogservice.models.repository;

import com.vitanur.blogservice.models.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}