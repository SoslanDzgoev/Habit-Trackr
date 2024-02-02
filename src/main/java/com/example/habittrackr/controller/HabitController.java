package com.example.habittrackr.controller;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.service.HabitService;
import com.example.habittrackr.storage.HabitKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable HabitKey id) {
        return habitService.getHabitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
        Habit newHabit = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(newHabit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable HabitKey id, @RequestBody Habit habit) {
        if (habitService.getHabitById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Habit updateHabit = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable HabitKey id) {
        if (habitService.getHabitById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        habitService.deleteHabitById(id);
        return ResponseEntity.ok().build();
    }

}
