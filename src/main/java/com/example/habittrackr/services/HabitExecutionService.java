package com.example.habittrackr.services;

import com.example.habittrackr.storage.executions.HabitExecution;


import java.util.List;

public interface HabitExecutionService {
    List<HabitExecution> getAllHabitExecutions();

    HabitExecution createHabitExecution(HabitExecution habitExecution);

}
