package com.vitanur.blogservice.demo;

import com.vitanur.blogservice.models.entity.Blog;
import com.vitanur.blogservice.models.entity.BlogImage;
import com.vitanur.blogservice.models.entity.Permission;
import com.vitanur.blogservice.models.entity.User;
import com.vitanur.blogservice.models.entity.UserType;
import com.vitanur.blogservice.models.repository.BlogImageRepository;
import com.vitanur.blogservice.models.repository.BlogRepository;
import com.vitanur.blogservice.models.repository.PermissionRepository;
import com.vitanur.blogservice.models.repository.UserRepository;
import com.vitanur.blogservice.models.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
@Profile("test")
public class TestDataLoader {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PermissionRepository permissionRepository;
    private final BlogRepository blogRepository;
    private final BlogImageRepository blogImageRepository;
    private final PasswordEncoder passwordEncoder;


    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        blogImageRepository.deleteAll();
        blogRepository.deleteAll();
        userRepository.deleteAll();
        userTypeRepository.deleteAll();
        permissionRepository.deleteAll();

        UserType admin = new UserType();
        admin.setName("Admin");
        userTypeRepository.save(admin);

        UserType user = new UserType();
        user.setName("User");
        userTypeRepository.save(user);

        Permission edit = new Permission();
        edit.setName("edit");
        permissionRepository.save(edit);

        Permission view = new Permission();
        view.setName("view");
        permissionRepository.save(view);

        admin.setPermissions(new HashSet<>(List.of(edit, view)));
        user.setPermissions(new HashSet<>(List.of(view)));
        userTypeRepository.saveAll(List.of(admin, user));

        User adminUser = new User();
        adminUser.setUsername("admin");
        String encoded = passwordEncoder.encode("admin");
        adminUser.setPassword(encoded);
        adminUser.setUserType(admin);
        adminUser.setPermissions(List.of(edit, view));
        adminUser.setEmail("admin@example.com");
        adminUser.setStatus(true);
        userRepository.save(adminUser);

        User normalUser = new User();
        normalUser.setUsername("user");
        String pass = passwordEncoder.encode("user");

        normalUser.setPassword(pass);
        normalUser.setUserType(user);
        normalUser.setPermissions(List.of(view));
        normalUser.setEmail("user@example.com");
        normalUser.setStatus(true);
        userRepository.save(normalUser);

        // Load Blogs
        Blog blog = new Blog();
        blog.setName("First Blog");
        blog.setTitle("Introduction to Spring Boot");
        blog.setContent("Content here");
        blog.setCover("cover.jpg");
        blog.setPublished(true);
        blog.setPublishedDate(LocalDateTime.now());
        blog.setUser(adminUser);
        blogRepository.save(blog);

        BlogImage image = new BlogImage();
        image.setFilePath("image1.jpg");
        image.setAltText("A descriptive alt text");
        image.setBlog(blog);
        blogImageRepository.save(image);
    }
}

