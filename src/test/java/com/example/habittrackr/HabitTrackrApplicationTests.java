package com.example.habittrackr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HabitTrackrApplicationTests {

    @Autowired
    HabitService habitService;

    @Test
    void contextLoads() {

    }

    @Test
    public void testCreate() {
        Habit habit = new Habit("Прогулка", 0, 5000,
                "Идентичность", 5000, "контракт", "Среда");
        Habit createdHabit = habitService.createOrUpdateHabit(habit);
        assertNotNull(createdHabit);
        assertNotNull(createdHabit.getName());
        assertEquals("Прогулка", createdHabit.getName());

    }

    @Test
    public void testRead() {
        Habit habit = new Habit("Прогулка", 0, 5000,
                "Идентичность", 5000, "контракт", "Среда");
        Habit habit2 = new Habit("Чтение", 0, 120,
                "Идентичность", 5000, "контракт", "Среда");

        habitService.createOrUpdateHabit(habit);
        habitService.createOrUpdateHabit(habit2);

        List<Habit> allHabits = habitService.getAllHabits();

        assertFalse(allHabits.isEmpty());
        assertEquals(2, allHabits.size());

    }

    @Test
    public void testUpdate() {
        Habit habit = new Habit("Прогулка", 0, 5000,
                "Идентичность", 5000, "контракт", "Среда");
        Habit createHabit = habitService.createOrUpdateHabit(habit);

        createHabit.setName("Тренировка");
        createHabit.setContract("новый контракт");

        Habit updateHabit = habitService.createOrUpdateHabit(createHabit);

        assertEquals("Тренировка", updateHabit.getName());
        assertEquals("новый контракт", updateHabit.getContract());
    }

    @Test
    public void testDelete() {
        Habit habit = new Habit("Прогулка", 0, 5000,
                "Идентичность", 5000, "контракт", "Среда");
        Habit createHabit = habitService.createOrUpdateHabit(habit);

        habitService.deleteHabitById(createHabit.getId());

        Optional<Habit> habitById = habitService.getHabitById(createHabit.getId());
        assertFalse(habitById.isPresent());

    }
}
