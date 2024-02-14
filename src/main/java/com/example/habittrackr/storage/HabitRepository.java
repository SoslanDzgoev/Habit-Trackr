package com.example.habittrackr.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit,Long> {

    void deleteByIdUserIdAndIdHabitId(Long userId, Long habitId);

    Optional<Habit> findByIdUserIdAndIdHabitId(Long userId, Long habitId);
}
