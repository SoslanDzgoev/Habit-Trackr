package com.example.habittrackr.service;

import com.example.habittrackr.storage.User;


import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createOrUpdateUser(User user);
    void deleteUserById(Long id);

}
