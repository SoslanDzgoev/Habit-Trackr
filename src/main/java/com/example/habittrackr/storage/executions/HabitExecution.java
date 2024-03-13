package com.example.habittrackr.storage.executions;

import com.example.habittrackr.storage.users.User;
import com.example.habittrackr.storage.habits.Habit;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HabitExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long executionId;

    @ManyToOne
    private Habit habit;

    @ManyToOne
    private User user;

    private LocalDateTime executionDate;

    @Enumerated(EnumType.STRING)
    private HabitStatus status;

    private Long activityParameter;

    @PrePersist
    protected void onCreate() {
        executionDate = LocalDateTime.now();
    }

    public Long getActivityParameter() {
        return activityParameter;
    }

    public void setActivityParameter(Long activityParameter) {
        this.activityParameter = activityParameter;
    }

    public Habit getHabit() {
        return habit;
    }

    public Long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime localDateTime) {
        this.executionDate = localDateTime;
    }

    public HabitStatus getStatus() {
        return status;
    }

    public void setStatus(HabitStatus status) {
        this.status = status;
    }
}
