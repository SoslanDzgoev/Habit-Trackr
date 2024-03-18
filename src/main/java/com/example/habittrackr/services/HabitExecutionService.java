package com.example.habittrackr.services;

import com.example.habittrackr.storage.executions.HabitExecution;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.users.User;


import java.util.List;
import java.util.Optional;

public interface HabitExecutionService {
    List<HabitExecution> getAllHabitExecutions();

    HabitExecution createOrUpdateHabitExecution(HabitExecution habitExecution);

    Optional<HabitExecution> getHabitExecutionByUserAndHabit(User user, Habit habit);
}
