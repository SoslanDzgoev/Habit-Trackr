package com.example.habittrackr.services;

import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.executions.HabitExecution;
import com.example.habittrackr.storage.executions.HabitExecutionRepository;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.users.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitExecutionServiceImpl implements HabitExecutionService{

    private final HabitExecutionRepository habitExecutionRepository;
    private final Mapper mapper;

    public HabitExecutionServiceImpl(HabitExecutionRepository habitExecutionRepository, Mapper mapper) {
        this.habitExecutionRepository = habitExecutionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HabitExecution> getAllHabitExecutions() {
        return habitExecutionRepository.findAll();
    }

    @Override
    public HabitExecution createOrUpdateHabitExecution(HabitExecution habitExecution) {
        return habitExecutionRepository.save(habitExecution);
    }

    @Override
    public Optional<HabitExecution> getHabitExecutionByUserAndHabit(User user, Habit habit) {
        return habitExecutionRepository.getHabitExecutionByUserAndHabit(user,habit);
    }

}
