package com.carrental.librarymanagementsysteam.service;

import com.carrental.librarymanagementsysteam.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    void save(User user, MultipartFile multipartFile);

    Optional<User> findOptionalByUsername(String username);

    User loadUserByUsername(String username);
}
