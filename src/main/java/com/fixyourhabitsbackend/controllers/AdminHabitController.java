package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.AdminHabitDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.services.AdminHabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("adminhabits")
public class AdminHabitController {

    @Autowired
    private AdminHabitService adminHabitService;

    @GetMapping
    public ResponseEntity<List<AdminHabitDto>> getAllAdminHabits() {
        return ResponseEntity.ok().body(adminHabitService.getAllAdminHabits());
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminHabitDto> getAdminHabitById(@PathVariable Long id) {
        AdminHabitDto getAdminHabit = adminHabitService.getAdminHabitById(id);
        return ResponseEntity.ok().body(getAdminHabit);
    }

    @PostMapping
    public ResponseEntity<AdminHabitDto> createAdminHabit(@RequestBody AdminHabit adminHabit) {
        final AdminHabitDto adminHabitDto = adminHabitService.createAdminHabit(adminHabit);
        final URI location = URI.create("/adminhabits/" + adminHabit.getId());
        return ResponseEntity.created(location).body(adminHabitDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<AdminHabitDto> updateAdminHabit(@PathVariable Long id, @RequestBody AdminHabit adminHabit ) {
        AdminHabitDto adminHabitDto = adminHabitService.updateAdminHabit(id, adminHabit);
        return ResponseEntity.ok().body(adminHabitDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AdminHabit> deleteAdminHabit(@PathVariable Long id) {
        adminHabitService.deleteAdminHabit(id);
        return ResponseEntity.noContent().build();
    }
}
