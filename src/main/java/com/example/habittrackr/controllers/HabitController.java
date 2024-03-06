package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.services.HabitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitServiceImpl habitServiceImpl;
    private final Mapper mapper;

    @Autowired
    public HabitController(HabitServiceImpl habitServiceImpl, Mapper mapper) {
        this.habitServiceImpl = habitServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping("/{userId}/{habitId}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable Long userId, @PathVariable Long habitId) {
        HabitDTO habitDTO = habitServiceImpl.getHabitById(userId, habitId)
                .map(mapper::toHabitDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(habitDTO);
    }

    @PutMapping("/{userId}/{habitId}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable long userId, @PathVariable Long habitId, @RequestBody HabitDTO habitDTO) {
        Habit existingHabit = habitServiceImpl.getHabitById(userId, habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingHabit.setName(habitDTO.getName());
        existingHabit.setIdentity(habitDTO.getIdentity());
        existingHabit.setInitialComplexity(habitDTO.getInitialComplexity());
        existingHabit.setContract(habitDTO.getContract());
        existingHabit.setHowToPrepareEvn(habitDTO.getHowToPrepareEvn());
        existingHabit.setNumberOfTimes(habitDTO.getNumberOfTimes());

        HabitDTO updateHabit = habitServiceImpl.createOrUpdateHabit(existingHabit);

        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{userId}/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable long userId, @PathVariable Long habitId) {
        habitServiceImpl.getHabitById(userId, habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        habitServiceImpl.deleteHabitById(userId, habitId);
        return ResponseEntity.ok().build();
    }
}
