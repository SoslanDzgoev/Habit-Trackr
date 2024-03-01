package com.example.habittrackr.dto;

import java.util.List;

public class UserWithHabitsDTO {

    private Long id;
    private String username;
    private List<HabitDTO> habits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<HabitDTO> getHabits() {
        return habits;
    }

    public void setHabits(List<HabitDTO> habits) {
        this.habits = habits;
    }
}
