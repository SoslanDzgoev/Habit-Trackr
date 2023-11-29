package com.example.habittrackr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class Controller {
    private final HabitService habitService;

    @Autowired
    public Controller(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
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
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit habit) {
        if (habitService.getHabitById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Habit updateHabit = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable long id) {
        if (!habitService.getHabitById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        habitService.deleteHabitById(id);
        return ResponseEntity.ok().build();
    }

}
