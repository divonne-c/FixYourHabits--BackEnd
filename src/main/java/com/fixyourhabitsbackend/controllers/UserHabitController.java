package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserHabitDto;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.services.UserHabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("userhabits")
public class UserHabitController {

    @Autowired
    private UserHabitService userHabitService;

    @GetMapping
    public ResponseEntity<List<UserHabitDto>> getAllUserHabits() {
        return ResponseEntity.ok().body(userHabitService.getAllUserHabits());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserHabitDto> getUserHabitById(@PathVariable Long id) {
        UserHabitDto getUserHabit = userHabitService.getUserHabitById(id);
        return ResponseEntity.ok().body(getUserHabit);
    }

    @PostMapping
    public ResponseEntity<UserHabitDto> createUserHabit(@RequestBody UserHabit userHabit) {
        final UserHabitDto userHabitDto = userHabitService.createUserHabit(userHabit);
        final URI location = URI.create("/userhabits/" + userHabit.getId());
        return ResponseEntity.created(location).body(userHabitDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserHabitDto> updateUserHabit(@PathVariable Long id, @RequestBody UserHabit userHabit ) {
        UserHabitDto userHabitDto = userHabitService.updateUserHabit(id, userHabit);
        return ResponseEntity.ok().body(userHabitDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserHabit> deleteUserHabit(@PathVariable Long id) {
        userHabitService.deleteUserHabit(id);
        return ResponseEntity.noContent().build();
    }

}
