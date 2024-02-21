package com.example.habittrackr.service;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitKey;
import com.example.habittrackr.storage.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class HabitServiceTest {
    @Autowired
    private HabitService habitService;
    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Soslan");
        user.setPassword("12345");
        user.setEmail("gmail.com");
        user.setHabits(new ArrayList<>());
        userService.createOrUpdateUser(user);
    }

    @Test
    void getAllHabits() {

        Habit habit = createHabit("HabitName1");
        Habit habit2 = createHabit("HabitName2");

        habitService.createOrUpdateHabit(habit);
        habitService.createOrUpdateHabit(habit2);

        List<Habit> allHabits = habitService.getAllHabits();

        assertFalse(allHabits.isEmpty());
        assertEquals(2, allHabits.size());
    }

    @Test
    void getHabitById() {
        Habit habit = createHabit("Прогулка");
        habitService.createOrUpdateHabit(habit);

        Long userId = habit.getId().getUserId();
        Long habitId = habit.getId().getHabitId();

        Optional<Habit> fetchedHabitOptional = habitService.getHabitById(userId, habitId);

        assertTrue(fetchedHabitOptional.isPresent());

        Habit fetchedHabit = fetchedHabitOptional.get();

        assertEquals("Прогулка", fetchedHabit.getName());
        assertEquals("Прогулка", fetchedHabit.getName());
    }

    @Test
    void testCreateAndUpdateHabit() {
        Habit habit = createHabit("Прогулка");
        Habit createdHabit = habitService.createOrUpdateHabit(habit);

        assertNotNull(createdHabit);
        assertEquals("Прогулка", createdHabit.getName());

        createdHabit.setName("Тренировка");
        createdHabit.setContract("новый контракт");

        Habit updatedHabit = habitService.createOrUpdateHabit(createdHabit);

        assertEquals("Тренировка", updatedHabit.getName());
        assertEquals("новый контракт", updatedHabit.getContract());
    }

    @Test

    public void testDeleteHabitById() {
        Habit habit = createHabit("HabitName");
        habit = habitService.createOrUpdateHabit(habit);

        assertNotNull(habitService.getHabitById(habit.getId().getUserId(), habit.getId().getHabitId()));

        habitService.deleteHabitById(habit.getId().getUserId(), habit.getId().getHabitId());

        assertFalse(habitService.getHabitById(habit.getId().getUserId(), habit.getId().getHabitId()).isPresent());
    }

    private Habit createHabit(String name) {
        Habit habit = new Habit();
        habit.setUser(user);
        habit.setName(name);
        habit.setIdentity("Habit Identity");
        habit.setInitialComplexity(1L);
        habit.setContract("Habit Contract");
        habit.setHowToPrepareEvn("Habit Preparation");HabitKey habitKey = new HabitKey(user.getId(), UUID.randomUUID().getMostSignificantBits());
        habit.setId(habitKey);

        return habit;

    }
}