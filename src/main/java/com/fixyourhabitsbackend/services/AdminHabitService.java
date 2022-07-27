package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminHabitDto;
import com.fixyourhabitsbackend.exceptions.RecordNotFoundException;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.repositories.AdminHabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminHabitService {

    @Autowired
    private AdminHabitRepository adminHabitRepository;

    public List<AdminHabitDto> getAllAdminHabits() {
        List<AdminHabitDto> adminHabitDtos = new ArrayList<>();
        List<AdminHabit> adminHabits = adminHabitRepository.findAll();

        for (AdminHabit habit : adminHabits) {
            adminHabitDtos.add(fromAdminHabit(habit));
        }
        return adminHabitDtos;
    }

    public AdminHabitDto getAdminHabitById(Long id) {
        Optional<AdminHabit> adminHabit = adminHabitRepository.findById(id);
        if (adminHabit.isPresent()) {
            return fromAdminHabit(adminHabit.get());
        } else {
            throw new RecordNotFoundException("AdminHabit is not found");
        }
    }

    public AdminHabitDto createAdminHabit(AdminHabit adminHabit) {
        final AdminHabit savedAdminHabit = adminHabitRepository.save(adminHabit);
        final AdminHabitDto adminHabitDto = fromAdminHabit(savedAdminHabit);
        return adminHabitDto;
    }

    public void deleteAdminHabit(Long adminHabitId) {
        Optional<AdminHabit> exist = adminHabitRepository.findById(adminHabitId);

        if (!exist.isPresent()) {
            throw new IllegalStateException("AdminHabit with id " + adminHabitId + " does not exist");
        }
        adminHabitRepository.deleteById(adminHabitId);
    }

    public AdminHabitDto updateAdminHabit(Long id, AdminHabit adminHabit) {
        Optional<AdminHabit> adminHabitFound = adminHabitRepository.findById(id);

        if (!adminHabitFound.isPresent()) {
            throw new RecordNotFoundException("AdminHabit not found");
        } else {
            AdminHabit newAdminHabit = adminHabitFound.get();
            newAdminHabit.setName(adminHabit.getName());
            newAdminHabit.setType(adminHabit.getType());

            adminHabitRepository.save(newAdminHabit);
            return fromAdminHabit(newAdminHabit);
        }
    }

    public static AdminHabitDto fromAdminHabit(AdminHabit adminHabit){
        var dto = new AdminHabitDto();
        dto.setId(adminHabit.getId());
        dto.setName(adminHabit.getName());
        dto.setType(adminHabit.getType());
        dto.setDescription(adminHabit.getDescription());

        return dto;
    }
}
