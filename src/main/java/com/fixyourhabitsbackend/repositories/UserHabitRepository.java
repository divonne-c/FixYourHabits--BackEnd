package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.UserHabit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHabitRepository extends JpaRepository<UserHabit, Long> {
}
