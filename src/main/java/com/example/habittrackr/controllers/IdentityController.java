package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.IdentityDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.IdentityServiceImpl;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.storage.identity.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/identities")
public class IdentityController {
    private final IdentityServiceImpl identityService;
    private final HabitServiceImpl habitService;
    private final Mapper mapper;

    @Autowired
    public IdentityController(IdentityServiceImpl identityService, HabitServiceImpl habitService, Mapper mapper) {
        this.identityService = identityService;
        this.habitService = habitService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<IdentityDTO> createIdentity(@RequestBody IdentityDTO identityDTO){
        Identity identity = mapper.toIdentity(identityDTO);
        IdentityDTO orUpdateIdentity = identityService.createOrUpdateIdentity(identity);
        return ResponseEntity.ok(orUpdateIdentity);
    }

    @PostMapping("/{habitId}/{identityId}")
    public ResponseEntity<HabitDTO> addIdentityToHabit(@PathVariable Long habitId, @PathVariable Long identityId){
        Habit habit = habitService.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Identity identity = identityService.getIdentityById(identityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        habit.setIdentity(identity);
        HabitDTO updateHabit = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(updateHabit);
    }

    @GetMapping("/{identityId}")
    public ResponseEntity<IdentityDTO> getIdentityById (@PathVariable Long identityId){
        return identityService.getIdentityById(identityId)
                .map(identity -> ResponseEntity.ok().body(mapper.toIdentityDTO(identity)))
                .orElse(ResponseEntity.notFound().build());
    }


}
