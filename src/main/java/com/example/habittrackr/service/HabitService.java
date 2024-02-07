package com.example.habittrackr.service;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitsRepository) {
        this.habitRepository = habitsRepository;
    }


    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Optional<Habit> getHabitById(Long userId, long habitId) {
        return habitRepository.findByIdUserIdAndIdHabitId(userId, habitId);
    }

    public Habit createOrUpdateHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabitById(Long userId, long habitId) {
        habitRepository.deleteByIdUserIdAndIdHabitId(userId, habitId);
    }


}
