package com.example.habittrackr.services;

import com.example.habittrackr.storage.User;
import com.example.habittrackr.storage.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

}
