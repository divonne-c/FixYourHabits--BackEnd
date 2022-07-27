package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminHabitDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.repositories.AdminHabitRepository;
import com.fixyourhabitsbackend.repositories.AdminProfileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={AdminHabitService.class})
class AdminHabitServiceTest {

    @Autowired
    AdminHabitService adminHabitService;

    @MockBean
    private AdminHabitRepository adminHabitRepository;

    @MockBean
    private AdminProfileRepository adminProfileRepository;

    @Test
    void getAllAdminHabits() {
        adminHabitService.getAllAdminHabits();

        Mockito.verify(adminHabitRepository).findAll();
    }

    @Test
    void getAdminHabitById() {
        AdminHabit adminHabit = new AdminHabit();
        AdminHabitDto adminHabitDto = new AdminHabitDto();
        adminHabit.setId(1L);
        adminHabitDto.setId(adminHabit.getId());

        Mockito.when(adminHabitRepository.findById(adminHabit.getId())).thenReturn(Optional.of(adminHabit));

        AdminHabitDto result = adminHabitService.getAdminHabitById(adminHabitDto.getId());
        assertEquals(1, result.getId());
    }


    @Test
    void deleteAdminHabit() {
        AdminHabit adminHabit = new AdminHabit();
        adminHabit.setId(1L);

        Mockito.when(adminHabitRepository.findById(adminHabit.getId())).thenReturn(Optional.of(adminHabit));

        adminHabitService.deleteAdminHabit(adminHabit.getId());

        Mockito.verify(adminHabitRepository).deleteById(adminHabit.getId());
    }
}