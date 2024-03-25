package com.example.habittrackr.mapper;

import com.example.habittrackr.dto.*;
import com.example.habittrackr.storage.executions.HabitExecution;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.identity.Identity;
import com.example.habittrackr.storage.users.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public List<UserInfoDTO> toUserDTOList(List<User> users) {
        return users.stream()
                .map(this::toUserInfoDTO)
                .collect(Collectors.toList());
    }

    public UserInfoDTO toUserInfoDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(user.getId());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setEmail(user.getEmail());
        return userInfoDTO;
    }

    public HabitDTO toHabitDTO(Habit habit) {
        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setId(habit.getHabitId());
        habitDTO.setName(habit.getName());
        habitDTO.setInitialComplexity(habit.getInitialComplexity());
        habitDTO.setContract(habit.getContract());
        habitDTO.setHowToPrepareEvn(habit.getHowToPrepareEvn());
        habitDTO.setExecutionCount(habit.getExecutionCount());

        return habitDTO;
    }

    public Habit toHabit(HabitDTO habitDTO) {
        Habit habit = new Habit();
        habit.setName(habitDTO.getName());
        habit.setInitialComplexity(habitDTO.getInitialComplexity());
        habit.setContract(habitDTO.getContract());
        habit.setHowToPrepareEvn(habitDTO.getHowToPrepareEvn());
        return habit;
    }


    public HabitExecution toHabitExecution(HabitExecutionDTO habitExecutionDTO, User user, Habit habit) {
        HabitExecution habitExecution = new HabitExecution();
        habitExecution.setActivityParameter(habitExecutionDTO.getActivityParameter());
        habitExecution.setStatus(habitExecutionDTO.getStatus());
        habitExecution.setUser(user);
        habitExecution.setHabit(habit);
        habitExecution.setExecutionDate(LocalDateTime.now());
        return habitExecution;
    }

    public HabitExecutionDTO toHabitExecutionDTO(HabitExecution habitExecution) {
        HabitExecutionDTO habitExecutionDTO = new HabitExecutionDTO();
        habitExecutionDTO.setExecutionId(habitExecution.getExecutionId());
        habitExecutionDTO.setLocalDateTime(habitExecution.getExecutionDate());
        habitExecutionDTO.setStatus(habitExecution.getStatus());
        habitExecutionDTO.setActivityParameter(habitExecution.getActivityParameter());
        return habitExecutionDTO;
    }

    public Identity toIdentity(IdentityDTO identityDTO) {
        Identity identity = new Identity();
        identity.setDescription(identityDTO.getDescription());
        return identity;
    }

    public IdentityDTO toIdentityDTO(Identity identity) {
        IdentityDTO identityDTO = new IdentityDTO();
        identityDTO.setDescription(identity.getDescription());
        return identityDTO;
    }
}
