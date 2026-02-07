package com.carrental.librarymanagementsysteam.service.imple;

import com.carrental.librarymanagementsysteam.model.User;
import com.carrental.librarymanagementsysteam.model.UserRole;
import com.carrental.librarymanagementsysteam.repository.UserRepository;
import com.carrental.librarymanagementsysteam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${library.management.upload.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public void save(User user, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                user.setPictureName(fileName);
            } catch (IOException e) {
                throw new RuntimeException("File not found");
            }
        }
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findOptionalByUsername(String username) {
        return userRepository.findOptionalByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
