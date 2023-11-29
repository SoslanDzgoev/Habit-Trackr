package com.example.habittrackr;

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

    public Optional<Habit> getHabitById(Long id) {
        return habitRepository.findById(id);
    }

    public Habit createOrUpdateHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabitById(Long id) {
        habitRepository.deleteById(id);
    }
}
