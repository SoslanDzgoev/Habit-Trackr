package com.example.habittrackr.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HabitKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "habit_id")
    private Long habitId;

    public HabitKey(Long userId, Long habitId) {
        this.userId = userId;
        this.habitId = habitId;
    }

    public HabitKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitKey habitKey = (HabitKey) o;
        return Objects.equals(userId, habitKey.userId) && Objects.equals(habitId, habitKey.habitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, habitId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }
}
