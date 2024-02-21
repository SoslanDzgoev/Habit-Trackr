package com.example.habittrackr.storage;

import jakarta.persistence.*;

@Entity
public class Habit {

    @EmbeddedId
    private HabitKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String identity;
    private Long initialComplexity;
    private String contract;
    private String howToPrepareEvn;
    private Long numberOfTimes;


    public Habit() {
    }

    public HabitKey getId() {
        return id;
    }

    public void setId(HabitKey id) {
        this.id = id;
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
