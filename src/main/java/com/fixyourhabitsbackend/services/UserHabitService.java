package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserHabitDto;
import com.fixyourhabitsbackend.exceptions.RecordNotFoundException;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.repositories.UserHabitRepository;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserHabitService {

    @Autowired
    private UserHabitRepository userHabitRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserHabitDto> getAllUserHabits() {
        List<UserHabitDto> userHabitDtos = new ArrayList<>();
        List<UserHabit> userHabits = userHabitRepository.findAll();

        for (UserHabit habit : userHabits) {
            userHabitDtos.add(fromUserHabit(habit));
        }

        return userHabitDtos;
    }

    public UserHabitDto getUserHabitById(Long id) {
        Optional<UserHabit> userHabit = userHabitRepository.findById(id);

        if (userHabit.isPresent()) {
            UserHabit userHabit1 = userHabit.get();
            return fromUserHabit(userHabit1);
        } else {
            throw new RecordNotFoundException("UserHabit is not found");
        }
    }

    public UserHabitDto createUserHabit(UserHabit userHabit) {
        final UserHabit savedUserHabit = userHabitRepository.save(userHabit);
        final UserHabitDto userHabitDto = fromUserHabit(savedUserHabit);
        return userHabitDto;
    }

    public void deleteUserHabit(Long userHabitId) {
        Optional<UserHabit> exist = userHabitRepository.findById(userHabitId);

        if (exist.isEmpty()) {
            throw new IllegalStateException("userHabit with id " + userHabitId + " does not exist");
        }
        userHabitRepository.deleteById(userHabitId);
    }

    public UserHabitDto updateUserHabit(Long id, UserHabit userHabit) {
        Optional<UserHabit> userHabitFound = userHabitRepository.findById(id);

        if (userHabitFound.isEmpty()) {
            throw new RecordNotFoundException("UserHabit not found");
        } else {
            UserHabit newUserHabit = userHabitFound.get();
            newUserHabit.setName(userHabit.getName());
            newUserHabit.setType(userHabit.getType());
            newUserHabit.setCompleted(userHabit.isCompleted());

            userHabitRepository.save(newUserHabit);
            return fromUserHabit(newUserHabit);
        }
    }

    public static UserHabitDto fromUserHabit(UserHabit userHabit){
        var dto = new UserHabitDto();
        dto.setId(userHabit.getId());
        dto.setName(userHabit.getName());
        dto.setType(userHabit.getType());
        dto.setCompleted(userHabit.isCompleted());

        return dto;
    }
}
