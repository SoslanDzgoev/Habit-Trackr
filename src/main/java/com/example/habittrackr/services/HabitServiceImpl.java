package com.example.habittrackr.services;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitRepository;
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
    public Optional<Habit> getHabitById(Long userId, long habitId) {
        return habitRepository.findByIdUserIdAndIdHabitId(userId, habitId);
    }

    @Override
    public HabitDTO createOrUpdateHabit(Habit habit) {
        habitRepository.save(habit);
        return mapper.toHabitDTO(habit);
    }

    @Override
    @Transactional
    public void deleteHabitById(Long userId, long habitId) {
        habitRepository.deleteByIdUserIdAndIdHabitId(userId, habitId);
    }


}
