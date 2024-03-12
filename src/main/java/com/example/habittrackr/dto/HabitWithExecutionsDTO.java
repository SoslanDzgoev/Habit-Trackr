package com.example.habittrackr.dto;

import java.util.List;

public class HabitWithExecutionsDTO {
    private HabitDTO habit;
    private List<HabitExecutionDTO> executions;

    public HabitDTO getHabit() {
        return habit;
    }

    public void setHabit(HabitDTO habit) {
        this.habit = habit;
    }

    public List<HabitExecutionDTO> getExecutions() {
        return executions;
    }

    public void setExecutions(List<HabitExecutionDTO> executions) {
        this.executions = executions;
    }
}
