package com.example.habittrackr;

import jakarta.persistence.*;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int everyDaySuccessful;
    private int currentComplexity;
    private String identity;
    private int initialComplexity;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEveryDaySuccessful() {
        return everyDaySuccessful;
    }

    public void setEveryDaySuccessful(int everyDaySuccessful) {
        this.everyDaySuccessful = everyDaySuccessful;
    }

    public int getCurrentComplexity() {
        return currentComplexity;
    }

    public void setCurrentComplexity(int currentComplexity) {
        this.currentComplexity = currentComplexity;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getInitialComplexity() {
        return initialComplexity;
    }

    public void setInitialComplexity(int initialComplexity) {
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
