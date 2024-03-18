package com.example.habittrackr.storage.executions;

import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitExecutionRepository extends JpaRepository<HabitExecution, Long> {
    Optional<HabitExecution> getHabitExecutionByUserAndHabit(User user, Habit habit);
}
