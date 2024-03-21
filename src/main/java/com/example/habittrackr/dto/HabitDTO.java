package com.example.habittrackr.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class HabitDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String identity;

    @NotEmpty
    private Long initialComplexity;

    @NotEmpty
    @Size(min = 2, max = 250)
    private String contract;

    @NotEmpty
    private String howToPrepareEvn;

    private Long executionCount;



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

    public Long getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(Long executionCount) {
        this.executionCount = executionCount;
    }
}
