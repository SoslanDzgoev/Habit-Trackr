package com.example.habittrackr.mapper;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.dto.UserInfoDTO;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getPassword());
        return user;
    }

    public List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    public HabitDTO toHabitDTO(Habit habit) {
        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setId(habit.getId().getHabitId());
        habitDTO.setName(habit.getName());
        habitDTO.setIdentity(habit.getIdentity());
        habitDTO.setInitialComplexity(habit.getInitialComplexity());
        habitDTO.setContract(habit.getContract());
        habitDTO.setHowToPrepareEvn(habit.getHowToPrepareEvn());
        habitDTO.setNumberOfTimes(habit.getNumberOfTimes());

        return habitDTO;
    }

    public Habit toHabit(HabitDTO habitDTO) {
        Habit habit = new Habit();
        habit.setName(habitDTO.getName());
        habit.setIdentity(habitDTO.getIdentity());
        habit.setInitialComplexity(habitDTO.getInitialComplexity());
        habit.setContract(habitDTO.getContract());
        habit.setHowToPrepareEvn(habitDTO.getHowToPrepareEvn());
        habit.setNumberOfTimes(habitDTO.getNumberOfTimes());
        return habit;
    }


    public UserInfoDTO toUserInfoDTO(UserDTO userDTO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userDTO.getId());
        userInfoDTO.setUsername(userDTO.getUsername());
        userInfoDTO.setEmail(userDTO.getEmail());
        return userInfoDTO;
    }

}
