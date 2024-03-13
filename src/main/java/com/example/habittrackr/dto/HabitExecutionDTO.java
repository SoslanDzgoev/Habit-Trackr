package com.example.habittrackr.dto;

import com.example.habittrackr.storage.executions.HabitStatus;

import java.time.LocalDateTime;

public class HabitExecutionDTO {

    private Long executionId;

    private LocalDateTime localDateTime;

    private HabitStatus status;

    private Long activityParameter;

    public Long getActivityParameter() {
        return activityParameter;
    }

    public void setActivityParameter(Long activityParameter) {
        this.activityParameter = activityParameter;
    }

    public Long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
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
