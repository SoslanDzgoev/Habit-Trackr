package com.example.habittrackr.storage.executions;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HabitExecutionKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "habit_id")
    private Long habitId;

    public HabitExecutionKey() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitExecutionKey that = (HabitExecutionKey) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getHabitId(), that.getHabitId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getHabitId());
    }
}
