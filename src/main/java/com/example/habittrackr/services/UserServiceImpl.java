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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO createOrUpdateUser(User user) {
        return mapper.toUserDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

}
