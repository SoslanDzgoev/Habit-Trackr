package com.example.habittrackr.services;

import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.storage.users.User;


import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    UserDTO createOrUpdateUser(User user);

    void deleteUserById(Long id);

}
