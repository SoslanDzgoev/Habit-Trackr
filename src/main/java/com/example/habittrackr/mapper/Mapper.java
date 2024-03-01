package com.example.habittrackr.mapper;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
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

}
