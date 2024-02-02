package com.example.habittrackr.service;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitKey;
import com.example.habittrackr.storage.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HabitServiceTest {
    @Autowired
    private HabitService habitService;
    @Autowired
    private UserService userService;

    @Test
    public void testsest(){
    }

    @Test
    public void testCreate() {
        User user = new User("soslan", "12345", "@gmail");
        userService.createOrUpdateUser(user);
        Habit habit = new Habit(user, "Прогулка",
                "Идентичность", 5000, "контракт", "Среда");

        Habit createdHabit = habitService.createOrUpdateHabit(habit);


        assertNotNull(createdHabit);
        assertNotNull(createdHabit.getName());
        assertEquals("Прогулка", createdHabit.getName());
    }

    @Test
    public void testRead() {
        User user = new User("soslan", "12345", "@gmail");
        userService.createOrUpdateUser(user);
        Habit habit = new Habit(user, "Прогулка","Идентичность", 5000, "контракт", "Среда");
        Habit habit2 = new Habit(user, "Чтение","Идентичность", 5000, "контракт", "Среда");

        habitService.createOrUpdateHabit(habit);
        habitService.createOrUpdateHabit(habit2);

        List<Habit> allHabits = habitService.getAllHabits();

        assertFalse(allHabits.isEmpty());
        assertEquals(2, allHabits.size());

    }

    @Test
    public void testUpdate() {
        User user = new User("soslan", "12345", "@gmail");
        userService.createOrUpdateUser(user);
        Habit habit = new Habit(user, "Прогулка","Идентичность", 5000, "контракт", "Среда");
        Habit createHabit = habitService.createOrUpdateHabit(habit);

        createHabit.setName("Тренировка");
        createHabit.setContract("новый контракт");

        Habit updateHabit = habitService.createOrUpdateHabit(createHabit);

        assertEquals("Тренировка", updateHabit.getName());
        assertEquals("новый контракт", updateHabit.getContract());
    }

    @Transactional
    @Test
    public void testDelete() {
        User user = new User("soslan", "12345", "@gmail");
        userService.createOrUpdateUser(user);
        Habit habit = new Habit(user, "Прогулка","Идентичность",
                5000, "контракт", "Среда");
        Habit createHabit = habitService.createOrUpdateHabit(habit);

        habitService.deleteHabitById(createHabit.getId());

        Optional<Habit> habitById = habitService.getHabitById(createHabit.getId());
        assertFalse(habitById.isPresent());

    }
}