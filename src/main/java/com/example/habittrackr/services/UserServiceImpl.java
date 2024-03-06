package com.example.habittrackr.services;

import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.User;
import com.example.habittrackr.storage.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO createOrUpdateUser(User user) {
        return mapper.toUserDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

}
