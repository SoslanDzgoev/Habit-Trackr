package com.example.habittrackr;

import jakarta.persistence.*;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long everyDaySuccessful;
    private long currentComplexity;
    private String identity;
    private long initialComplexity;
    private String contract;
    private String howToPrepareEvn;

    public Habit(String name, int everyDaySuccessful, int currentComplexity,
                 String identity, int initialComplexity, String contract, String howToPrepareEvn) {
        this.name = name;
        this.everyDaySuccessful = everyDaySuccessful;
        this.currentComplexity = currentComplexity;
        this.identity = identity;
        this.initialComplexity = initialComplexity;
        this.contract = contract;
        this.howToPrepareEvn = howToPrepareEvn;
    }

    public Habit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEveryDaySuccessful() {
        return everyDaySuccessful;
    }

    public void setEveryDaySuccessful(long everyDaySuccessful) {
        this.everyDaySuccessful = everyDaySuccessful;
    }

    public long getCurrentComplexity() {
        return currentComplexity;
    }

    public void setCurrentComplexity(long currentComplexity) {
        this.currentComplexity = currentComplexity;
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
