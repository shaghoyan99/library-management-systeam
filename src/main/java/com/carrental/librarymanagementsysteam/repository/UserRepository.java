package com.carrental.librarymanagementsysteam.repository;

import com.carrental.librarymanagementsysteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findOptionalByUsername(String username);

    User findUserByUsername(String username);



}
