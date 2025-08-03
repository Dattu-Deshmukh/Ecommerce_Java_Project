package com.example.Service;

import com.example.Entity.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    // Add more methods as needed (e.g., get all users, change password, etc.)
}
