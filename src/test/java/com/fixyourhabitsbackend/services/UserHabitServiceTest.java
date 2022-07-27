package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserHabitDto;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.repositories.UserHabitRepository;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={UserHabitService.class})
class UserHabitServiceTest {

    @Autowired
    UserHabitService userHabitService;

    @MockBean
    private UserHabitRepository userHabitRepository;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Test
    void getAllUserHabits() {
        userHabitService.getAllUserHabits();

        Mockito.verify(userHabitRepository).findAll();
    }

    @Test
    void getUserHabitById() {
        UserHabit userHabit = new UserHabit();
        UserHabitDto userHabitDto = new UserHabitDto();
        userHabit.setId(1L);
        userHabitDto.setId(userHabit.getId());

        Mockito.when(userHabitRepository.findById(userHabit.getId())).thenReturn(Optional.of(userHabit));

        UserHabitDto result = userHabitService.getUserHabitById(userHabitDto.getId());
        assertEquals(1, result.getId());
    }


    @Test
    void deleteUserHabit() {
        UserHabit userHabit = new UserHabit();
        userHabit.setId(1L);

        Mockito.when(userHabitRepository.findById(userHabit.getId())).thenReturn(Optional.of(userHabit));

        userHabitService.deleteUserHabit(userHabit.getId());

        Mockito.verify(userHabitRepository).deleteById(userHabit.getId());
    }
}