package com.example.habittrackr.services;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.storage.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitService {
    List<Habit> getAllHabits();

    Optional<Habit> getHabitById(Long userId, long habitId);

    HabitDTO createOrUpdateHabit(Habit habit);

    void deleteHabitById(Long userId, long habitId);
}
