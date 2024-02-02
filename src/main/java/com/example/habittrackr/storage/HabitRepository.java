package com.example.habittrackr.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId")
    List<Habit> findByUserId(Long userId);

    void deleteById(HabitKey id);

    Optional<Habit> findById(HabitKey id);
}
