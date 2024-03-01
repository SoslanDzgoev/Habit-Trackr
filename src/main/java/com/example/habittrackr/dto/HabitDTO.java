package com.example.habittrackr.dto;

public class HabitDTO {

    private Long id;
    private String name;
    private String identity;
    private Long initialComplexity;
    private String contract;
    private String howToPrepareEvn;
    private Long numberOfTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getInitialComplexity() {
        return initialComplexity;
    }

    public void setInitialComplexity(Long initialComplexity) {
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

    public Long getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(Long numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }
}
