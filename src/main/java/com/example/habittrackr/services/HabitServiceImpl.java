package com.example.habittrackr.services;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.habits.HabitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;
    private final Mapper mapper;

    public HabitServiceImpl(HabitRepository habitsRepository, Mapper mapper) {
        this.habitRepository = habitsRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @Override
    public Optional<Habit> getHabitById(long habitId) {
        return habitRepository.findById(habitId);
    }

    @Override
    public HabitDTO createOrUpdateHabit(Habit habit) {
        Habit save = habitRepository.save(habit);
        return mapper.toHabitDTO(save);
    }

    @Override
    @Transactional
    public void deleteHabitById(long habitId) {
        habitRepository.deleteById(habitId);
    }

}
