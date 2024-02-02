package com.example.habittrackr.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HabitKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "habit_name")
    private String habitName;

    public HabitKey(long userId, String habitName) {
        this.userId = userId;
        this.habitName = habitName;
    }

    public HabitKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitKey habitKey = (HabitKey) o;
        return userId == habitKey.userId && Objects.equals(habitName, habitKey.habitName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, habitName);
    }
}
