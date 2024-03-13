package com.example.habittrackr.storage.habits;

import com.example.habittrackr.storage.users.User;
import com.example.habittrackr.storage.executions.HabitExecution;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habitId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String identity;
    private Long initialComplexity;
    private String contract;
    private String howToPrepareEvn;
    private Long numberOfTimes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habit", orphanRemoval = true)
    private List<HabitExecution> executions;

    public List<HabitExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<HabitExecution> executions) {
        this.executions = executions;
    }

    public Habit() {
    }

    public void setInitialComplexity(Long initialComplexity) {
        this.initialComplexity = initialComplexity;
    }

    public Long getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(Long numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public long getInitialComplexity() {
        return initialComplexity;
    }

    public void setInitialComplexity(long initialComplexity) {
        this.initialComplexity = initialComplexity;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getHowToPrepareEvn() {
        return howToPrepareEvn;
    }

    public void setHowToPrepareEvn(String howToPrepareEvn) {
        this.howToPrepareEvn = howToPrepareEvn;
    }

}
