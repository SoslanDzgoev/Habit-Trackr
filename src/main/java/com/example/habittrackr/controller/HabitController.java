package com.example.habittrackr.controller;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.service.HabitServiceImpl;
import com.example.habittrackr.storage.HabitKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitServiceImpl habitServiceImpl;

    @Autowired
    public HabitController(HabitServiceImpl habitServiceImpl) {
        this.habitServiceImpl = habitServiceImpl;
    }


    @GetMapping("/{userId}/{habitId}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long userId, @PathVariable Long habitId) {
        return habitServiceImpl.getHabitById(userId, habitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/{habitId}")
    public ResponseEntity<Habit> updateHabit(@PathVariable long userId,@PathVariable Long habitId, @RequestBody Habit habit) {
        if (habitServiceImpl.getHabitById(userId, habitId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        habit.setId(new HabitKey(userId, habitId));
        Habit updateHabit = habitServiceImpl.createOrUpdateHabit(habit);
        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{userId}/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable long userId,@PathVariable Long habitId) {
        if (habitServiceImpl.getHabitById(userId, habitId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        habitServiceImpl.deleteHabitById(userId, habitId);
        return ResponseEntity.ok().build();
    }
}
