package com.example.Service;

import com.example.Entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    void saveUser(User user);
}
