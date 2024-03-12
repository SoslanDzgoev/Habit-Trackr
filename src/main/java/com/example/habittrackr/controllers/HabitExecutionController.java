package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.HabitExecutionDTO;
import com.example.habittrackr.dto.HabitWithExecutionsDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitExecutionServiceImpl;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.executions.HabitExecution;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habits")
public class HabitExecutionController {

    private final UserServiceImpl userService;
    private final HabitServiceImpl habitService;
    private final Mapper mapper;
    private final HabitExecutionServiceImpl habitExecutionService;

    @Autowired
    public HabitExecutionController(UserServiceImpl userService, HabitServiceImpl habitService, Mapper mapper, HabitExecutionServiceImpl habitExecutionService) {
        this.userService = userService;
        this.habitService = habitService;
        this.mapper = mapper;
        this.habitExecutionService = habitExecutionService;
    }

    @PutMapping("/{userId}/{habitId}/execute")
    public ResponseEntity<HabitWithExecutionsDTO> executeHabit
            (@PathVariable Long userId, @PathVariable Long habitId, @RequestBody HabitExecutionDTO habitExecutionDTO) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Habit habit = habitService.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (habit.getNumberOfTimes() == null) {
            habit.setNumberOfTimes(1L);
        } else {
            Long currentNumberOfTime = habit.getNumberOfTimes();
            habit.setNumberOfTimes(currentNumberOfTime + 1);
        }



        HabitDTO updateHabit = habitService.createOrUpdateHabit(habit);

        HabitExecution habitExecution = mapper.toHabitExecution(habitExecutionDTO,user,habit);

        if (habitExecution.getLocalDateTime() == null) {
            habitExecution.setLocalDateTime(LocalDateTime.now());
        }
        habitExecutionService.createOrUpdateHabitExecution(habitExecution);

        List<HabitExecutionDTO> habitExecutions = habit.getExecutions().stream()
                .map(mapper::toHabitExecutionDTO).toList();

        HabitWithExecutionsDTO habitWithExecutionsDTO = new HabitWithExecutionsDTO();
        habitWithExecutionsDTO.setHabit(updateHabit);
        habitWithExecutionsDTO.setExecutions(habitExecutions);

        return ResponseEntity.ok(habitWithExecutionsDTO);

    }
}
