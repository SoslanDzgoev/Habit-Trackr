package com.example.habittrackr.services;

import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.executions.HabitExecution;
import com.example.habittrackr.storage.executions.HabitExecutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public HabitExecution createHabitExecution(HabitExecution habitExecution) {
        return habitExecutionRepository.save(habitExecution);
    }

}
