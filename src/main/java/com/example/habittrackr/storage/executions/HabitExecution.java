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

    @Column(name = "execution_date")
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HabitStatus status;

    @PrePersist
    protected void onCreate() {
        localDateTime = LocalDateTime.now();
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public HabitStatus getStatus() {
        return status;
    }

    public void setStatus(HabitStatus status) {
        this.status = status;
    }
}
