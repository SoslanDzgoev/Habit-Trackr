package com.example.habittrackr.storage;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Habit {

    @EmbeddedId
    @AttributeOverride(name = "userId", column = @Column(name = "user_id"))
    private HabitKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String identity;
    private long initialComplexity;
    private String contract;
    private String howToPrepareEvn;

    public Habit(User user, String name, String identity, long initialComplexity, String contract, String howToPrepareEvn) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("User or userId cannot be null");
        }
        this.user = user;
        this.name = name;
        this.identity = identity;
        this.initialComplexity = initialComplexity;
        this.contract = contract;
        this.howToPrepareEvn = howToPrepareEvn;
        this.id = new HabitKey(user.getId(), UUID.randomUUID().getMostSignificantBits());
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return getInitialComplexity() ==
                habit.getInitialComplexity() && Objects.equals(getId(),
                habit.getId()) && Objects.equals(user, habit.user) && Objects.equals(getName(),
                habit.getName()) && Objects.equals(getIdentity(),
                habit.getIdentity()) && Objects.equals(getContract(),
                habit.getContract()) && Objects.equals(getHowToPrepareEvn(),
                habit.getHowToPrepareEvn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), user, getName(), getIdentity(), getInitialComplexity(), getContract(), getHowToPrepareEvn());
    }
}
