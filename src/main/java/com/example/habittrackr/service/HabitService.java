package com.example.habittrackr.service;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitKey;
import com.example.habittrackr.storage.HabitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Habit> getHabitById(HabitKey id) {
        return habitRepository.findById(id);
    }

    public Habit createOrUpdateHabit(Habit habit) {
        return habitRepository.save(habit);
    }
@Transactional
    public void deleteHabitById(HabitKey id) {
        habitRepository.deleteById(id);
    }
}
