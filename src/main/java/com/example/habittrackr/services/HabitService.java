package com.example.habittrackr.services;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.storage.habits.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitService {
    List<Habit> getAllHabits();

    Optional<Habit> getHabitById(long habitId);

    HabitDTO createOrUpdateHabit(Habit habit);

    void deleteHabitById(long habitId);
}
