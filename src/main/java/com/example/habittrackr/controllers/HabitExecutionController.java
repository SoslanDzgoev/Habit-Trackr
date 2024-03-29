package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitExecutionDTO;
import com.example.habittrackr.dto.HabitWithExecutionsDTO;
import com.example.habittrackr.dto.UpdateActivityDTO;
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

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitExecutionController {

    private final HabitServiceImpl habitService;
    private final Mapper mapper;
    private final HabitExecutionServiceImpl habitExecutionService;

    @Autowired
    public HabitExecutionController( HabitServiceImpl habitService, Mapper mapper, HabitExecutionServiceImpl habitExecutionService) {

        this.habitService = habitService;
        this.mapper = mapper;
        this.habitExecutionService = habitExecutionService;
    }

    @GetMapping("/{habitId}/executions")
    public ResponseEntity<HabitWithExecutionsDTO> getHabitExecutions(@PathVariable Long habitId){
        Habit habit = habitService.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<HabitExecution> habitExecutionsList = habit.getExecutions();

        List<HabitExecutionDTO> habitExecutionDTOS = habitExecutionsList.stream()
                .map(mapper::toHabitExecutionDTO).toList();
        HabitWithExecutionsDTO habitWithExecutionsDTO = new HabitWithExecutionsDTO();
        habitWithExecutionsDTO.setHabit(mapper.toHabitDTO(habit));
        habitWithExecutionsDTO.setExecutions(habitExecutionDTOS);
        return ResponseEntity.ok(habitWithExecutionsDTO);
    }

    @PostMapping("/{userId}/{habitId}/execute")
    public ResponseEntity<Void> executeHabit
            (@PathVariable Long userId, @PathVariable Long habitId, @RequestBody HabitExecutionDTO habitExecutionDTO) {
        User user = new User();
        user.setId(userId);

        Habit habit = habitService.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (habit.getExecutionCount() == null) {
            habit.setExecutionCount(1L);
        } else {
            Long currentNumberOfTime = habit.getExecutionCount();
            habit.setExecutionCount(currentNumberOfTime + 1);
        }

        habitService.createOrUpdateHabit(habit);

        HabitExecution habitExecution = mapper.toHabitExecution(habitExecutionDTO, user, habit);

        habitExecutionService.createOrUpdateHabitExecution(habitExecution);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/{userId}/{habitId}/updateActivityParameter")
    public ResponseEntity<Void> updateActivityParameter(
            @PathVariable Long userId, @PathVariable Long habitId, @RequestBody UpdateActivityDTO updateActivityDTO){
        User user = new User();
        user.setId(userId);
        Habit habit = habitService.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        HabitExecution habitExecution = habitExecutionService.getHabitExecutionByUserAndHabit(user, habit)
                .orElseGet(()-> {
                    HabitExecution newHabitExecution = new HabitExecution();
                    newHabitExecution.setUser(user);
                    newHabitExecution.setHabit(habit);
                    return newHabitExecution;
                });

        habitExecution.setActivityParameter(updateActivityDTO.getActivityParameter());

        habitExecutionService.createOrUpdateHabitExecution(habitExecution);
        return ResponseEntity.ok().build();
    }
}
