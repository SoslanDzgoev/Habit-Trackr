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
class HabitServiceImplTest {
    @Autowired
    private HabitServiceImpl habitServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Soslan");
        user.setPassword("12345");
        user.setEmail("gmail.com");
        user.setHabits(new ArrayList<>());
        userServiceImpl.createOrUpdateUser(user);
    }

    @Test
    void getAllHabits() {

        Habit habit = createHabit("HabitName1");
        Habit habit2 = createHabit("HabitName2");

        habitServiceImpl.createOrUpdateHabit(habit);
        habitServiceImpl.createOrUpdateHabit(habit2);

        List<Habit> allHabits = habitServiceImpl.getAllHabits();

        assertFalse(allHabits.isEmpty());
        assertEquals(2, allHabits.size());
    }

    @Test
    void getHabitById() {
        Habit habit = createHabit("Прогулка");
        habitServiceImpl.createOrUpdateHabit(habit);

        Long userId = habit.getId().getUserId();
        Long habitId = habit.getId().getHabitId();

        Optional<Habit> fetchedHabitOptional = habitServiceImpl.getHabitById(userId, habitId);

        assertTrue(fetchedHabitOptional.isPresent());

        Habit fetchedHabit = fetchedHabitOptional.get();

        assertEquals("Прогулка", fetchedHabit.getName());
        assertEquals("Прогулка", fetchedHabit.getName());
    }

    @Test
    void testCreateAndUpdateHabit() {
        Habit habit = createHabit("Прогулка");
        Habit createdHabit = habitServiceImpl.createOrUpdateHabit(habit);

        assertNotNull(createdHabit);
        assertEquals("Прогулка", createdHabit.getName());

        createdHabit.setName("Тренировка");
        createdHabit.setContract("новый контракт");

        Habit updatedHabit = habitServiceImpl.createOrUpdateHabit(createdHabit);

        assertEquals("Тренировка", updatedHabit.getName());
        assertEquals("новый контракт", updatedHabit.getContract());
    }

    @Test

    public void testDeleteHabitById() {
        Habit habit = createHabit("HabitName");
        habit = habitServiceImpl.createOrUpdateHabit(habit);

        assertNotNull(habitServiceImpl.getHabitById(habit.getId().getUserId(), habit.getId().getHabitId()));

        habitServiceImpl.deleteHabitById(habit.getId().getUserId(), habit.getId().getHabitId());

        assertFalse(habitServiceImpl.getHabitById(habit.getId().getUserId(), habit.getId().getHabitId()).isPresent());
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